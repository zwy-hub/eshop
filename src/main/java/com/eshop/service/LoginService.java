package com.eshop.service;

import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.Functions;
import com.eshop.pojo.TreeNode;
import com.eshop.pojo.vo.ResponseResult;

import java.util.List;

public interface LoginService {
    //验证信息
    ResponseResult checkMsg(AdminInfo ai);

    //登录成功后，获取当前用户权限的树
    List<TreeNode> build(List<Functions> f);
}
