package com.ssm.workbench.dao.clueDao;

import com.ssm.workbench.domain.clue.Clue;

public interface ClueDao {
    int insertOne(Clue clue);

    Clue selectById(String id);

    void deleteById(String clueId);
}
