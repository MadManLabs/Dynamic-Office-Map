package com.fsociety.domclient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fsociety.domclient.R;
import com.fsociety.domclient.dto.DeskDTO;
import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.rest.UpdatePersonDeskByName;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.register_desk_fragment)
public class RegisterDeskFragment extends BaseFragment {
	@ViewById(R.id.registerDesk1TextView)
	protected TextView registerDesk1TextView;
	@ViewById(R.id.registerDesk2TextView)
	protected TextView registerDesk2TextView;
	@ViewById(R.id.readQRCodeButton)
	protected Button readQRCodeButton;

	@AfterViews
	protected void setupViews() {
		registerDesk1TextView.setText(Html.fromHtml(String.format(getResources().getString(R.string.register_desk_activity_register_desk_1_text), getResources().getString(R.string.application_name))));
		if (NfcAdapter.getDefaultAdapter(getActivity()) != null) {
			registerDesk2TextView.setText(Html.fromHtml(String.format(getResources().getString(R.string.register_desk_activity_register_desk_2_text), getResources().getString(R.string.application_name))));
		} else {
			registerDesk2TextView.setVisibility(View.INVISIBLE);
		}
	}

	@Click(R.id.readQRCodeButton)
	protected void onReadQRCodeButtonClick(View view) {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == Activity.RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				Toast.makeText(getActivity(), "Scan resulted in code:" + contents, Toast.LENGTH_LONG).show();
				PersonDTO personDTO = new PersonDTO();
				personDTO.setId(application.getSettings().getId());
				DeskDTO deskDTO = new DeskDTO();
				deskDTO.setId(Integer.valueOf(contents));
				personDTO.setDesk(deskDTO);
				new UpdatePersonDeskByName(this, personDTO).execute();
			} else if (resultCode == Activity.RESULT_CANCELED) {
				Toast.makeText(getActivity(), getResources().getString(R.string.register_desk_activity_scan_cancelled_text), Toast.LENGTH_LONG).show();
			}
		}
	}
}
