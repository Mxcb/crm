package com.ssm.workbench.service.impl;

import com.ssm.utils.UUIDUtil;
import com.ssm.workbench.dao.clueDao.ClueActivityRelationDao;
import com.ssm.workbench.dao.clueDao.ClueDao;
import com.ssm.workbench.dao.clueDao.ClueRemarkDao;
import com.ssm.workbench.domain.clue.Clue;
import com.ssm.workbench.domain.clue.ClueActivityRelation;
import com.ssm.workbench.domain.transaction.Tran;
import com.ssm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueDao clueDao;

    @Autowired
    private ClueActivityRelationDao cActRelDao;

    @Override
    public boolean save(Clue clue) {
        boolean flag=true;
        int count=clueDao.insertOne(clue);
        if (count!=1) flag=false;
        return flag;
    }

    @Override
    public Clue clueDetail(String id) {
        return clueDao.selectById(id);
    }

    @Override
    public boolean unbind(String id) {
        boolean flag=true;
        int count=cActRelDao.deleteById(id);
        if (count!=1) flag=false;
        return flag;
    }

    @Override
    public boolean bind(String[] aids, String cid) {
        int count=0;
        ClueActivityRelation clueActivityRelation=new ClueActivityRelation();
        for (String aid:aids){
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setClueId(cid);
            clueActivityRelation.setActivityId(aid);
            count+=cActRelDao.insert(clueActivityRelation);
        }
        System.out.println(count == aids.length);
        return count == aids.length;
    }

    @Override
    public boolean convert(Tran tran, String clueId, String createBy) {
        return false;
    }

}
