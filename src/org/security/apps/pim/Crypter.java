package org.security.apps.pim;

import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.widget.Toast;

public class Crypter {
	public static byte[] encrypt(Context context, String message, String key) {
		byte[] clearText = null;
		try {
			clearText = message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		byte[] byteKey = null;
		try {
			byteKey = key.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

//		String testResult = null;
//		
//		try {
//			testResult = new String(byteKey, "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
//		
		
		byte[] result = new byte[clearText.length];
		
		for (int count = 0; count < clearText.length; count++) {
			result[count] = (byte) (clearText[count] ^ byteKey[count % byteKey.length]);
		}

//		String cypherText = null;
//		try {
//			cypherText = new String(result, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		return result;
	}

	public static String decrypt(String cypherText, String key) {
		byte[] cypherBytes = null;
		try {
			cypherBytes = cypherText.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		byte[] byteKey = null;
		try {
			byteKey = key.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		byte[] result = new byte[cypherBytes.length];
		for (int count = 0; count < cypherBytes.length; count++) {
			result[count] = (byte) (cypherBytes[count] ^ byteKey[count
					% byteKey.length]);
		}

		String decrypted = "";
		try {
			decrypted = new String(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return decrypted;
	}
}