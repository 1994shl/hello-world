package com.shl.ssa.shop.product.service;

import com.shl.ssa.shop.bean.entity.ProductEntity;

/**
 * @author 施海林
 * @create 2022-05-26 11:29
 * @Description 产品服务类
 */
public interface ProductService {

    /**
     * 根据商品id获取商品信息
     */
    ProductEntity getProductById(Long pid);


    /**
     * 扣减商品库存
     */
    int updateProductStockById(Integer count, Long id);
}
