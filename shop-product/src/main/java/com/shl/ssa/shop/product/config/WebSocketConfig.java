package com.shl.ssa.shop.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 施海林
 * @create 2023-09-20 16:34
 * @Description webSocket配置类
 */
@Configuration
public class WebSocketConfig {

   @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
