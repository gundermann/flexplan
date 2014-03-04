package com.flexplan.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SimpleDialog extends DialogFragment {
	
	protected OnClickListener listener;
	protected String message;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return buildDialog();
	}

	protected Dialog buildDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setMessage(message);
		dialogBuilder.setPositiveButton(android.R.string.ok, listener);
		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
	return dialogBuilder.create();	
	}

	public static SimpleDialog newInstance(OnClickListener listener, String message) {
		SimpleDialog dialog = new SimpleDialog();
		dialog.message = message;
		dialog.listener = listener;
		return dialog;
	}
	
}
