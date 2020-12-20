package com.eshop.pojo;

import lombok.Data;

@Data
public class OrderInfo {
    private int id;
    private int uid;
    private UserInfo ui;
    private String status;
    private String orderTime;
    private double orderPrice;
    private String orderTimeFrom;
    private String orderTimeTo;
}
