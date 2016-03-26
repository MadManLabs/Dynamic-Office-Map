package com.fsociety.domclient.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fsociety.domclient.R;
import com.fsociety.domclient.activity.RegisterDeskActivity_;
import com.fsociety.domclient.service.StorageWriterService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.home_fragment)
public class HomeFragment extends BaseFragment {
	@Bean
	protected StorageWriterService storageWriterService;

	@ViewById(R.id.usernameEditText)
	protected EditText usernameEditText;
	@ViewById(R.id.loginButton)
	protected Button loginButton;
	@ViewById(R.id.registerDeskButton)
	protected Button registerDeskButton;
	@ViewById(R.id.loggedAsTextView)
	protected TextView loggedAsTextView;
	@ViewById(R.id.loggedInUserRelativeLayout)
	protected RelativeLayout loggedInUserRelativeLayout;
	@ViewById(R.id.notLoggedInUserRelativeLayout)
	protected RelativeLayout notLoggedInUserRelativeLayout;

	@AfterViews
	protected void setupViews() {
		if (application.getSettings().getUsername() == null) {
			loggedInUserRelativeLayout.setVisibility(View.INVISIBLE);
			notLoggedInUserRelativeLayout.setVisibility(View.VISIBLE);
		} else {
			loggedInUserRelativeLayout.setVisibility(View.VISIBLE);
			notLoggedInUserRelativeLayout.setVisibility(View.INVISIBLE);
			loggedAsTextView.setText(String.format(getResources().getString(R.string.home_activity_logged_as_text), application.getSettings().getUsername()));
		}
	}

	@Click(R.id.loginButton)
	protected void onLoginButtonClick(View view) {
		// TODO: Check with a REST call if the username exists...
		application.getSettings().setUsername(usernameEditText.getText().toString());
		storageWriterService.saveObjectToFile(application.getSettings(), application.getConfiguration().getApplicationBinDirectory(), application.getConfiguration().getSettingsFilename());
		setupViews();
	}

	@Click(R.id.registerDeskButton)
	protected void onReadQRCodeButtonClick(View view) {
		Intent intent = new Intent(getActivity(), RegisterDeskActivity_.class);
		getActivity().startActivity(intent);
	}
}
