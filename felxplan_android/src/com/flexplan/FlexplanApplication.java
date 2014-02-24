package com.flexplan;

import com.flexplan.persistence.CacheDBHelper;
import com.flexplan.persistence.CacheDBHelperImpl;
import com.flexplan.persistence.FlextimeDBHelperImpl;
import com.flexplan.persistence.FlextimeDBHelper;

import android.app.Application;

public class FlexplanApplication extends Application {

	private FlextimeDBHelperImpl flextimeDbHelper;
	private CacheDBHelperImpl cacheDbHelper;

	@Override
	public void onCreate() {
		super.onCreate();
		flextimeDbHelper = new FlextimeDBHelperImpl(this);
		cacheDbHelper = new CacheDBHelperImpl(this);
	}

	public FlextimeDBHelper getFlextimeDB() {
		return flextimeDbHelper;
	}

	public CacheDBHelper getCacheDB() {
		return cacheDbHelper;
	}

	public boolean isDateCached(String newDate) {
		return cacheDbHelper.getCachedDate().equals(newDate);
	}

	public boolean existsCacheData() {
		return !getCacheDB().isEmpty();
	}

}
