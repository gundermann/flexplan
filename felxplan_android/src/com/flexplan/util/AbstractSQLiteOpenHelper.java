package com.flexplan.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import com.flexplan.common.util.Column;
import com.flexplan.common.util.NoAnnotationFoundException;
import com.flexplan.common.util.Table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class AbstractSQLiteOpenHelper extends SQLiteOpenHelper {

	List<Object> tables;

	public AbstractSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version, List<Object> tables) {
		super(context, name, factory, version);
		this.tables = tables;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (Object table : tables) {
			String createSQL;
			try {
				createSQL = resolveCreateSQL(table);
				db.execSQL(createSQL);
			} catch (NoAnnotationFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private String resolveCreateSQL(Object table) throws NoAnnotationFoundException{
		StringBuilder sb = new StringBuilder();
		sb.append("create table ").append(resolveTableName(table)).append(" (");
		Field[] fArray = table.getClass().getFields();
		int counter = fArray.length;
		for(Field f : fArray){
			Annotation[] aArray = f.getAnnotations();
			sb.append(resolveConstraintForColumn(aArray));
			if((counter--) >= 0){
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}

	private String resolveConstraintForColumn(Annotation[] aArray) {
		StringBuilder sb = new StringBuilder();
		for(Annotation a : aArray){
			if(a instanceof Column){
				sb.append(((Column) a).Type()).append(" ");
				if(((Column) a).ID()){
					sb.append("primary key ");
				}
				break;
			}
		}
		return sb.toString();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (Object table : tables) {
			String tablename;
			try {
				tablename = resolveTableName(table);
				db.execSQL("DROP TABLE IF EXISTS " + tablename);
			} catch (NoAnnotationFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private String resolveTableName(Object table)
			throws NoAnnotationFoundException {
		String tablename;
		for (Annotation a : table.getClass().getAnnotations()) {
			if (a instanceof Table) {
				tablename = ((Table) a).Name();
				if (tablename == null) {
					throw new NoAnnotationFoundException("@Table");
				}
				else{
					return tablename;
				}
			}
		}
		throw new NoAnnotationFoundException("@Table");
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
}
