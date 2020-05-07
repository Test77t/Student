package com.atguigu.test;

import com.atguigu.bean.User;
import com.atguigu.service.Clazz2Service;
import com.atguigu.service.ClazzService;
import com.atguigu.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Autowired
    private UserService userService;

    @Test
    public void test1()
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Clazz2Service bean = applicationContext.getBean(Clazz2Service.class);
        System.out.println(bean.GetClazzById(1));
    }
    @Test
    public void test3()
    {
        System.out.println(userService.getClass());
       // System.out.println(userService.GetUserById(1));
    }
}
