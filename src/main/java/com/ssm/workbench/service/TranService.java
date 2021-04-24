package com.ssm.workbench.service;

import com.ssm.workbench.domain.transaction.Tran;
import com.ssm.workbench.domain.transaction.TranHistory;

import java.util.List;
import java.util.Map;

public interface TranService {

    boolean save(Tran tran, String customerName);

    Tran detail(String id);

    List<TranHistory> selectHistoryById(String tranId);

    boolean changeStage(Tran t);

    Map<String, Object> getCharts();
}
