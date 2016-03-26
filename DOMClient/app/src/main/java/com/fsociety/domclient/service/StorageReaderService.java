package com.fsociety.domclient.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@EBean(scope = Scope.Singleton)
public class StorageReaderService {
	private static final String TAG = "StorageReaderService";

	public <T> List<T> readObjectListFromFile(Class<T> objectClass, String path, String filename) {
		List<Object> unknownContentList;
		List<T> genericObjectList = new ArrayList<>();
		BufferedReader input = null;
		try {
			File file = new File(path, filename);
			input = new BufferedReader(new FileReader(file));
			Type listType = new TypeToken<List<Object>>() {
			}.getType();
			unknownContentList = new Gson().fromJson(input.readLine(), listType);
			if (unknownContentList != null) {
				for (Object object : unknownContentList) {
					String itemJson = new Gson().toJson(object);
					genericObjectList.add(new Gson().fromJson(itemJson, objectClass));
				}
			} else {
				Log.e(TAG, "UnknownContentList is null!");
			}
		} catch (IOException e) {
			Log.e(TAG, "Cannot read input BufferedReader!");
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "Input is not in JSON form or it may be encrypted (or decrypted with the wrong password)!");
			e.printStackTrace();
			genericObjectList = null;
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "Cannot close input BufferedReader!");
				e.printStackTrace();
			}
		}
		return genericObjectList;
	}

	public <T> T readObjectFromFile(Class<T> objectClass, String path, String filename/* , T... objectInstantiationParameters */) {
		T genericObject = null;
		BufferedReader input = null;
		try {
			File file = new File(path, filename);
			if (file.exists()) {
				input = new BufferedReader(new FileReader(file));
				genericObject = new Gson().fromJson(input.readLine(), objectClass);
			} else {
				// As per Gson User Guide: "Well-behaved classes that are meant for serialization and deserialization should have a no-argument constructor"
				// For details visit: https://sites.google.com/site/gson/gson-user-guide
		/* @formatter:off */
		/*List<Class<?>> objectInstantiationClassParameters = new ArrayList<Class<?>>();
		for (T objectInstantiationClassParameter : objectInstantiationParameters) {
			objectInstantiationClassParameters.add(objectInstantiationClassParameter.getClass());
		}
		genericObject = objectClass.getConstructor(objectInstantiationClassParameters.toArray(new Class<?>[objectInstantiationClassParameters.size()])).newInstance(objectInstantiationParameters);*/
		/* @formatter:on */
				genericObject = objectClass.getConstructor().newInstance();
			}
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "Input is not in JSON form or it may be encrypted (or decrypted with the wrong password)!");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, "Cannot read input BufferedReader!");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Log.e(TAG, "NoSuchMethodException when invoking constructor for the given class!");
			e.printStackTrace();
		} catch (InstantiationException e) {
			Log.e(TAG, "InstantiationException when invoking constructor for the given class!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Log.e(TAG, "IllegalAccessException when invoking constructor for the given class!");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			Log.e(TAG, "IllegalArgumentException when invoking constructor for the given class!");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			Log.e(TAG, "InvocationTargetException when invoking constructor for the given class!");
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "Cannot close input BufferedReader!");
				e.printStackTrace();
			}
		}
		return genericObject;
	}
}
