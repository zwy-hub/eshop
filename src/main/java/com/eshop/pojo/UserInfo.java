package com.eshop.pojo;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String realName;
    private String sex;
    private String address;
    private String email;
    private String regDate;
    private int status;
}
