package com.shl.ssa.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.shl.ssa.shop.bean.entity.ProductEntity;
import com.shl.ssa.shop.product.service.ProductService;
import com.shl.ssa.shop.utils.common.Result;
import com.shl.ssa.shop.utils.constant.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 施海林
 * @create 2022-05-26 11:33
 * @Description
 */
@Slf4j
@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping(value = "/get/{pid}")
    public ProductEntity getProduct(@PathVariable("pid") Long pid) {
        ProductEntity product = productService.getProductById(pid);
        log.info("获取到的商品信息为：{}", JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping(value = "/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count) {
        log.info("更新商品库存传递的参数为: 商品id:{}, 购买数量:{} ", pid, count);
        int updateCount = productService.updateProductStockById(count, pid);
        Result<Integer> result = new Result<>(HttpCode.SUCCESS, "执行成功", updateCount);
        return result;
    }
}
