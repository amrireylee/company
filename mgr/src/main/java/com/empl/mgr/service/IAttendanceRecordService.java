package com.empl.mgr.service;

import com.empl.mgr.model.TeAttendanceRecord;
import com.empl.mgr.support.JSONReturn;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 16:28 2018/4/23
 */
public interface IAttendanceRecordService {
    JSONReturn saveOrUpdate(TeAttendanceRecord teAttendanceRecord);

    JSONReturn list(TeAttendanceRecord teAttendanceRecord, int pageNum, int pageSize);

    JSONReturn delete(TeAttendanceRecord teAttendanceRecord);
}
