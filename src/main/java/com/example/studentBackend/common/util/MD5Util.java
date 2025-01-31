package com.example.studentBackend.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 对字符串进行MD5加密
     * @param input 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryptToMD5(String input) {
        try {
            // 获取MD5消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算输入字符串的MD5哈希值
            byte[] messageDigest = md.digest(input.getBytes());
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不存在", e);
        }
    }

    /**
     * 验证程序
     * @param args String[]
     */
    public static void main(String[] args) {
        String originalString = "Hello, World!";
        String encryptedString = encryptToMD5(originalString);
        System.out.println("Original String: " + originalString);
        System.out.println("MD5 Encrypted String: " + encryptedString);
    }
}
