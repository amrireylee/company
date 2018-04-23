package com.empl.mgr.controller;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.model.TeSalary;
import com.empl.mgr.service.ISalaryService;
import com.empl.mgr.support.JSONReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 10:44 2018/4/23
 */
@Scope
@Controller
@RequestMapping(value = "employees/salary")
public class SalaryController  extends AbstractController {

    @Autowired
    private ISalaryService iSalaryService;

    @ResponseBody
    @RequestMapping(value = "/saveSalary", method = RequestMethod.POST)
    @SecureValid(code = "02001", desc = "保存员工薪资信息", type = MethodType.ADD)
    public JSONReturn saveSalary(@RequestBody TeSalary teSalary, HttpSession httpSession) {
        return iSalaryService.saveOrUpdate(teSalary);
    }

    @ResponseBody
    @RequestMapping(value = "/findSalaryList")
    @SecureValid(code = "02001", desc = "获取员工薪资列表", type = MethodType.FIND)
    public JSONReturn findSalaryList(HttpSession httpSession,
                                                  TeSalary teSalary,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return iSalaryService.list(teSalary, pageNum, pageSize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "02001", desc = "删除薪资记录", type = MethodType.DELETE)
    public JSONReturn delete(HttpSession session, TeSalary teSalary) {
        return iSalaryService.delete(teSalary);
    }

}
