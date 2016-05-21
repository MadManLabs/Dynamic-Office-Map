package com.endava.fsociety.dynamicofficemap.server.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fstancu on 5/21/2016.
 */

public class MD5Util {

    public static String hex(byte[] array) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return stringBuffer.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

}
