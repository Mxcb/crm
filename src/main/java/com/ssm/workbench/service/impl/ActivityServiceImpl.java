package com.ssm.workbench.service.impl;

import com.ssm.settings.dao.UserDao;
import com.ssm.settings.domain.User;
import com.ssm.vo.Pagination;
import com.ssm.workbench.dao.activityDao.ActivityDao;
import com.ssm.workbench.dao.activityDao.ActivityRemarkDao;
import com.ssm.workbench.domain.activity.Activity;
import com.ssm.workbench.domain.activity.ActivityRemark;
import com.ssm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityRemarkDao activityRemarkDao;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(Activity activity) {
        return activityDao.insertActivity(activity)==1;
    }

    @Override
    public Pagination<Activity> pageList(Map<String, Object> map) {
        int count=activityDao.selectCount(map);
        List<Activity> activities=activityDao.selectByLimit(map);
        Pagination<Activity> pagination=new Pagination<>();
        pagination.setTotal(count);
        pagination.setDataList(activities);
        return pagination;
    }

    @Override
    public boolean delete(String[] ids) {
        /**
         * 删除时要注意级联关系
         */
        boolean flag=true;
        //查询要删除的备注的数量
        int count=activityRemarkDao.getCountById(ids);
        //删除备注,返回受到影响的条数
        int total=activityRemarkDao.deleteByIds(ids);
        //删除市场活动记录
        int countAc=activityDao.deleteById(ids);
        if (count != total || countAc!=ids.length){
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        Map<String,Object> map=new HashMap<>();
        List<User> uList=userDao.getUserList();
        Activity activity=activityDao.selectById(id);
        map.put("uList",uList);
        map.put("a",activity);
        return map;
    }

    @Override
    public boolean updateById(Activity activity) {
        int a=activityDao.updateActivity(activity);
        boolean flag=true;
        if (a!=1) flag=false;
        return flag;
    }

    @Override
    public Activity detail(String id) {
        return activityDao.selectAndUserById(id);
    }

    @Override
    public List<ActivityRemark> getRemarkListByActId(String activityId) {
        return activityRemarkDao.getByActId(activityId);
    }

    @Override
    public boolean deleteRemark(String id) {
        boolean flag=true;
        int count=activityRemarkDao.deleteById(id);
        if (count!=1) flag=false;
        return flag;
    }

    @Override
    public boolean saveRemark(ActivityRemark remark) {
        boolean flag=true;
        int count=activityRemarkDao.insert(remark);
        if (count!=1) flag=false;
        return flag;
    }

    @Override
    public boolean updateRemark(ActivityRemark remark) {
        boolean flag=true;
        int count=activityRemarkDao.updateById(remark);
        if (count!=1) flag=false;
        return flag;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {
        return activityDao.selectByClueId(clueId);
    }

    @Override
    public List<Activity> selectByLikeName(Map<String, String> map) {
        return activityDao.selectByLike(map);
    }

    @Override
    public List<Activity> getActivityListByName(String name) {
        return activityDao.selectLikeByName(name);
    }
}
