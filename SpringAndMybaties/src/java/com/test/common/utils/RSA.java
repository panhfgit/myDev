/**
 * $Id: RSA.java,v 1.0 16/11/15 下午2:52 zhangruiping Exp $
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author zhangruiping
 * @version $Id: RSA.java,v 1.1 16/11/15 下午2:52 zhangruiping Exp $
 *          Created on 16/11/15 下午2:52
 */
public class RSA {

    private static final Logger logger = LoggerFactory.getLogger(DES.class);

    /**
     * 加密
     * @param plainText 原始数据
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText, String publicKey) throws Exception {
        Cipher cipher = null;
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            cipher = Cipher.getInstance("RSA");//cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            byte[] output = cipher.doFinal(plainText.getBytes());
            return Base64Util.encode(output);
        } catch (NoSuchAlgorithmException e) {
            logger.error("无此加密算法", e);
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            logger.error("", e);
            throw e;
        } catch (InvalidKeyException e) {
            logger.error("加密公钥非法,请检查", e);
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            logger.error("明文长度非法", e);
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            logger.error("明文数据已损坏", e);
            throw new Exception("明文数据已损坏");
        }
    }


    /**
     * 解密
     * @param cipherData 密文
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherData, String privateKey) throws Exception {
        Cipher cipher = null;
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            cipher = Cipher.getInstance("RSA");//cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            byte[] output = cipher.doFinal(Base64Util.decode(cipherData));
            return new String(output);
        } catch (NoSuchAlgorithmException e) {
            logger.error("无此加密算法", e);
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            logger.error("", e);
            throw e;
        } catch (InvalidKeyException e) {
            logger.error("解密私钥非法,请检查", e);
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            logger.error("密文长度非法", e);
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            logger.error("密文数据已损坏", e);
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 解密，工程使用默认的私钥
     * @param cipherData
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherData) throws Exception {

        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMr9lLwouuDCmd1M\r" +
                "76aZTSy37Amk1ll7nu6TzTxsc2aK1kA6WZBjYGFdfu8pEwkgFlg8jQdKw9CejY/5\r" +
                "g/PCFRktFn0m1sRZsVL5rXvassmGvCcuwDb4ZdQiXWEjwqonX87puWgz0H98x4Og\r" +
                "uO5KVZWG891pjVvF/k4njf806SH/AgMBAAECgYAIy+ReIwW2J7izNlpMRehqP64i\r" +
                "3NnNFuZUQGNscuatUubl8aFf+G58yahuhMEmbBWLzVPvNPY3XCeWTaHCNm7jjKaf\r" +
                "MHAkv/D+nRgtbEjgp2SAn2X82bvOPd40CzHqn+uwXBfpE3kjw003GsRQMQSrRBF1\r" +
                "i+HpKxm3I2oTIJ/XgQJBAOXTvLuXSwg1Q5La1WzcJ4c6d/wyxKLvTvczDemibfpy\r" +
                "MA5dhxMbrR042VPGkoOEhPqDSVdOukSvLWvvMnNQ9vkCQQDiG3by4UKBSETv8icb\r" +
                "yHx5oiI545lMwBPNBKd5DD1zwCoN0Dxn5e1gMLOtRQV7YZ/8gnYGCM/tN9mZnjXM\r" +
                "JMa3AkEAh/aaedQIZIO2RUPG7U7U57BUWVPjdozih2Whvm0EaRxyh50XEtvVryr5\r" +
                "jxEzjjCwxs8ybT86/PUxhuAMVaI5aQJAWpeKdxHN5aKhDEpHZRhU1x8g+3S3bUrg\r" +
                "JBqjRxqiXIg30UUHjtFO5GVe+CNZaO/ae/+BbjWFqZPg+W2sPFjXzQJBAKQY46ZH\r" +
                "YJSfXfgAkoLgnHxJtNTlsrTIkfXPK4jnT+YoMMlxMmp+BwfUvDsF2+36SquH8YSI\r" +
                "r1dkNNMqNQffNW8=";
        return decrypt(cipherData, privateKey);
    }


    public static void main(String[] args) {

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDY2kHs1+/gP3hUU6QIwGMhK7FW\r" +
                "JzmVNyxtmpYHixl97cdEOpsQmlmBG5UP7oSkSeht0ct1gaolx9EYE0RxFsk4A76J\r" +
                "uKe912c3fjR0bldslckA3lYEKOrxRHY6pIaTQrl08w8iBxUTjRH5TfQzwNAw4LcG\r" +
                "/CPLDbCT2aXbuwAl8wIDAQAB";
        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANjaQezX7+A/eFRT\r" +
                "pAjAYyErsVYnOZU3LG2algeLGX3tx0Q6mxCaWYEblQ/uhKRJ6G3Ry3WBqiXH0RgT\r" +
                "RHEWyTgDvom4p73XZzd+NHRuV2yVyQDeVgQo6vFEdjqkhpNCuXTzDyIHFRONEflN\r" +
                "9DPA0DDgtwb8I8sNsJPZpdu7ACXzAgMBAAECgYEAxEqUoEo65Vcumc54Tj+vu6fs\r" +
                "R1sMQaiGu9PReJSOB7KDzJvsH1RILpkEDj7OrFQGY9oi/IPZu4crG0rdiiyhCDur\r" +
                "1XDPlS33JJ5IOs/xY/+wspJokMX/ww2HJrxZbNxHOGMCp6O8akulHHa58jOl7KZl\r" +
                "o/9lBou3v8GLZD9mafECQQDuusJ4nWtLUUKqdRZ+X2kxAPkJ2WPxibmAS+fZdesa\r" +
                "lgYzAIrWEESJDZai7Mvh5MYIXXuuzYIDivC47MlLzC8JAkEA6IpW3wJnYAUvMeEM\r" +
                "Q6AeSEaCH1yslOrKjmY+zn/YcU8srlwKMsrK97ZYlFh7plp0XiM4gM9/6jL7iiRm\r" +
                "clmwGwJAIXvAWvp5Wg8DsrTJeuAW+PCR/XeoNPCEPCJpWm7XktsH849X6b5OXoqx\r" +
                "XhioX/6eZ/30owff/G84ZDeR5De3QQJBAMk5RLkP9HJzqgZIjvtJ6Ep0sQ8Ae9sV\r" +
                "XZhhw8GfgaVzdN+gh7ayI3a9lOMxujYWmhIH+lIEvNTlryM4ImqbT7cCQQC/DOiH\r" +
                "5+Y/+OTGZRL6pQhoMHOnQLAGH6RsaHgxcxQIPxdK9xZkUrAnUZ7oEyAO47Ps0nx1\r" +
                "P9vu6LbE8OubOqRH";


        //测试字符串
        String plainText = "abcc";

        try {
            //加密
            String xxx = RSA.encrypt(plainText, publicKey);
            System.out.println("加密结果：\r" + xxx);

            //解密
            String yyy = RSA.decrypt(xxx, privateKey);
            System.out.println("\r\r解密结果：\r" + yyy);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}