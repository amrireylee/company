package com.empl.mgr.dao;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeSalary;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 23:14 2018/4/22
 */
@Repository
public class salaryDao extends AbstractDao<TeSalary> {

    @Override
    public Class<TeSalary> getEntityClass() {
        // TODO Auto-generated method stub
        return TeSalary.class;
    }

    // long acctId, String acctName, String acctNickname, Date createTime, String creator

    @SuppressWarnings("unchecked")
    public List<TeSalary> findSalaryList(int page, String val) {
        // TODO Auto-generated method stub
        StringBuffer query = new StringBuffer();
        query.append("select new com.empl.mgr.model.TeSalary ");
        query.append("(te.id, te.base, te.post, te.prix, te.emId) ");
        query.append("from TeSalary te ");
        query.append(StringUtils.isEmpty(val) ? "" : " where (te.emId = " + val
                + " )");
        query.append("order by te.id desc");
        return findSession().createQuery(query.toString())
                .setFirstResult((page - 1) * PageConstant.PAGE_LIST)
                .setMaxResults(PageConstant.PAGE_LIST).list();
    }

    public int findSalaryPage(String val) {
        // TODO Auto-generated method stub
        StringBuffer query = new StringBuffer();
        query.append("select count(te.id) from TeSalary te ");
        query.append(StringUtils.isEmpty(val) ? "" : " where (te.emId = " + val
                + " )");
        return Integer.parseInt(findSession().createQuery(query.toString())
                .uniqueResult().toString());
    }
}
