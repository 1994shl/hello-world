package com.shl.ssa.shop.order.feign;

import com.shl.ssa.shop.bean.entity.ProductEntity;
import com.shl.ssa.shop.order.feign.fallback.factory.ProductServiceFallBackFactory;
import com.shl.ssa.shop.utils.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 施海林
 * @create 2022-05-26 18:28
 * @Description
 */

//@FeignClient(value = "server-product", fallback = ProductServiceFallBack.class)
@FeignClient(value = "server-product",fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductService {

    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    ProductEntity getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
