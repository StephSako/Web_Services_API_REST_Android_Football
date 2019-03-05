package com.example.footballapi.view.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.example.footballapi.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            addPreferencesFromResource(R.xml.preferences);
        } catch (Exception e) {
            Log.d("ERROR_PREF", "Erreur lors du chargement des préferences.");
        }
    }
}