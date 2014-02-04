package com.flexplan.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.SaveDiscardProvider;

public class DiscardListener implements OnClickListener {

	private SaveDiscardProvider provider;

	public DiscardListener(SaveDiscardProvider provider) {
		this.provider = provider;
	}

	@Override
	public void onClick(DialogInterface paramDialogInterface, int paramInt) {
		provider.discard();
	}

}
