package com.eshop.service;

import com.eshop.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    //获取合法客户
    List<UserInfo> getValidUser();
    //根据客户编号查询客户
    UserInfo getUserInfoById(int id);

    //分页显示客户
    List<UserInfo> findUserInfo(UserInfo userInfo);

    //客户计数
    Integer count(UserInfo userInfo);

    //修改指定编号的用户状态
    void modifyStatus(String ids, int flag);
}
