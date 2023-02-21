package com.shl.ssa.shop.starter;

import com.shl.ssa.shop.starter.conditional.ConditionOnShop;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 施海林
 * @create 2023-02-21 15:02
 * @Description 第三方自动配置类通过SPI配置发现，可参考/META-INF/spring.factories
 */
@Configuration
@EnableConfigurationProperties(ShopStarterProperties.class)
public class ShopStarterAutoConfiguration {

    @Bean
    //ShopCondition#mactchmatches方法返回true，此bean才会自动装配
    @ConditionOnShop
    //@ConditionalOnMissingBean() 可条件装配，如类、属性配置等
    public ShopStarterTest shopStarterTest() {
        return new ShopStarterTest();
    }
}
