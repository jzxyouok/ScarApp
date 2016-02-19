package com.zero2ipo.framework.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;


/**
 * <p>
 * 封装同RSA非对称加密算法有关的方法，可用于数字签名，RSA加密解密
 * </p>
 * 
 * @Copyright:WDSsoft
 */

public class RSASTool {
    public RSASTool() {
    }
    
    private static final String _GY = "30819f300d06092a864886f70d010101050003818d0030818902818100be2e85137cf4757887f32f1b83b833c8aaead172a384b1b2e2ff5677cd16544222bd195932ace919bfb0394f4d4c51c7d7561f66d170a2046a8a831870cf8a1e845ac3b6a26d2fd8139695fa761a3973f53b691547b56a9ecc900f437ec0661ebad2d8e5b9ba00cfd987495c5b4dc9a7903ece0b62ffac1331843f7d569434e90203010001";
    private static final String _SY = "30819f300d06092a864886f70d010101050003818d0030818902818100be2e85137cf4757887f32f1b83b833c8aaead172a384b1b2e2ff5677cd16544222bd195932ace919bfb0394f4d4c51c7d7561f66d170a2046a8a831870cf8a1e845ac3b6a26d2fd8139695fa761a3973f53b691547b56a9ecc900f437ec0661ebad2d8e5b9ba00cfd987495c5b4dc9a7903ece0b62ffac1331843f7d569434e90203010001";
    
    /**
     * 使用私钥加密数据 用一个已打包成byte[]形式的私钥加密数据，即数字签名
     * 
     * @param keyInByte
     *            打包成byte[]的私钥
     * @param source
     *            要签名的数据，一般应是数字摘要
     * @return 签名 byte[]
     */
    public static byte[] sign(byte[] keyInByte, byte[] source) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(keyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initSign(privKey);
            sig.update(source);
            return sig.sign();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 验证数字签名
     * 
     * @param keyInByte
     *            打包成byte[]形式的公钥
     * @param source
     *            原文的数字摘要
     * @param sign
     *            签名（对原文的数字摘要的签名）
     * @return 是否证实 boolean
     */
    public static boolean verify(byte[] keyInByte, byte[] source, byte[] sign) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            Signature sig = Signature.getInstance("SHA1withRSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(keyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            sig.initVerify(pubKey);
            sig.update(source);
            return sig.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 建立新的密钥对，返回打包的byte[]形式私钥和公钥
     * 
     * @return 
     *         包含打包成byte[]形式的私钥和公钥的object[],其中，object[0]为私钥byte[],object[1]为公钥byte
     *         []
     */
    public static Object[] giveRSAKeyPairInByte() {
        KeyPair newKeyPair = creatmyKey();
        if (newKeyPair == null)
            return null;
        Object[] re = new Object[2];
        if (newKeyPair != null) {
            PrivateKey priv = newKeyPair.getPrivate();
            byte[] b_priv = priv.getEncoded();
            PublicKey pub = newKeyPair.getPublic();
            byte[] b_pub = pub.getEncoded();
            re[0] = b_priv;
            re[1] = b_pub;
            return re;
        }
        return null;
    }
    
    /**
     * 新建密钥对
     * 
     * @return KeyPair对象
     */
    public static KeyPair creatmyKey() {
        KeyPair myPair;
        long mySeed;
        mySeed = System.currentTimeMillis();
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            random.setSeed(mySeed);
            keyGen.initialize(1024, random);
            myPair = keyGen.generateKeyPair();
        } catch (Exception e1) {
            return null;
        }
        return myPair;
    }
    
    /**
     * 使用RSA公钥加密数据
     * 
     * @param pubKeyInByte
     *            打包的byte[]形式公钥
     * @param data
     *            要加密的数据
     * @return 加密数据
     */
    public static byte[] encryptByRSA(byte[] pubKeyInByte, byte[] data) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(pubKeyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 用RSA私钥解密
     * 
     * @param privKeyInByte
     *            私钥打包成byte[]形式
     * @param data
     *            要解密的数据
     * @return 解密数据
     */
    public static byte[] decryptByRSA(byte[] privKeyInByte, byte[] data) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(
                    privKeyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
        
    }
    
    /**
     * 客户端调用该方法加密 返回 [0]摘要 [1]数字签名
     * **/
    public static String[] jiami(String pubkey, String data) {
        // 读取公钥信息
        byte[] gy = StringUtil.hex2byte(pubkey);
        
        // 获得摘要
        byte[] zhaiyao = Digest.MdigestMD5(data);
        
        // 使用公钥对摘要进行加密 获得密文 即数字签名 长度256
        byte[] sign = encryptByRSA((byte[]) gy, zhaiyao);
        
        // 二进制转字符串
        return new String[] { StringUtil.byte2hex(zhaiyao),
                StringUtil.byte2hex(sign) };
    }
    
    /**
     * 服务端调用该方法验签
     * prikey：私钥
     * zhaiyao：摘要
     * sign：签名
     * **/
    public static boolean yanqian(String prikey, String zhaiyao, String sign) {
        byte[] syb = StringUtil.hex2byte(prikey);
        byte[] zhaiyaob = StringUtil.hex2byte(zhaiyao);
        byte[] signb = StringUtil.hex2byte(sign);
        
        // 服务端验签
        byte[] newSource1 = decryptByRSA(syb, signb);
        
        // 数据对比
        if (Arrays.equals(zhaiyaob, newSource1)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 生成公钥-私钥字符串对 [0]公钥 [1]私钥
     * **/
    public static String[] createkey() {
        Object[] v = giveRSAKeyPairInByte();
        String prikey = StringUtil.byte2hex((byte[]) v[0]);
        String pubkey = StringUtil.byte2hex((byte[]) v[1]);
        
        return new String[] { pubkey, prikey };
    }
    

}