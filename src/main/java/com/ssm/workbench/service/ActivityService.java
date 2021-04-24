package com.ssm.workbench.service;

import com.ssm.vo.Pagination;
import com.ssm.workbench.domain.activity.Activity;
import com.ssm.workbench.domain.activity.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    boolean save(Activity activity);

    Pagination<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    boolean updateById(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByActId(String activityId);

    boolean deleteRemark(String id);

    boolean saveRemark(ActivityRemark remark);

    boolean updateRemark(ActivityRemark remark);

    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> selectByLikeName(Map<String, String> map);

    List<Activity> getActivityListByName(String name);
}
