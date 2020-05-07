package com.atguigu.service;

import com.atguigu.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    public User GetUserById(Integer id);
    public void InsertUser(User user);
    public User findByUserName(String username);
    public List<User> findUserList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public int edit(User user);
    public int delete(String ids);
}
