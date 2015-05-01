package com.example.br1;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;

/**
 * Global static global variables for our app
 *
 * Created by juanvallejo on 3/9/15.
 */
public class Globals {

    public static MainActivity mainActivityContext;
    public static PreferenceActivity sharedPreferencesContext;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;

    public static String notificationsText = Constants.NOTIFICATIONS_DEFAULT_TEXT;

    // set settings preferences
    public static boolean customBroadcastTracking   = false;
    public static boolean notificationsEnabled      = false;
    public static int broadcastCount                = 0;


}