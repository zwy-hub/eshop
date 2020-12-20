package com.eshop.service;

import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;
import com.eshop.pojo.vo.LayuiList;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {
    //分页获取订单信息
    List<OrderInfo> findOrderInfo(OrderInfo orderInfo);

    LayuiList<OrderInfo> find(OrderInfo orderInfo);
    //订单总数
    Integer count(OrderInfo orderInfo);
    //添加订单主表
    int addOrderInfo(OrderInfo oi);
    //添加订单明细
    int addOrderDetail(OrderDetail od);
    //根据订单编号获取订单信息
    OrderInfo getOrderInfoById(int id);
    //根据订单编号获取订单明细信息
    List<OrderDetail> getOrderDetailByOid(int oid);
    //删除订单
    int deleteOrder(int id);

    int getMaxId();

    void saveOrder(OrderInfo orderInfo,String data);
}
