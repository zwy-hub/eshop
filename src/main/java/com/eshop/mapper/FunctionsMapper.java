package com.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eshop.pojo.Functions;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FunctionsMapper extends BaseMapper<Functions> {
    //根据管理员id获取功能权限
    List<Functions> selectByAdminId(Integer id);
    //功能权限树
    List<Functions> all();
}
