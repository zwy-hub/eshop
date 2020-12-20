package com.eshop.controller;


import com.eshop.pojo.UserInfo;
import com.eshop.pojo.vo.LayuiList;
import com.eshop.pojo.vo.ResponseResult;
import com.eshop.service.UserInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/list")
    public LayuiList<UserInfo> list(UserInfo userInfo) {
        List<UserInfo> userInfos = userInfoService.findUserInfo(userInfo);
        return new LayuiList<>(userInfos.size(), userInfos);
    }

    @PostMapping("/info")
    public UserInfo getUserInfo(int id) {
        return userInfoService.getUserInfoById(id);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestParam("ids") String ids, UserInfo userInfo) {
        userInfoService.modifyStatus(ids, userInfo.getStatus());
        return new ResponseResult(200, "修改成功");
    }

}
