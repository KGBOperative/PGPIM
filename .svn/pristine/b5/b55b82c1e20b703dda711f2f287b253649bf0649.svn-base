package org.security.apps.pim;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSreceiver extends BroadcastReceiver {

	private static final String prefix = "PGPIM encrypted message:";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		if (null != bundle) {
			Object[] pdus = (Object[]) bundle.get("pdus");

			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				if (messages[i].getDisplayMessageBody().startsWith(prefix)) {

					String decryptedMsg = Crypter.decrypt(
							messages[i].getDisplayMessageBody().substring(
									prefix.length()), "thisisakey");
					
					Intent keepMsgIntent = new Intent(context,
							MessageWriter.class);

					keepMsgIntent.putExtra("phoneNum",
							messages[i].getOriginatingAddress());

					keepMsgIntent.putExtra("incomingMsg", decryptedMsg);

					context.startActivity(keepMsgIntent);
				}

				else {
					// delete the message from the phone
				}
			}
		}
	}
}