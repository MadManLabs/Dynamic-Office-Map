package com.fsociety.domclient.rest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.fsociety.domclient.activity.RegisterDeskActivity;
import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.fragment.RegisterDeskFragment;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class UpdatePersonDeskByName extends AsyncTask<Void, Void, Boolean> {
	private static final String TAG = "UpdatePersonDeskByName";

	private RegisterDeskFragment registerDeskFragment;
	private RegisterDeskActivity registerDeskActivity;
	private ProgressDialog progressDialog;
	private PersonDTO personDTO;

	public UpdatePersonDeskByName(RegisterDeskFragment registerDeskFragment, PersonDTO personDTO) {
		this.registerDeskFragment = registerDeskFragment;
		this.personDTO = personDTO;
	}

	public UpdatePersonDeskByName(RegisterDeskActivity registerDeskActivity, PersonDTO personDTO) {
		this.registerDeskActivity = registerDeskActivity;
		this.personDTO = personDTO;
	}

	@Override
	protected void onPreExecute() {
		if (registerDeskFragment != null || registerDeskActivity != null) {
			if (registerDeskFragment != null) {
				progressDialog = new ProgressDialog(registerDeskFragment.getActivity());
			}
			if (registerDeskActivity != null) {
				progressDialog = new ProgressDialog(registerDeskActivity);
			}
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			String url = "";
			if (registerDeskFragment != null) {
				url = "http://" + registerDeskFragment.application.getConfiguration().getServerIp() + ":" + registerDeskFragment.application.getConfiguration().getServerPort() + "/dynamaps/api/v1/office/person";
			}
			if (registerDeskActivity != null) {
				url = "http://" + registerDeskActivity.application.getConfiguration().getServerIp() + ":" + registerDeskActivity.application.getConfiguration().getServerPort() + "/dynamaps/api/v1/office/person";
			}
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			restTemplate.postForObject(url, personDTO, String.class);
			return true;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			return false;
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (registerDeskFragment != null || registerDeskActivity != null) {
			progressDialog.dismiss();
		}
		if (result) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(registerDeskFragment != null ? registerDeskFragment.getActivity() : registerDeskActivity);
			alertDialogBuilder.setTitle("Operation successful")
					.setMessage("Your new desk has been registered. Thank you!").setCancelable(false);
			alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
					if (registerDeskFragment != null) {
						registerDeskFragment.getActivity().finish();
					}
					if (registerDeskActivity != null) {
						registerDeskActivity.finish();
					}
				}
			});
			alertDialogBuilder.show();
		} else {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(registerDeskFragment != null ? registerDeskFragment.getActivity() : registerDeskActivity);
			alertDialogBuilder.setIconAttribute(android.R.attr.alertDialogIcon);
			alertDialogBuilder.setTitle("Operation error")
					.setMessage("The new desk could not be registered. Please try again later!").setCancelable(false);
			alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
			alertDialogBuilder.show();
		}
	}
}
