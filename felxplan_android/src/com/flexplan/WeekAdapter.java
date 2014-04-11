package com.flexplan;

import com.flexplan.common.util.DateHelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WeekAdapter extends FragmentPagerAdapter {

	private int currentYear;

	public WeekAdapter(FragmentManager supportFragmentManager) {
		super(supportFragmentManager);
		currentYear = DateHelper.getCurrentYear();
	}

	@Override
	public int getCount() {
		return 54;
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 52) {
			return FlextimeOverviewFragment.newInstance(1, currentYear + 1);
		}
		if(position == 53){
			return FlextimeOverviewFragment.newInstance(2, currentYear + 1);
		}
		return FlextimeOverviewFragment.newInstance(position+1, currentYear);
	}

	public void initPrevYear() {
		currentYear--;
	}

	public void initNextYear() {
		currentYear++;
	}

}
