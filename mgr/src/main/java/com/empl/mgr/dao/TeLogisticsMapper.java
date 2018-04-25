package com.empl.mgr.dao;

import com.empl.mgr.model.TeLogistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeLogisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeLogistics record);

    int insertSelective(TeLogistics record);

    TeLogistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeLogistics record);

    int updateByPrimaryKey(TeLogistics record);

    int save(TeLogistics teLogistics);

    List<TeLogistics> list(TeLogistics teLogistics);

    int delete(@Param("id") Long id);

    int use(@Param("id") Long id);

    int notUse(@Param("id") Long id);

}