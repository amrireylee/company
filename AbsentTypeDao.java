package com.empl.mgr.dao;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeAbsentType;
import com.empl.mgr.model.TeAccountRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Amrir
 * @Description:
 * @Date: Created in 23:22 2018/4/22
 */
@Repository
public class AbsentTypeDao extends AbstractDao<TeAbsentType> {

    @Override
    public Class<TeAbsentType> getEntityClass() {
        // TODO Auto-generated method stub
        return TeAbsentType.class;
    }

    @SuppressWarnings("unchecked")
    public List<TeAbsentType> findAbsentTypeList(int page, String searchVal) {
        // TODO Auto-generated method stub
        StringBuffer query = new StringBuffer();
        query.append("select new TeAbsentType (id, name, amount) from TeAbsentType ");
        query.append(StringUtils.isNotBlank(searchVal) ? "where name like '%" + searchVal + "%'" : "");
        query.append("order by id desc ");
        return findSession().createQuery(query.toString()).setFirstResult((page - 1) * PageConstant.PAGE_LIST)
                .setMaxResults(PageConstant.PAGE_LIST).list();
    }

    public int findAbsentTypeCount(String searchVal) {
        // TODO Auto-generated method stub
        StringBuffer query = new StringBuffer();
        query.append("select count(id) from TeAbsentType ");
        query.append(StringUtils.isNotBlank(searchVal) ? "where name like '%" + searchVal + "%'" : "");
        return Integer.parseInt(findSession().createQuery(query.toString()).uniqueResult().toString());
    }

    @SuppressWarnings("unchecked")
    public List<TeAccountRole> findMyCharacter(String acctName) {
        StringBuffer query = new StringBuffer();
        query.append("from TeAbsentType where name = ?");
        return findSession().createQuery(query.toString()).setString(0, acctName).list();
    }

}
