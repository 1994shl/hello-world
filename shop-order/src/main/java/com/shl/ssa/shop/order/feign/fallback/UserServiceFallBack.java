package com.shl.ssa.shop.order.feign.fallback;

import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.order.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * @author 施海林
 * @create 2022-07-06 15:58
 * @Description
 */
@Component
public class UserServiceFallBack implements UserService {

    @Override
    public UserEntity getUser(Long uid) {
        UserEntity user = new UserEntity();
        user.setId(-1L);
        return user;
    }
}
