package com.ssm.workbench.dao.clueDao;

import com.ssm.workbench.domain.clue.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {

    int deleteById(String id);

    int insert(ClueActivityRelation clueActivityRelation);

    List<ClueActivityRelation> selectByClueId(String clueId);

    void delete(ClueActivityRelation clueActivityRelation);
}
