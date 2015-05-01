package com.example.br1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.preference.EditTextPreference;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;

/**
 * Application actions that execute throughout the app's runtime.
 *
 * Created by juanvallejo on 3/12/15.
 */
public class Actions {

    /**
     *  Increase the global broadcast count by 1
     */
    public static void increaseBroadcastCount() {
        Globals.broadcastCount++;
        Actions.updateBroadcastCountPreference();
    }

    /**
     * Sets the broadcast count preference to the
     * current number held by global broadcast count
     */
    public static void updateBroadcastCountPreference() {
        Globals.sharedPreferences.edit().putInt(Constants.BROADCASTS_TOTAL, Globals.broadcastCount).commit();
    }

    /**
     * Resets total broadcast count preference.
     */
    public static void resetBroadcastCount() {
        Globals.broadcastCount = 0;
        Actions.updateBroadcastCountPreference();
        Actions.sendToast("Broadcast count reset");
    }

    /**
     *  Sends a notification containing the current
     *  text set by the user in preferences
     */
    public static void sendNotification(Context context) {

        NotificationManager notifications = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(context)
                .setContentTitle(Globals.sharedPreferences.getString(Constants.NOTIFICATIONS_DEFAULT, Constants.NOTIFICATIONS_DEFAULT_TEXT))
                .setContentText("Have received " + Globals.broadcastCount + " notifications")
                .setSmallIcon(R.drawable.ic_launcher)
                .setOngoing(false)
                .getNotification();

        notification.flags |= Notification.FLAG_INSISTENT;

        notifications.notify(Constants.NOTIFICATION_ID, notification);
    }

    public static void sendToast(String text) {
        Toast.makeText(Globals.mainActivityContext, text, Toast.LENGTH_LONG).show();
    }

}
