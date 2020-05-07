package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User GetUserById(Integer id) {
        return userDao.GetUserById(id);
    }

    @Override
    public void InsertUser(User user) {
        userDao.InsertUser(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return userDao.getTotal(queryMap);
    }

    @Override
    public int edit(User user) {
        return userDao.edit(user);
    }

    @Override
    public int delete(String ids) {
        return userDao.delete(ids);
    }

    @Override
    public List<User> findUserList(Map queryMap) {
        return userDao.findUserList(queryMap);
    }
}
