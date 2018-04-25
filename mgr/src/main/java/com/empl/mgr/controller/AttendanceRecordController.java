package com.empl.mgr.controller;

import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.model.TeAttendanceRecord;
import com.empl.mgr.service.IAttendanceRecordService;
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
@RequestMapping(value = "employees/attendanceRecord")
public class AttendanceRecordController extends AbstractController {

    @Autowired
    private IAttendanceRecordService iAttendanceRecordService;

    @ResponseBody
    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    @SecureValid(code = "02001", desc = "保存员工出勤记录信息", type = MethodType.ADD)
    public JSONReturn saveAttendanceRecord(@RequestBody TeAttendanceRecord teAttendanceRecord, HttpSession httpSession) {
        return iAttendanceRecordService.saveOrUpdate(teAttendanceRecord);
    }

    @ResponseBody
    @RequestMapping(value = "/findAttendanceRecordList")
    @SecureValid(code = "02001", desc = "获取出勤记录列表", type = MethodType.FIND)
    public JSONReturn findAttendanceRecordList(HttpSession httpSession,
                                               TeAttendanceRecord teAttendanceRecord,
                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        return iAttendanceRecordService.list(teAttendanceRecord, pageNum, pageSize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @SecureValid(code = "02001", desc = "删除出勤记录", type = MethodType.DELETE)
    public JSONReturn delete(HttpSession session, TeAttendanceRecord teAttendanceRecord) {
        return iAttendanceRecordService.delete(teAttendanceRecord);
    }

}

