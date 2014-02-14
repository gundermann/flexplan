package com.flexplan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.util.AbstractDialog;

public class DeleteDialog extends AbstractDialog{

	
	private DeleteProvider provider;
	private FlextimeDay day;

	@Override
	protected void instantiateViews(View view) {
	}

	@Override
	protected Dialog buildDialog(View view) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setMessage(R.string.ask_delete);
		dialogBuilder.setPositiveButton(android.R.string.ok, new DeleteListener(provider, day));
		dialogBuilder.setNegativeButton(android.R.string.cancel, null);
	return dialogBuilder.create();	
	}

	@Override
	protected View getCurrentView() {
		return null;
	}

	public static DialogFragment newInstance(DeleteProvider provider,
			FlextimeDay day) {
		DeleteDialog dialog = new DeleteDialog();
		dialog.provider = provider;
		dialog.day = day;
		return dialog;
	}

}
