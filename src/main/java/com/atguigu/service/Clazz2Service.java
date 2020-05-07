package com.atguigu.service;

import com.atguigu.bean.Clazz2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface Clazz2Service {
    public Clazz2 GetClazzById(Integer id);
    public void InsertClazz(Clazz2 clazz2);
    public Clazz2 findByClazzName(String name);
    public List<Clazz2> findClazzList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int edit(Clazz2 clazz2);
    public int delete(String ids);
}
