package com.flexplan;

import android.os.Bundle;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.persistence.CacheDBHelper;
import com.flexplan.persistence.FlextimeDBHelper;
import com.flexplan.util.AbstractActivity;

abstract public class AbstractFlextimeActivity extends AbstractActivity {

	protected FlextimeDBHelper felxtimeDbHelper;

	protected CacheDBHelper cacheDbHelper;

	protected FlextimeDay currentFlextimeDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupDB();
	}

	protected void setupDB() {
		this.felxtimeDbHelper = ((FlexplanApplication) getApplication())
				.getFlextimeDB();
		this.cacheDbHelper = ((FlexplanApplication) getApplication())
		.getCacheDB();
	}

	public void updateCache() {
		cacheDbHelper.insertOrUpdateFlextimeDay(currentFlextimeDay);
	}

	public void setFlextimeDay(FlextimeDay flextimeDay) {
		currentFlextimeDay = flextimeDay;
	}

	protected FlextimeDBHelper getFelxtimeDbHelper() {
		return felxtimeDbHelper;
	}

	protected CacheDBHelper getCacheDbHelper() {
		return cacheDbHelper;
	}

	protected FlextimeDay getCurrentFlextimeDay() {
		return currentFlextimeDay;
	}

}
