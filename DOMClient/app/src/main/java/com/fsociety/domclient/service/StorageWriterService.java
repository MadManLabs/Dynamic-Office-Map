package com.fsociety.domclient.service;

import android.util.Log;

import com.fsociety.domclient.core.Application;
import com.google.gson.Gson;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@EBean(scope = Scope.Singleton)
public class StorageWriterService {
	private static final String TAG = "StorageWriterService";

	public <T> void saveObjectToFile(T genericObject, String path, String filename) {
		// Application instance mustn't be set in constructor because it is called before Application is created (since it is a @Bean of Application)!
		Application application = Application.getInstance();

		BufferedWriter output = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				if (!file.mkdirs()) {
					Log.e(TAG, "Cannot create the folders specified in the path!");
				}
			}
			file = new File(path, filename);
			output = new BufferedWriter(new FileWriter(file));
			output.write(new Gson().toJson(genericObject));
		} catch (IOException e) {
			Log.e(TAG, "Cannot create output BufferedWriter!");
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "Cannot close output BufferedWriter!");
				e.printStackTrace();
			}
		}
	}
}
