package com.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshop.mapper.UserInfoMapper;
import com.eshop.pojo.UserInfo;
import com.eshop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getValidUser() {//合法客户状态为1
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        return userInfoMapper.selectList(wrapper);
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return userInfoMapper.selectOne(wrapper);
    }

    @Override
    public List<UserInfo> findUserInfo(UserInfo userInfo) {
        return userInfoMapper.selectByPage(userInfo);
    }

    @Override
    public Integer count(UserInfo userInfo) {
        return userInfoMapper.count(userInfo);
    }

    @Override
    public void modifyStatus(String ids, int flag) {
        List<Integer> list = new ArrayList<>();
        String[] arr = ids.split(",");
        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }
        userInfoMapper.updateState(list, flag);
    }
}
