package com.joe.phonebook.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.joe.phonebook.api.PhoneInit;


/**
 * Created by htrf-pc on 2016/6/27.
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static void cellPhone(String pNumber, TextView mPhoneNumber){
        int start = Integer.parseInt(PhoneInit.getInstance().getCellPhoneLength()[0]);
        int end = Integer.parseInt(PhoneInit.getInstance().getCellPhoneLength()[1]);
        if(!TextUtils.isEmpty(pNumber) && pNumber.length() > 6 && start >= 0 && end >= 0){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= start && i <= end) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            mPhoneNumber.setText(sb.toString());
        }
    }

    public static void cellPhone(String startTitle, String pNumber, TextView mPhoneNumber){
        int start = Integer.parseInt(PhoneInit.getInstance().getCellPhoneLength()[0]);
        int end = Integer.parseInt(PhoneInit.getInstance().getCellPhoneLength()[1]);
        if(!TextUtils.isEmpty(pNumber) && pNumber.length() > 6 && start >= 0 && end >= 0 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= start && i <= end) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            mPhoneNumber.setText(startTitle+""+sb.toString());
        }
    }

    public static String cellPhone(String pNumber){
        int start = Integer.parseInt(PhoneInit.getInstance().getCellPhoneLength()[0]);
        int end = Integer.parseInt(PhoneInit.getInstance().getCellPhoneLength()[1]);
        if(!TextUtils.isEmpty(pNumber) && pNumber.length() > 6  && start >= 0 && end >= 0){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= start && i <= end) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

           return sb.toString();
        }
        return pNumber;
    }
}
