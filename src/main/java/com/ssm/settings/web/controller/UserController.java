package com.ssm.settings.web.controller;

import com.ssm.settings.domain.User;
import com.ssm.settings.service.UserService;
import com.ssm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd,HttpServletRequest request,HttpServletResponse response) {
        //获取IP地址
        String ip=request.getRemoteAddr();
        System.out.println(ip);
        loginPwd= MD5Util.getMD5(loginPwd);
        Map<String,Object> map=new HashMap<>();
        try{
           User user=userService.login(loginAct,loginPwd,ip);
           request.getSession().setAttribute("user",user);
           //System.out.println(request.getSession().getAttribute("user"));
            //如果登陆验证失败,出现异常就不会执行到此处
           map.put("success",true);
        }catch (Exception e){
           e.printStackTrace();
           String msg=e.getMessage();
           map.put("success",false);
           map.put("msg",msg);
           return map;
        }
        return map;
    }
}
