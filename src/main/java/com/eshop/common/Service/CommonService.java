package com.eshop.common.Service;

import com.eshop.pojo.vo.LayuiList;

import java.util.List;

/**
 * 通用Service
 *
 * @param <V> 实体类Vo
 * @param <E> 实体类
 * @param <T> id主键类型
 */
public interface CommonService<V, E,T> {
    //数据
    LayuiList<List<V>> list(V entityVo);
    //主键获取
    LayuiList<V> get(T id);
    //保存
    LayuiList<V> save(V entityVo);
    //删除
    LayuiList<T> delete(T id);
}
