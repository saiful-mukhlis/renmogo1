package com.praktikum.dao;

import java.util.List;

import javax.persistence.Table;

import org.bmb.dao.entity.DaoIdListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Tableid;
import com.praktikum.db.Kembalid;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;


@Repository
@Transactional
public class KembalidDao extends DaoIdListener<Kembalid> {
	public KembalidDao() {
		super(Kembalid.class);
	}
	public void save(Kembalid domain) {
		Table table = domainClass.getAnnotation(Table.class);
		String tableName;
		if (table!=null) {
			tableName = table.name();
		}else{
			tableName=domainClass.getSimpleName().toLowerCase();
		}
		Tableid tableid=getTableid(tableName);
		long now=tableid.getNo();
		domain.setId(now);
		sessionFactory.getCurrentSession().save(domain);
		now=now+tableid.getIncr();
		tableid.setNo(now);
		sessionFactory.getCurrentSession().save(tableid);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List getAllByKembali(long value) {
		return sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT * FROM KEMBALID WHERE KEMBALI_ID="+value+" ORDER BY KEMBALID_ID")
				.addEntity(domainClass)
				.list();
	}
}
