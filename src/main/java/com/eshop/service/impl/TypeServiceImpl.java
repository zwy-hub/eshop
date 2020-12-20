package com.eshop.service.impl;

import com.eshop.mapper.TypeMapper;
import com.eshop.pojo.Type;
import com.eshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> getAll() {
        return typeMapper.selectAll();
    }

    @Override
    public int addType(Type type) {
        return typeMapper.add(type);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.update(type);
    }
}
