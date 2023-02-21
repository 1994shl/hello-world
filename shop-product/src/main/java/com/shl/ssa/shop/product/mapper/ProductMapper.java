package com.shl.ssa.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shl.ssa.shop.bean.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 施海林
 * @create 2022-05-26 11:24
 * @Description
 */
@Repository
public interface ProductMapper extends BaseMapper<ProductEntity> {

    /**
     * 扣减商品库存
     */
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}
