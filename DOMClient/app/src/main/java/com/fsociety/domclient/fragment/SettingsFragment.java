package com.fsociety.domclient.fragment;

import android.widget.CheckBox;
import android.widget.EditText;

import com.fsociety.domclient.R;
import com.fsociety.domclient.service.StorageWriterService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.settings_fragment)
public class SettingsFragment extends BaseFragment {
	@Bean
	protected StorageWriterService storageWriterService;

	@ViewById(R.id.enableBeaconUpdatesCheckBox)
	protected CheckBox enableBeaconUpdatesCheckBox;
	@ViewById(R.id.ipEditText)
	protected EditText ipEditText;
	@ViewById(R.id.portEditText)
	protected EditText portEditText;


	@AfterViews
	protected void setupViews() {
		enableBeaconUpdatesCheckBox.setChecked(application.getSettings().getEnableBeaconUpdates());
		ipEditText.setText(application.getSettings().getServerIp());
		portEditText.setText(application.getSettings().getServerPort());
	}

	@Override
	public void onPause() {
		super.onPause();
		application.getSettings().setEnableBeaconUpdates(enableBeaconUpdatesCheckBox.isChecked());
		application.setupBeaconManager();
		if (!application.getSettings().getEnableBeaconUpdates()) {
			application.getRegionBootstrap().disable();
		}
		application.getSettings().setServerIp(ipEditText.getText().toString());
		application.getSettings().setServerPort(portEditText.getText().toString());
	}

	@Override
	public void onStop() {
		super.onStop();
		storageWriterService.saveObjectToFile(application.getSettings(), application.getConfiguration().getApplicationBinDirectory(), application.getConfiguration().getSettingsFilename());
	}
}
