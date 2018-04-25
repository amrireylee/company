package com.empl.mgr.dao;

import com.empl.mgr.model.TeRemind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeRemindMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeRemind record);

    int insertSelective(TeRemind record);

    TeRemind selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeRemind record);

    int updateByPrimaryKey(TeRemind record);

    int save(TeRemind teRemind);

    List<TeRemind> list(TeRemind teRemind);

    int delete(@Param("id") Long id);
}