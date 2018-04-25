package com.empl.mgr.dao;

import com.empl.mgr.model.TeProfit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeProfitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeProfit record);

    int insertSelective(TeProfit record);

    TeProfit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeProfit record);

    int updateByPrimaryKey(TeProfit record);

    int save(TeProfit teProfit);

    List<TeProfit> list(TeProfit teProfit);

    int delete(@Param("id") Long id);

    TeProfit selectLast();
}