package com.flexplan;

import com.flexplan.persistence.DBHelper;
import com.flexplan.persistence.FlextimeDB;

import android.app.Application;

public class FlexplanApplication extends Application {

	private DBHelper dbHelper;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		dbHelper = new DBHelper(this);
	}


	public FlextimeDB getDbHelper() {
		return dbHelper;
	}

}
