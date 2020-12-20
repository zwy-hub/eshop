package com.eshop.pojo;

import lombok.Data;
//商品查询条件
@Data
public class SearchProductInfo {
    private int id;
    private String code;
    private String name;
    private String brand;
    private double priceFrom;
    private double priceTo;
    private int tid;
}
