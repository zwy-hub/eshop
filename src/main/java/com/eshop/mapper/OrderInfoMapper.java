package com.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    //获取订单信息
    List<OrderInfo> selectByPage(OrderInfo orderInfo);
    //根据条件查询订单总数
    Integer count(OrderInfo orderInfo);
    //保存订单主表信息insert
    int saveOrderInfo(OrderInfo oi);
    //保存订单明细insert
    int saveOrderDetail(OrderDetail od);
    //根据订单编号获取订单对象
    OrderInfo getOrderInfoById(int id);
    //根据订单编号获取订单明细
    List<OrderDetail> getOrderDetailByOid(int oid);
    //根据订单编号删除订单主表记录
    int deleteOrderInfo(int id);
    //根据订单编号删除订单明细记录
    int deleteOrderDetail(int oid);

    int getMaxId();
}
