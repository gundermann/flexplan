package com.felxplan;

import com.felxplan.persistence.DBHelper;

import android.app.Application;

public class FlexplanApplication extends Application {

	private DBHelper dbHelper;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		dbHelper = new DBHelper(this);
	}


	public DBHelper getDbHelper() {
		return dbHelper;
	}

}
