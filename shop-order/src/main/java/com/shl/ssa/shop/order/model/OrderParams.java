package com.shl.ssa.shop.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 施海林
 * @create 2022-05-26 13:29
 * @Description 订单信息
 */
@Data
public class OrderParams implements Serializable {

    private static final long serialVersionUID = -3422134937432274878L;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 产品Id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;


}
