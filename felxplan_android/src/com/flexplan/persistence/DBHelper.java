package com.flexplan.persistence;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.flexplan.common.Factory;
import com.flexplan.common.business.FlextimeDay;
import com.flexplan.common.business.WorkBreak;

public class DBHelper extends SQLiteOpenHelper implements FlextimeDB {

	private static final int VERSION = 1;

	private static final String NAME = "flextime.db";

	private static final String TAG = DBHelper.class.getSimpleName();

	public DBHelper(com.flexplan.FlexplanApplication app) {
		super(app, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(FlextimeTable.createTable());
		db.execSQL(BreakTimeTable.createTable());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL(FlextimeTable.dropTable());
		db.execSQL(BreakTimeTable.dropTable());
		onCreate(db);
	}

	@Override
	public void insertFlextimeDay(FlextimeDay flextimeDay) {
		getWritableDatabase().insert(FlextimeTable.TABLE_NAME, null,
				FlextimeTable.getContentValues(flextimeDay));
	}

	@Override
	public List<FlextimeDay> getAllFlextimeDays() {
		Cursor flextimeCursor = getReadableDatabase().query(
				FlextimeTable.TABLE_NAME, FlextimeTable.selectAll(), null,
				null, null, null, null);
		return loadFlextimeDays(flextimeCursor);
	}

	private List<FlextimeDay> loadFlextimeDays(Cursor flextimeCursor) {
		List<FlextimeDay> flextimeDays = new ArrayList<FlextimeDay>();
		flextimeCursor.moveToFirst();
		while (flextimeCursor.moveToNext()) {
			long date = flextimeCursor.getLong(0);
			long timeFrom = flextimeCursor.getLong(1);
			long timeTo = flextimeCursor.getLong(2);
			flextimeDays.add(Factory.getInstance().createFlextimeDay(date,
					timeFrom, timeTo, getWorkBreaksForFlextimeDay(date)));
		}
		flextimeCursor.close();
		return flextimeDays;
	}

	private List<WorkBreak> getWorkBreaksForFlextimeDay(long date) {
		List<WorkBreak> workBreaks = new ArrayList<WorkBreak>();

		SQLiteDatabase db = super.getReadableDatabase();
		Cursor breakCursor = db.query(BreakTimeTable.TABLE_NAME,
				BreakTimeTable.selectTime(), BreakTimeTable.getWhereDate(date),
				null, null, null, null, null);
		while (breakCursor.moveToNext()) {
			long timeFrom = breakCursor.getLong(0);
			long timeTo = breakCursor.getLong(1);
			workBreaks.add(Factory.getInstance().createWorkBreak(timeFrom, timeTo));
		}

		breakCursor.close();
		db.close();
		return workBreaks;
	}

	@Override
	public SQLiteDatabase getReadableDatabase() {
		if (super.getReadableDatabase().isOpen()) {
			super.getReadableDatabase().close();
		}
		return super.getReadableDatabase();
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		if (super.getWritableDatabase().isOpen()) {
			super.getWritableDatabase().close();
		}
		return super.getWritableDatabase();
	}

	public void insertWorkBreak(WorkBreak workBreak, long date) {
		getReadableDatabase().insert(BreakTimeTable.TABLE_NAME, null,
				BreakTimeTable.getContentValues(workBreak, date));
	}

	public List<FlextimeDay> getCurrentWeekDays(int weekOfYear, int year) {
		//TODO read from DB
		List<FlextimeDay> week = new ArrayList<FlextimeDay>();
		for(int day = 1; day < 8; day++){
			week.add(Factory.getInstance().createFreeDayOfWeek(day, weekOfYear, year));
		}
		return week;
	}
}
