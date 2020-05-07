package com.atguigu.controller;

import com.atguigu.bean.User;
import com.atguigu.page.PageList;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserList {
    @Autowired
    private UserService userService;

    //用户登录表单
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView)
    {
        modelAndView.setViewName("user/user_list");
        return modelAndView;
    }

    //添加用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(User user)
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(user.getUsername()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入用户名");
            return ret;
        }
        if(user.getPassword()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入密码");
            return ret;
        }
        User existUser = userService.findByUserName(user.getUsername());
        if(existUser != null)
        {
            //说明用户名已经存在
            ret.put("type", "errors");
            ret.put("msg", "用户名已经存在，请重新输入新的用户名");
            return ret;
        }
        userService.InsertUser(user);
        ret.put("type", "success");
        ret.put("msg", "用户添加成功");
        return ret;
    }
    //修改用户
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(User user)
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(user.getUsername()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入用户名");
            return ret;
        }
        if(user.getPassword()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入密码");
            return ret;
        }
        User existUser = userService.findByUserName(user.getUsername());
        if(existUser != null)
        {
            if(user.getId() != existUser.getId())
            {
                //说明用户名已经存在
                ret.put("type", "errors");
                ret.put("msg", "用户名已经存在，请重新输入新的用户名");
                return ret;
            }
        }
        userService.edit(user);
        ret.put("type", "success");
        ret.put("msg", "用户修改成功");
        return ret;
    }

    //获取登录表单，其中包含分页功能
    @RequestMapping("/get_list")
    @ResponseBody
    public Map<String, Object> GetList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            PageList pageList
    )
    {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", pageList.getOffset());
        queryMap.put("pageSize", pageList.getRows());
        ret.put("rows", userService.findUserList(queryMap));
        ret.put("total", userService.getTotal(queryMap));
        return ret;
    }

    //删除用户
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value = "ids[]",required = true) int[] ids
    )
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的数据");
            return ret;
        }
        String idsString = "";
        for(int id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        if(userService.delete(idsString) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功");
        return ret;
    }













}
