package com.example.br1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by juanvallejo on 4/21/15.
 */

public class activityPreference extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();

        //android.R.id.content gives you the root element of a view, without having to know
        //its actual name/type/ID. Check out stackoverflow.com/questions/4486034/
        //This is useful in fragment transactions like:
        //mFragmentTransaction.add(android.R.id.content, myFragment)
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
    }

    public static class PrefsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {

            if(key.equals("BROADCASTS_ENABLE")) {

                // check to see if notifications have been enabled
                if(Globals.customBroadcastTracking) {

                    // disable notifications
                    Globals.customBroadcastTracking = false;
                    findPreference("BROADCASTS_TOTAL").setEnabled(false);
                    findPreference("NOTIFICATIONS_ENABLE").setEnabled(false);

                } else {

                    // enable notifications
                    Globals.customBroadcastTracking = true;
                    findPreference("BROADCASTS_TOTAL").setEnabled(true);
                    findPreference("NOTIFICATIONS_ENABLE").setEnabled(true);

                }

            } else if(key.equals("NOTIFICATIONS_ENABLE")) {

            }

        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

    }
}

