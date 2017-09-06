package com.joe.phonebook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joe.phonebook.db.DBManager;
import com.joe.phonebook.db.ExtensionField;
import com.joe.phonebook.entity.TD_User;
import com.joe.phonebook.entity.TD_UserFieldSecret;

import java.util.ArrayList;

/**
 * 定义某个保密字段哪些人能看 DAO
 *
 * Created by Think on 2017/1/18.
 */

public class TD_UserFieldSecretDAO {
    private static final String TABLE_NAME = "TD_UserFieldSecret";
    private SQLiteDatabase db;
    private static final String USERID = "UserId";
    private static final String FIELDNAME = "FieldName";
    private Context context;

    public TD_UserFieldSecretDAO(Context context) {
        db = DBManager.getInstance(context);
        this.context = context;
    }

    /**
     * 获取保密的用户信息
     * @param userIds
     * @return
     */
    public TD_UserFieldSecret getUserFieldSecret(String userIds) {
        if (db.isOpen()) {
            Cursor cursor = db.query(TABLE_NAME, null, USERID + "=?",
                    new String[] { userIds }, null, null, null);
            TD_UserFieldSecret mTD_UserFieldSecret = new TD_UserFieldSecret();
            if (cursor.moveToNext()) {
                String userId = cursor.getString(cursor.getColumnIndex(USERID));
                String fieldName = cursor.getString(cursor
                        .getColumnIndex(FIELDNAME));
                TD_UserDAO mTD_UserDAO = new TD_UserDAO(context);
                TD_User mTD_User = mTD_UserDAO.getTD_User(fieldName);
                mTD_UserFieldSecret.setUserId(userId);
                mTD_UserFieldSecret.setFieldName(fieldName);
                mTD_UserFieldSecret.setmTD_User(mTD_User);
                cursor.close();
                return mTD_UserFieldSecret;
            }

        }
        return null;
    }

    /**
     * 向数据库中增加列
     *
     * @param culumn
     * @param culumnValueArray
     */
    public void insert(String[] culumn,ArrayList<String[]> culumnValueArray){
        for (String c : culumn) {
            if (!ExtensionField.checkColumnExist(db, c, TABLE_NAME)) {
                String sql = "ALTER TABLE " + TABLE_NAME + "  ADD COLUMN " + c
                        + " VARCHAR(200)";
                db.execSQL(sql);
            }
        }
        for(String[] culumnValue :culumnValueArray){
            ContentValues values = new ContentValues();
            for(int i = 0 ; i < culumn.length ; i++){
                values.put(culumn[i], culumnValue[i]);
            }
            db.replace(TABLE_NAME, null, values);
        }
    }
}
