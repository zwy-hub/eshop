package com.eshop.common.Service;

import com.eshop.pojo.vo.LayuiList;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 通用Service实现类
 *
 * @param <V> 实体类Vo
 * @param <E> 实体类
 * @param <T> id主键类型
 */
@Service
public class CommonServiceImpl<V, E, T> implements CommonService<V, E, T> {

    @Override
    public LayuiList<List<V>> list(V entityVo) {
        return null;
    }

    @Override
    public LayuiList<V> get(T id) {
        return null;
    }

    @Override
    public LayuiList<V> save(V entityVo) {
        return null;
    }

    @Override
    public LayuiList<T> delete(T id) {
        return null;
    }
}
