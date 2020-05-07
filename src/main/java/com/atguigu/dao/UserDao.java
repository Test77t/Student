package com.atguigu.dao;

import com.atguigu.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    @Select("select * from user where id=#{id}")
    public User GetUserById(Integer id);

    @Update("insert into user(username, password) values(#{username}, #{password})")
    public void InsertUser(User user);

    //通过用户名进行查询
    @Select("select * from user where username=#{username}")
    public User findByUserName(String username);

    //查询所有的用户
    @Select("select * from user where username like #{username} limit #{offset}, #{pageSize} ")
    public List<User> findUserList(Map<String, Object> queryMap);

    //得到分页的总数
    @Select("select count(id) from user where username like #{username}")
    public int getTotal(Map<String, Object> queryMap);


    //修改用户信息
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int edit(User user);

    //删除用户
    @Delete("delete from user where id in (${value})")
    public int delete(String ids);
}
