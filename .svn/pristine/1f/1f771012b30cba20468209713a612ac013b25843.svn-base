package org.security.apps.pim;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MessageWriter extends Activity {

	private ArrayList<String> chatLog;
	private ArrayAdapter<String> adapter;
	private ListView chatListView;

	EditText e;
	TextView t;
	Button b;
	String number;
	String pubKey;

	public void encrypt(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

		e = (EditText) findViewById(R.id.messageEdit);
		t = (TextView) findViewById(R.id.messageText);
		b = (Button) findViewById(R.id.messageEncrypt);

		t.setText(e.getText().toString());
		b.setText("Send");

		String clearText = e.getText().toString();

		chatLog.add("me: " + clearText);
		
		Intent i = new Intent(MessageWriter.this, Encrypter.class);
		i.putExtra("number", number);
		i.putExtra("publicKey", pubKey);
		i.putExtra("clearText", clearText);
		startActivity(i);
		// SEND TO ENCRYPTER

		// RETRIEVE MESSAGE FROM ENCRYPTER

		// SEND MESSAGE TO SMS-HANDLER

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_writer);
		Intent i = getIntent();
		Bundle bundle = i.getExtras();

		chatListView = (ListView) findViewById(R.id.chatListView);
		chatLog = new ArrayList<String>();

		if (bundle.containsKey("incomingMsg")) {
			chatLog.add(number + ": " + bundle.getString("incomingMsg"));
		}

		else {
			number = bundle.getString("number");
			pubKey = bundle.getString("publicKey");
		}
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, chatLog);
		chatListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_message_writer, menu);
		return true;
	}

}