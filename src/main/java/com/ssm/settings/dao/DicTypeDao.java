package com.ssm.settings.dao;

import com.ssm.settings.domain.DicType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DicTypeDao {

    @Select("select * from tbl_dic_type")
    List<DicType> getTypeAll();

}
