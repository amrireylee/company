package com.empl.mgr.service;

import com.empl.mgr.model.TeSalary;
import com.empl.mgr.support.JSONReturn;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 10:21 2018/4/23
 */
public interface ISalaryService {

    JSONReturn saveOrUpdate(TeSalary teSalary);

    JSONReturn list(TeSalary teSalary, int pageNum, int pageSize);

    JSONReturn delete(TeSalary teSalary);
}
