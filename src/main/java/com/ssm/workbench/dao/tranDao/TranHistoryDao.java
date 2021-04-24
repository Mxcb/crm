package com.ssm.workbench.dao.tranDao;

import com.ssm.workbench.domain.transaction.TranHistory;

import java.util.List;

public interface TranHistoryDao {

    int insert(TranHistory tranHistory);

    List<TranHistory> selectHistoryById(String tranId);
}
