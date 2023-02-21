package com.shl.ssa.shop.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.shl.ssa.shop.utils.snowflake.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 施海林
 * @create 2022-05-25 15:11
 * @Description
 */
@Data
@TableName("t_order_item")
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 9194731993678336030L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    @TableField("t_order_id")
    private Long orderId;

    /**
     * 商品id
     */
    @TableField("t_pro_id")
    private Long proId;

    /**
     * 商品名称
     */
    @TableField("t_pro_name")
    private String proName;

    /**
     * 商品价格（单价）
     */
    @TableField("t_pro_price")
    private BigDecimal proPrice;

    /**
     * 购买数量
     */
    @TableField("t_number")
    private Integer number;

    public OrderItemEntity(){
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}
