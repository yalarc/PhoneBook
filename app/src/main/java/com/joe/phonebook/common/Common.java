package com.joe.phonebook.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by Think on 2017/1/17.
 */

public class Common {
    /**SD卡缓存目录*/
    public static final String EXT_ROOT_FOLDER =  Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DEFAULT_FOLDER_NAME = "joe";
    public static final String DEFAULT_FOLDER = EXT_ROOT_FOLDER+ File.separator+DEFAULT_FOLDER_NAME;
    public static final String PHONE_BOOK = DEFAULT_FOLDER+File.separator+"phoneFile";


}
