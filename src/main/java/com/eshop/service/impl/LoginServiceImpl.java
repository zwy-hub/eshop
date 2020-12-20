package com.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshop.mapper.AdminInfoMapper;
import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.Functions;
import com.eshop.pojo.TreeNode;
import com.eshop.pojo.vo.ResponseResult;
import com.eshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Override
    public ResponseResult checkMsg(AdminInfo ai) {
        ResponseResult result = new ResponseResult();
        AdminInfo adminInfo = adminInfoMapper.selectByNameAndPwd(ai);
        if (adminInfo != null && adminInfo.getName() != null) {//信息验证成功

            AdminInfo info = adminInfoMapper.selectById(adminInfo.getId());//获取用户名的权限
            if (info.getFs().size() > 0) {
                result.setCode(200);
                result.setMessage("登录成功");
                result.setAdminInfo(info);
            } else {
                result.setCode(250);
                result.setMessage("权限不足，请联系超级管理员");
            }
        } else {
            result.setCode(300);
            result.setMessage("用户名或密码不正确");
        }
        return result;
    }
    //登录成功后，获取当前用户权限的树
    @Override
    public  List<TreeNode> build(List<Functions> f){
        List<TreeNode> nodes = new ArrayList<>();
        //生成节点
        for (Functions functions : f) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(functions.getId());
            treeNode.setText(functions.getName());
            treeNode.setUrl(functions.getUrl());
            treeNode.setFid(functions.getParentId());
            nodes.add(treeNode);
        }
        //树
        List<TreeNode> treeNodes = new ArrayList<>();
        //生成根节点
        for (TreeNode node : nodes) {
            if (node.getFid()==0)
                treeNodes.add(node);
        }
        //添加叶子节点
        for (TreeNode node : nodes) {
            if (node.getFid()!=0){
                for (TreeNode treeNode : treeNodes) {
                    if (node.getFid()==treeNode.getId())
                        treeNode.getChildren().add(node);
                }
            }
        }
        return treeNodes;
    }
}
