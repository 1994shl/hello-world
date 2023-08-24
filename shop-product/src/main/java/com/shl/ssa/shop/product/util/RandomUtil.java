package com.shl.ssa.shop.product.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    private static final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] LETTERS =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static int randomInt() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt();
    }

    public static int random(int bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(bound);
    }

    /**
     * 调用此方法，即可产生指定长度的随机数字字符串
     *
     * @param length 字符串的长度
     * @return 指定长度的随机数字字符串
     */
    public static String randomInt(int length) {
        return randomString(NUMBERS, length);
    }

    /**
     * 调用此方法，即可产生指定长度的随机字符串
     *
     * @param length 字符串的长度
     * @return 指定长度的随机字符串
     */
    public static String randomString(int length) {
        return randomString(LETTERS, length);
    }

    public static String randomString(final char[] rangeStr, int length) {
        if (null == rangeStr || rangeStr.length == 0 || length <= 0) {
            return "";
        }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] randBuffer = new char[length];
        for (int i = 0; i < length; i++) {
            randBuffer[i] = rangeStr[random.nextInt(rangeStr.length)];
        }
        return new String(randBuffer);
    }

}
