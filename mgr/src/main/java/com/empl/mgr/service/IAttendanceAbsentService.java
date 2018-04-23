package com.empl.mgr.service;

import com.empl.mgr.model.TeAttendanceAbsent;
import com.empl.mgr.support.JSONReturn;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 16:27 2018/4/23
 */
public interface IAttendanceAbsentService {

    JSONReturn saveOrUpdate(TeAttendanceAbsent teAttendanceAbsent);

    JSONReturn list(TeAttendanceAbsent teAttendanceAbsent, int pageNum, int pageSize);

    JSONReturn delete(TeAttendanceAbsent teAttendanceAbsent);

}
