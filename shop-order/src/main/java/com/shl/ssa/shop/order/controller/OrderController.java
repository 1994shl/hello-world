package com.shl.ssa.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.order.model.OrderParams;
import com.shl.ssa.shop.order.service.OrderService;
import com.shl.ssa.shop.order.strategyfactory.BHandler;
import com.shl.ssa.shop.order.strategyfactory.StrategyFactory;
import com.shl.ssa.shop.order.thread.TestThread;
import com.shl.ssa.shop.starter.ShopStarterTest;
import com.shl.ssa.shop.utils.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 施海林
 * @create 2022-05-26 13:43
 * @Description
 */
@Slf4j
@RestController
public class OrderController {

    private static volatile boolean pauseFlag = false;

    @Resource
    @Qualifier(value = "orderServiceV6Impl")
    private OrderService orderService;

    @GetMapping(value = "/submit_order")
    public Result submitOrder(@RequestBody OrderParams orderParams) {
        log.info("提交订单时传递的参数:{}", JSONObject.toJSONString(orderParams));
        orderService.saveOrder(orderParams);
        return new Result<>("success");
    }

    @GetMapping(value = "/concurrent_request")
    public String concurrentRequest() {
        log.info("测试业务在高并发场景下是否存在问题");
        return "hailin";
    }

    @GetMapping(value = "/testOOM")
    public void test() {
        System.out.println("测试oom");
        List<UserEntity> list = new ArrayList<>();
        while (true) {
            list.add(new UserEntity());
        }

    }

    @GetMapping(value = "/testArthas")
    public String testArthas() {
        return "hello arthas";
    }

    /**
     * 测试sentinel
     *
     * @return
     */
    @GetMapping(value = "/testSentinel")
    public String testSentinel() {
        log.info("测试Sentinel");
        return "sentinel";
    }


    @GetMapping(value = "/testPause")
    public void testPause() throws InterruptedException {

        TestThread.getInstance().suspend();

    }

    @GetMapping(value = "/testResume")
    public void testResume() {

        TestThread.getInstance().resume();

    }


    @Resource
    private StrategyFactory factory;

    @Resource
    private BHandler bHandler;

    @PostMapping(value = "/testStrategy")
    public void testStrategy() {
        //factory.getHandler("AHandler").handle();

        //factory.getHandlerList().forEach(handler -> handler.handle());

        // bHandler.handle();

        factory.getHandlerMap().forEach((k, v) -> {
            System.out.println(k);
            v.handle();
        });
    }

    @Resource
    private ShopStarterTest shopStarterTest;

    @PostMapping(value = "/thirdPartyAutoConfiguration")
    public void thirdPartyAutoConfiguration(){
        shopStarterTest.doSth();
    }

    @GetMapping(value = "/testRequest")
    public void testRequest(HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>testRequest is " + request.getMethod());
    }

}
