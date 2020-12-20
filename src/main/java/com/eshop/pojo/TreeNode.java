package com.eshop.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
//显示菜单，树形控件节点信息
@Data
public class TreeNode {
    private int id;
    private String text;
    private String url;
    private int fid;
    private List<TreeNode> children= new ArrayList<>();;
}
