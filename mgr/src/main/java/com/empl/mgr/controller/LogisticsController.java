package com.empl.mgr.controller;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.model.TeLogistics;
import com.empl.mgr.service.ILogisticsService;
import com.empl.mgr.support.JSONReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 23:51 2018/4/24
 */
@Scope
@Controller
@RequestMapping(value = "employees/logistics")
public class LogisticsController extends AbstractController {

    @Autowired
    private ILogisticsService iLogisticsService;

    @ResponseBody
    @RequestMapping(value = "/saveLogistics", method = RequestMethod.POST)
    @SecureValid(code = "05001", desc = "保存后勤信息", type = MethodType.ADD)
    public JSONReturn saveLogistics(@RequestBody TeLogistics teLogistics, HttpSession httpSession) {
        return iLogisticsService.saveOrUpdate(teLogistics);
    }

    @ResponseBody
    @RequestMapping(value = "/findLogisticsList")
    @SecureValid(code = "05001", desc = "获取后勤列表", type = MethodType.FIND)
    public JSONReturn findLogisticsList(HttpSession httpSession,
                                     TeLogistics teLogistics,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return iLogisticsService.list(teLogistics, pageNum, pageSize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "05001", desc = "删除后勤记录", type = MethodType.DELETE)
    public JSONReturn delete(HttpSession session, TeLogistics teLogistics) {
        return iLogisticsService.delete(teLogistics);
    }

    @RequestMapping(value = "use", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "05001", desc = "使用物品", type = MethodType.MODIFY)
    public JSONReturn useLogistics(HttpSession session, TeLogistics teLogistics) {
        return iLogisticsService.useLogistics(teLogistics);
    }

    @RequestMapping(value = "notUse", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "05001", desc = "返还物品", type = MethodType.MODIFY)
    public JSONReturn notUseLogistics(HttpSession session, TeLogistics teLogistics) {
        return iLogisticsService.notUseLogistics(teLogistics);
    }
}
