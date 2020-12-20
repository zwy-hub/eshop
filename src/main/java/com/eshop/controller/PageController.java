package com.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

//页面控制器
@Controller
public class PageController {
    //登录页面
    @RequestMapping({"/", ""})
    public String login() {
        return "login";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("f");
        session.removeAttribute("tree");
        return "redirect:";
    }

    //首页
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //商品列表页
    @RequestMapping("/product/page")
    public String page() {
        return "sys/product/product";
    }

    //商品类型列表页
    @RequestMapping("/type/page")
    public String type() {
        return "sys/product/type";
    }

    //查询订单
    @RequestMapping("/order/search")
    public String search() {
        return "sys/order/order";
    }

    //创建订单
    @RequestMapping("/order/create")
    public String create() {
        return "sys/order/create_order";
    }

    //用户列表
    @RequestMapping("/user")
    public String user() {
        return "sys/user/user";
    }

    //管理员列表
    @RequestMapping("/admin")
    public String admin() {
        return "sys/user/admin";
    }

}
