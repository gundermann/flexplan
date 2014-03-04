package com.flexplan.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.flexplan.ListenerFactory;
import com.flexplan.R;
import com.flexplan.setup.SaveDiscardProvider;

public class SaveOrDiscardDialog extends DialogFragment {

	private SaveDiscardProvider provider;

	public static SaveOrDiscardDialog newInstance(SaveDiscardProvider provider) {
		SaveOrDiscardDialog dialog = new SaveOrDiscardDialog();
		dialog.provider = provider;
		return dialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return buildDialog();
	}

	protected Dialog buildDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getActivity());
		dialogBuilder.setMessage(provider.getSaveDiscardMessage());
		dialogBuilder.setPositiveButton(R.string.save,
				ListenerFactory.createSaveListener(provider));
		dialogBuilder.setNegativeButton(R.string.discard,
				ListenerFactory.createDiscardListener(provider));
		return dialogBuilder.create();
	}

}
