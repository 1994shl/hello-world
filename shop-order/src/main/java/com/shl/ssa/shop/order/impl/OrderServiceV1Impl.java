package com.shl.ssa.shop.order.impl;

import com.alibaba.fastjson.JSONObject;
import com.shl.ssa.shop.bean.entity.OrderEntity;
import com.shl.ssa.shop.bean.entity.OrderItemEntity;
import com.shl.ssa.shop.bean.entity.ProductEntity;
import com.shl.ssa.shop.bean.entity.UserEntity;
import com.shl.ssa.shop.order.mapper.OrderItemMapper;
import com.shl.ssa.shop.order.mapper.OrderMapper;
import com.shl.ssa.shop.order.model.OrderParams;
import com.shl.ssa.shop.order.service.OrderService;
import com.shl.ssa.shop.utils.common.Result;
import com.shl.ssa.shop.utils.constant.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 施海林
 * @create 2022-05-26 13:30
 * @Description
 */
@Slf4j
@Service
public class OrderServiceV1Impl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (ObjectUtils.isEmpty(orderParams)) {
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }

        UserEntity user = restTemplate.getForObject(
                "http://localhost:8060/user/get/" + orderParams.getUserId(), UserEntity.class);
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        ProductEntity product = restTemplate.getForObject(
                "http://localhost:8070/product/get/" + orderParams.getProductId(), ProductEntity.class);
        if (product == null) {
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()) {
            throw new RuntimeException("商品库存不足: " + JSONObject.toJSONString(orderParams));
        }
        OrderEntity order = new OrderEntity();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = restTemplate.getForObject(
                "http://localhost:8070/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }
}
