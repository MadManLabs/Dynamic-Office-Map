package com.fsociety.domclient.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fsociety.domclient.R;
import com.fsociety.domclient.rest.GetProjectDtoById;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.project_details_fragment)
public class ProjectDetailsFragment extends BaseFragment {
	@ViewById(R.id.projectNameTextView)
	protected TextView projectNameTextView;
	@ViewById(R.id.descriptionTextView)
	protected TextView descriptionTextView;
	@ViewById(R.id.technologiesTextView)
	protected TextView technologiesTextView;
	@ViewById(R.id.logoImageView)
	protected ImageView logoImageView;

	public TextView getDescriptionTextView() {
		return descriptionTextView;
	}

	public void setDescriptionTextView(TextView descriptionTextView) {
		this.descriptionTextView = descriptionTextView;
	}

	public ImageView getLogoImageView() {
		return logoImageView;
	}

	public void setLogoImageView(ImageView logoImageView) {
		this.logoImageView = logoImageView;
	}

	public TextView getProjectNameTextView() {
		return projectNameTextView;
	}

	public void setProjectNameTextView(TextView projectNameTextView) {
		this.projectNameTextView = projectNameTextView;
	}

	public TextView getTechnologiesTextView() {
		return technologiesTextView;
	}

	public void setTechnologiesTextView(TextView technologiesTextView) {
		this.technologiesTextView = technologiesTextView;
	}

	@AfterViews
	protected void setupViews() {
		String code = null;
		Bundle extras = getActivity().getIntent().getExtras();
		if(extras !=null) {
			code = extras.getString("code");
		}
		if (code != null) {
			new GetProjectDtoById(this, code).execute();
		}
	}
}
