package com.yuan.demo.utils;

import java.util.Random;

public class StringUtil {
    private static String ENGLISH_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomStr(int len) {
        if (len <= 0){
            throw new IllegalArgumentException("参数异常 | "+len);
        }
        char[] ret = new char[len];
        char[] chars = ENGLISH_STRING.toCharArray();
        Random rand = new Random();
        for (int i = 0; i < len; i++){
            ret[i] = chars[rand.nextInt(chars.length)];
        }
        return new String(ret);
    }

    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static void main(String[] args) {
        System.out.println(randomStr(10));
    }
}
