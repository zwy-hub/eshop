package com.eshop.service;

import com.eshop.pojo.ProductInfo;
import com.eshop.pojo.vo.LayuiList;

import java.util.List;
import java.util.Map;

public interface ProductInfoService {
    //分页获取商品
    List<ProductInfo> findProductInfo(ProductInfo productInfo);

    LayuiList<ProductInfo> find(ProductInfo productInfo);

    //商品总数
    Integer count(ProductInfo productInfo);

    //添加商品
    void addProductInfo(ProductInfo pi);

    //修改商品
    void modifyProductInfo(ProductInfo pi);

    //更新商品状态
    void modifyStatus(int id);

    //获取在售商品列表
    List<ProductInfo> getOnSaleProduct();

    //根据商品id获取商品对象
    ProductInfo getProductInfoById(int id);

    int getIdByCode(String code);
}
