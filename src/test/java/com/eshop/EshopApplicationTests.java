package com.eshop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.eshop.mapper.*;
import com.eshop.pojo.*;
import com.eshop.service.AdminInfoService;
import com.eshop.service.OrderInfoService;
import com.eshop.service.ProductInfoService;
import com.eshop.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class EshopApplicationTests {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    AdminInfoMapper adminInfoMapper;
    @Autowired
    FunctionsMapper functionsMapper;
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    AdminInfoService adminInfoService;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    OrderInfoService orderInfoService;

    @Test
    void contextLoads() {
        HashMap<String, Object> params = new HashMap<>();
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserName("zhang3");
//        params.put("userInfo",userInfo);
//        Pager pager = new Pager();
//        pager.setCurPage(2);//第几页，，从1开始
//        pager.setPerPageRows(1);//每页的数据数

//        params.put("pager",pager);

//        System.out.println(userInfoMapper.selectByPage(params));
//        System.out.println(userInfoMapper.count(params));
        System.out.println(userInfoMapper.getValidUser());

    }
    @Test
    void update(){
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("status",0).eq("id",2);
        userInfoMapper.update(null,wrapper);
        System.out.println("success");
    }
    @Test
    void search(){
//        System.out.println(userInfoService.getValidUser());
//        System.out.println(userInfoService.getUserInfoById(3));
//
        System.out.println(productInfoService.getOnSaleProduct());
        System.out.println(productInfoService.getProductInfoById(5));
//        AdminInfo ai = new AdminInfo();
//        ai.setName("admin");
//        ai.setPwd("123456");
//        System.out.println(adminInfoMapper.selectByNameAndPwd(ai));

//        System.out.println(adminInfoMapper.selectById(1));
    }
    @Test
    void product(){
        HashMap<String, Object> params = new HashMap<>();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCode("0115");
        Type type = new Type();
        type.setId(2);
//        productInfo.setType(type);
        params.put("productInfo",productInfo);
        System.out.println(productInfoMapper.selectByPage(productInfo));
        System.out.println(productInfoMapper.count(productInfo));

        //save
//        ProductInfo pi = new ProductInfo();
//        Type type = new Type();
//        type.setId(6);
//        pi.setId(11);
//        pi.setStatus(0);pi.setCode("0528");pi.setBrand("Nike");pi.setIntro("运动鞋");
//        pi.setName("A10452");pi.setNum(150);pi.setPrice(300);pi.setPic("ccc");
//        pi.setType(type);
//        productInfoMapper.edit(pi);
//        System.out.println("success");
    }
    @Test
    void type(){
        Type type = new Type();
        type.setId(11);
        type.setName("日用");
        System.out.println(typeMapper.update(type));
    }

    @Test
    void orderInfo(){
        //查询
//        HashMap<String, Object> params = new HashMap<>();
        OrderInfo orderInfo = new OrderInfo();
//        orderInfo.setStatus("退款中");
//        params.put("orderInfo",orderInfo);
//        System.out.println(orderInfoMapper.selectByPage(params));
//        System.out.println(orderInfoMapper.count(params));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOid(6);orderDetail.setPid(7);
        orderDetail.setNum(2);
        orderInfoMapper.saveOrderDetail(orderDetail);
        orderInfoMapper.deleteOrderDetail(6);
        System.out.println("success");
    }
    @Test
    void admin(){

    }
}
