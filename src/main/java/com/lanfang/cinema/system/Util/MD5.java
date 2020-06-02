package com.lanfang.cinema.system.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String code(String s){
        try {
            MessageDigest md =MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for(int o =0; o<byteDigest.length;o++){
                i = byteDigest[o];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){

    }
}
