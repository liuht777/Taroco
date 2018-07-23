package cn.taroco.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述
 *
 * @author liuht
 * @date 2018/5/4 10:46
 */
public class HashUtils {

    private static MessageDigest md5 = null;

    private HashUtils() {

    }

    /**
     * 使用FNV1_32_HASH算法计算字符串的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     *
     * @param str 待计算的字符串
     * @return hash值
     */
    public static int fnv132Hash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    /**
     * 使用MD5算法计算字符串的Hash值,保证一致性哈希的平衡性
     *
     * @param str 待计算的字符串
     * @return hash值
     */
    public static long md5Hash(String str) {
        if (md5 == null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("no md5 algrithm found");
            }
        }
        md5.reset();
        md5.update(str.getBytes());
        byte[] bKey = md5.digest();
        //具体的哈希函数实现细节--每个字节 & 0xFF 再移位
        long result = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16
                | ((long) (bKey[1] & 0xFF) << 8) | ((long) bKey[0] & 0xFF));
        // 得到无符号值
        return result & 0xffffffffL;
    }
}
