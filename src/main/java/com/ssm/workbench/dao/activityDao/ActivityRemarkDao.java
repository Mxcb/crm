package com.ssm.workbench.dao.activityDao;

import com.ssm.workbench.domain.activity.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {

    int getCountById(String[] ids);

    int deleteByIds(String[] ids);

    List<ActivityRemark> getByActId(String activityId);

    int deleteById(String id);

    int insert(ActivityRemark remark);

    int updateById(ActivityRemark remark);
}
