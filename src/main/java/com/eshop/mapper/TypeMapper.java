package com.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eshop.pojo.Type;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {
    //查询所有商品类型
    List<Type> selectAll();

    //根据类型编号查询商品类型
    Type selectById(int id);

    //添加商品类型
    int add(Type type);

    //更新商品类型
    int update(Type type);
}
