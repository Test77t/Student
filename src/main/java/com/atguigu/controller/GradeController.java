package com.atguigu.controller;

import com.atguigu.bean.Clazz;
import com.atguigu.page.PageList;
import com.atguigu.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/grade")
@Controller
public class GradeController {

    @Autowired
    private ClazzService clazzService;

    //页面跳转
    @RequestMapping("/list")
    public String GoToList()
    {
        return "grade/grade_list";
    }

    //添加用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Clazz clazz)
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(clazz.getName()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入班级名");
            return ret;
        }
        if(clazz.getRemark()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入这个班级的备注");
            return ret;
        }
        Clazz clazz2 = clazzService.findByClazzName(clazz.getName());
        if(clazz2 != null)
        {
            //说明班级已经存在
            ret.put("type", "errors");
            ret.put("msg", "班级添加失败，班级名字重复");
            return ret;
        }
        //调用数据库，进行添加
        clazzService.InsertClazz(clazz);
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
        ret.put("rows", clazzService.findClazzList(queryMap));
        ret.put("total", clazzService.getTotal(queryMap));
        return ret;
    }

    //修改用户
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Clazz clazz)
    {
        Map<String, String> ret = new HashMap<String, String>();
        if(clazz.getName()==null)
        {
            //说明没有输入用户名
            ret.put("type", "errors");
            ret.put("msg", "请输入年级名称");
            return ret;
        }
        if(clazz.getRemark() == null)
        {
            //说明没有输入备注信息
            ret.put("type", "errors");
            ret.put("msg", "请输入这个年级的备注信息");
            return ret;
        }
        Clazz existClazz = clazzService.findByClazzName(clazz.getName());
        if(existClazz != null)
        {
            if(clazz.getId() != existClazz.getId())
            {
                //说明用年级已经存在
                ret.put("type", "errors");
                ret.put("msg", "班级信息已经存在，请重新输入新的年级信息");
                return ret;
            }
        }
        clazzService.edit(clazz);
        ret.put("type", "success");
        ret.put("msg", "年级信息修改成功");
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
        if(clazzService.delete(idsString) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功");
        return ret;
    }

}
