package com.travis.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5Util {
    private static final char DIGITS[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 获取文件的MD5码
     * @param absPath 文件路径
     * @return 文件的MD5码
     */
    public final static String getFileMD5(String absPath) {
        try {
            File file = new File(absPath);
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            FileChannel filechannel = fis.getChannel();
            MappedByteBuffer mbb = filechannel
                    .map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            mdTemp.update(mbb);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = DIGITS[byte0 & 0xf];
            }
            fis.close();
            return new String(str);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定字符串的MD5码
     * @param s 字符串
     * @return 字符串的MD5码
     */
    public final static String getMD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
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
            return null;
        }
    }
}
