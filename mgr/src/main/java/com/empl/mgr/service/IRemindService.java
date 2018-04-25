package com.empl.mgr.service;

import com.empl.mgr.model.TeRemind;
import com.empl.mgr.support.JSONReturn;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 23:42 2018/4/24
 */
public interface IRemindService {
    JSONReturn saveOrUpdate(TeRemind teRemind);

    JSONReturn list(TeRemind teRemind, int pageNum, int pageSize);

    JSONReturn delete(TeRemind teRemind);
}
