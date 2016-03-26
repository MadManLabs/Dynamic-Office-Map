package com.fsociety.domclient.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.fsociety.domclient.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.user_details_fragment)
public class UserDetailsFragment extends BaseFragment {
	@AfterViews
	protected void setupViews() {
		Bundle extras = getActivity().getIntent().getExtras();
		if(extras !=null) {
			String username = extras.getString("username");
			Toast.makeText(getActivity(), username, Toast.LENGTH_LONG).show();
		}
	}
}
