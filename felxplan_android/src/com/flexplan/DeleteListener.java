package com.flexplan;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DeleteListener implements OnClickListener {

	private DeleteProvider provider;
	private Object object;

	public DeleteListener(DeleteProvider provider, Object o) {
		this.provider = provider;
		this.object = o;
	}

	@Override
	public void onClick(DialogInterface dialog, int arg1) {
		provider.delete(object);
	}

}
