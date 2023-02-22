package com.shl.ssa.shop.order.extend.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 施海林
 * @create 2023-02-21 16:56
 * @Description spring boot扩展方式：初始化器，在refresh之前
 */
//@Component 初始化器在bean装配之前，只能使用spi注入
public class ShopOrderInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println(">>>>>>>>>>ShopOrderInitializer is working!");
    }
}
