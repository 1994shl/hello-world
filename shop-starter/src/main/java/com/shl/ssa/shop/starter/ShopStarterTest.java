package com.shl.ssa.shop.starter;

import javax.annotation.Resource;

/**
 * @author 施海林
 * @create 2023-02-21 10:17
 * @Description
 */
public class ShopStarterTest {

    @Resource
    private ShopStarterProperties properties;

    /*public void doSth(String workName) {
        System.out.println("shl is work on " + workName);
    }*/

    public void doSth() {
        System.out.println("shl is working on " + properties.getWorkName());
    }

}
