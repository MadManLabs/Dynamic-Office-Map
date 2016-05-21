package com.fsociety.domclient.rest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
			final String url = "http://" + homeFragment.application.getSettings().getServerIp() + ":" + homeFragment.application.getSettings().getServerPort() + "/api/person";
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
			usernames.add(personDTO.getUsername());
		}
		homeFragment.personDTOs = personDTOs;

		Boolean userFound = false;
		if (personDTOs!=null && personDTOs.size()>0) {
			for (PersonDTO personDTO:personDTOs) {
				if (homeFragment.getUsernameEditText().getText().toString().equals(personDTO.getUsername())) {
					userFound = true;
					homeFragment.application.getSettings().setLoggedInPersonDTO(personDTO);
					homeFragment.getStorageWriterService().saveObjectToFile(homeFragment.application.getSettings(), homeFragment.application.getConfiguration().getApplicationBinDirectory(), homeFragment.application.getConfiguration().getSettingsFilename());
					homeFragment.setupViews();
				}
			}
		}
		if (!userFound) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(homeFragment.getActivity());
			alertDialogBuilder.setIconAttribute(android.R.attr.alertDialogIcon);
			alertDialogBuilder.setTitle("Login error")
					.setMessage("The user you have entered is not valid. Please try again!").setCancelable(false);
			alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
			alertDialogBuilder.show();
		}
	}
}
