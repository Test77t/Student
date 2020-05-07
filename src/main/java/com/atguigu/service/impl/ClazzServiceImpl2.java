package com.atguigu.service.impl;

import com.atguigu.bean.Clazz2;
import com.atguigu.dao.Clazz2Dao;
import com.atguigu.service.Clazz2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl2 implements Clazz2Service {
    @Autowired
    private Clazz2Dao clazz2Dao;

    @Override
    public Clazz2 GetClazzById(Integer id) {
        return clazz2Dao.GetClazzById(id);
    }

    @Override
    public void InsertClazz(Clazz2 clazz2) {
        clazz2Dao.InsertClazz(clazz2);
    }

    @Override
    public Clazz2 findByClazzName(String name) {
        return clazz2Dao.findByClazzName(name);
    }

    @Override
    public List<Clazz2> findClazzList(Map<String, Object> queryMap) {
        return clazz2Dao.findClazzList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return clazz2Dao.getTotal(queryMap);
    }

    @Override
    public int edit(Clazz2 clazz2) {
        return clazz2Dao.edit(clazz2);
    }

    @Override
    public int delete(String ids) {
        return clazz2Dao.delete(ids);
    }
}
