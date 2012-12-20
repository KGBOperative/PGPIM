package org.security.apps.pim;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int MENU_MESG = 1;
	public static final int MENU_REMV = 2;
	public static final int MENU_ADD = 3;

	private boolean noContacts;

	HashMap<Integer, Contact> tempSet;
	private ArrayAdapter<String> adapter;
	private ContactList contactList;
	private ListView contactListView;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		contactListView = (ListView) findViewById(R.id.contactListView);
		ArrayList<String> contacts = new ArrayList<String>();
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		contactList = new ContactList(this.getApplicationContext());

		if (extras != null) {
			Contact newContact = new Contact(extras.getString("name"),
					extras.getString("phoneNum"));

			// Toast.makeText(this.getApplicationContext(),
			// contactList.getContacts().size(), Toast.LENGTH_SHORT).show();

			contactList.addContact(newContact);

			for (Contact c : contactList.getContacts().values()) {
				// Toast.makeText(this.getApplicationContext(), c.toString(),
				// Toast.LENGTH_SHORT).show();
			}
			contactList.saveContactList(this.getApplicationContext());

		}

		tempSet = contactList.getContacts();
		noContacts = tempSet.isEmpty();

		if (noContacts) {
			contacts.add("Add Contact");
		} else {
			for (Contact c : tempSet.values()) {
				contacts.add(c.toString());
				// Toast.makeText(this.getApplicationContext(), c.toString(),
				// Toast.LENGTH_SHORT).show();
			}
		}

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, contacts);
		contactListView.setAdapter(adapter);

		registerForContextMenu(contactListView);

		// contactListView.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		for (int i = 0; i < 3; ++i) {
			if (contactList.saveContactList(this.getApplicationContext()))
				return;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.clear();
		menu.add(Menu.NONE, MENU_ADD, Menu.NONE, "Add Contact");
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (noContacts) {
			menu.add(Menu.NONE, MENU_ADD, Menu.NONE, "Add Contact");
		}

		else {
			AdapterView.AdapterContextMenuInfo info =
		            (AdapterView.AdapterContextMenuInfo) menuInfo;
		    String selectedContact = ((TextView) info.targetView).getText().toString();
		    
		    menu.setHeaderTitle(selectedContact);
			menu.add(Menu.NONE, MENU_MESG, Menu.NONE, "Message Contact");
			menu.add(Menu.NONE, MENU_REMV, Menu.NONE, "Remove Contact");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_MESG:
			Toast.makeText(this.getApplicationContext(), "messaging contact",
					Toast.LENGTH_SHORT).show();
//			String number = (String)item.getTitle();
//			Toast.makeText(getApplicationContext(), number, Toast.LENGTH_SHORT).show();
		    
			sendMessage("8888", "thisisakey");
			
			return true;

		case MENU_REMV:
			Toast.makeText(this.getApplicationContext(), "removing contact",
					Toast.LENGTH_SHORT).show();
			return true;

		case MENU_ADD:
			// Toast.makeText(this.getApplicationContext(), "adding contact",
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainActivity.this,
					NewContactActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ADD:
			// Toast.makeText(this.getApplicationContext(), "adding contact",
			// Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainActivity.this,
					NewContactActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	public void sendMessage(String number, String pubKey) {
		Intent i = new Intent(MainActivity.this, MessageWriter.class);
		i.putExtra("number", number);
		i.putExtra("publicKey", pubKey);
		startActivity(i);
	}

	public void loadList() {

	}
}
