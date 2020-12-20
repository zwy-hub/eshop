package com.eshop.pojo;

import lombok.Data;

import java.util.List;

@Data
public class AdminInfo {
    private int id;
    private String name;
    private String pwd;
    private List<Functions> fs;
}
