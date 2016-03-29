package com.fsociety.domclient.core;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import java.io.File;

@EBean(scope = Scope.Singleton)
public class Configuration {
	private String settingsFilename, applicationDirectory, applicationBinDirectory, applicationBinTempDirectory, serverIp, serverPort;

	protected Configuration(Context context) {
		// Endava
		serverIp = "192.168.168.107";
		// Wireless
		//serverIp = "192.168.173.1";
		//serverIp = "192.168.166.69";
		serverPort = "8080";
		settingsFilename = "settings.json";
		applicationDirectory = context.getFilesDir().getAbsolutePath();
		applicationBinDirectory = context.getFilesDir().getAbsolutePath() + File.separator + "bin";
		applicationBinTempDirectory = context.getFilesDir().getAbsolutePath() + File.separator + "bin" + File.separator + "temp";
	}

	public String getServerIp() {
		return serverIp;
	}

	public String getServerPort() {
		return serverPort;
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
