package com.eshop.service.impl;

import com.eshop.mapper.OrderInfoMapper;
import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;
import com.eshop.pojo.vo.LayuiList;
import com.eshop.service.OrderInfoService;
import com.eshop.service.ProductInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public List<OrderInfo> findOrderInfo(OrderInfo orderInfo) {
        return orderInfoMapper.selectByPage(orderInfo);
    }

    @Override
    public LayuiList<OrderInfo> find(OrderInfo orderInfo) {
        List<OrderInfo> orderInfos = orderInfoMapper.selectByPage(orderInfo);
        return new LayuiList<>(orderInfos.size(),orderInfos);
    }

    @Override
    public Integer count(OrderInfo orderInfo) {
        return orderInfoMapper.count(orderInfo);
    }

    @Override
    public int addOrderInfo(OrderInfo oi) {
        return orderInfoMapper.saveOrderInfo(oi);
    }

    @Override
    public int addOrderDetail(OrderDetail od) {
        return orderInfoMapper.saveOrderDetail(od);
    }

    @Override
    public OrderInfo getOrderInfoById(int id) {
        return orderInfoMapper.getOrderInfoById(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOid(int oid) {
        return orderInfoMapper.getOrderDetailByOid(oid);
    }

    @Override
    public int deleteOrder(int id) {
        int result = 1;
        try {
            orderInfoMapper.deleteOrderDetail(id);
            orderInfoMapper.deleteOrderInfo(id);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    @Override
    public int getMaxId() {
        return orderInfoMapper.getMaxId();
    }

    @Override
    @Transactional
    public void saveOrder(OrderInfo orderInfo, String data) {
        addOrderInfo(orderInfo);
        int id = getMaxId();
        JSONArray jsonArray = JSONArray.fromObject(data);
        for (Object o : jsonArray) {
            JSONObject jsonObject = JSONObject.fromObject(o);
            OrderDetail o1 = (OrderDetail) JSONObject.toBean(jsonObject, OrderDetail.class);
            int pid = productInfoService.getIdByCode(o1.getPiCode());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOid(id);
            orderDetail.setPid(pid);
            orderDetail.setNum(o1.getNum());
            addOrderDetail(orderDetail);
        }
    }
}
