package com.ssm.settings.service;

import com.ssm.exception.LoginException;
import com.ssm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginName, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
