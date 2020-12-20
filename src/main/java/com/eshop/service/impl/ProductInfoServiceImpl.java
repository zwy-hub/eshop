package com.eshop.service.impl;

import com.eshop.mapper.ProductInfoMapper;
import com.eshop.pojo.ProductInfo;
import com.eshop.pojo.vo.LayuiList;
import com.eshop.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findProductInfo(ProductInfo productInfo) {
        return productInfoMapper.selectByPage(productInfo);
    }

    @Override
    public LayuiList<ProductInfo> find(ProductInfo productInfo) {
        List<ProductInfo> productInfos = productInfoMapper.selectByPage(productInfo);
        return new LayuiList<>(productInfos.size(), productInfos);
    }

    @Override
    public Integer count(ProductInfo productInfo) {
        return productInfoMapper.count(productInfo);
    }

    @Override
    public void addProductInfo(ProductInfo pi) {
        productInfoMapper.save(pi);
    }

    @Override
    public void modifyProductInfo(ProductInfo pi) {
        productInfoMapper.edit(pi);
    }

    @Override
    public void modifyStatus(int id) {
        productInfoMapper.updateState(id);
    }

    @Override
    public List<ProductInfo> getOnSaleProduct() {
        return productInfoMapper.getOnSaleProduct();
    }

    @Override
    public ProductInfo getProductInfoById(int id) {
        return productInfoMapper.getProductInfoById(id);
    }

    @Override
    public int getIdByCode(String code) {
        return productInfoMapper.getIdByCode(code);
    }
}
