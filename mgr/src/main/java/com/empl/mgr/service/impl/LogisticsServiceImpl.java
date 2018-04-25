package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeLogisticsMapper;
import com.empl.mgr.model.TeLogistics;
import com.empl.mgr.service.ILogisticsService;
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
 * @Date: Created in 21:12 2018/4/24
 */
@Scope
@Service("iLogisticsService")
public class LogisticsServiceImpl implements ILogisticsService {
    @Autowired
    private TeLogisticsMapper teLogisticsMapper;


    @Transactional
    public JSONReturn saveOrUpdate(TeLogistics teLogistics){
        if (teLogistics!=null){
            if (teLogistics.getId()!=null){
                int result = teLogisticsMapper.updateByPrimaryKeySelective(teLogistics);
                if (result==0){
                    return JSONReturn.buildFailure("更新失败");
                }
                return JSONReturn.build(true,"更新成功");
            }else{
                teLogistics.setUseNum(0);
                teLogistics.setNotUseNum(teLogistics.getTotal());
                int result = teLogisticsMapper.save(teLogistics);
                if (result==0){
                    return JSONReturn.buildFailure("新增失败(result)");
                }

                return JSONReturn.build(true,"新增成功");
            }
        }
        return JSONReturn.build(true,"新增或更新参数不正确");

    }

    public JSONReturn list(TeLogistics teLogistics, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<TeLogistics> teLogisticsList = teLogisticsMapper.list(teLogistics);
        PageInfo pageResult = new PageInfo(teLogisticsList);
        return JSONReturn.build(true,pageResult);
    }

    public JSONReturn delete(TeLogistics teLogistics){
        int rowCount = teLogisticsMapper.delete(teLogistics.getId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("删除成功");
        }
        return JSONReturn.buildFailure("删除失败");
    }

    public JSONReturn useLogistics(TeLogistics teLogistics){
        int rowCount = teLogisticsMapper.use(teLogistics.getId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("更新成功");
        }
        return JSONReturn.buildFailure("更新失败");
    }

    public JSONReturn notUseLogistics(TeLogistics teLogistics){
        int rowCount = teLogisticsMapper.notUse(teLogistics.getId());
        if (rowCount>0){
            return JSONReturn.build(true,"更新成功");
        }
        return JSONReturn.buildFailure("更新失败");
    }
}
