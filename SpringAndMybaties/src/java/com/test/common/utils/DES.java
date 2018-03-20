/**
 * $Id: DES.java,v 1.0 16/11/14 下午4:21 zhangruiping Exp $
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author zhangruiping
 * @version $Id: DES.java,v 1.1 16/11/14 下午4:21 zhangruiping Exp $
 *          Created on 16/11/14 下午4:21
 */
public class DES {

    private static final Logger logger = LoggerFactory.getLogger(DES.class);

    /**
     * 加密
     *
     * @param original 原文
     * @param cipher   秘钥
     * @return 密文
     */
    public static String encrypt(String original, String cipher) throws Exception {
        byte[] cipherTextByte = encrypt(original.getBytes(), cipher);
        return Base64Util.encode(cipherTextByte);
    }


    /**
     * 加密
     *
     * @param original 原文
     * @param cipher   秘钥
     * @return 密文
     */
    public static byte[] encrypt(byte[] original, String cipher) throws Exception{
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(cipher.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            Cipher cipherIns = Cipher.getInstance("DES");
            cipherIns.init(Cipher.ENCRYPT_MODE, secureKey, random);
            return cipherIns.doFinal(original);
        } catch (Throwable e) {
            logger.error("加密失败！", e);
            throw e;
        }
    }


    /**
     * 解密
     *
     * @param cipherText
     * @param cipher
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherText, String cipher) throws Exception {
        byte[] decryResult = decrypt(Base64Util.decode(cipherText), cipher);
        return new String(decryResult);
    }


    /**
     * 解密
     *
     * @param cipherText
     * @param cipher
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] cipherText, String cipher) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(cipher.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secureKey = keyFactory.generateSecret(desKey);
        Cipher cipherIns = Cipher.getInstance("DES");
        cipherIns.init(Cipher.DECRYPT_MODE, secureKey, random);
        return cipherIns.doFinal(cipherText);
    }


    public static void main(String args[]) throws Exception {

        String original = "asiainfo$123";
        String cipher = "0f25da31";

        String cipherText = DES.encrypt(original, cipher);
        System.out.println(original + ": " + cipherText);

        System.out.println(original + ":" + DES.decrypt("wAMpXgRvJ7PxsqWvjlHU+A==", "0f25da31"));
    }

}