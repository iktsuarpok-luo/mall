package com.cskaoyan.mall.util;

import java.util.Random;

/**
 * @author ethan
 * @date 2019/8/16 22:21
 */
public class RandomUtils {
    /*获取对应数量的随机字符*/
    public static String getRandomString(Integer num){
        String base = "abcdefghigklmnopqrstuvwxyz12346789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <num ; i++) {
            int anInt = random.nextInt(base.length());
            stringBuilder.append(base.charAt(anInt));
        }
        return stringBuilder.toString();
    }
}
