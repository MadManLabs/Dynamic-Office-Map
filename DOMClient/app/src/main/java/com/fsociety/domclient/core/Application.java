package com.fsociety.domclient.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.fsociety.domclient.activity.ProjectDetailsActivity_;
import com.fsociety.domclient.rest.UpdatePersonDeskById;
import com.fsociety.domclient.service.StorageReaderService;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@EApplication
public class Application extends android.app.Application implements BootstrapNotifier {
	private static final String TAG = "Application";
	private static Application instance = null;
	private RegionBootstrap regionBootstrap;

	WifiManager wifiManager;
	WifiReceiver receiverWifi;
	List<ScanResult> wifiList;
	List<String> listOfProvider =new ArrayList<>();

	@Bean
	protected Configuration configuration;
	@Bean
	protected StorageReaderService storageReaderService;

	private Settings settings;

	public static Application getInstance() {
		if (instance == null) {
			Log.e(TAG, "Instance of Application shouldn't be null!");
			throw new NullPointerException("Instance of Application shouldn't be null!");
		}
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		settings = storageReaderService.readObjectFromFile(Settings.class, configuration.getApplicationBinDirectory(), configuration.getSettingsFilename());

		setupBeaconManager();
		if (!settings.getEnableBeaconUpdates()) {
			regionBootstrap.disable();
		}
		wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
		scaning();
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public Settings getSettings() {
		return settings;
	}

	public RegionBootstrap getRegionBootstrap() {
		return regionBootstrap;
	}

	public void setupBeaconManager() {
		BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
		beaconManager.setBackgroundScanPeriod(1100);
		beaconManager.setBackgroundBetweenScanPeriod(20000);
		beaconManager.setForegroundBetweenScanPeriod(1100);
		beaconManager.setForegroundScanPeriod(20000);
		beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
		Region region = new Region("com.fsociety.domclient", Identifier.parse("FDA50693-A4E2-4FB1-AFCF-C6EB07647825"), null, null);
		regionBootstrap = new RegionBootstrap(this, region);
	}

	private void scaning() {
		// wifi scaned value broadcast receiver
		receiverWifi = new WifiReceiver();
		// Register broadcast receiver
		// Broacast receiver will automatically call when number of wifi
		// connections changed
		registerReceiver(receiverWifi, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		wifiManager.startScan();

	}

	@Override
	public void didDetermineStateForRegion(int arg0, Region arg1) {
	}

	@Override
	public void didEnterRegion(Region region) {
		if (settings.getEnableBeaconUpdates()) {
			if (getSettings().getLoggedInPersonDTO() != null) {
				new UpdatePersonDeskById(this, getSettings().getLoggedInPersonDTO(), "BEACON", region.getId1().toString()).execute();
			} else {
				Intent intent = new Intent(this, ProjectDetailsActivity_.class);
				intent.putExtra("code", region.getId1().toString());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				this.startActivity(intent);
			}
		}
	}

	@Override
	public void didExitRegion(Region arg0) {
		// Don't care
	}

	class WifiReceiver extends BroadcastReceiver {

		// This method call when number of wifi connections changed
		public void onReceive(Context c, Intent intent) {
			wifiList = wifiManager.getScanResults();

			/* sorting of wifi provider based on level */
			Collections.sort(wifiList, new Comparator<ScanResult>() {
				@Override
				public int compare(ScanResult lhs, ScanResult rhs) {
					return (lhs.level > rhs.level ? -1
							: (lhs.level == rhs.level ? 0 : 1));
				}
			});
			listOfProvider.clear();
			String providerName;
			for (int i = 0; i < wifiList.size(); i++) {
				/* to get SSID and BSSID of wifi provider*/
				providerName = wifiList.get(i).BSSID;
				if ("90:f6:52:5a:1b:2c".equalsIgnoreCase(providerName)) {

					listOfProvider.add(String.valueOf(wifiList.get(i).level));
					if (wifiList.get(i).level > -47) {
						if (settings.isEnableWifiUpdates()) {
							new UpdatePersonDeskById(Application.getInstance(), getSettings().getLoggedInPersonDTO(), "BEACON", providerName).execute();
						}
					}
				}
			}
			Log.e("WifiReceiver", listOfProvider.toString());
		}
	}

}
