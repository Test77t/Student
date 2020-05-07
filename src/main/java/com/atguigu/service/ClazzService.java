package com.atguigu.service;

import com.atguigu.bean.Clazz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ClazzService {
    public Clazz GetClazzById(Integer id);
    public Clazz findByClazzName(String name);
    public void InsertClazz(Clazz clazz);
    public int getTotal(Map<String, Object> queryMap);
    public List<Clazz> findClazzList(Map<String, Object> queryMap);
    public int edit(Clazz clazz);
    public int delete(String ids);
    public List<Clazz> findAll();
}
