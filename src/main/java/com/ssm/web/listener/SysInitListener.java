package com.ssm.web.listener;

import com.ssm.settings.domain.DicValue;
import com.ssm.settings.service.DicService;
import com.ssm.settings.service.impl.DicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;


public class SysInitListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {


    // Public constructor is required by servlet spec
    public SysInitListener() {

    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        //通过sec对象获取监听到的上下文域对象,source就是application
        //javax.servlet.ServletContextEvent[source=org.apache.catalina.core.ApplicationContextFacade@626f893a]
        System.out.println("上下文对象创建,服务器缓存数据字典开始");
        ServletContext application=sce.getServletContext();
        DicService dicService =  (DicService) WebApplicationContextUtils.getWebApplicationContext(application).getBean("dicServiceImpl");
        Map<String, List<DicValue>> map=dicService.getDic();
        Set<String> keys=map.keySet();
        for (String key:keys){
            application.setAttribute(key,map.get(key));
        }
//        System.out.println(map);
        System.out.println("服务器缓存数据字典结束");

        ResourceBundle resourceBundle=ResourceBundle.getBundle("stage2possibility");
        Enumeration<String> bundleKeys = resourceBundle.getKeys();
        Map<String,String> bundleMap=new HashMap<>();
        while(bundleKeys.hasMoreElements()){
            String key= bundleKeys.nextElement();
            String value=resourceBundle.getString(key);
            bundleMap.put(key,value);
        }
        application.setAttribute("mapBundle",bundleMap);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
