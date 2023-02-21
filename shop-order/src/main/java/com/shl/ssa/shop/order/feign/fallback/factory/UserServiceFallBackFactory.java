package com.shl.ssa.shop.order.feign.fallback.factory;

import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.order.feign.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 施海林
 * @create 2022-07-06 17:10
 * @Description
 */
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable cause) {
        return uid -> {
            UserEntity user = new UserEntity();
            user.setId(-1L);
            return user;
        };
    }
}
