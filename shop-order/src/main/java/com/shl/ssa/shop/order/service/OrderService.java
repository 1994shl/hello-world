package com.shl.ssa.shop.order.service;

import com.shl.ssa.shop.order.model.OrderParams;

/**
 * @author 施海林
 * @create 2022-05-26 13:25
 * @Description
 */
public interface OrderService {

    /**
     * 保存订单
     */
    void saveOrder(OrderParams orderParams);
}
