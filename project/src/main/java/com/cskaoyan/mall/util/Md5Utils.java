package com.cskaoyan.mall.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    public static String getMd5(String content){
        String result =  Md5Again(content)+"$10$20";
        return Md5Again(result)+"$10$20";

    }
    public static String Md5Again(String content){
        try {
            content = content+"$10$20";
            MessageDigest md5 = MessageDigest.getInstance("md5");
            StringBuffer sb = new StringBuffer();
            byte[] bytes = content.getBytes();
            byte[] digits = md5.digest(bytes);
            for (byte digit : digits) {
                int i = digit & 0xfc;
                String s = Integer.toHexString(i);
                if (s.length() == 1) {
                    sb.append(0);
                }
                sb.append(s);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
