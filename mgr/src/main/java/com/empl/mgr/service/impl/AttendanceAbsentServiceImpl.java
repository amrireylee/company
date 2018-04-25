package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeAbsentTypeMapper;
import com.empl.mgr.dao.TeAttendanceAbsentMapper;
import com.empl.mgr.dao.TeAttendanceRecordMapper;
import com.empl.mgr.dao.TeSalaryMapper;
import com.empl.mgr.model.TeAbsentType;
import com.empl.mgr.model.TeAttendanceAbsent;
import com.empl.mgr.service.IAttendanceAbsentService;
import com.empl.mgr.support.JSONReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private TeAttendanceRecordMapper teAttendanceRecordMapper;

    @Autowired
    private TeSalaryMapper teSalaryMapper;

    @Autowired
    private TeAbsentTypeMapper teAbsentTypeMapper;


    @Transactional
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
                    return JSONReturn.buildFailure("新增失败(result)");
                }
                int resultRecord = teAttendanceRecordMapper.addAbsent(teAttendanceAbsent.getEmId());
                if (resultRecord==0){
                    return JSONReturn.buildFailure("新增失败(resultRecord)");
                }
                TeAbsentType teAbsentType = teAbsentTypeMapper.selectByPrimaryKey(teAttendanceAbsent.getTypeId());
                if (teAbsentType == null){
                    return JSONReturn.buildFailure("新增失败(teAbsentType)");
                }
                int resultSalary = teSalaryMapper.subPrix(teAbsentType.getAmount().floatValue(),teAttendanceAbsent.getEmId());
                if (resultSalary == 0){
                    return JSONReturn.buildFailure("新增失败(resultSalary)");
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
        int rowCount = teAttendanceAbsentMapper.delete(teAttendanceAbsent.getEmId());
        if (rowCount==0){
            return JSONReturn.buildFailure("删除失败");
        }
        int resultRecord = teAttendanceRecordMapper.subAbsent(teAttendanceAbsent.getEmId());
        if (resultRecord==0){
            return JSONReturn.buildFailure("删除失败(resultRecord)");
        }
        TeAbsentType teAbsentType = teAbsentTypeMapper.selectByPrimaryKey(teAttendanceAbsent.getTypeId());
        if (teAbsentType == null){
            return JSONReturn.buildFailure("删除失败(teAbsentType)");
        }
        int resultSalary = teSalaryMapper.subPrix(teAbsentType.getAmount().floatValue(),teAttendanceAbsent.getEmId());
        if (resultSalary == 0){
            return JSONReturn.buildFailure("删除失败(resultSalary)");
        }
        return JSONReturn.buildSuccess("删除成功");
    }
}
