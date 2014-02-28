package com.flexplan.util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public abstract class AbstractActivity extends FragmentActivity {

	public static String TAG;
	protected SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getSimpleName();
		prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		setup();

		initElements();

	}

	protected void setup() {
		setContentView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(getMenu(), menu);
		return true;
	}

	abstract protected int getMenu();

	//TODO Annotation Framework
	abstract protected void initElements();

	protected abstract void setContentView();

	@Deprecated
	protected void startNextActivity(Intent intent) {
		startActivity(intent);
	}
	
	protected void startNextActivity(Class<? extends Activity> activity){
		startActivity(new Intent(getApplicationContext(), activity));
	}

}
