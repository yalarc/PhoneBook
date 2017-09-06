package com.joe.phonebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joe.phonebook.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Think on 2017/2/6.
 * 获取SQLiteDatabase
 */

public class DBManager {
    private static final String TAG = "DBManager";
    public static SQLiteDatabase database;
    /**
     * 数据库文件名称
     */
    public static final String DATABASE_FILENAME = "htmitech_mysql";
    public static String databaseSdcardPath ="/sdcard/fanxin";
    public static SQLiteDatabase openDatabase(Context context){
        try {
            databaseSdcardPath = context.getDatabasePath("htmitech").getPath();
            String databaseFilename = databaseSdcardPath + "/" + DATABASE_FILENAME+".db";
            Log.d(TAG, "openDatabase: databaseFilename");
            File dir = new File(databaseSdcardPath);
            if(!dir.exists()){
                dir.mkdir();
            }
            if(!(new File (databaseFilename)).exists()){
                InputStream is = context.getResources().openRawResource(R.raw.htmitech_mysql);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer))>0){
                    fos.write(buffer,0,count);
                }
                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(databaseFilename,null);
            return database;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "openDatabase: e");
        }
        return  null;

    }
    public static SQLiteDatabase getInstance(Context context){
        if(database == null){
            database = openDatabase(context);
        }
        return database;
    }
    public static void repelct(Context context){
        database = null;
    }
}
