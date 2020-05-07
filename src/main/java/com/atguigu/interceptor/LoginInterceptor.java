package com.atguigu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Object user = request.getSession().getAttribute("user");
        if(user == null)
        {
            if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                //ajax请求
                Map<String, String> ret = new HashMap<String, String>();
                ret.put("type", "error");
                ret.put("msg", "登录状态已失去，请重新登录");
                //response.getWriter().write(JSONObject.fromObject(ret).toString());
                return false;
            }
            //说明用户未登录，或者登录失效
            System.out.println("用户未登录，或者登录失效");
            response.sendRedirect("http://localhost:8080/hello");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
