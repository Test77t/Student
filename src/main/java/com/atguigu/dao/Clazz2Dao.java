package com.atguigu.dao;

import com.atguigu.bean.Clazz2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Clazz2Dao {
    //按照id查找班级信息
    @Select("select * from clazz2 where id=#{id}")
    public Clazz2 GetClazzById(Integer id);

    //添加年级信息
    @Update("insert into clazz2(gradeId, name, remark) values(#{gradeId},#{name}, #{remark})")
    public void InsertClazz(Clazz2 clazz2);

    //通过年级名称进行查询
    @Select("select * from clazz2 where name=#{name}")
    public Clazz2 findByClazzName(String name);

    //得到分页的总数
    @Select("select count(id) from clazz2 where name like #{name}")
    public int getTotal(Map<String, Object> queryMap);

    //查询所有的年级信息
    @Select("select * from clazz2 where name like #{name} limit #{offset}, #{pageSize} ")
    public List<Clazz2> findClazzList(Map<String, Object> queryMap);

    //修改班级信息
    @Update("update clazz2 set gradeId=#{gradeId}, name=#{name},remark=#{remark} where id=#{id}")
    public int edit(Clazz2 clazz2);

    //删除班级
    @Delete("delete from clazz2 where id in (${value})")
    public int delete(String ids);
}
