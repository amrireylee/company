package com.empl.mgr.dao;

import com.empl.mgr.model.TeAttendanceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeAttendanceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeAttendanceRecord record);

    int insertSelective(TeAttendanceRecord record);

    TeAttendanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeAttendanceRecord record);

    int updateByPrimaryKey(TeAttendanceRecord record);

    int save(TeAttendanceRecord teAttendanceRecord);

    List<TeAttendanceRecord> list(TeAttendanceRecord teAttendanceRecord);

    int delete(@Param("id") Long id);
}