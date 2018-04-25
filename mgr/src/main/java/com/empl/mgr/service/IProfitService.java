package com.empl.mgr.service;

import com.empl.mgr.model.TeProfit;
import com.empl.mgr.support.JSONReturn;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 15:52 2018/4/25
 */
public interface IProfitService {
    JSONReturn saveOrUpdate(TeProfit teProfit);

    JSONReturn list(TeProfit teProfit, int pageNum, int pageSize);

    JSONReturn delete(TeProfit teProfit);
}
