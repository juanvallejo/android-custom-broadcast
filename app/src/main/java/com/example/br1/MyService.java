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

	/**
	 * If service is called through the startService() method
	 * this is used.
	 * @param intent
	 * @param flags
	 * @param startId
	 * @return
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// update preferences broadcast count
		Actions.increaseBroadcastCount();

		// check to see if notifications are
		// enabled and send to the user
		if(Globals.notificationsEnabled) {
			Actions.sendNotification(Globals.mainActivityContext);
		}

		Actions.sendToast("Broadcast sent");

		// stop service
		stopSelf();
		return 0;
	}

	/**
	 * If service is bound instead of started, this is called
	 * @param intent
	 * @return
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
