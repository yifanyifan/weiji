package com.fujiang.weiji.controller.take;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJson {
    public JSONObject getHttpJson(String url, int comefrom) throws Exception {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //设置HTTP的请求头(参考材料准备截图的请求头信息)
            connection.setRequestProperty("Accept", "*/*");//设置浏览器可以接收的媒体类型
            connection.setRequestProperty("Connection", "keep-alive");//网页打开，建立连接
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3928.4 Safari/537.36");//客户端使用的操作系统和浏览器的名称和版本
            // 建立实际的连接
            connection.connect();
            //请求成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String jsonString = baos.toString();
                baos.close();
                is.close();
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                JSONObject jsonArray = getJsonString(jsonString, comefrom);
                return jsonArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJsonString(String str, int comefrom) throws Exception{
        JSONObject jo = null;
        if(comefrom==1){
            return null;//new JSONObject(str);
        }else if(comefrom==2){
            int indexStart = 0;
            //字符处理
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('){
                    indexStart = i;
                    break;
                }
            }
            String strNew = "";
            //分割字符串
            for(int i=indexStart+1;i<str.length()-1;i++){
                strNew += str.charAt(i);
            }
            return null;//new JSONObject(strNew);
        }
        return jo;
    }

}

