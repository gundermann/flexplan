package com.flexplan.setup;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.flexplan.util.DeleteProvider;

public class OnDeleteClickListener<T> implements OnItemLongClickListener,
		OnItemClickListener, OnClickListener {

	private DeleteProvider<T> deleteProvider;
	private T object;

	public OnDeleteClickListener(DeleteProvider<T> deleteProvider, T object) {
		this.deleteProvider = deleteProvider;
		this.object = object;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		if(object == null){
			object = (T) adapterView.getAdapter().getItem(position);
		}
		deleteProvider.initDelete(object);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		deleteProvider.initDelete(object);

	}

	@Override
	public void onClick(View v) {
		deleteProvider.initDelete(object);
	}

}
