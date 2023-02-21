package com.shl.ssa.shop.user.service;

import com.shl.ssa.shop.bean.entity.UserEntity;

/**
 * @author 施海林
 * @create 2022-05-26 10:01
 * @Description 用户服务
 */
public interface UserService {

    /**
     * 根据id获取用户信息
     */
    UserEntity getUserById(Long userId);
}
