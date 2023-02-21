package com.shl.ssa.shop.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.shl.ssa.shop.utils.snowflake.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 施海林
 * @create 2022-05-25 15:07
 * @Description
 */
@Data
@TableName("t_product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1130802299766356367L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("t_pro_name")
    private String proName;

    /**
     * 商品价格
     */
    @TableField("t_pro_price")
    private BigDecimal proPrice;

    /**
     * 商品库存
     */
    @TableField("t_pro_stock")
    private Integer proStock;

    public ProductEntity() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}
