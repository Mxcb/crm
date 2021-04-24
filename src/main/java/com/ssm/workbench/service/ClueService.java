package com.ssm.workbench.service;

import com.ssm.workbench.domain.clue.Clue;
import com.ssm.workbench.domain.transaction.Tran;

public interface ClueService {
    boolean save(Clue clue);

    Clue clueDetail(String id);

    boolean unbind(String id);

    boolean bind(String[] aids,String cid);

    boolean convert(Tran tran,String clueId,String createBy);
}
