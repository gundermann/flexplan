package com.flexplan;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.flexplan.common.business.FlextimeDay;

public class DeleteListener implements OnClickListener {

	private DeleteProvider provider;
	private FlextimeDay day;

	public DeleteListener(DeleteProvider provider, FlextimeDay day) {
		this.provider = provider;
		this.day = day;
	}

	@Override
	public void onClick(DialogInterface dialog, int arg1) {
		provider.delete(day);
	}

}
