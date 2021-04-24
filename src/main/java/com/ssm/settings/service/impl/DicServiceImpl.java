package com.ssm.settings.service.impl;

import com.ssm.settings.dao.DicTypeDao;
import com.ssm.settings.dao.DicValueDao;
import com.ssm.settings.domain.DicType;
import com.ssm.settings.domain.DicValue;
import com.ssm.settings.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DicTypeDao dicTypeDao;

    @Autowired
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getDic() {
        Map<String,List<DicValue>> map=new HashMap<>();
        List<DicType> types= dicTypeDao.getTypeAll();
        for (DicType dicType:types){
            String code=dicType.getCode();
            List<DicValue> dicValues=dicValueDao.getListByCode(code);
            map.put(code,dicValues);
        }
        return map;
    }
}
