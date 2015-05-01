package com.example.br1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set the context of the app
		Globals.mainActivityContext = this;
		Globals.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		// initialize globals
		Globals.broadcastCount = Globals.sharedPreferences.getInt(Constants.BROADCASTS_TOTAL, 0);
		Globals.notificationsText = Globals.sharedPreferences.getString(Constants.NOTIFICATIONS_DEFAULT, Constants.NOTIFICATIONS_DEFAULT_TEXT);
		Globals.customBroadcastTracking = Globals.sharedPreferences.getBoolean(Constants.BROADCASTS_ENABLE, false);
		Globals.notificationsEnabled = Globals.sharedPreferences.getBoolean(Constants.NOTIFICATIONS_ENABLE, false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		int id = item.getItemId();

		if (id == R.id.action_settings) {

			Intent settingsView = new Intent(this, activityPreference.class);
			startActivity(settingsView);

			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Click handler for the broadcast button
	 * @param view
	 */
	public void doBroadcast(View view){

		// check to see if broadcasts have been enabled
		if(Globals.customBroadcastTracking) {

			// send intent to MyReceiver.java
			Intent myIntent = new Intent("com.example.br1.MYACTION");
			sendBroadcast(myIntent);

		} else {
			Toast.makeText(this, "Broadcasts are not enabled, see settings.", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Reset broadcast count
	 * @param view
	 */
	public void resetCount(View view) {
		Actions.resetBroadcastCount();
	}

}
