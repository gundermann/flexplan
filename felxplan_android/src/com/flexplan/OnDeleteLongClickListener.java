package com.flexplan;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class OnDeleteLongClickListener<T> implements OnItemLongClickListener {

	private DeleteProvider<T> deleteProvider;

	public OnDeleteLongClickListener(DeleteProvider<T> deleteProvider) {
		this.deleteProvider = deleteProvider;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		T o = (T) adapterView.getAdapter().getItem(position);
		deleteProvider.initDelete(o);
		return true;
	}

}
