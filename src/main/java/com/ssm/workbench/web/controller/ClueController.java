package com.ssm.workbench.web.controller;

import com.ssm.settings.domain.User;
import com.ssm.settings.service.UserService;
import com.ssm.utils.DateTimeUtil;
import com.ssm.utils.UUIDUtil;
import com.ssm.workbench.domain.clue.Clue;
import com.ssm.workbench.service.ActivityService;
import com.ssm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getActivityListByClueId.do")
    @ResponseBody
    private Object getActivityListByClueId(String clueId) {
        System.out.println("进入到根据线索ID获取市场活动列表的操作");
        return activityService.getActivityListByClueId(clueId);
    }

    @RequestMapping("/clueDetail.do")
    private ModelAndView clueDetail(String id) {
        System.out.println("进入到线索的详细信息页");
        ModelAndView mv=new ModelAndView("clue/detail");
        Clue clue=clueService.clueDetail(id);
        mv.addObject("clueOwner",clue);
        return mv;
    }

    @RequestMapping("/saveClue.do")
    @ResponseBody
    private Object saveClue(Clue clue, HttpSession session) {
        System.out.println("执行线索添加操作");
        Map<String,Object> map=new HashMap<>();

        String  id= UUIDUtil.getUUID();
        String  createBy= ((User)session.getAttribute("user")).getName();
        String  createTime= DateTimeUtil.getSysTime();

        clue.setId(id);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        boolean flag=clueService.save(clue);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/getUserList.do")
    @ResponseBody
    private Object getUserList() {
        System.out.println("进入线索模块获取用户列表");
        return userService.getUserList();
    }
}
