package com.flexplan;

import android.os.Bundle;

import com.flexplan.persistence.CacheDBHelper;
import com.flexplan.util.AbstractActivity;

abstract public class AbstractFlextimeActivity extends AbstractActivity {

	protected CacheDBHelper cacheDbHelper;
	
	protected FlexplanApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (FlexplanApplication) getApplication();
		setupDB();
	}

	protected void setupDB() {
		this.cacheDbHelper = ((FlexplanApplication) getApplication())
		.getCacheDB();
	}

	protected CacheDBHelper getCacheDbHelper() {
		return cacheDbHelper;
	}

}
