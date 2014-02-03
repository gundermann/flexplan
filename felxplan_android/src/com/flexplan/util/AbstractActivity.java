package com.flexplan.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public abstract class AbstractActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

	abstract protected void initElements();

	protected abstract void setContentView();
	
	protected void startNextActivity(Intent intent) {
		startActivity(intent);
	}

}
