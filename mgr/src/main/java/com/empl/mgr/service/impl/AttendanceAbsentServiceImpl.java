package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeAttendanceAbsentMapper;
import com.empl.mgr.model.TeAttendanceAbsent;
import com.empl.mgr.service.IAttendanceAbsentService;
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
 * @Date: Created in 18:31 2018/4/23
 */
@Scope
@Service("iAttendanceAbsentService")
public class AttendanceAbsentServiceImpl implements IAttendanceAbsentService {
    @Autowired
    private TeAttendanceAbsentMapper teAttendanceAbsentMapper;


    public JSONReturn saveOrUpdate(TeAttendanceAbsent teAttendanceAbsent){
        if (teAttendanceAbsent!=null){
            if (teAttendanceAbsent.getId()!=null){
                int result = teAttendanceAbsentMapper.updateByPrimaryKeySelective(teAttendanceAbsent);
                if (result==0){
                    return JSONReturn.buildFailure("更新失败");
                }
                return JSONReturn.build(true,"更新成功");
            }else{
                int result = teAttendanceAbsentMapper.save(teAttendanceAbsent);
                if (result==0){
                    return JSONReturn.buildFailure("新增失败");
                }
                return JSONReturn.build(true,"新增成功");
            }
        }
        return JSONReturn.build(true,"新增或更新参数不正确");

    }

    public JSONReturn list(TeAttendanceAbsent teAttendanceAbsent, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<TeAttendanceAbsent> teAttendanceAbsentList = teAttendanceAbsentMapper.list(teAttendanceAbsent);
        PageInfo pageResult = new PageInfo(teAttendanceAbsentList);
        return JSONReturn.build(true,pageResult);
    }

    public JSONReturn delete(TeAttendanceAbsent teAttendanceAbsent){
        int rowCount = teAttendanceAbsentMapper.delete(teAttendanceAbsent.getId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("删除成功");
        }
        return JSONReturn.buildFailure("删除失败");
    }
}
