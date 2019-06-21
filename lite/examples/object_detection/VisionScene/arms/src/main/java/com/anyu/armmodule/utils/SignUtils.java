package com.anyu.armmodule.utils;

import java.util.*;

/**
 * Created by 张宝 on 2018/8/5.
 * # 1、 签名方式  #
 * $uuid = "b9514c52-5363-4364-b73f-a2ec93ae6b34";
 * 签名方式  按照参数首字符排序 组合链接字符串 + uuid
 * 如 请求参数  url="b=v1&a=v2&c=v3&time=2342342342";
 * 排序后   url=a=v2&b=v1&c=v3&time=2342342342
 * 加密字符串 sign= md5（url+uuid ）
 */

public class SignUtils {

    private static final String TAG = "SignUtils";
    private static String uuid = "&key=b9514c52-5363-4364-b73f-a2ec93ae6b34";
    
    public static String getSign(TreeMap<String, String> mapParent) {
        String s = sortMap(mapParent);
        LogUtils.e(TAG, s);
        String md5 = MD5.encoderByMd5(s);
        LogUtils.e(TAG, md5);
        return md5;
    }

    private static String sortMap(Map<String, String> map) {
        String strvalue = "";

        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        // 重写集合的排序方法：按字母顺序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(final Map.Entry<String, String> o1, final Map.Entry<String, String> o2) {
                return (o1.getKey().toString().compareTo(o2.getKey()));
            }
        });

        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, String> mp = list.get(i);
            strvalue += mp.getKey() + "=" + mp.getValue() + "&";
        }

        if (strvalue.endsWith("&")) {
            strvalue = strvalue.substring(0, strvalue.length() - 1);
        }

        strvalue += uuid;


        String toLowerCase = strvalue.toLowerCase();

        return toLowerCase;
    }


    private static String sortMap2(Map<String, Object> map) {
        String strvalue = "";

        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(map.entrySet());

        // 重写集合的排序方法：按字母顺序
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(final Map.Entry<String, Object> o1, final Map.Entry<String, Object> o2) {
                return (o1.getKey().toString().compareTo(o2.getKey()));
            }
        });

        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Object> mp = list.get(i);
            strvalue += mp.getKey() + "=" + mp.getValue() + "&";
        }

        if (strvalue.endsWith("&")) {
            strvalue = strvalue.substring(0, strvalue.length() - 1);
        }

        strvalue += uuid;


        String toLowerCase = strvalue.toLowerCase();

        return toLowerCase;
    }


    public static String getSign2(TreeMap<String, Object> mapParent) {
        String s = sortMap2(mapParent);
        LogUtils.e(TAG, s);
        String md5 = MD5.encoderByMd5(s);
        LogUtils.e(TAG, md5);
        return md5;
    }


    public static String getTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

}
