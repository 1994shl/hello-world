package com.shl.ssa.shop.product.config;

/**
 * @author 施海林
 * @create 2023-08-16 15:22
 * @Description
 */
public class TickClock {
    private static long currentTimeMillis = 0;

    static void tick() {
        currentTimeMillis = System.currentTimeMillis();
    }

    public static long currentTimeMillis() {
        if (0 == currentTimeMillis) {
            return System.currentTimeMillis();
        }
        return currentTimeMillis;
    }

}
