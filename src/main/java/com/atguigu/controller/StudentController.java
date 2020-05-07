package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/student")
@Controller
public class StudentController {

    //页面跳转
    @RequestMapping("/list")
    public ModelAndView GotoList(ModelAndView modelAndView)
    {
        modelAndView.setViewName("student/student_list");
        return modelAndView;
    }


}
