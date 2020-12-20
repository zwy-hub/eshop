package com.eshop.controller;

import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.Functions;
import com.eshop.pojo.Pager;
import com.eshop.pojo.vo.LayuiList;
import com.eshop.pojo.vo.ResponseResult;
import com.eshop.service.AdminInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminInfoService adminInfoService;

    //带分页查询功能的管理员列表信息
    @PostMapping("/list")
    public LayuiList<AdminInfo> list(AdminInfo adminInfo, Pager pager) {
        List<AdminInfo> adminInfos = adminInfoService.find(adminInfo);
        List<AdminInfo> adminInfo0 = new ArrayList<>();
        int curParamIndex = pager.getFirstLimitParam();
        int limit = 0;
        while (curParamIndex < adminInfos.size() && limit < pager.getLimit()) {
            adminInfo0.add(adminInfos.get(curParamIndex));
            curParamIndex++;
            limit++;
        }
        return new LayuiList<>(adminInfos.size(), adminInfo0);
    }

    //删除管理员
    @DeleteMapping("/del/{id}")
    public ResponseResult del(@PathVariable("id") int id) {
        return new ResponseResult(200, "删除成功");
    }

    //重置密码
    @PutMapping("/resetPassword/{id}")
    public ResponseResult reset(@PathVariable("id") int id) {
        adminInfoService.resetPwd(id);
        return new ResponseResult(200, "重置密码成功");
    }

    //菜单功能树
    @PostMapping("/tree")
    public List<Functions> all() {
        return adminInfoService.all();
    }

    //添加admin
    @PostMapping("/save")
    public ResponseResult save(AdminInfo adminInfo, @RequestParam("functions") String functions) {
        adminInfoService.save(adminInfo, functions);
        return new ResponseResult(200, "操作成功");
    }
}
