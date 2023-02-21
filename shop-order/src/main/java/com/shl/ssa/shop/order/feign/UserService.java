package com.shl.ssa.shop.order.feign;

import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.order.feign.fallback.factory.UserServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 施海林
 * @create 2022-05-26 18:26
 * @Description
 */
//@FeignClient(value = "server-user", fallback = UserServiceFallBack.class)
@FeignClient(value = "server-user", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {

    @GetMapping(value = "/user/get/{uid}")
    UserEntity getUser(@PathVariable("uid") Long uid);
}
