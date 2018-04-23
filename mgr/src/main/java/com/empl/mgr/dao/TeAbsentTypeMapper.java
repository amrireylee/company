package com.empl.mgr.dao;

import com.empl.mgr.model.TeAbsentType;

public interface TeAbsentTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeAbsentType record);

    int insertSelective(TeAbsentType record);

    TeAbsentType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeAbsentType record);

    int updateByPrimaryKey(TeAbsentType record);
}