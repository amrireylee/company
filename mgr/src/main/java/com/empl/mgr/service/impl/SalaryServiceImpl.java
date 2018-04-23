package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeSalaryMapper;
import com.empl.mgr.model.TeSalary;
import com.empl.mgr.service.ISalaryService;
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
 * @Date: Created in 10:21 2018/4/23
 */
@Scope
@Service("iSalaryService")
public class SalaryServiceImpl implements ISalaryService {

    @Autowired
    private TeSalaryMapper teSalaryMapper;

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
                int result = teSalaryMapper.save(teSalary);
                if (result==0){
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
        int rowCount = teSalaryMapper.delete(teSalary.getId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("删除成功");
        }
        return JSONReturn.buildFailure("删除失败");
    }
}
