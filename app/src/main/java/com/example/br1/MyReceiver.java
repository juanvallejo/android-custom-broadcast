package com.example.br1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 *  Receives broadcast intent from main activity
 */

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		// pass content from main activity and start a custom service
		Intent myIntent = new Intent(context, MyService.class);
		context.startService(myIntent);

	}

}
