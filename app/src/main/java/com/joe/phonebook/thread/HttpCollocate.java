package com.joe.phonebook.thread;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网路请求配置
 *
 * @author joe
 *         Created by Think on 2017/1/17.
 */

public class HttpCollocate {
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    public static final int GET = 0x1001;
    public static final int POST = 0x1002;
    public static final String GETSYNCDATA_METHOD = "GetMobileData/GetSyncDataSWJ"; //mysql数据库路径
    public static final String REQUSET_HTTP_HOST = "http://oaapp.cjh.com.cn:8081/swj/api/"; //环境路径
    public static final String REQUSET_PHONE_BOOK = REQUSET_HTTP_HOST + GETSYNCDATA_METHOD;

    public static String requestByHttpPost(Object entity, String url) throws Exception {

        //新建HttpPost对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, HttpCollocate.CONTENT_TYPE_TEXT_JSON);
        //Post参数
        StringEntity stringEntity = new StringEntity(JSON.toJSON(entity).toString(), "UTF-8");
        stringEntity.setContentType(HttpCollocate.CONTENT_TYPE_APPLICATION_JSON);
        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, HttpCollocate.CONTENT_TYPE_TEXT_JSON));
        //设置参数实体
        httpPost.setEntity(stringEntity);

        //获取httpClient对象 Api23已经不提供，需要引入第三方包
        HttpClient httpClient = new DefaultHttpClient();
        //获取HttpResponse实例
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int code = httpResponse.getStatusLine().getStatusCode();
        //判断是否可以请求成功
        if (code == HttpStatus.SC_OK) {
            //获取返回的数据
            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return result;
        } else {
            throw new Exception("http request " + code + "is not 200");
        }


    }

    public static String requestGet(String downPath){
        {
            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(downPath);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setRequestMethod("GET");
                int code = 0;
                if ((code = httpURLConnection.getResponseCode()) == HttpStatus.SC_OK) {
                    String jsonString = readStream(httpURLConnection.getInputStream());
                    return jsonString;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
        }
        return "";

    }

    private static String readStream(InputStream inputStream){
        InputStreamReader inputStreamReader;
        String result = "";
        try {
            String line = "";
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine())!= null){
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
