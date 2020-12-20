package com.eshop.pojo;

import lombok.Data;

@Data
public class OrderDetail {
    private int id;
    private int oid;
    private OrderInfo oi;
    private int pid;
    private String piCode;
    private ProductInfo pi;
    private int num;
    private double price;
    private double totalPrice;
}
