package com.flexplan.util;


public abstract class AbstractActivityWithExtraInput extends
		AbstractActivity {

	@Override
	protected void setup() {
		setupExtras();
		super.setup();
	}

	abstract protected void setupExtras();


	
	
}
