package com.shl.ssa.shop.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.shl.ssa.shop.utils.snowflake.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 施海林
 * @create 2022-05-25 15:10
 * @Description
 */
@Data
@TableName("t_order")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 8417614486979702070L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户id
     */
    @TableField("t_user_id")
    private Long userId;

    /**
     * 用户名
     */
    @TableField("t_user_name")
    private String username;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;


    /**
     * 商品价格（总价）
     */
    @TableField("t_total_price")
    private BigDecimal totalPrice;

    public OrderEntity() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}
