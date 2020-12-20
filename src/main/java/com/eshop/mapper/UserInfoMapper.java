package com.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eshop.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    //获取status字段为1的客户列表
    List<UserInfo> getValidUser();

    //根据id查找
    UserInfo getUserInfoById(int id);

    //分页获取信息
    List<UserInfo> selectByPage(UserInfo userInfo);

    //根据条件查询客户总数
    Integer count(UserInfo userInfo);

    //根据id（有多个）更新状态
    void updateState(List ids, int flag);
}
