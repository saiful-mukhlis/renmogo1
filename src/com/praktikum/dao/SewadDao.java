package com.praktikum.dao;

import java.util.List;

import javax.persistence.Table;

import org.bmb.dao.entity.DaoIdListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Tableid;
import com.global.App;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;


@Repository
@Transactional
public class SewadDao extends DaoIdListener<Sewad> {
	
	public static int STATUS_BELUM_DIKEMBALIKAN=0;
	public static int STATUS_SUDAH_DIKEMBALIKAN=1;
	
	public SewadDao() {
		super(Sewad.class);
	}
	
	public void save(Sewad domain) {
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
	public List getAllBySewa(long value) {
		return sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT * FROM SEWAD WHERE SEWA_ID="+value+" ORDER BY SEWAD_ID")
				.addEntity(domainClass)
				.list();
	}
	
	public String[] getStatusData() {
		String a1 = App.getT("Belum Dikembalikan");
		String ta = App.getT("Sudah Dikembalikan");
		String[] stringStatus = { a1, ta };
		return stringStatus;
	}
}
