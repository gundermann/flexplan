package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class OverwriteListener implements OnClickListener {

	private OverwriteProvider provider;

	public OverwriteListener(OverwriteProvider provider) {
		this.provider = provider;
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		provider.overwriteOrSave();
	}

}
