package com.empl.mgr.dao;

import com.empl.mgr.model.TeAttendanceAbsent;
import com.empl.mgr.model.TeAttendanceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeAttendanceAbsentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeAttendanceAbsent record);

    int insertSelective(TeAttendanceAbsent record);

    TeAttendanceAbsent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeAttendanceAbsent record);

    int updateByPrimaryKey(TeAttendanceAbsent record);

    int save(TeAttendanceAbsent teAttendanceAbsent);

    List<TeAttendanceAbsent> list(TeAttendanceAbsent teAttendanceAbsent);

    int delete(@Param("emId") Long emId);


}