package com.shl.ssa.shop.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.ssa.shop.bean.entity.ProductEntity;
import com.shl.ssa.shop.product.mapper.ProductMapper;
import com.shl.ssa.shop.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 施海林
 * @create 2022-05-26 11:30
 * @Description
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {

    @Override
    public ProductEntity getProductById(Long pid) {
        return getById(pid);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int updateProductStockById(Integer count, Long id) {
        return getBaseMapper().updateProductStockById(count, id);
    }
}
