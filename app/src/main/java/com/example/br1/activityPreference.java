package com.example.br1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
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

        Globals.sharedPreferencesContext = this;

    }

    public static class PrefsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);



            ((CheckBoxPreference)findPreference(Constants.BROADCASTS_ENABLE)).setChecked(Globals.customBroadcastTracking);
            ((CheckBoxPreference)findPreference(Constants.NOTIFICATIONS_ENABLE)).setChecked(Globals.notificationsEnabled);
            ((CheckBoxPreference)findPreference(Constants.NOTIFICATIONS_ENABLE)).setEnabled(Globals.customBroadcastTracking);
            ((EditTextPreference)findPreference(Constants.NOTIFICATIONS_DEFAULT)).setSummary(Globals.notificationsText);
            ((EditTextPreference)findPreference(Constants.NOTIFICATIONS_DEFAULT)).setText(Globals.notificationsText);
            ((Preference)findPreference(Constants.BROADCASTS_TOTAL)).setEnabled(Globals.customBroadcastTracking);
            ((Preference)findPreference(Constants.BROADCASTS_TOTAL)).setSummary("" + Globals.broadcastCount);

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {

            if(key.equals(Constants.BROADCASTS_ENABLE)) {

                // check to see if notifications have been enabled
                if(Globals.customBroadcastTracking) {

                    // disable notifications
                    Globals.customBroadcastTracking = false;
                    Globals.sharedPreferences.edit().putBoolean(Constants.BROADCASTS_ENABLE, false).commit();
                    findPreference(Constants.BROADCASTS_TOTAL).setEnabled(false);
                    findPreference(Constants.NOTIFICATIONS_ENABLE).setEnabled(false);

                } else {

                    // enable notifications
                    Globals.customBroadcastTracking = true;
                    Globals.sharedPreferences.edit().putBoolean(Constants.BROADCASTS_ENABLE, true).commit();
                    findPreference(Constants.BROADCASTS_TOTAL).setEnabled(true);
                    findPreference(Constants.NOTIFICATIONS_ENABLE).setEnabled(true);

                }

            } else if(key.equals(Constants.NOTIFICATIONS_ENABLE)) {

                if(Globals.notificationsEnabled) {
                    Globals.notificationsEnabled = false;
                    Globals.sharedPreferences.edit().putBoolean(Constants.NOTIFICATIONS_ENABLE, false).commit();
                } else {
                    Globals.notificationsEnabled = true;
                    Globals.sharedPreferences.edit().putBoolean(Constants.NOTIFICATIONS_ENABLE, true).commit();
                }

            } else if(key.equals(Constants.NOTIFICATIONS_DEFAULT)) {
                Globals.notificationsText = Globals.sharedPreferences.getString(Constants.NOTIFICATIONS_DEFAULT, Constants.NOTIFICATIONS_DEFAULT_TEXT);
                Globals.sharedPreferences.edit().putString(Constants.NOTIFICATIONS_DEFAULT, Globals.notificationsText).commit();
                ((EditTextPreference)findPreference(Constants.NOTIFICATIONS_DEFAULT)).setSummary(Globals.notificationsText);
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

