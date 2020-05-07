package com.atguigu.dao;

import com.atguigu.bean.Clazz;
import com.atguigu.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClazzDao {

    //按照id查找信息
    @Select("select * from clazz where id=#{id}")
    public Clazz GetClazzById(Integer id);

    //通过年级名称进行查询
    @Select("select * from clazz where name=#{name}")
    public Clazz findByClazzName(String name);

    //添加年级信息
    @Update("insert into clazz(name, remark) values(#{name}, #{remark})")
    public void InsertClazz(Clazz clazz);

    //得到分页的总数
    @Select("select count(id) from clazz where name like #{name}")
    public int getTotal(Map<String, Object> queryMap);

    //查询所有的年级信息
    @Select("select * from clazz where name like #{name} limit #{offset}, #{pageSize} ")
    public List<Clazz> findClazzList(Map<String, Object> queryMap);

    //查找所有
    @Select("select * from clazz")
    public List<Clazz> findAll();

    //修改用户信息
    @Update("update clazz set name=#{name},remark=#{remark} where id=#{id}")
    public int edit(Clazz clazz);

    //删除用户
    @Delete("delete from clazz where id in (${value})")
    public int delete(String ids);
}
