package com.empl.mgr.service;

import com.empl.mgr.model.TeLogistics;
import com.empl.mgr.support.JSONReturn;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 21:12 2018/4/24
 */
public interface ILogisticsService {
    JSONReturn saveOrUpdate(TeLogistics teLogistics);

    JSONReturn list(TeLogistics teLogistics, int pageNum, int pageSize);

    JSONReturn delete(TeLogistics teLogistics);

    JSONReturn useLogistics(TeLogistics teLogistics);

    JSONReturn notUseLogistics(TeLogistics teLogistics);

}
