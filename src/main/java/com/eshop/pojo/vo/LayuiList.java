package com.eshop.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class LayuiList<T> {
    /**
     * layui表格固定返回格式
     */
    private Integer code = 0;
    private String msg = "";
    private Integer count;
    private List<T> data;

    public LayuiList(Integer code, String message, Integer count, List<T> data) {
        this.code=code;
        this.msg=message;
        this.count=count;
        this.data=data;
    }
    public LayuiList(Integer count, List<T> data) {
        this.count=count;
        this.data=data;
    }

    public LayuiList() {}
}
