package com.flexplan;

import android.view.View;
import android.view.View.OnClickListener;

import com.flexplan.util.DeleteProvider;

public class OnDeleteClickListener<T> implements OnClickListener{

	private T object;
	private DeleteProvider<T> provider;

	public OnDeleteClickListener(DeleteProvider<T> provider, T object) {
		this.provider = provider;
		this.object = object;
	}

	@Override
	public void onClick(View v) {
		provider.delete(object);
	}

}
