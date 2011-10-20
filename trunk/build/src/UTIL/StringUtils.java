/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UTIL;

import java.util.*;
import java.security.MessageDigest;
import java.text.*;

/**
 *
 * @author Mr Long
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String random(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    public static String md5(String input) throws Exception{
        try {
            byte[] md5hash = new byte[32];
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.update(input.getBytes("iso-8859-1"), 0, input.length());
            md5hash = md5.digest();

            return convertToHex(md5hash);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String convertToHex(String data) {
        return StringUtils.convertToHex(data.getBytes());
    }

    public static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String ConverCurrency(double money) {
         NumberFormat Nd = NumberFormat.getCurrencyInstance(Locale.US);
         return Nd.format(money/19600);
    }

     public static String ConverCurrency(String str, int index) {
         String[] ss = str.split(" ");
         return ss[index];
    }

     public static String implode(String glue, String[]array) {
        if(array.length < 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < array.length; i++) {
            sb.append(glue).append(array[i]);
        }

        return sb.substring(glue.length()).toString();
    }
     
    public static float convertToFloat(String data) {
        return Float.parseFloat(data);
    }
     
}
