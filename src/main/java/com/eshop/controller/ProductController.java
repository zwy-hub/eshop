package com.eshop.controller;

import com.eshop.pojo.ProductInfo;
import com.eshop.pojo.vo.LayuiList;
import com.eshop.pojo.vo.ResponseResult;
import com.eshop.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductInfoService productInfoService;

    //查询商品
    @PostMapping("/list")
    public LayuiList<ProductInfo> list(ProductInfo productInfo) {
        return productInfoService.find(productInfo);
    }

    //添加商品
    @PostMapping("/set")
    public ResponseResult insert(ProductInfo productInfo) {
        productInfoService.addProductInfo(productInfo);
        return new ResponseResult(200, "添加成功");
    }

    //修改商品
    @PutMapping("/set")
    public ResponseResult update(ProductInfo productInfo) {
        productInfoService.modifyProductInfo(productInfo);
        return new ResponseResult(200, "修改成功");
    }

    //删除（下架）商品
    @DeleteMapping("/del")
    public ResponseResult delete(int id) {
        productInfoService.modifyStatus(id);
        return new ResponseResult(200, "下架成功");
    }
}
