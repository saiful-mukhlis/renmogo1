package com.basic.dao;

import org.bmb.dao.entity.DaoIdListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Bos;
import com.basic.db.Tableid;

@Repository
@Transactional
public class TableidDao extends DaoIdListener<Tableid> {
	public TableidDao() {
		super(Tableid.class);
	}
	@Override
	@Transactional(readOnly = false)
	public void save(Tableid domain) {
		sessionFactory.getCurrentSession().save(domain);
	}
}
