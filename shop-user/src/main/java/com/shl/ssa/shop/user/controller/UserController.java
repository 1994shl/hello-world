package com.shl.ssa.shop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 施海林
 * @create 2022-05-26 10:34
 * @Description
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/get/{uid}")
    public UserEntity getUser(@PathVariable("uid") Long uid) {
        UserEntity user = userService.getUserById(uid);
        log.info("获取到的用户信息为：{}", JSONObject.toJSONString(user));
        return user;
    }

    @GetMapping(value = "/get/longConnect")
    public DeferredResult<String> testDeferredResult() {

        DeferredResult<String> resultDeferredResult = new DeferredResult<>(1000L, "Time out");

        resultDeferredResult.onTimeout(() -> {
            log.info("Timeout");
        });
        resultDeferredResult.onCompletion(() -> {
            log.info("Completion");
        });
        log.info("Async thread work");
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        return resultDeferredResult;
    }

    @PostMapping(value = "/testOpenfeign")
    public String testOpenfeign(HttpServletRequest request) {

        log.info("testOpenfeign and request is {}", JSONObject.toJSONString(request));

        return "openFeign";
    }
}
