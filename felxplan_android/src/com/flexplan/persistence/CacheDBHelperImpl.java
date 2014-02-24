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

public class CacheDBHelperImpl extends SQLiteOpenHelper implements
		CacheDBHelper {

	private static final int VERSION = 2;

	private static final String NAME = "cache.db";

	private static final String TAG = CacheDBHelperImpl.class.getSimpleName();

	public CacheDBHelperImpl(com.flexplan.FlexplanApplication app) {
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
		if (isEmpty()) {
			getReadableDatabase().insert(FlextimeTable.TABLE_NAME, null,
					FlextimeTable.getContentValues(flextimeDay));
		} else {
			getReadableDatabase().update(FlextimeTable.TABLE_NAME,
					FlextimeTable.getContentValues(flextimeDay),
					getWhere(FlextimeTable.DATE, flextimeDay.getDate()), null);
		}
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
		cleanupBreaks();
		for (WorkBreak workBreak : currentFlextimeDay.getWorkBreaks()) {
			getWritableDatabase().insertOrThrow(BreakTimeTable.TABLE_NAME, BreakTimeTable.ID,
					BreakTimeTable.getContentValues(workBreak,
							currentFlextimeDay.getDate()));
		}
	}

	private void cleanupBreaks() {
		getWritableDatabase().delete(BreakTimeTable.TABLE_NAME, null, null);		
	}

	@Override
	public long getStartTimeOfDay(String newDate) {
		Cursor c = getReadableDatabase().query(FlextimeTable.TABLE_NAME,
				FlextimeTable.selectTimeFrom(),
				getWhere(FlextimeTable.DATE, newDate), null, null, null, null);
		c.moveToFirst();
		long result = c.getLong(0);
		c.close();
		return result;
	}

	@Override
	public long getEndTimeOfDay(String newDate) {
		Cursor c = getReadableDatabase().query(FlextimeTable.TABLE_NAME,
				FlextimeTable.selectTimeTo(),
				getWhere(FlextimeTable.DATE, newDate), null, null, null, null);
		c.moveToFirst();
		long result = c.getLong(0);
		c.close();
		return result;
	}

	@Override
	public boolean isEmpty() {
		Cursor c = getReadableDatabase().query(FlextimeTable.TABLE_NAME,
				FlextimeTable.selectAll(), null, null, null, null, null);
		if (c.getCount() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public String getCachedDate() {
		Cursor c = getReadableDatabase().query(FlextimeTable.TABLE_NAME,
				FlextimeTable.selectAll(), null, null, null, null, null);
		c.moveToFirst();
		return c.getString(0);
	}

	@Override
	public void cleanup() {
		getWritableDatabase().delete(FlextimeTable.TABLE_NAME, null, null);
		cleanupBreaks();
	}

	@Override
	public FlextimeDay getCachedFlextimeDay() {
		Cursor c = getReadableDatabase().query(FlextimeTable.TABLE_NAME,
				FlextimeTable.selectAll(), null, null, null, null, null);
		c.moveToFirst();
		String date = c.getString(0);
		long timeFrom = c.getLong(1);
		long timeTo = c.getLong(2);
		c.close();
		return Factory.getInstance().createFlextimeDay(date, timeFrom, timeTo,
				getWorkBreaksForFlextimeDay(date));
	}

	public List<WorkBreak> getWorkBreaksForFlextimeDay(String date) {
		List<WorkBreak> workBreaks = new ArrayList<WorkBreak>();
		Cursor c = getReadableDatabase().query(BreakTimeTable.TABLE_NAME,
				BreakTimeTable.selectTime(),
				getWhere(BreakTimeTable.DATE, date), null, null, null, null,
				null);
		while (c.moveToNext()) {
			long timeFrom = c.getLong(0);
			long timeTo = c.getLong(1);
			workBreaks.add(Factory.getInstance().createWorkBreak(timeFrom,
					timeTo));
		}
		c.close();
		return workBreaks;
	}

}
