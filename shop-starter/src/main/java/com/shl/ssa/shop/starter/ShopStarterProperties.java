package com.shl.ssa.shop.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 施海林
 * @create 2023-02-21 15:21
 * @Description 自动属性配置类
 */
@ConfigurationProperties("shl.shop")
public class ShopStarterProperties {

    private String workName = "touching fish";

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
