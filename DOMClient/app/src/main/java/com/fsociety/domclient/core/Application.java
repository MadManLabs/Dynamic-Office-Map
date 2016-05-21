package com.fsociety.domclient.core;

import android.util.Log;
import android.widget.Toast;

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

@EApplication
public class Application extends android.app.Application implements BootstrapNotifier {
	private static final String TAG = "Application";
	private static Application instance = null;
	private RegionBootstrap regionBootstrap;

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

	@Override
	public void didDetermineStateForRegion(int arg0, Region arg1) {
	}

	@Override
	public void didEnterRegion(Region region) {
		if (settings.getEnableBeaconUpdates()) {
			if (getSettings().getLoggedInPersonDTO() != null) {
				new UpdatePersonDeskById(this, getSettings().getLoggedInPersonDTO(), "BEACON", region.getId1().toString()).execute();
			} else {
				Toast.makeText(this, "User is not logged in!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void didExitRegion(Region arg0) {
		// Don't care
	}
}
