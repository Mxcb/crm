package com.ssm.workbench.web.controller;

import com.ssm.settings.domain.User;
import com.ssm.utils.DateTimeUtil;
import com.ssm.workbench.domain.transaction.Tran;
import com.ssm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("workbench/transaction/")
public class TransactionController {

    @Autowired
    private TranService tranService;

    @RequestMapping("/changeStage.do")
    @ResponseBody
    private Object changeStage(Tran tran,HttpServletRequest request) {
        System.out.println("进入到修改阶段的操作");
        String editBy=((User)request.getSession().getAttribute("user")).getName();
        String editTime= DateTimeUtil.getSysTime();
        Map<String,String> m= (Map<String,String>) request.getServletContext().getAttribute("mapBundle");
        tran.setEditBy(editBy);
        tran.setEditTime(editTime);
        tran.setPossibility(m.get(tran.getStage()));
        boolean flag=tranService.changeStage(tran);
        Map<String,Object> map=new HashMap<>();
        map.put("success",flag);
        map.put("tran",tran);
        return map;
    }

    @RequestMapping("/detail.do")
    private ModelAndView detail(String id, HttpServletRequest request) {
        System.out.println("进入到跳转详细页");
        ModelAndView mv=new ModelAndView("transaction/detail");
        Tran tran=tranService.detail(id);
        Map<String,String> map= (Map<String, String>) request.getServletContext().getAttribute("mapBundle");
        tran.setPossibility(map.get(tran.getStage()));
        mv.addObject("tran",tran);
        return mv;
    }
}
