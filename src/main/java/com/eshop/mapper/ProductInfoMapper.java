package com.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eshop.pojo.ProductInfo;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    //分页获取商品
    List<ProductInfo> selectByPage(ProductInfo productInfo);

    //根据条件查询商品总数
    Integer count(ProductInfo productInfo);

    //添加商品
    void save(ProductInfo pi);

    //根据id删除商品
    void del(int id);

    //修改商品
    void edit(ProductInfo pi);

    //更新商品状态
    void updateState(int id);

    //获取在售商品列表
    List<ProductInfo> getOnSaleProduct();

    //根据商品id获取商品对象
    ProductInfo getProductInfoById(int id);
    int getIdByCode(String code);
}
