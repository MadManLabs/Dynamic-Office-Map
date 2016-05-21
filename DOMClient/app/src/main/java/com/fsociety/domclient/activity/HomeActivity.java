package com.fsociety.domclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fsociety.domclient.R;
import com.fsociety.domclient.service.StorageWriterService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.home_activity)
public class HomeActivity extends BaseActivity {
	private Menu homeMenu;

	@Bean
	protected StorageWriterService storageWriterService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		displayHomeAsUpEnabled = false;
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		homeMenu = menu;
		getMenuInflater().inflate(R.menu.home_menu, menu);
		resetMenu();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
			case R.id.settings_action:
				intent = new Intent(this, SettingsActivity_.class);
				startActivity(intent);
				return true;
			case R.id.logout_action:
				application.getSettings().setLoggedInPersonDTO(null);
				storageWriterService.saveObjectToFile(application.getSettings(), application.getConfiguration().getApplicationBinDirectory(), application.getConfiguration().getSettingsFilename());
				recreate();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void resetMenu() {
		if (application.getSettings().getLoggedInPersonDTO() == null) {
			homeMenu.findItem(R.id.logout_action).setVisible(false);
		} else {
			homeMenu.findItem(R.id.logout_action).setVisible(true);
		}
	}
}
