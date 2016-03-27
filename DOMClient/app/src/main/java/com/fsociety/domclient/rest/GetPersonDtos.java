package com.fsociety.domclient.rest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.fragment.FindFriendFragment;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetPersonDtos extends AsyncTask<Void, Void, List<PersonDTO>> {
	private static final String TAG = "GetPersonDtos";

	private FindFriendFragment findFriendFragment;
	private ProgressDialog progressDialog;

	public GetPersonDtos(FindFriendFragment findFriendFragment) {
		this.findFriendFragment = findFriendFragment;
	}

	@Override
	protected void onPreExecute() {
		if (findFriendFragment != null) {
			progressDialog = new ProgressDialog(findFriendFragment.getActivity());
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}
	}

	@Override
	protected List<PersonDTO> doInBackground(Void... params) {
		try {
			final String url = "http://" + findFriendFragment.application.getConfiguration().getServerIp() + ":" + findFriendFragment.application.getConfiguration().getServerPort() + "/dynamaps/api/v1/office/person";
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			PersonDTO[] personDTOs = restTemplate.getForObject(url, PersonDTO[].class);
			return Arrays.asList(personDTOs);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(List<PersonDTO> personDTOs) {
		super.onPostExecute(personDTOs);
		if (findFriendFragment.getActivity() != null) {
			progressDialog.dismiss();
		}
		List<String> usernames = new ArrayList<>();
		for (PersonDTO personDTO : personDTOs) {
			usernames.add(personDTO.getName());
		}
		ArrayAdapter<String> searchUsernameInstantAutoCompleteTextViewAdapter = new ArrayAdapter<>(findFriendFragment.getActivity(), android.R.layout.simple_list_item_1, usernames);
		findFriendFragment.getSearchUsernameInstantAutoCompleteTextView().setAdapter(searchUsernameInstantAutoCompleteTextViewAdapter);
		//findFriendFragment.getSearchUsernameInstantAutoCompleteTextView().setText("");
	}
}
