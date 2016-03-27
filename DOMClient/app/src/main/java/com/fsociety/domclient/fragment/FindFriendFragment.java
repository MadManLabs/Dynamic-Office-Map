package com.fsociety.domclient.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fsociety.domclient.R;
import com.fsociety.domclient.activity.UserDetailsActivity_;
import com.fsociety.domclient.rest.GetPersonDtos;
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

	public List<String> usernames = new ArrayList<>();

	public InstantAutoCompleteTextView getSearchUsernameInstantAutoCompleteTextView() {
		return searchUsernameInstantAutoCompleteTextView;
	}

	@AfterViews
	protected void setupViews() {
		new GetPersonDtos(this).execute();
	}

	@Click(R.id.findFriendButton)
	protected void onFindFriendButtonClick(View view) {
		if (usernames.contains(searchUsernameInstantAutoCompleteTextView.getText().toString())) {
			Intent intent = new Intent(getActivity(), UserDetailsActivity_.class);
			intent.putExtra("username", searchUsernameInstantAutoCompleteTextView.getText().toString());
			getActivity().startActivity(intent);
		} else {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
			alertDialogBuilder.setIconAttribute(android.R.attr.alertDialogIcon);
			alertDialogBuilder.setTitle("Form error")
					.setMessage("Please select an user from the dropdown!").setCancelable(false);
			alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
			alertDialogBuilder.show();
		}
	}
}
