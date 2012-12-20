package org.security.apps.pim;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_contact, menu);
		return true;
	}

	public void saveContact(View v) {
        EditText editNameText = (EditText) findViewById(R.id.editNameText);
        EditText editPhoneText = (EditText) findViewById(R.id.editPhoneText);
        
        Contact contact = new Contact(editNameText.getText().toString(), editPhoneText.getText().toString());
        
        Intent intent = new Intent(NewContactActivity.this, MainActivity.class);
        //intent.putExtra("name", contact.getName());
        intent.putExtra("name", contact.getName());
        intent.putExtra("phoneNum", contact.getPhoneNum());
        intent.putExtra("publicKey", contact.getPublicKey());
		//Toast.makeText(this.getApplicationContext(), contact.toString(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
        
        finish();
	}
	
	public void cancelSave(View v) {
		Intent intent = new Intent(NewContactActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
