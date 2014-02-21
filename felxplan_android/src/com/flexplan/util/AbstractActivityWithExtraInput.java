package com.flexplan.util;

@Deprecated
public abstract class AbstractActivityWithExtraInput extends AbstractActivity {

	@Override
	protected void setup() {
		if (getIntent() != null && getIntent().getExtras() != null
				&& !getIntent().getExtras().isEmpty()) {
			setupExtras();
		}
		super.setup();
	}

	abstract protected void setupExtras();

}
