package com.flexplan.util;

import com.flexplan.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class OverwriteDialog extends AbstractDialog {

	private OverwriteProvider provider;

	public static DialogFragment newInstance(
			OverwriteProvider provider) {
		OverwriteDialog dialog = new OverwriteDialog();
		dialog.provider = provider;
		dialog.setCancelable(false);
		return dialog;
	}

	@Override
	protected void instantiateViews(View view) {
	}

	@Override
	protected Dialog buildDialog(View view) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setMessage(R.string.save_or_discard);
		dialogBuilder.setPositiveButton(android.R.string.ok, new OverwriteListener(provider));
		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
	return dialogBuilder.create();	
	}

	@Override
	protected View getCurrentView() {
		return null;
	}

}
