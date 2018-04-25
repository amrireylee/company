package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeAttendanceRecordMapper;
import com.empl.mgr.model.TeAttendanceRecord;
import com.empl.mgr.service.IAttendanceRecordService;
import com.empl.mgr.support.JSONReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 16:28 2018/4/23
 */
@Scope
@Service("iAttendanceRecordService")
public class AttendanceRecordServiceImpl implements IAttendanceRecordService {
    @Autowired
    private TeAttendanceRecordMapper teAttendanceRecordMapper;


    public JSONReturn saveOrUpdate(TeAttendanceRecord teAttendanceRecord){
        if (teAttendanceRecord!=null){
            if (teAttendanceRecord.getOutDays()==null){
                teAttendanceRecord.setOutDays(0);
            }
            if (teAttendanceRecord.getId()!=null){
                int result = teAttendanceRecordMapper.updateByPrimaryKeySelective(teAttendanceRecord);
                if (result==0){
                    return JSONReturn.buildFailure("更新失败");
                }
                return JSONReturn.build(true,"更新成功");
            }else{
                int result = teAttendanceRecordMapper.save(teAttendanceRecord);
                if (result==0){
                    return JSONReturn.buildFailure("新增失败");
                }
                return JSONReturn.build(true,"新增成功");
            }
        }
        return JSONReturn.build(true,"新增或更新参数不正确");

    }

    public JSONReturn list(TeAttendanceRecord teAttendanceRecord, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<TeAttendanceRecord> teAttendanceRecordList = teAttendanceRecordMapper.list(teAttendanceRecord);
        PageInfo pageResult = new PageInfo(teAttendanceRecordList);
        return JSONReturn.build(true,pageResult);
    }

    public JSONReturn delete(TeAttendanceRecord teAttendanceRecord){
        int rowCount = teAttendanceRecordMapper.delete(teAttendanceRecord.getEmId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("删除成功");
        }
        return JSONReturn.buildFailure("删除失败");
    }
}
