package com.shl.ssa.shop.starter.conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author 施海林
 * @create 2023-02-21 15:24
 * @Description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(ShopCondition.class)
public @interface ConditionOnShop {
}
