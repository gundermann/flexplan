package com.flexplan;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class OnDeleteLongClickListener implements OnItemLongClickListener {

	private DeleteProvider deleteProvider;

	public OnDeleteLongClickListener(DeleteProvider deleteProvider) {
		this.deleteProvider = deleteProvider;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		Object o = adapterView.getAdapter().getItem(
				position);
		deleteProvider.initDelete(o);
		return true;
	}

}
