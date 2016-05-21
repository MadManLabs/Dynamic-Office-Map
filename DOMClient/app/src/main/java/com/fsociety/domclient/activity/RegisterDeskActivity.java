package com.fsociety.domclient.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.fsociety.domclient.R;
import com.fsociety.domclient.dto.DeskDTO;
import com.fsociety.domclient.dto.PersonDTO;
import com.fsociety.domclient.fragment.RegisterDeskFragment;
import com.fsociety.domclient.rest.UpdatePersonDeskById;

import org.androidannotations.annotations.EActivity;

import java.io.UnsupportedEncodingException;

@EActivity(R.layout.register_desk_activity)
public class RegisterDeskActivity extends BaseActivity {
	private NfcAdapter nfcAdapter;
	private PendingIntent pendingIntent;
	private IntentFilter writeTagFilters[];
	private boolean writeMode;
	private Tag readTag;

	public RegisterDeskFragment registerDeskFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter != null) {
			pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
			IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
			tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
			writeTagFilters = new IntentFilter[]{tagDetected};
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (nfcAdapter != null) {
			writeMode = false;
			nfcAdapter.disableForegroundDispatch(this);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (nfcAdapter != null) {
			writeMode = true;
			nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
			Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			if (parcelables != null && parcelables.length > 0) {
				//Toast.makeText(this, readTextFromMessage((NdefMessage) parcelables[0]), Toast.LENGTH_LONG).show();
				PersonDTO personDTO = new PersonDTO();
				//personDTO.setId(application.getSettings().getId());
				DeskDTO deskDTO = new DeskDTO();
				String nfcCode = readTextFromMessage((NdefMessage) parcelables[0]);
				String codeType = nfcCode.split("=")[0];
				String code = nfcCode.split("=")[1];
				//personDTO.setDesk(deskDTO);
				new UpdatePersonDeskById(this, application.getSettings().getLoggedInPersonDTO(), codeType, code).execute();
			} else {
				Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
			}

		}
	}

	private String readTextFromMessage(NdefMessage ndefMessage) {
		NdefRecord[] ndefRecords = ndefMessage.getRecords();
		if (ndefRecords != null && ndefRecords.length > 0) {
			NdefRecord ndefRecord = ndefRecords[0];
			try {
				byte[] payload = ndefRecord.getPayload();
				String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
				int languageSize = payload[0] & 0063;
				return new String(payload, languageSize + 1,
						payload.length - languageSize - 1, textEncoding);
			} catch (UnsupportedEncodingException e) {
				Log.e("getTextFromNdefRecord", e.getMessage(), e);
				return null;
			}
		} else {
			Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
			return null;
		}
	}
}
