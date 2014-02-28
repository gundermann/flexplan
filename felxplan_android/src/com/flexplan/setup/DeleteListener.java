package com.flexplan.setup;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DeleteListener<T> implements OnClickListener {

	private DeleteProvider<T> provider;
	private T object;

	public DeleteListener(DeleteProvider<T> provider, T o) {
		this.provider = provider;
		this.object = o;
	}

	@Override
	public void onClick(DialogInterface dialog, int arg1) {
		provider.delete(object);
	}

}
