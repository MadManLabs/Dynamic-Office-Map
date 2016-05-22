package com.fsociety.domclient.fragment;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fsociety.domclient.R;
import com.fsociety.domclient.activity.FindFriendActivity_;
import com.fsociety.domclient.activity.RegisterDeskActivity_;
import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.rest.GetPersonDtosAtLogin;
import com.fsociety.domclient.service.StorageWriterService;
import com.fsociety.domclient.ui.InstantAutoCompleteTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.home_fragment)
public class HomeFragment extends BaseFragment {
	@Bean
	protected StorageWriterService storageWriterService;

	@ViewById(R.id.usernameEditText)
	protected EditText usernameEditText;
	@ViewById(R.id.usernameToAdministerInstantAutoCompleteTextView)
	protected InstantAutoCompleteTextView usernameToAdministerInstantAutoCompleteTextView;
	@ViewById(R.id.loginButton)
	protected Button loginButton;
	@ViewById(R.id.registerDeskButton)
	protected Button registerDeskButton;
	@ViewById(R.id.findFriendButton)
	protected Button findFriendButton;
	@ViewById(R.id.loggedAsTextView)
	protected TextView loggedAsTextView;
	@ViewById(R.id.loggedInUserRelativeLayout)
	protected RelativeLayout loggedInUserRelativeLayout;
	@ViewById(R.id.notLoggedInUserRelativeLayout)
	protected RelativeLayout notLoggedInUserRelativeLayout;
	@ViewById(R.id.administerRelativeLayout)
	protected RelativeLayout administerRelativeLayout;

	public StorageWriterService getStorageWriterService() {
		return storageWriterService;
	}

	public EditText getUsernameEditText() {
		return usernameEditText;
	}

	public List<PersonDTO> personDTOs;
	public List<String> usernames = new ArrayList<>();

	@AfterViews
	public void setupViews() {
		if (application.getSettings().getLoggedInPersonDTO() == null) {
			loggedInUserRelativeLayout.setVisibility(View.INVISIBLE);
			notLoggedInUserRelativeLayout.setVisibility(View.VISIBLE);
		} else {
			loggedInUserRelativeLayout.setVisibility(View.VISIBLE);
			notLoggedInUserRelativeLayout.setVisibility(View.INVISIBLE);
			loggedAsTextView.setText(Html.fromHtml(String.format(getResources().getString(R.string.home_activity_logged_as_text), application.getSettings().getLoggedInPersonDTO().getName())));
			if (application.getSettings().getLoggedInPersonDTO().getRole().equals("PARTNER")) {
				administerRelativeLayout.setVisibility(View.VISIBLE);
				findFriendButton.setVisibility(View.GONE);

				if (personDTOs!=null) {
					for (PersonDTO personDTO : personDTOs) {
						usernames.add(personDTO.getUsername());
					}
					ArrayAdapter<String> searchUsernameInstantAutoCompleteTextViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, usernames);
					usernameToAdministerInstantAutoCompleteTextView.setAdapter(searchUsernameInstantAutoCompleteTextViewAdapter);
				}
			}
		}
	}

	@Click(R.id.loginButton)
	protected void onLoginButtonClick(View view) {
		new GetPersonDtosAtLogin(this).execute();
	}

	@Click(R.id.registerDeskButton)
	protected void onReadQRCodeButtonClick(View view) {
		if (personDTOs != null) {
			for (PersonDTO personDTO : personDTOs) {
				if (usernameToAdministerInstantAutoCompleteTextView.getText().toString().equals(personDTO.getUsername())) {
					application.getSettings().setAdministerPersonDTO(personDTO);
				}
			}
			Intent intent = new Intent(getActivity(), RegisterDeskActivity_.class);
			getActivity().startActivity(intent);
		} else {
			Toast.makeText(getActivity(), "Please relogin to continue!", Toast.LENGTH_LONG).show();
		}
	}

	@Click(R.id.findFriendButton)
	protected void onFindFriendButtonClick(View view) {
		Intent intent = new Intent(getActivity(), FindFriendActivity_.class);
		getActivity().startActivity(intent);
	}
}
