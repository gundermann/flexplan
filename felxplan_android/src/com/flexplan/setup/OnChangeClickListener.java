package com.flexplan.setup;



import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnChangeClickListener<T> implements OnItemClickListener {

	private ChangeProvider<T> changeProvider;

	public OnChangeClickListener(ChangeProvider<T> changeProvider) {
		this.changeProvider = changeProvider;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		T o =  (T) adapterView.getAdapter().getItem(position);
		changeProvider.initChange(o);
	}
}
