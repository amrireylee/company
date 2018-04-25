package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeAttendanceAbsentMapper;
import com.empl.mgr.dao.TeAttendanceRecordMapper;
import com.empl.mgr.dao.TeSalaryMapper;
import com.empl.mgr.model.TeAttendanceRecord;
import com.empl.mgr.model.TeSalary;
import com.empl.mgr.service.ISalaryService;
import com.empl.mgr.support.JSONReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.empl.mgr.utils.DateTimeUtil.getCurrentMonthLastDay;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 10:21 2018/4/23
 */
@Scope
@Service("iSalaryService")
public class SalaryServiceImpl implements ISalaryService {

    @Autowired
    private TeSalaryMapper teSalaryMapper;

    @Autowired
    private TeAttendanceRecordMapper teAttendanceRecordMapper;

    @Autowired
    private TeAttendanceAbsentMapper teAttendanceAbsentMapper;

    @Transactional
    public JSONReturn saveOrUpdate(TeSalary teSalary){
        if (teSalary!=null){
            if (teSalary.getId()!=null){
                teSalary.setSum(teSalary.getBase()+teSalary.getPost()+teSalary.getPrix());
                int result = teSalaryMapper.updateByPrimaryKeySelective(teSalary);
                if (result==0){
                    return JSONReturn.buildFailure("更新失败");
                }
                return JSONReturn.build(true,"更新成功");
            }else{
                teSalary.setSum(teSalary.getBase()+teSalary.getPost()+teSalary.getPrix());
                int resultSalary = teSalaryMapper.save(teSalary);
                if (resultSalary==0){
                    return JSONReturn.buildFailure("新增失败");
                }
                TeAttendanceRecord teAttendanceRecord = new TeAttendanceRecord();
                teAttendanceRecord.setEmId(teSalary.getEmId());
                teAttendanceRecord.setInDays(getCurrentMonthLastDay());
                teAttendanceRecord.setOutDays(0);
                int resultRecord = teAttendanceRecordMapper.save(teAttendanceRecord);
                if (resultRecord==0){
                    return JSONReturn.buildFailure("新增失败");
                }
                return JSONReturn.build(true,"新增成功");
            }
        }
        return JSONReturn.build(true,"新增或更新参数不正确");

    }

    public JSONReturn list(TeSalary teSalary, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<TeSalary> teSalaryList = teSalaryMapper.list(teSalary);
        PageInfo pageResult = new PageInfo(teSalaryList);
        return JSONReturn.build(true,pageResult);
    }

    public JSONReturn delete(TeSalary teSalary){
        int rowCount = teSalaryMapper.delete(teSalary.getEmId());
        if (rowCount==0){
            return JSONReturn.buildFailure("删除失败");
        }
        int recordCount = teAttendanceRecordMapper.delete(teSalary.getEmId());
        if (recordCount==0){
            return JSONReturn.buildFailure("删除失败");
        }
        int absentCount = teAttendanceAbsentMapper.delete(teSalary.getEmId());
        if (absentCount==0){
            return JSONReturn.buildFailure("删除失败");
        }return JSONReturn.buildSuccess("删除成功");
    }
}
