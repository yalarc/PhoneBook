package com.joe.phonebook.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 对数据库进行扩展操作
 * @author Tony
 *
 */
public class ExtensionField {
	/**
	 * 
	 * @param db
	 * @param columnName 添加的列名字
	 * @param tableName  对应表名称
	 * @return
	 */
	public static boolean checkColumnExist(SQLiteDatabase db, String columnName, String tableName) {
		boolean result = false;
		Cursor cursor = null;
		try {
			// 查询一行
			cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0",
					null);
			result = cursor != null && cursor.getColumnIndex(columnName) != -1;
		} catch (Exception e) {

		} finally {
			if (null != cursor && !cursor.isClosed()) {
				cursor.close();
			}
		}

		return result;
	}
}
