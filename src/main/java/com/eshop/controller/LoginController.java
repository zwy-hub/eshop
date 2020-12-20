package com.eshop.controller;

import com.eshop.pojo.AdminInfo;
import com.eshop.pojo.vo.ResponseResult;
import com.eshop.service.AdminInfoService;
import com.eshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private AdminInfoService adminInfoService;
    @Autowired
    LoginService loginService;

    //登录请求
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody AdminInfo ai, HttpSession session) {
        ResponseResult result = loginService.checkMsg(ai);
        if (result.getCode() == 200) {
            AdminInfo af = adminInfoService
                    .getAdminInfoAndFunctions(result.getAdminInfo().getId());
            session.setAttribute("id", af.getId());
            session.setAttribute("name", af.getName());
            //功能列表
            session.setAttribute("f", af.getFs());
            //功能列表树
            session.setAttribute("tree", loginService.build(af.getFs()));
        }
        return result;
    }
}
