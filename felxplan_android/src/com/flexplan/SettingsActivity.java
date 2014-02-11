package com.flexplan;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.text.InputType;

public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		setupSimplePreferencesScreen();
	}

	@SuppressWarnings("deprecation")
	private void setupSimplePreferencesScreen() {

		addPreferencesFromResource(R.xml.flexplan_preference);
		((EditTextPreference) getPreferenceScreen().getPreference(0))
				.getEditText().setInputType(
						InputType.TYPE_CLASS_NUMBER);
		((EditTextPreference) getPreferenceScreen().getPreference(1))
		.getEditText().setInputType(
				InputType.TYPE_CLASS_DATETIME);
		((EditTextPreference) getPreferenceScreen().getPreference(2))
		.getEditText().setInputType(
				InputType.TYPE_CLASS_DATETIME);
		((EditTextPreference) getPreferenceScreen().getPreference(3))
		.getEditText().setInputType(
				InputType.TYPE_CLASS_NUMBER);

		// Add 'notifications' preferences, and a corresponding header.
		PreferenceCategory fakeHeader = new PreferenceCategory(this);
		fakeHeader.setTitle(R.string.pref_header_notifications);
		getPreferenceScreen().addPreference(fakeHeader);
		addPreferencesFromResource(R.xml.pref_notification);

		// Add 'data and sync' preferences, and a corresponding header.
		fakeHeader = new PreferenceCategory(this);
		fakeHeader.setTitle(R.string.pref_header_data_sync);
		getPreferenceScreen().addPreference(fakeHeader);

	}
}