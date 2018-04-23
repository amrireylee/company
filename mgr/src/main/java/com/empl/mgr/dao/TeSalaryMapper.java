package com.empl.mgr.dao;

import com.empl.mgr.model.TeSalary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeSalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeSalary record);

    int insertSelective(TeSalary record);

    TeSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeSalary record);

    int updateByPrimaryKey(TeSalary record);

    int save(TeSalary teSalary);

    List<TeSalary> list(TeSalary teSalary);

    int delete(@Param("id") Long id);
}