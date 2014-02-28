package com.flexplan.setup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

import com.flexplan.R;
import com.flexplan.R.string;
import com.flexplan.util.AbstractDialog;

public class DeleteDialog extends AbstractDialog{

	private OnClickListener listener;

	@Override
	protected void instantiateViews(View view) {
	}

	@Override
	protected Dialog buildDialog(View view) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setMessage(R.string.ask_delete);
		dialogBuilder.setPositiveButton(android.R.string.ok, listener);
		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
	return dialogBuilder.create();	
	}

	@Override
	protected View getCurrentView() {
		return null;
	}

	public static DeleteDialog newInstance(OnClickListener listener) {
		DeleteDialog dialog = new DeleteDialog();
		dialog.listener = listener;
		return dialog;
	}

}
