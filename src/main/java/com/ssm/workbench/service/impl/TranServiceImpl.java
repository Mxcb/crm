package com.ssm.workbench.service.impl;

import com.ssm.utils.DateTimeUtil;
import com.ssm.utils.UUIDUtil;
import com.ssm.workbench.dao.tranDao.TranDao;
import com.ssm.workbench.dao.tranDao.TranHistoryDao;
import com.ssm.workbench.domain.transaction.Tran;
import com.ssm.workbench.domain.transaction.TranHistory;
import com.ssm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TranServiceImpl implements TranService {

    @Autowired
    private TranDao tranDao;

    @Autowired
    private TranHistoryDao tranHistoryDao;

    @Override
    public boolean save(Tran tran, String customerName) {
        return false;
    }

    @Override
    public Tran detail(String id) {
        return tranDao.selectById(id);
    }

    @Override
    public List<TranHistory> selectHistoryById(String tranId) {
        return null;
    }

    @Override
    public boolean changeStage(Tran t) {
        boolean flag=true;
        int count=tranDao.update(t);
        if (count!=1) flag=false;
        if (flag){
            TranHistory tranHistory=new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setMoney(t.getMoney());
            tranHistory.setStage(t.getStage());
            tranHistory.setExpectedDate(t.getExpectedDate());
            tranHistory.setCreateBy(t.getEditBy());
            tranHistory.setCreateTime(DateTimeUtil.getSysTime());
            tranHistory.setTranId(t.getId());
            int countTranHis=tranHistoryDao.insert(tranHistory);
            if (countTranHis!=1) flag=false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getCharts() {
        return null;
    }
}
