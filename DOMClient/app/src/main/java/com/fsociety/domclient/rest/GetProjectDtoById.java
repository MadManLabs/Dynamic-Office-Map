package com.fsociety.domclient.rest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.fsociety.domclient.dto.ProjectDTO;
import com.fsociety.domclient.fragment.ProjectDetailsFragment;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GetProjectDtoById extends AsyncTask<Void, Void, ProjectDTO> {
	private static final String TAG = "GetProjectDtoById";

	private ProjectDetailsFragment projectDetailsFragment;
	private ProgressDialog progressDialog;
	private String code;
	private Bitmap bmp;

	public GetProjectDtoById(ProjectDetailsFragment projectDetailsFragment, String code) {
		this.projectDetailsFragment = projectDetailsFragment;
		this.code = code;
	}

	@Override
	protected void onPreExecute() {
		if (projectDetailsFragment != null) {
			progressDialog = new ProgressDialog(projectDetailsFragment.getActivity());
			progressDialog.setMessage("Loading...");
			progressDialog.show();
		}
	}

	@Override
	protected ProjectDTO doInBackground(Void... params) {
		try {
			final String url = "http://" + projectDetailsFragment.application.getSettings().getServerIp() + ":" + projectDetailsFragment.application.getSettings().getServerPort() + "/api/project/beacon/" + code;
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			ProjectDTO[] personDTOs = restTemplate.getForObject(url, ProjectDTO[].class);

			URL imageUrl = null;
			try {
				imageUrl = new URL(personDTOs[0].getLogo());
				bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return personDTOs[0];
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(ProjectDTO projectDTO) {
		super.onPostExecute(projectDTO);
		if (projectDTO != null) {
			if (projectDetailsFragment.getActivity() != null) {
				progressDialog.dismiss();
			}
			projectDetailsFragment.getLogoImageView().setImageBitmap(bmp);
			projectDetailsFragment.getProjectNameTextView().setText(Html.fromHtml(projectDTO.getName()));
			projectDetailsFragment.getDescriptionTextView().setText(Html.fromHtml(projectDTO.getDescription()));
			projectDetailsFragment.getTechnologiesTextView().setText(Html.fromHtml(projectDTO.getTechnologies()));
		} else {
			Toast.makeText(projectDetailsFragment.getActivity(), "Couldn't retrieve project data!", Toast.LENGTH_LONG).show();
		}
	}
}
