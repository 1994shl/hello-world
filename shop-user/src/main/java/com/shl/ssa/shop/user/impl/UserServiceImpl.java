package com.shl.ssa.shop.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.user.mapper.UserMapper;
import com.shl.ssa.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 施海林
 * @create 2022-05-26 10:03
 * @Description
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity getUserById(Long userId) {
        return getById(userId);
    }
}
