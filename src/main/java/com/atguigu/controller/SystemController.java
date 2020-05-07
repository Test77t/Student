package com.atguigu.controller;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.tools.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SystemController {
    @Autowired
    private UserService userService;
    @RequestMapping("/hello")
    public String Test()
    {
        //来到登录界面
        return "system/login";
    }

    //退出处理
    @RequestMapping("/login_out")
    public String login_out()
    {
        System.out.println("系统正常退出，谢谢光临");
        return "system/login";
    }





    @RequestMapping("/index")
    public String Test3()
    {
        return "system/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(
            @RequestParam(value="username",required=true) String username,
            @RequestParam(value="password",required=true) String password,
            @RequestParam(value="vcode",required=true) String vcode,
            @RequestParam(value="type",required=true) int type,
            HttpServletRequest request)
    {

        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(username)){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空");
            return ret;
        }
        if(StringUtils.isEmpty(password)){
            ret.put("type", "error");
            ret.put("msg", "密码不能为空");
            return ret;
        }
        if(StringUtils.isEmpty(vcode)){
            ret.put("type", "error");
            ret.put("msg", "验证码不能为空");
            return ret;
        }
        String loginCpacha = (String)request.getSession().getAttribute("loginCpacha");
        if(StringUtils.isEmpty(loginCpacha)){
            ret.put("type", "error");
            ret.put("msg", "长时间没进行操作，请刷新页面");
            return ret;
        }
        if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
            ret.put("type", "error");
            ret.put("msg", "验证码输入错误");
            return ret;
        }
        request.getSession().setAttribute("loginCpacha", null);
        //接下来进行数据库操作
        if(type == 1){
            //管理员进行登录
            User user = userService.findByUserName(username);
            if(user == null){
                ret.put("type", "error");
                ret.put("msg", "该用户不存在");
                return ret;
            }
            if(!password.equals(user.getPassword())){
                ret.put("type", "error");
                ret.put("msg", "用户密码输入错误，请重新输入");
                return ret;
            }

            request.getSession().setAttribute("user", user);

        }
        /*if(type == 2){
            //学生登录
            Student student = studentService.findByUserName(username);
            if(student == null){
                ret.put("type", "error");
                ret.put("msg", "该学生不存在");
                return ret;
            }
            if(!password.equals(student.getPassword())){
                ret.put("type", "error");
                ret.put("msg", "学生密码输入错误");
                return ret;
            }
            request.getSession().setAttribute("user", student);
        }*/
        request.getSession().setAttribute("userType", type);
        //request.setAttribute("userType", true);
        ret.put("type", "success");
        ret.put("msg", "登录成功");
        return ret;
    }

    @RequestMapping(value="/getCpacha",method= RequestMethod.GET)
    public void getCpacha(HttpServletRequest request,
              @RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
              @RequestParam(value="w",defaultValue="98",required=false) Integer w,
              @RequestParam(value="h",defaultValue="33",required=false) Integer h,
                          HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCpacha", generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
