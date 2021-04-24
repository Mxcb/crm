package com.ssm.workbench.dao.tranDao;

import com.ssm.workbench.domain.transaction.Tran;

import java.util.List;
import java.util.Map;

public interface TranDao {

    int getTotal();

    int insert(Tran tran);

    Tran selectById(String id);

    int update(Tran t);

    List<Map<String, Object>> selectByGroup();
}
