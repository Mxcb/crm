package com.ssm.workbench.web.controller;


import com.ssm.settings.domain.User;
import com.ssm.settings.service.UserService;
import com.ssm.utils.DateTimeUtil;
import com.ssm.utils.UUIDUtil;
import com.ssm.vo.Pagination;
import com.ssm.workbench.domain.activity.Activity;
import com.ssm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @RequestMapping("/save.do")
    @ResponseBody
    public Object save(Activity activity, HttpSession session) {

        Map<String,Boolean> map=new HashMap<>();
        System.out.println("执行市场活动的添加操作");
        String id= UUIDUtil.getUUID();
        String createTime= DateTimeUtil.getSysTime();
        String createBy=((User)session.getAttribute("user")).getName();

        activity.setCreateBy(createBy);
        activity.setCreateTime(createTime);
        activity.setId(id);

        boolean flag=activityService.save(activity);
        System.out.println(flag);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public Object getUserList() {
        return userService.getUserList();
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    public Object pageList(String name,String owner,String startTime,String endTime,Integer pageSize,Integer pageNo) {
        System.out.println("进入到查询市场列表活动的操作");
        //每页展示的记录数
//        int pageSize
        //当前是第几页
//        int pageNo
        Integer skipCount=(pageNo-1)*pageSize;

        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        return activityService.pageList(map);
    }

    @RequestMapping("/getUserListAndActivity.do")
    @ResponseBody
    public Object getUserListAndActivity(String id) {
        System.out.println("进入到查询用户信息列表和根据和根据市场活动id查询单条记录操作");
        return activityService.getUserListAndActivity(id);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Object delete(String[] id) {
        System.out.println("执行市场活动的删除操作");
        //获取参数数组,适用于参数名字相同
        boolean flag=activityService.delete(id);
        Map<String,Boolean> map=new HashMap<>();
        map.put("success",flag);
        return map;
    }

//    public static Object test03(){
//        return new HashMap<String,Boolean>().put("success",true);
////        return new Object();
//    }
//
//    public static void main(String[] args) {
//        System.out.println(test03());
//        Map<String,Object> map=new HashMap<>();
//        map.put("success",new Object());
//        System.out.println(map.put("success",true));
//
//    }
    @RequestMapping("/detail.do")
    public ModelAndView detail(String id) {
        System.out.println("进入到跳转详细信息页的操作");
        Activity activity=activityService.detail(id);
        ModelAndView mv=new ModelAndView("activity/detail");
        mv.addObject("act",activity);
        return mv;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public Object update(Activity activity,HttpSession session) {
        System.out.println("进入到市场活动的修改操作");
        String editTime= DateTimeUtil.getSysTime();
        String editBy=((User)session.getAttribute("user")).getName();

        activity.setCreateBy(editBy);
        activity.setCreateTime(editTime);

        boolean flag=activityService.updateById(activity);
        Map<String,Boolean> map=new HashMap<>();
        map.put("success",flag);
        return map;
    }
}
