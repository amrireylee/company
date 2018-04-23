package com.empl.mgr.controller;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.model.TeAttendanceAbsent;
import com.empl.mgr.service.IAttendanceAbsentService;
import com.empl.mgr.support.JSONReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 16:43 2018/4/23
 */
@Scope
@Controller
@RequestMapping(value = "employees/attendanceAbsent")
public class AttendanceAbsentController extends AbstractController {

    @Autowired
    private IAttendanceAbsentService iAttendanceAbsentService;

    @ResponseBody
    @RequestMapping(value = "/saveAbsent", method = RequestMethod.POST)
    @SecureValid(code = "02001", desc = "保存员工缺勤信息", type = MethodType.ADD)
    public JSONReturn saveAttendanceAbsent(@RequestBody TeAttendanceAbsent teAttendanceAbsent, HttpSession httpSession) {
        return iAttendanceAbsentService.saveOrUpdate(teAttendanceAbsent);
    }

    @ResponseBody
    @RequestMapping(value = "/findAttendanceAbsentList")
    @SecureValid(code = "02001", desc = "获取缺勤记录列表", type = MethodType.FIND)
    public JSONReturn findAttendanceAbsentList(HttpSession httpSession,
                                               TeAttendanceAbsent teAttendanceAbsent,
                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return iAttendanceAbsentService.list(teAttendanceAbsent, pageNum, pageSize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "02001", desc = "删除缺勤记录", type = MethodType.DELETE)
    public JSONReturn delete(HttpSession session, TeAttendanceAbsent teAttendanceAbsent) {
        return iAttendanceAbsentService.delete(teAttendanceAbsent);
    }

}

