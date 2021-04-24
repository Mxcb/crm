package com.ssm.settings.dao;

import com.ssm.settings.domain.DicValue;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DicValueDao {

    List<DicValue> getListByCode(String code);

}
