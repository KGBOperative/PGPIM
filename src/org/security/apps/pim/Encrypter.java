package org.security.apps.pim;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.Toast;

public class Encrypter extends Activity {

	String message;
	String key;
	String number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encrypter);
		Intent i = getIntent();
		Bundle b = i.getExtras();

		message = b.getString("clearText");
		key = b.getString("publicKey");
		number = b.getString("number");
		byte[] encryptedMsg = Crypter.encrypt(getApplicationContext(), message, key);
		Toast.makeText(getApplicationContext(), encryptedMsg + " is encrypted", Toast.LENGTH_SHORT).show();
		sendMessage(encryptedMsg);
	}

	public void sendMessage(byte[] msg) {

		SmsManager smsManager = SmsManager.getDefault();
//		smsManager.sendTextMessage(Uri.parse(number), null, s, null, null);
		smsManager.sendDataMessage(Uri.parse(number).toString(), null, (short)6626, msg, null, null);
//		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
//				+ number));
//		intent.putExtra("sms_body", s);
//		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_encrypter, menu);
		return true;
	}

}
