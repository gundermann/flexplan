package com.flexplan;

public interface DateFieldProvider {

	void updateDateField(int dayOfMonth, int month, int year);
	
	void updateTimeFields();
}
