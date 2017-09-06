package com.joe.phonebook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joe.phonebook.application.BaseApplication;
import com.joe.phonebook.db.DBManager;
import com.joe.phonebook.db.ExtensionField;
import com.joe.phonebook.entity.SYS_Department;
import com.joe.phonebook.entity.SYS_OrgUser;
import com.joe.phonebook.entity.SYS_User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员部门关系DAO
 *
 * Created by Think on 2017/1/18.
 */

public class SYS_OrgUserDAO {
    /**数据库表*/
    private static final String TABLE_NAME = "org_user_org";
    /***/
    private static final String ID = "ID";
    /**用户ID*/
    private static final String USERID = "user_id";
    /**集团单位ID*/
    private static final String GROUPCORPID = "group_corp_id";
    /**组织ID*/
    private static final String DEPARTMENTCODE = "org_id";
    /**企业编码*/
    private static final String CORPID = "corp_id";
    /**是否部门负责人*/
    private static final String ISMANAGER = "is_manager";
    /**职务名称*/
    private static final String ORGTITLE = "org_title";
    /**用户职务尊称，例如：李总、张局长、王主任等*/
    private static final String USERTITLENAME = "user_titlename";
    /**办公电话*/
    private static final String OFFICEPHONE = "office_phone";
    /**传真*/
    private static final String FAX = "fax";

    private SQLiteDatabase db;
    private BaseApplication myApp;
    public Context context;

    public SYS_OrgUserDAO(Context context) {
        this.context = context;
        db = DBManager.getInstance(context);
    }
    public SYS_OrgUserDAO(SQLiteDatabase db){
        this.db = db;
    }

    /**
     * 根据人员ID获取部门ID
     * @param mSYS_User
     * @return
     */
    public SYS_OrgUser getSYSOrgUser(SYS_User mSYS_User){
        if (db.isOpen()) {
            Cursor cursor = null;
            try {
                cursor = db.query(TABLE_NAME, null, USERID + "=?",
                        new String[]{mSYS_User.getUserId()}, null, null, null);
                if (cursor.moveToFirst()) {
                    SYS_OrgUser mSYS_OrgUser = new SYS_OrgUser();
                    String userId = cursor.getString(cursor.getColumnIndex(USERID));
                    String departmentCode = cursor.getString(cursor.getColumnIndex(DEPARTMENTCODE));
//					int disOrder = cursor.getInt(cursor.getColumnIndex(DISORDER));
                    mSYS_OrgUser.setUserId(userId);
                    mSYS_OrgUser.setDepartmentCode(departmentCode);
//					mSYS_OrgUser.setDisOrder(disOrder);
                    cursor.close();
                    return mSYS_OrgUser;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(cursor != null){
                    cursor.close();
                }

            }
        }
        return null;
    }


    /**
     *
     * 根据人员ID获取部门ID
     * @param DepartmentCode
     * @param mSYS_Department
     * @return
     * @throws ParseException
     */
    public List<SYS_User> findPartmentIdOrgUser(String DepartmentCode, SYS_Department mSYS_Department) throws ParseException {
        if (db.isOpen()) {
            List<SYS_User> mArrayList = new ArrayList<SYS_User>();
            Cursor cursor = null;
            try {
//					cursor = db.query(TABLE_NAME, null, DEPARTMENTCODE + "=? order by "+ DISORDER +" ASC",
//							new String[] { DepartmentCode }, null, null, null);
                cursor = db.query(TABLE_NAME, null, DEPARTMENTCODE + "=?",
                        new String[] { DepartmentCode }, null, null, null);
                while (cursor.moveToNext()) {
                    SYS_OrgUser mSYS_OrgUser = new SYS_OrgUser();
                    String userId = cursor.getString(cursor.getColumnIndex(USERID));
                    String departmentCode = cursor.getString(cursor.getColumnIndex(DEPARTMENTCODE));
//						int disOrder = cursor.getInt(cursor.getColumnIndex(DISORDER));
                    mSYS_OrgUser.setUserId(userId);
                    mSYS_OrgUser.setDepartmentCode(departmentCode);
//						mSYS_OrgUser.setDisOrder(disOrder);
                    SYS_UserDAO mSYS_UserDAO = new SYS_UserDAO(context);
                    SYS_User mSYS_User = mSYS_UserDAO.findUserIdSYS_Users(userId);
                    if(mSYS_User != null) {
                        mSYS_User.setmSYS_Department(mSYS_Department);
                        mArrayList.add(mSYS_User);
                    }
                }
                cursor.close();
                return mArrayList;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(cursor != null){
                    cursor.close();
                }
            }
        }
        return null;
    }

    /**
     * 向数据表插入新的列
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
