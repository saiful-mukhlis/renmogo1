package org.bmb.dao.entity;

import java.math.BigInteger;

import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.basic.dao.adapter.Dao;
import org.bmb.dao.adapter.IdListener;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Tableid;
import com.global.App;

@Transactional
public class DaoIdListener<T extends IdListener> extends Dao<T, Long> {

	protected Logger log = Logger.getLogger(DaoIdListener.class);

	public DaoIdListener(Class<T> domainClass) {
		super(domainClass);
	}
	
	

	@Override
	public T getById(Long id) {
		return super.getById(id);
	}
	public T getById(int id) {
		return super.getById((long) id);
	}



	@Transactional(readOnly = false)
	public void save(T domain) {
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
	
	@Transactional(readOnly = false)
	public void update(T domain) {
		sessionFactory.getCurrentSession().update(domain);
	}

	@Transactional(readOnly = false)
	public void saveAutoCode(T domain) {
		generateAutoCode(domain);
		sessionFactory.getCurrentSession().save(domain);
	}

	public void generateAutoCode(T domain) {

		Table table = domainClass.getAnnotation(Table.class);
		String tableName;
		if (table!=null) {
			tableName = table.name();
		}else{
			tableName=domainClass.getSimpleName().toLowerCase();
		}
		Tableid tableid=getTableid(tableName);
//		BigInteger newid = getNewId(tableName);

		if (domain.getCode() == null
				|| domain.getCode().equalsIgnoreCase("Auto")
				|| domain.getCode().trim().equalsIgnoreCase("")) {
			 

			long now=tableid.getNo();
			boolean useFormat=true;
			if (tableid.getPref()==null || tableid.getPref().equalsIgnoreCase("")) {
				useFormat=false;
			}
			String format=tableid.getPref();
			if (format==null) {
				format="";
			}
			boolean ulang=true;
			String nowWithFormat="";
			while (ulang) {
				nowWithFormat=now+"";
				if (useFormat) {
					int panjangTanpaFormat=nowWithFormat.length();
					int panjangFormat=format.length();
					if (panjangTanpaFormat<panjangFormat) {
						nowWithFormat=format+nowWithFormat;
						int panjangSetelahFormat=nowWithFormat.length();
						nowWithFormat=nowWithFormat.substring(panjangSetelahFormat-panjangFormat, panjangSetelahFormat);
					}
				}
				long od=countByColumn("code", nowWithFormat);
				if (od!=0) {
					now++;
					System.out.println(now);
				}else{
					ulang=false;
				}
			}
			domain.setCode(nowWithFormat);
			domain.setId(now);
			now=now+tableid.getIncr();
			tableid.setNo(now);
			sessionFactory.getCurrentSession().save(tableid);
//			model.field("code", nowWithFormat);
//			model.save();
//			int aut=num.field("increment");
//			now=now+aut;
//			num.field("now", now , OType.LONG);
//			num.save();
		
			
		}

	}
//	protected void generateKode(T domain, Tableid no){
//		domain.setCode(no+"");
//	}

}
