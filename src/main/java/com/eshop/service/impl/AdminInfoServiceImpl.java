package com.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshop.mapper.AdminInfoMapper;
import com.eshop.mapper.FunctionsMapper;
import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.Functions;
import com.eshop.pojo.Pager;
import com.eshop.pojo.Powers;
import com.eshop.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    private AdminInfoMapper adminInfoMapper;
    @Autowired
    private FunctionsMapper functionsMapper;

    @Override
    public AdminInfo login(AdminInfo ai) {
        //根据登录名和密码查询管理员
        return adminInfoMapper.selectByNameAndPwd(ai);
    }

    @Override
    public AdminInfo getAdminInfoAndFunctions(Integer id) {
        return adminInfoMapper.selectById(id);
    }

    @Override
    public List<AdminInfo> find(AdminInfo ai) {
        return adminInfoMapper.selectWithPage(ai);
    }

    @Override
    public void resetPwd(int id) {
        adminInfoMapper.resetPwd(id);
    }

    @Override
    public List<Functions> all() {
        return functionsMapper.all();
    }

    @Override
    @Transactional
    public void save(AdminInfo adminInfo, String functions) {
        //判断adminInfo的id是否为0，为0则为新增，不为零则为修改
        int id = adminInfo.getId();
        if (id == 0) {
            adminInfo.setPwd("123456");
            adminInfoMapper.add(adminInfo);
            id = adminInfoMapper.getMaxId();
        }
        //下面修改权限
        List<Integer> list = new ArrayList<>();
        String[] arr = functions.split(",");
        if (arr.length > 1) {
            for (String s : arr) {
                list.add(Integer.parseInt(s));
            }
        }
        //先删除（根据aid删除），再添加（aid，和list[]）
        adminInfoMapper.delPowersByAId(id);
        Powers powers = new Powers();
        powers.setAid(id);
        for (Integer integer : list) {
            powers.setFid(integer.intValue());
            adminInfoMapper.insertPowers(powers);
        }
    }
}
