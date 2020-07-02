package com.rul.blog.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String md5(String message){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //message转为字节数组
            byte[] input = message.getBytes();
            //利用md编码字节数组
            byte[] output = md.digest(input);
            return Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
