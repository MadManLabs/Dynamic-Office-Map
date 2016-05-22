package com.fsociety.domclient.rest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.fsociety.domclient.activity.RegisterDeskActivity;
import com.fsociety.domclient.core.Application;
import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.fragment.RegisterDeskFragment;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class UpdateAssetById extends AsyncTask<Void, Void, Boolean> {
	private static final String TAG = "UpdateAssetById";
	private static final String ZONE = "ZONE";
	private static final String ASSET = "ASSET";

	private RegisterDeskFragment registerDeskFragment;
	private RegisterDeskActivity registerDeskActivity;
	private Application application;
	private ProgressDialog progressDialog;
	private PersonDTO personDTO;
	private String codeType;
	private String code;

	public UpdateAssetById(RegisterDeskFragment registerDeskFragment, PersonDTO personDTO, String codeType, String code) {
		this.registerDeskFragment = registerDeskFragment;
		this.personDTO = personDTO;
		this.codeType = codeType;
		this.code = code;
	}

	public UpdateAssetById(RegisterDeskActivity registerDeskActivity, PersonDTO personDTO, String codeType, String code) {
		this.registerDeskActivity = registerDeskActivity;
		this.personDTO = personDTO;
		this.codeType = codeType;
		this.code = code;
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
				if (!codeType.equalsIgnoreCase(ZONE)) {
					url = "http://" + registerDeskFragment.application.getSettings().getServerIp() + ":" + registerDeskFragment.application.getSettings().getServerPort() + "/"+code+"/tenant/"+personDTO.getId();
				}
			}
			if (registerDeskActivity != null) {
				if (!codeType.equalsIgnoreCase(ZONE)) {
					url = "http://" + registerDeskActivity.application.getSettings().getServerIp() + ":" + registerDeskActivity.application.getSettings().getServerPort() + "/"+code+"/tenant/"+personDTO.getId();
				}
			}
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			restTemplate.put(url, personDTO, String.class);
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
			if (result) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(registerDeskFragment != null ? registerDeskFragment.getActivity() : registerDeskActivity);
				alertDialogBuilder.setTitle("Operation successful").setMessage("Your new position has been registered. Thank you!").setCancelable(false);
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
				alertDialogBuilder.setTitle("Operation error").setMessage("The new position could not be registered. Please try again later!").setCancelable(false);
				alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
				alertDialogBuilder.show();
			}
		}
	}
}
