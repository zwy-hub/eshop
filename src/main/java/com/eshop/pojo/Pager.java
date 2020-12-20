package com.eshop.pojo;

import lombok.Data;
//分页信息
@Data
public class Pager {
    private int page=1;//当前页
    private int limit=10;//每页显示的记录数
    private int rowCount;//记录总数
    private int pageCount;//总页数
    private int firstLimitParam=(page-1)*limit;
    public int getPageCount(){
        return (rowCount+limit-1)/limit;
    }
    //分页显示时，获取当前页的第一条记录的索引
    public int getFirstLimitParam(){
        return (this.page-1)*this.limit;
    }
}
