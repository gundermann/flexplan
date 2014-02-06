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
import com.flexplan.common.util.DateHelper;

public class DBHelper extends SQLiteOpenHelper implements FlextimeDB {

	private static final int VERSION = 2;

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
	public void insertOrUpdateFlextimeDay(FlextimeDay flextimeDay) {
		getWritableDatabase().insertOrThrow(FlextimeTable.TABLE_NAME, null,
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
		if (flextimeCursor.moveToFirst()) {
			do {
				String date = flextimeCursor.getString(0);
				long timeFrom = flextimeCursor.getLong(1);
				long timeTo = flextimeCursor.getLong(2);
				flextimeDays.add(Factory.getInstance().createFlextimeDay(date,
						timeFrom, timeTo, getWorkBreaksForFlextimeDay(date)));
			} while (flextimeCursor.moveToNext());
		}
		flextimeCursor.close();
		if (flextimeDays.isEmpty()) {
			flextimeDays.add(null);
		}
		return flextimeDays;
	}

	private List<WorkBreak> getWorkBreaksForFlextimeDay(String date) {
		List<WorkBreak> workBreaks = new ArrayList<WorkBreak>();

		SQLiteDatabase db = super.getReadableDatabase();
		Cursor breakCursor = db.query(BreakTimeTable.TABLE_NAME,
				BreakTimeTable.selectTime(),
				getWhere(BreakTimeTable.DATE, date), null, null, null, null,
				null);
		while (breakCursor.moveToNext()) {
			long timeFrom = breakCursor.getLong(0);
			long timeTo = breakCursor.getLong(1);
			workBreaks.add(Factory.getInstance().createWorkBreak(timeFrom,
					timeTo));
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

	public List<FlextimeDay> getCurrentWeekDays(int weekOfYear, int year) {
		List<FlextimeDay> week = new ArrayList<FlextimeDay>();
		for (int i = 2; i <= 7; i++) {
			week.add(findFlextimeDay(i, weekOfYear, year));
		}
		// ist der Sonntag der deutschen Woche
		week.add(findFlextimeDay(1, weekOfYear, year));
		return week;
	}

	private FlextimeDay findFlextimeDay(int i, int weekOfYear, int year) {
		String date = DateHelper.getDateByWeekOfYearAsString(i, weekOfYear,
				year);
		Cursor c = getReadableDatabase().query(FlextimeTable.TABLE_NAME,
				FlextimeTable.selectAll(), getWhere(FlextimeTable.DATE, date),
				null, null, null, null);
		FlextimeDay day = loadFlextimeDays(c).get(0);
		c.close();
		if (day == null)
			day = Factory.getInstance()
					.createFreeDayOfWeek(i, weekOfYear, year);
		return day;
	}

	protected String getWhere(String column, Object value) {
		String[] columns = new String[] { column };
		String[] values = new String[] { value.toString() };
		return getWhere(columns, values);
	}

	protected String getWhere(String[] columns, Object[] value) {
		StringBuilder sb = new StringBuilder();
		int size = columns.length;
		for (int index = 0; index < size; index++) {
			sb.append(columns[index]).append("='").append(value[index])
					.append("'");
			if (index + 1 < size) {
				sb.append(" AND ");
			}
		}
		return sb.toString();
	}

	@Override
	public void insertWorkBreaks(FlextimeDay currentFlextimeDay) {
		cleanWorkBreaks(currentFlextimeDay);
		for (WorkBreak workBreak : currentFlextimeDay.getWorkBreaks()) {
			getWritableDatabase().insert(
					BreakTimeTable.TABLE_NAME,
					null,
					BreakTimeTable.getContentValues(workBreak,
							currentFlextimeDay.getDate()));
		}
	}

	private void cleanWorkBreaks(FlextimeDay flextimeDay) {
		getWritableDatabase().delete(BreakTimeTable.TABLE_NAME,
				getWhere(BreakTimeTable.DATE, flextimeDay.getDate()), null);
	}

	@Override
	public void delete(FlextimeDay flextimeDay) {
		getWritableDatabase().delete(FlextimeTable.TABLE_NAME,
				getWhere(FlextimeTable.DATE, flextimeDay.getDate()), null);
	}

}
