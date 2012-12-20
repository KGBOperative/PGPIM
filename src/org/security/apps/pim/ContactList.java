package org.security.apps.pim;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContactList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int flagValue = 99;
	private static final String fileName = "PGPIMContactList";
	private static final String prefFlag = "flag";
	private static final String prefContacts = "contacts";
	private static final String prefIds = "ids";
	private static final String prefNums = "phoneNums";
	private static final String prefPubKeys = "pubKeys";
	private static final String prefPubKey = "pubKey";
	private static final String prefPrivKey = "privKey";

	private HashMap<Integer, Contact> contacts;
	private CharSequence publicKey;
	private CharSequence privateKey;

	public ContactList(Context context) {
		// try {
		// InputStream file = new FileInputStream("savedContacts");
		// InputStream buffer = new BufferedInputStream(file);
		// ObjectInput input = new ObjectInputStream(buffer);
		//
		// try {
		// ContactList savedContacts = (ContactList) input.readObject();
		// this.contacts = savedContacts.contacts;
		// } finally {
		// input.close();
		// }
		// } catch (ClassNotFoundException cnfe) {
		// Log.d(null, null, cnfe);
		// contacts = new HashMap<Integer, Contact>();
		// } catch (IOException ioe) {
		// Log.d(null, null, ioe);
		// contacts = new HashMap<Integer, Contact>();
		// }
		ContactList temp = ContactList.restoreContacts(context, fileName);

		if (temp != null) {
			this.contacts = temp.contacts;
//			Toast.makeText(context, "fucker saved", Toast.LENGTH_SHORT).show();
		} else {
			this.contacts = new HashMap<Integer, Contact>();
			this.privateKey = null;
			this.publicKey = null;
//			Toast.makeText(context, "fucker is new", Toast.LENGTH_SHORT).show();

		}
	}

	private ContactList(HashMap<Integer, Contact> contacts,
			CharSequence publicKey, CharSequence privateKey) {
		this.contacts = contacts;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	// public static void writeToFile(Context context, Object object, String
	// filename) {
	//
	// ObjectOutputStream objectOut = null;
	// try {
	//
	// FileOutputStream fileOut = context.openFileOutput(filename,
	// Activity.MODE_PRIVATE);
	// objectOut = new ObjectOutputStream(fileOut);
	// objectOut.writeObject(object);
	// fileOut.getFD().sync();
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// if (objectOut != null) {
	// try {
	// objectOut.close();
	// } catch (IOException e) {
	// // do nowt
	// }
	// }
	// }
	// }
	//
	public static ContactList restoreContacts(Context context, String filename) {
		SharedPreferences prefs = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);

		if (prefs.getInt(prefFlag, 0) == 0)
			return null;
		//
		// ObjectInputStream objectIn = null;
		// Object object = null;
		// try {
		//
		// FileInputStream fileIn =
		// context.getApplicationContext().openFileInput(filename);
		// objectIn = new ObjectInputStream(fileIn);
		// object = objectIn.readObject();
		//
		// } catch (FileNotFoundException e) {
		// // Do nothing
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } finally {
		// if (objectIn != null) {
		// try {
		// //Toast.makeText(context, "closing file", Toast.LENGTH_SHORT).show();
		// objectIn.close();
		// } catch (IOException e) {
		// // do nowt
		// }
		// }
		// }
		//
		// return object;

		CharSequence tempPrivKey = (CharSequence) prefs.getString(prefPrivKey,
				null);
		CharSequence tempPubKey = (CharSequence) prefs.getString(prefPubKey,
				null);

		Contact tempContact;
		int length = prefs.getStringSet(prefIds, null).size();
		
		String[] ids = new String[length]; 
		prefs.getStringSet(prefIds, null).toArray(ids);
		
		String[] names = new String[length];
		prefs.getStringSet(prefContacts, null).toArray(names);
		
		String[] phoneNums = new String[length];
		prefs.getStringSet(prefNums, null).toArray(phoneNums);
		
		String[] pubKeys = new String[length];
		prefs.getStringSet(prefPubKeys, null).toArray(pubKeys);
		
		HashMap<Integer, Contact> tempContacts = new HashMap<Integer, Contact>();

		for (int i = 0; i < ids.length; ++i) {
			tempContact = new Contact(Integer.valueOf(ids[i]), names[i], phoneNums[i], pubKeys[i]);
			tempContacts.put(Integer.valueOf(ids[i]), tempContact);
		}

		return new ContactList(tempContacts, tempPrivKey, tempPubKey);
	}

	public boolean saveContactList(Context context) {
		Set<Integer> ids = contacts.keySet();

		Set<String> idSet = new HashSet<String>();
		Set<String> phoneNumSet = new HashSet<String>();
		Set<String> nameSet = new HashSet<String>();
		Set<String> pubKeySet = new HashSet<String>();

		Contact temp;

		for (Integer id : ids) {
			temp = contacts.get(id);

			idSet.add(id.toString());
			nameSet.add(temp.getName());
			phoneNumSet.add(temp.getPhoneNum());
			pubKeySet.add((String) temp.getPublicKey());
		}

		// ContactList.writeToFile(c, (Object) contacts, fileName);
		// String fuck = "fuck";
		// ContactList.writeToFile(c, (Object)fuck, fileName);

		SharedPreferences prefs = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();

		editor.putInt(prefFlag, flagValue);
		editor.putString(prefPrivKey, (String) privateKey);
		editor.putString(prefPubKey, (String) publicKey);
		editor.putStringSet(prefIds, idSet);
		editor.putStringSet(prefContacts, nameSet);
		editor.putStringSet(prefNums, phoneNumSet);
		editor.putStringSet(prefPubKeys, pubKeySet);

		return editor.commit();
	}

	public void addContact(Contact contact) {
		int id = contacts.size();
		contacts.put(id, contact);
		// Log.d("save", contact.getName());
	}

	public boolean removeContact(Integer id) {
		return (contacts.remove(id) != null) ? true : false;
	}

	public HashMap<Integer, Contact> getContacts() {
		return contacts;
	}
}