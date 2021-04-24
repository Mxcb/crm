package com.ssm.settings.dao;

import com.ssm.settings.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface UserDao {

    User login(Map<String, Object> map);

    List<User> getUserList();
}
