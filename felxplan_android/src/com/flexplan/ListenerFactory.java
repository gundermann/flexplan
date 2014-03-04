package com.flexplan;

import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;
import com.flexplan.setup.ChangeProvider;
import com.flexplan.setup.OnChangeClickListener;
import com.flexplan.setup.OnDeleteLongClickListener;
import com.flexplan.setup.SaveDiscardProvider;
import com.flexplan.util.DeleteListener;
import com.flexplan.util.DeleteProvider;
import com.flexplan.util.DiscardListener;
import com.flexplan.util.OverwriteListener;
import com.flexplan.util.OverwriteProvider;
import com.flexplan.util.SaveListener;

import android.content.DialogInterface.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListenerFactory {

	public static OnClickListener createOverrideListener(
			OverwriteProvider provider) {
		return new OverwriteListener(provider);
	}

	public static OnClickListener createSaveListener(
			SaveDiscardProvider provider) {
		return new SaveListener(provider);
	}

	public static OnClickListener createDiscardListener(
			SaveDiscardProvider provider) {
		return new DiscardListener(provider);
	}

	public static OnClickListener createBreakDeleteListener(
			DeleteProvider<WorkBreak> provider, WorkBreak workbreak) {
		return new DeleteListener<WorkBreak>(provider, workbreak);
	}

	public static OnClickListener createFlextimeDeleteListener(
			DeleteProvider<FlextimeDay> provider, FlextimeDay flextimeDay) {
		return new DeleteListener<FlextimeDay>(provider, flextimeDay);
	}

	public static OnItemClickListener createOnChangeFlextimeListener(
			ChangeProvider<FlextimeDay> provider) {
		return new OnChangeClickListener<FlextimeDay>(provider);
	}

	public static OnItemClickListener createOnChangeBreakListener(
			ChangeProvider<WorkBreak> provider) {
		return new OnChangeClickListener<WorkBreak>(provider);
	}

	public static OnItemLongClickListener createDeleteFlextimeLongClickListener(
			DeleteProvider<FlextimeDay> provider) {
		return new OnDeleteLongClickListener<FlextimeDay>(provider);
	}
	
	public static OnItemLongClickListener createDeleteBreakLongClickListener(
			DeleteProvider<WorkBreak> provider) {
		return new OnDeleteLongClickListener<WorkBreak>(provider);
	}
}
