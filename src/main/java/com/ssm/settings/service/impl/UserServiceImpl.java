package com.ssm.settings.service.impl;

import com.ssm.exception.LoginException;
import com.ssm.settings.dao.UserDao;
import com.ssm.settings.domain.User;
import com.ssm.settings.service.UserService;
import com.ssm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user=userDao.login(map);
        if (user==null){
            throw new LoginException("账号密码错误");
        }
        //如果程序执行到此处,说明登陆验证成功,需要继续向下验证
        String expireTime=user.getExpireTime();
        String currentTime= DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime)<0){
            throw new LoginException("帐号已失效");
        }
        String lookState=user.getLockState();
        if ("0".equals(lookState)){
            throw new LoginException("账号已锁定");
        }

        String allowIps=user.getAllowIps();
        if (!allowIps.contains(ip)){
            throw new LoginException("ip地址受限");
        }
        //执行到此处,说明以上未抛出异常
        return user;
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

}
