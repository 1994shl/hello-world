package com.shl.ssa.shop.order.feign.fallback.factory;

import com.shl.ssa.shop.bean.entity.ProductEntity;
import com.shl.ssa.shop.order.feign.ProductService;
import com.shl.ssa.shop.utils.common.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 施海林
 * @create 2022-07-06 17:14
 * @Description
 */
@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable cause) {
        return new ProductService() {
            @Override
            public ProductEntity getProduct(Long pid) {
                ProductEntity product = new ProductEntity();
                product.setId(-1L);
                return product;
            }

            @Override
            public Result<Integer> updateCount(Long pid, Integer count) {
                Result<Integer> result = new Result<>();
                result.setCode(1001);
                result.setCodeMsg("触发了容错逻辑");
                return result;
            }
        };
    }
}
