package com.fsociety.domclient.fragment;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.fsociety.domclient.R;
import com.fsociety.domclient.rest.GetPersonDtoByName;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.user_details_fragment)
public class UserDetailsFragment extends BaseFragment {
	@ViewById(R.id.nameTextView)
	protected TextView nameTextView;
	@ViewById(R.id.emailTextView)
	protected TextView emailTextView;
	@ViewById(R.id.permanentFloorZoneTextView)
	protected TextView permanentFloorZoneTextView;
	@ViewById(R.id.temporaryFloorZoneTextView)
	protected TextView temporaryFloorZoneTextView;
	@ViewById(R.id.mapWebView)
	protected WebView mapWebView;

	private String username;

	public TextView getTemporaryFloorZoneTextView() {
		return temporaryFloorZoneTextView;
	}

	public TextView getPermanentFloorZoneTextView() {
		return permanentFloorZoneTextView;
	}

	public TextView getEmailTextView() {
		return emailTextView;
	}

	public WebView getMapWebView() {
		return mapWebView;
	}

	public TextView getNameTextView() {
		return nameTextView;
	}

	public String getUsername() {
		return username;
	}

	@AfterViews
	protected void setupViews() {
		Bundle extras = getActivity().getIntent().getExtras();
		if(extras !=null) {
			username = extras.getString("username");
			//Toast.makeText(getActivity(), username, Toast.LENGTH_LONG).show();
		}
		mapWebView.getSettings().setJavaScriptEnabled(true);
		mapWebView.getSettings().setBuiltInZoomControls(true);
		mapWebView.setVerticalScrollBarEnabled(true);
		mapWebView.setHorizontalScrollBarEnabled(true);
		mapWebView.getSettings().setLoadWithOverviewMode(true);
		mapWebView.getSettings().setUseWideViewPort(true);
		mapWebView.setScrollbarFadingEnabled(false);
		nameTextView.setText(String.format(getResources().getString(R.string.user_details_activity_name_text), ""));
		emailTextView.setText(String.format(getResources().getString(R.string.user_details_activity_email_text), ""));
		new GetPersonDtoByName(this).execute();
	}
}
