package com.jiejue.netty.util;

import java.security.MessageDigest;

/**
 * @author jiakuantu
 * @descriprtion checkSum生成器
 * @date 2016年9月14日
 * @QQ 44822331
 */
public class CheckSumGenerator {

    public static String genCheckSum(String userName, String password, long timeStramp) {
        String str = userName + "_" + password + "_" + timeStramp;
        return md5(str);
    }


    public static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(md5("12xsxscece"));
    }
}
