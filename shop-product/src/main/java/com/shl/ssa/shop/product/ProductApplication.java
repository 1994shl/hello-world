package com.shl.ssa.shop.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 施海林
 * @create 2022-05-25 14:54
 * @Description
 */
@EnableAsync
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"com.shl.ssa.shop.product.mapper"})
public class ProductApplication {

    /**
     * -Dserver.port=8061 可启动多个实例
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
