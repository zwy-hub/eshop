package com.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.Pager;
import com.eshop.pojo.Powers;

import java.util.HashMap;
import java.util.List;

public interface AdminInfoMapper {
    //根据登录名和密码查询管理员
    AdminInfo selectByNameAndPwd(AdminInfo ai);
    //根据管理员id获取管理员对象及关联的功能集合
    AdminInfo selectById(Integer id);

    List<AdminInfo> selectWithPage(AdminInfo ai);

    void add(AdminInfo ai);

    void resetPwd(int id);

    int getMaxId();

    void delPowersByAId(int aid);

    void insertPowers(Powers powers);
}
