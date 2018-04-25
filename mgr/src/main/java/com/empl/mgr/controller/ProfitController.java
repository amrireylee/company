package com.empl.mgr.controller;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.model.TeProfit;
import com.empl.mgr.service.IProfitService;
import com.empl.mgr.support.JSONReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 16:24 2018/4/25
 */
@Scope
@Controller
@RequestMapping(value = "employees/profit")
public class ProfitController {

    @Autowired
    private IProfitService iProfitService;

    @ResponseBody
    @RequestMapping(value = "/saveProfit", method = RequestMethod.POST)
    @SecureValid(code = "07001", desc = "保存资产收支信息", type = MethodType.ADD)
    public JSONReturn saveProfit(@RequestBody TeProfit teProfit, HttpSession httpSession) {
        return iProfitService.saveOrUpdate(teProfit);
    }

    @ResponseBody
    @RequestMapping(value = "/findProfitList")
    @SecureValid(code = "07001", desc = "获取资产收支列表", type = MethodType.FIND)
    public JSONReturn findProfitList(HttpSession httpSession,
                                     TeProfit teProfit,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return iProfitService.list(teProfit, pageNum, pageSize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "07001", desc = "删除资产收支记录", type = MethodType.DELETE)
    public JSONReturn delete(HttpSession session, TeProfit teProfit) {
        return iProfitService.delete(teProfit);
    }

}
