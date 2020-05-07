package com.atguigu.service.impl;

import com.atguigu.bean.Clazz;
import com.atguigu.dao.ClazzDao;
import com.atguigu.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;
    @Override
    public Clazz GetClazzById(Integer id) {
        return clazzDao.GetClazzById(id);
    }

    @Override
    public Clazz findByClazzName(String name) {
        return clazzDao.findByClazzName(name);
    }

    @Override
    public void InsertClazz(Clazz clazz) {
        clazzDao.InsertClazz(clazz);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return clazzDao.getTotal(queryMap);
    }

    @Override
    public List<Clazz> findClazzList(Map<String, Object> queryMap) {
        return clazzDao.findClazzList(queryMap);
    }

    @Override
    public int edit(Clazz clazz) {
        return clazzDao.edit(clazz);
    }

    @Override
    public int delete(String ids) {
        return clazzDao.delete(ids);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzDao.findAll();
    }


}
