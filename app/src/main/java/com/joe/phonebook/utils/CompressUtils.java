package com.joe.phonebook.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * zip压缩文件操作工具类 支持密码 依赖zip4j开源项目
 * @author  joe
 * Created by Think on 2017/1/17.
 */

public class CompressUtils {

    /**
     * @param zip 指定的压缩文件
     * @param dest 解压目录
     * @param passWord 解压密码
     * @return 解压后的文件数组
     * @throws ZipException 压缩文件有损坏，解压失败
     */
    public static File[] upZip(String zip, String dest,String passWord)throws ZipException{
        File zipFile = new File(zip);
        return unZip(zipFile,dest,passWord);
    }

    /**
     * @param zip 指定的压缩文件
     * @param password 解压密码
     * @return 解压后的文件数组
     * @throws ZipException 压缩文件有损坏，解压失败
     */
    public static File[] upZip(String zip,String password)throws ZipException{
        File zipFile = new File(zip);
        File parentDir = zipFile.getParentFile();
        return unZip(zipFile,parentDir.getAbsolutePath(),password);
    }


    /**
     * @param zipFile 指定的ZIP压缩文件
     * @param dest  解压目录
     * @param password 解压密码
     * @return 解压后的文件数组
     * @throws ZipException 压缩文件文件有损坏，解压失败
     */
    public static File[] unZip(File zipFile,String dest,String password)throws ZipException{
        ZipFile zipFile1 = new ZipFile(zipFile);
        zipFile1.setFileNameCharset("UTF-8");
        if(!zipFile1.isValidZipFile()){
            throw new ZipException("压缩文件有损坏或解压失败！");
        }
        File destDir = new File(dest);
        if(destDir.isDirectory() && !destDir.exists()){
            destDir.mkdir();
        }
        if(zipFile1.isEncrypted()){
            zipFile1.setPassword(password.toCharArray());

        }
        zipFile1.extractAll(dest);
        List<FileHeader> headerList = zipFile1.getFileHeaders();
        List<File> extraFileList = new ArrayList<File>();
        for (FileHeader fileHeader: headerList
             ) {
            if(!fileHeader.isDirectory()){
                extraFileList.add(new File(destDir,fileHeader.getFileName()));
            }

        }
        File[] extraFiles = new File[extraFileList.size()];
        extraFileList.toArray(extraFiles);
        if(zipFile.exists()){
            zipFile.delete();
        }
        return extraFiles;

    }


}
