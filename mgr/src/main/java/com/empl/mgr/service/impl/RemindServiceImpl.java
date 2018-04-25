package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeRemindMapper;
import com.empl.mgr.model.TeRemind;
import com.empl.mgr.service.IRemindService;
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
 * @Date: Created in 23:43 2018/4/24
 */
@Scope
@Service("iRemindService")
public class RemindServiceImpl implements IRemindService{
    @Autowired
    private TeRemindMapper teRemindMapper;


    @Transactional
    public JSONReturn saveOrUpdate(TeRemind teRemind){
        if (teRemind!=null){
            if (teRemind.getId()!=null){
                int result = teRemindMapper.updateByPrimaryKeySelective(teRemind);
                if (result==0){
                    return JSONReturn.buildFailure("更新失败");
                }
                return JSONReturn.build(true,"更新成功");
            }else{
                int result = teRemindMapper.save(teRemind);
                if (result==0){
                    return JSONReturn.buildFailure("新增失败(result)");
                }

                return JSONReturn.build(true,"新增成功");
            }
        }
        return JSONReturn.build(true,"新增或更新参数不正确");

    }

    public JSONReturn list(TeRemind teRemind, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<TeRemind> teRemindList = teRemindMapper.list(teRemind);
        PageInfo pageResult = new PageInfo(teRemindList);
        return JSONReturn.build(true,pageResult);
    }

    public JSONReturn delete(TeRemind teRemind){
        int rowCount = teRemindMapper.delete(teRemind.getId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("删除成功");
        }
        return JSONReturn.buildFailure("删除失败");
    }
}
