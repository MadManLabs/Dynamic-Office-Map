package com.fsociety.domclient.core;

import android.content.Intent;
import android.util.Log;

import com.fsociety.domclient.activity.HomeActivity_;
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

		BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
		beaconManager.setBackgroundBetweenScanPeriod(5000);
		beaconManager.setForegroundBetweenScanPeriod(5000);
		beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
		Region region = new Region("com.fsociety.domclient", Identifier.parse("ADA50693-A4E2-4FB1-AFCF-C6EB07647825"), null, null);
		regionBootstrap = new RegionBootstrap(this, region);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public Settings getSettings() {
		return settings;
	}

	@Override
	public void didDetermineStateForRegion(int arg0, Region arg1) {
		// Don't care
	}

	@Override
	public void didEnterRegion(Region region) {
		Log.d(TAG, "Got a didEnterRegion call");
		// This call to disable will make it so the activity below only gets launched the first time a beacon is seen (until the next time the app is launched)
		// if you want the Activity to launch every single time beacons come into view, remove this call.
		//regionBootstrap.disable();
		Intent intent = new Intent(this, HomeActivity_.class);
		// IMPORTANT: in the AndroidManifest.xml definition of this activity, you must set android:launchMode="singleInstance" or you will get two instances
		// created when a user launches the activity manually and it gets launched from here.
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startActivity(intent);
	}

	@Override
	public void didExitRegion(Region arg0) {
		// Don't care
	}
}
