package com.fsociety.domclient.core;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import java.io.File;

@EBean(scope = Scope.Singleton)
public class Configuration {
	private String settingsFilename, applicationDirectory, applicationBinDirectory, applicationBinTempDirectory;

	protected Configuration(Context context) {
		settingsFilename = "settings.json";
		applicationDirectory = context.getFilesDir().getAbsolutePath();
		applicationBinDirectory = context.getFilesDir().getAbsolutePath() + File.separator + "bin";
		applicationBinTempDirectory = context.getFilesDir().getAbsolutePath() + File.separator + "bin" + File.separator + "temp";
	}

	public String getSettingsFilename() {
		return settingsFilename;
	}

	public String getApplicationDirectory() {
		return applicationDirectory;
	}

	public String getApplicationBinDirectory() {
		return applicationBinDirectory;
	}

	public String getApplicationBinTempDirectory() {
		return applicationBinTempDirectory;
	}
}
