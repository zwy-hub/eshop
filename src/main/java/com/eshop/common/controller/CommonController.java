package com.eshop.common.controller;

import com.eshop.common.Service.CommonService;
import com.eshop.pojo.vo.LayuiList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 通用Controller
 *
 * @param <V> 实体类Vo
 * @param <E> 实体类
 * @param <T> id主键类型
 */
//@Controller
public class CommonController<V, E, T> {
    @Autowired
    private CommonService<V, E, T> commonService;
    @PostMapping("list")
    public LayuiList<List<V>> list(V entityVo) {
        return commonService.list(entityVo);
    }

    @GetMapping("get/{id}")
    public LayuiList<V> get(@PathVariable("id") T id) {
        return commonService.get(id);
    }

    @PostMapping("save")
    public LayuiList<V> save(V entityVo) {
        return commonService.save(entityVo);
    }

    @DeleteMapping("delete/{id}")
    public LayuiList<T> delete(@PathVariable("id") T id) {
        return commonService.delete(id);
    }
}
