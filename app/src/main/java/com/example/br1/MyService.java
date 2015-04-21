package com.example.br1;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {


	private static final String TAG = "BRandServiceSysAction";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "In Service", Toast.LENGTH_SHORT).show();	
		Log.e(TAG,"In BRandServiceandSystemAction");
		stopSelf();
		return 0;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
