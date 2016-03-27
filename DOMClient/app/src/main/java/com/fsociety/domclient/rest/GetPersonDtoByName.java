package com.fsociety.domclient.rest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.fsociety.domclient.R;
import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.fragment.UserDetailsFragment;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class GetPersonDtoByName extends AsyncTask<Void, Void, PersonDTO> {
	private static final String TAG = "GetPersonDtoByName";

	private UserDetailsFragment userDetailsFragment;
	private ProgressDialog progressDialog;

	public GetPersonDtoByName(UserDetailsFragment userDetailsFragment) {
		this.userDetailsFragment = userDetailsFragment;
	}

	@Override
	protected void onPreExecute() {
		if (userDetailsFragment != null) {
			progressDialog = new ProgressDialog(userDetailsFragment.getActivity());
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}
	}

	@Override
	protected PersonDTO doInBackground(Void... params) {
		try {
			final String url = "http://" + userDetailsFragment.application.getConfiguration().getServerIp() + ":" + userDetailsFragment.application.getConfiguration().getServerPort() + "/dynamaps/api/v1/office/person/" + userDetailsFragment.getUsername();
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			PersonDTO personDTO = restTemplate.getForObject(url, PersonDTO.class);
			return personDTO;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(PersonDTO personDTO) {
		super.onPostExecute(personDTO);
		if (userDetailsFragment.getActivity() != null) {
			progressDialog.dismiss();
		}
		userDetailsFragment.getNameTextView().setText(String.format(userDetailsFragment.getResources().getString(R.string.user_details_activity_name_text), personDTO.getName()));
		userDetailsFragment.getEmailTextView().setText(String.format(userDetailsFragment.getResources().getString(R.string.user_details_activity_email_text), personDTO.getEmail()!=null ? personDTO.getEmail() : ""));
		userDetailsFragment.getFloorTextView().setText(userDetailsFragment.getFloorTextView().getText()+ " " + ((personDTO.getZone()!=null && personDTO.getZone().getFloor()!=null &&  personDTO.getZone().getFloor().getName()!= null) ? personDTO.getZone().getFloor().getName() : ""));
		userDetailsFragment.getMapWebView().loadUrl("http://" + userDetailsFragment.application.getConfiguration().getServerIp() + ":" + userDetailsFragment.application.getConfiguration().getServerPort() + "/second/#/personMap/"+personDTO.getId());
	}
}
