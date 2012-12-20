package org.security.apps.pim;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

	private Integer id;
	private String name;
	private String phoneNum;
	private CharSequence publicKey;

	public Contact(Integer id, String name, String phoneNum, CharSequence publicKey) {
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.publicKey = publicKey;
	}

	public Contact(String name, String phoneNum) {
		this.id = -1;
		this.name = name;
		this.phoneNum = phoneNum;
		this.publicKey = null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Contact(Parcel in) {
		name = in.readString();
		phoneNum = in.readString();
		publicKey = (CharSequence) in.readString();
	}
	
	@Override
	public String toString() {
		return name + ": " + phoneNum;
//		return phoneNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public CharSequence getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(CharSequence publicKey) {
		this.publicKey = publicKey;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		Bundle bundle = new Bundle();

		bundle.putString("name", name);
		bundle.putString("phoneNum", phoneNum);
		bundle.putCharSequence("publicKey", publicKey);
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
		public Contact createFromParcel(Parcel in) {
			return new Contact(in);
		}

		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
}
