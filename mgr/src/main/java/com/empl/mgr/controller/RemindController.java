package com.empl.mgr.controller;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.model.TeRemind;
import com.empl.mgr.service.IRemindService;
import com.empl.mgr.support.JSONReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 23:45 2018/4/24
 */
@Scope
@Controller
@RequestMapping(value = "employees/remind")
public class RemindController extends AbstractController {

    @Autowired
    private IRemindService iRemindService;

    @ResponseBody
    @RequestMapping(value = "/saveRemind", method = RequestMethod.POST)
    @SecureValid(code = "06001", desc = "保存事程信息", type = MethodType.ADD)
    public JSONReturn saveRemind(@RequestBody TeRemind teRemind, HttpSession httpSession) {
        return iRemindService.saveOrUpdate(teRemind);
    }

    @ResponseBody
    @RequestMapping(value = "/findRemindList")
    @SecureValid(code = "06001", desc = "获取事程列表", type = MethodType.FIND)
    public JSONReturn findSalaryList(HttpSession httpSession,
                                     TeRemind teRemind,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return iRemindService.list(teRemind, pageNum, pageSize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "06001", desc = "删除事程记录", type = MethodType.DELETE)
    public JSONReturn delete(HttpSession session, TeRemind teRemind) {
        return iRemindService.delete(teRemind);
    }

}
