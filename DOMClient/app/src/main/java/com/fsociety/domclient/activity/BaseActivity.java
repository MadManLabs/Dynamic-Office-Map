package com.fsociety.domclient.activity;

import android.app.Activity;
import android.os.Bundle;

import com.fsociety.domclient.core.Application;

/**
 * Created by atundrea on 3/26/2016.
 */
public class BaseActivity extends Activity {
	protected Application application = Application.getInstance();

	Boolean displayHomeAsUpEnabled = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
	}

	private void setupActionBar() {
		if (getActionBar() != null) {
			getActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
		}
	}
}
