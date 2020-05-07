package com.atguigu.controller;

import com.atguigu.bean.Clazz;
import com.atguigu.bean.Clazz2;
import com.atguigu.page.PageList;
import com.atguigu.service.Clazz2Service;
import com.atguigu.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/clazz")
@Controller
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private Clazz2Service clazz2Service;

    //页面跳转
    @RequestMapping("/list")
    public ModelAndView GoToList(ModelAndView modelAndView)
    {
        modelAndView.setViewName("clazz/clazzList");
        modelAndView.addObject("gradeList", clazzService.findAll());
        return modelAndView;
    }

    //添加用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Clazz2 clazz2)
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(clazz2.getName()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入班级名");
            return ret;
        }
        if(clazz2.getRemark()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入这个班级的备注");
            return ret;
        }
        Clazz2 clazz = clazz2Service.findByClazzName(clazz2.getName());
        if(clazz != null)
        {
            //说明年级已经存在
            ret.put("type", "errors");
            ret.put("msg", "年级添加失败，年级名字重复");
            return ret;
        }
        //调用数据库，进行添加
        clazz2Service.InsertClazz(clazz2);
        ret.put("type", "success");
        ret.put("msg", "班级添加成功");
        return ret;
    }

    //获取登录表单，其中包含分页功能
    @RequestMapping("/get_list")
    @ResponseBody
    public Map<String, Object> GetList(
            @RequestParam(value = "name",required = false,defaultValue = "") String name,
            PageList pageList
    )
    {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", "%"+name+"%");
        queryMap.put("offset", pageList.getOffset());
        queryMap.put("pageSize", pageList.getRows());
        ret.put("rows", clazz2Service.findClazzList(queryMap));
        ret.put("total", clazz2Service.getTotal(queryMap));
        return ret;
    }

    //修改用户
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Clazz2 clazz2)
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(clazz2.getName()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入班级名称");
            return ret;
        }
        if(clazz2.getRemark() == null)
        {
            //说明没有输入备注信息
            ret.put("type", "errors");
            ret.put("msg", "请输入这个班级的备注信息");
            return ret;
        }
        Clazz2 existClazz2 = clazz2Service.findByClazzName(clazz2.getName());
        if(existClazz2 != null)
        {
            if(clazz2.getId() != existClazz2.getId())
            {
                //说明用年级已经存在
                ret.put("type", "errors");
                ret.put("msg", "班级信息已经存在，请重新输入新的班级信息");
                return ret;
            }
        }
        clazz2Service.edit(clazz2);
        ret.put("type", "success");
        ret.put("msg", "班级信息修改成功");
        return ret;
    }

    //删除年级信息
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
        if(clazz2Service.delete(idsString) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功");
        return ret;
    }

}
