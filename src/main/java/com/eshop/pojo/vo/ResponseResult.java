package com.eshop.pojo.vo;

import com.eshop.pojo.AdminInfo;
import lombok.Data;

@Data
public class ResponseResult {
    //验证用户登录
    private Integer code;
    private String message;
    //顺便把用户信息存起来
    private AdminInfo adminInfo;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, AdminInfo adminInfo) {
        this.code = code;
        this.message = message;
        this.adminInfo = adminInfo;
    }
}
