package com.ssm.workbench.dao.clueDao;

import com.ssm.workbench.domain.clue.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> selectListByClueId(String clueId);

    void delete(ClueRemark clueRemark);

}
