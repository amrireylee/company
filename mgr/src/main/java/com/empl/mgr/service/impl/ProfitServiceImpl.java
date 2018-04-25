package com.empl.mgr.service.impl;

import com.empl.mgr.dao.TeProfitMapper;
import com.empl.mgr.model.TeProfit;
import com.empl.mgr.service.IProfitService;
import com.empl.mgr.support.JSONReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 15:51 2018/4/25
 */
@Scope
@Service("iProfitService")
public class ProfitServiceImpl implements IProfitService {
    @Autowired
    private TeProfitMapper teProfitMapper;


    @Transactional
    public JSONReturn saveOrUpdate(TeProfit teProfit){
        if (teProfit!=null){
            if (teProfit.getId()!=null){
                int result = teProfitMapper.updateByPrimaryKeySelective(teProfit);
                if (result==0){
                    return JSONReturn.buildFailure("更新失败");
                }
                return JSONReturn.build(true,"更新成功");
            }else{
                TeProfit profit = teProfitMapper.selectLast();
                teProfit.setPrincipal(profit.getPrincipal());
                if (profit.getIncome()==null){
                    profit.setIncome(BigDecimal.valueOf(0));
                }
                if (profit.getAllIncome()==null){
                    profit.setAllIncome(BigDecimal.valueOf(0));
                }
                if (profit.getAllExpense()==null){
                    profit.setAllExpense(BigDecimal.valueOf(0));
                }
                if (profit.getProfit()==null){
                    profit.setProfit(BigDecimal.valueOf(0));
                }
                if (teProfit.getType()==1){
                    //收入
                    teProfit.setProfit(profit.getProfit().add(teProfit.getIncome()));
                    teProfit.setAllIncome(profit.getAllIncome().add(teProfit.getIncome()));
                    teProfit.setAllExpense(profit.getAllExpense());
                }
                if (teProfit.getType()==2){
                    //支出
                    teProfit.setProfit(profit.getProfit().subtract(teProfit.getIncome()));
                    teProfit.setAllExpense(teProfit.getIncome().add(profit.getAllExpense()));
                    teProfit.setAllIncome(profit.getIncome());
                }
                int result = teProfitMapper.save(teProfit);
                if (result==0){
                    return JSONReturn.buildFailure("新增失败(result)");
                }

                return JSONReturn.build(true,"新增成功");
            }
        }
        return JSONReturn.build(true,"新增或更新参数不正确");

    }

    public JSONReturn list(TeProfit teProfit, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<TeProfit> teProfitList = teProfitMapper.list(teProfit);
        PageInfo pageResult = new PageInfo(teProfitList);
        return JSONReturn.build(true,pageResult);
    }

    public JSONReturn delete(TeProfit teProfit){
        int rowCount = teProfitMapper.delete(teProfit.getId());
        if (rowCount>0){
            return JSONReturn.buildSuccess("删除成功");
        }
        return JSONReturn.buildFailure("删除失败");
    }

}
