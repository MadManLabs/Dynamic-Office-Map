package com.fsociety.domclient.rest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.fragment.HomeFragment;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetPersonDtosAtLogin extends AsyncTask<Void, Void, List<PersonDTO>> {
	private static final String TAG = "GetPersonDtosAtLogin";

	private HomeFragment homeFragment;
	private ProgressDialog progressDialog;

	public GetPersonDtosAtLogin(HomeFragment homeFragment) {
		this.homeFragment = homeFragment;
	}

	@Override
	protected void onPreExecute() {
		if (homeFragment != null) {
			progressDialog = new ProgressDialog(homeFragment.getActivity());
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}
	}

	@Override
	protected List<PersonDTO> doInBackground(Void... params) {
		try {
			final String url = "http://" + homeFragment.application.getConfiguration().getServerIp() + ":" + homeFragment.application.getConfiguration().getServerPort() + "/dynamaps/api/v1/office/person";
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
		if (homeFragment.getActivity() != null) {
			progressDialog.dismiss();
		}
		List<String> usernames = new ArrayList<>();
		for (PersonDTO personDTO : personDTOs) {
			usernames.add(personDTO.getName());
		}
		homeFragment.personDTOs = personDTOs;
	}
}
