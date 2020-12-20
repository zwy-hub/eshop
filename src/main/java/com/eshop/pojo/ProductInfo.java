package com.eshop.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Options;

@Data
public class ProductInfo {
    private int id;
    private String code;
    private String name;
    private Type type;
    private String brand;
    private String pic;
    private int num;
    private double price;
    private String intro;
    private int status;
    private double priceFrom;
    private double priceTo;
}
