package com.eshop.controller;

import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;
import com.eshop.pojo.vo.LayuiList;
import com.eshop.pojo.vo.ResponseResult;
import com.eshop.service.OrderInfoService;
import com.eshop.service.ProductInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderInfoService orderInfoService;

    //订单列表
    @PostMapping("/list")
    public LayuiList<OrderInfo> list(OrderInfo orderInfo) {
        return orderInfoService.find(orderInfo);
    }

    //订单详情列表
    @PostMapping("/detail")
    public LayuiList<OrderDetail> detail(int oid) {
        List<OrderDetail> orderDetailByOid = orderInfoService.getOrderDetailByOid(oid);
        for (OrderDetail orderDetail : orderDetailByOid) {
            orderDetail.setTotalPrice(orderDetail.getNum() * orderDetail.getPi().getPrice());
        }
        return new LayuiList<>(orderDetailByOid.size(), orderDetailByOid);
    }

    //创建订单及订单详细
    @PutMapping("/save")
    public ResponseResult save(OrderInfo orderInfo,HttpServletRequest request){
        String data = request.getParameter("data");
        orderInfoService.saveOrder(orderInfo,data);
        return new ResponseResult(200, "添加成功");
    }

    //删除订单
    @DeleteMapping("/del")
    public ResponseResult delete(int id) {
        int i = orderInfoService.deleteOrder(id);
        if (i == 1) return new ResponseResult(200, "删除成功");
        return new ResponseResult(500, "删除失败");
    }
}
