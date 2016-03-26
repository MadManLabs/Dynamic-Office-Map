package com.fsociety.domclient.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.fsociety.domclient.R;
import com.fsociety.domclient.activity.UserDetailsActivity_;
import com.fsociety.domclient.ui.InstantAutoCompleteTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.find_friend_fragment)
public class FindFriendFragment extends BaseFragment {
	@ViewById(R.id.searchUsernameInstantAutoCompleteTextView)
	protected InstantAutoCompleteTextView searchUsernameInstantAutoCompleteTextView;
	@ViewById(R.id.findFriendButton)
	protected Button findFriendButton;

	@AfterViews
	protected void setupViews() {
		// TODO: REST call to retrieve all the available usernames

		List<String> availableUsernames = new ArrayList<String>();
		availableUsernames.add("adriantc1");
		availableUsernames.add("adriantc2");
		availableUsernames.add("adriantc3");
		availableUsernames.add("fstancu1");
		availableUsernames.add("fstancu2");
		availableUsernames.add("fstancu3");
		availableUsernames.add("caldea1");
		availableUsernames.add("caldea2");
		availableUsernames.add("caldea3");
		ArrayAdapter<String> searchUsernameInstantAutoCompleteTextViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, availableUsernames);
		searchUsernameInstantAutoCompleteTextView.setAdapter(searchUsernameInstantAutoCompleteTextViewAdapter);
	}

	@Click(R.id.findFriendButton)
	protected void onFindFriendButtonClick(View view) {
		Intent intent = new Intent(getActivity(), UserDetailsActivity_.class);
		intent.putExtra("username", searchUsernameInstantAutoCompleteTextView.getText().toString());
		getActivity().startActivity(intent);
	}
}
