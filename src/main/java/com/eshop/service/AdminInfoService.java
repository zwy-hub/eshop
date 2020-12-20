package com.eshop.service;

import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.Functions;
import com.eshop.pojo.Pager;

import java.util.List;

public interface AdminInfoService {
    //登录验证
    AdminInfo login(AdminInfo ai);
    //根据管理员编号，获取管理员对象及关联的功能权限
    AdminInfo getAdminInfoAndFunctions(Integer id);

    List<AdminInfo> find(AdminInfo ai);

    void resetPwd(int id);

    //菜单权限树
    List<Functions> all();

    void save(AdminInfo adminInfo,String functions);
}
