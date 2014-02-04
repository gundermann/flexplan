package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.SaveDiscardProvider;

public class SaveListener implements OnClickListener {

	private SaveDiscardProvider provider;

	public SaveListener(SaveDiscardProvider provider) {
		this.provider = provider;
	}

	@Override
	public void onClick(DialogInterface paramDialogInterface, int paramInt) {
		provider.save();
	}

}
