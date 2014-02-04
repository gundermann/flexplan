package com.flexplan.util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public abstract class AbstractDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View view = getCurrentView();
		instantiateViews(view);
		return buildDialog(view);
	}
	
	abstract protected void instantiateViews(View view);
	
	abstract protected Dialog buildDialog(View view);
	
	abstract protected View getCurrentView();
	
}
