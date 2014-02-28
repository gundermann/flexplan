package com.flexplan;


import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnChangeClickListener implements OnItemClickListener {

	private ChangeProvider changeProvider;

	public OnChangeClickListener(ChangeProvider changeProvider) {
		this.changeProvider = changeProvider;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		Object o = adapterView.getAdapter().getItem(position);
		changeProvider.initChange(o);
	}
}
