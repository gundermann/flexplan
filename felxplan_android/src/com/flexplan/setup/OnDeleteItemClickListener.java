package com.flexplan.setup;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.flexplan.util.DeleteProvider;

public class OnDeleteItemClickListener<T> implements OnItemLongClickListener,
		OnItemClickListener{

	private DeleteProvider<T> deleteProvider;

	public OnDeleteItemClickListener(DeleteProvider<T> deleteProvider) {
		this.deleteProvider = deleteProvider;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		T object = (T) adapterView.getAdapter().getItem(position);
		deleteProvider.initDelete(object);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		T object = (T) adapterView.getAdapter().getItem(position);
		deleteProvider.initDelete(object);
	}

}
