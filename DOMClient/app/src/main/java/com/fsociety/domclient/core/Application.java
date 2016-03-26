package com.fsociety.domclient.core;

import android.util.Log;

import com.fsociety.domclient.service.StorageReaderService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

@EApplication
public class Application extends android.app.Application {
	private static final String TAG = "Application";
	private static Application instance = null;

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
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public Settings getSettings() {
		return settings;
	}
}
