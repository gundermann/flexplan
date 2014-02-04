package com.flexplan.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;

import com.flexplan.R;
import com.flexplan.SaveDiscardProvider;

public class SaveOrDiscardDialog extends AbstractDialog{

	private SaveDiscardProvider provider;

	public static SaveOrDiscardDialog newInstance(
			SaveDiscardProvider provider) {
		SaveOrDiscardDialog dialog = new SaveOrDiscardDialog();
		dialog.provider = provider;
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
		dialogBuilder.setPositiveButton(R.string.save, new SaveListener(provider));
		dialogBuilder.setNegativeButton(R.string.discard, new DiscardListener(provider));
	return dialogBuilder.create();	
	}

	@Override
	protected View getCurrentView() {
		return null;
		}

}
