package com.zero2ipo.framework.safe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @title :数据传输加解密
 * @author: zhengYunFei
 * @data: 2013-07-01
 */
public class SafeUtil {
//    private static final String KEY_PATH = "c:/key.txt";
    
//    public static void main(String[] args) {
//        try {
//            String str = "v;asd;asdf;sdf;asdf;as;df324;翟；地方";
//            System.out.println("加密前：" + str);
//            System.out.println("加密前长度：" + str.length());
////            str = clock(str);
//            System.out.println("加密后：" + str);
//            System.out.println("加密后长度：" + str.length());
//            
////            str = unClock(str);
//            System.out.println("解密后：" + str);
//            System.out.println("解密后长度：" + str.length());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    // 字符串加密
    public static String clock(String keyPath,String str) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 获得密匙数据
        FileInputStream fi = new FileInputStream(new File(keyPath));
        byte rawKeyData[] = new byte[fi.available()];
        fi.read(rawKeyData);
        fi.close();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        // 现在，获取要加密的文件数据
        byte data[] = str.getBytes();
        // 正式执行加密操作
        byte encryptedData[] = cipher.doFinal(data);
        // 用加密后的数据覆盖原文件
        return parseByte2HexStr(encryptedData);
    }
    
    /* 
     * 字符串解密
     * */
    public static String unClock(String keyPath,String str) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 获得密匙数据
        FileInputStream fi = new FileInputStream(new File(keyPath));
        byte rawKeyData[] = new byte[fi.available()];// = new byte[5];
        fi.read(rawKeyData);
        fi.close();
        
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        
        // 正式执行解密操作
        byte decryptedData[] = cipher.doFinal(parseHexStr2Byte(str));
        return new String(decryptedData);
    }
    
    /**
     * 文件加密　
     */
    public static void clock(String keyFilePath,String filePath,String outFilePath) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 获得密匙数据
        FileInputStream fi = new FileInputStream(new File(keyFilePath));
        byte rawKeyData[] = new byte[fi.available()];
        fi.read(rawKeyData);
        fi.close();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        
        // 现在，获取要加密的文件数据
        FileInputStream fi2 = new FileInputStream(new File(filePath));
        byte data[] = new byte[fi2.available()];
        fi2.read(data);
        fi2.close();
        // 正式执行加密操作
        byte encryptedData[] = cipher.doFinal(data);
        // 用加密后的数据覆盖原文件
        FileOutputStream fo = new FileOutputStream(new File(outFilePath));
        fo.write(encryptedData);
        fo.close();
    }
    
    /**
     * 文件解密　
     */
    public static void unClock(String keyFilePath,String filePath,String outFilePath) throws Exception {
     // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 获得密匙数据
        FileInputStream fi = new FileInputStream(new File(keyFilePath));
        byte rawKeyData[] = new byte[fi.available()];// = new byte[5];
        fi.read(rawKeyData);
        fi.close();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        // 现在，获取数据并解密
        FileInputStream fi2 = new FileInputStream(new File(filePath));
        byte encryptedData[] = new byte[fi2.available()];
        fi2.read(encryptedData);
        fi2.close();
        // 正式执行解密操作
        byte decryptedData[] = cipher.doFinal(encryptedData);
        // 这时把数据还原成原有的类文件
        FileOutputStream fo = new FileOutputStream(new File(outFilePath));
        fo.write(decryptedData);
    }
    /**
     * 将二进制转换成16进制 　
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    /**
     * 将16进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    /***
     * MD5算法加密
     * **/
    public static String md5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
               'a', 'b', 'c', 'd', 'e', 'f' };//16进制
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");//消息摘要 一个消息摘要就是一个数据块的数字指纹
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
            e.printStackTrace();
        }
        return null;
     }
    
}
