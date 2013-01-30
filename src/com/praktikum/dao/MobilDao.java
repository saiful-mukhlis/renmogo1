package com.praktikum.dao;

import java.util.List;

import org.bmb.dao.entity.DaoIdListener;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.App;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;

@Repository
@Transactional
public class MobilDao extends DaoIdListener<Mobil> {
	
	public static int STATUS_SUDAH_DIKEMBALIKAN=0;
	public static int STATUS_DISEWA=1;
	
	public MobilDao() {
		super(Mobil.class);
		defaultOrder = Order.asc("code");
	}

	@SuppressWarnings("unchecked")
	public List getAllByTypeMobil(long value) {
		return sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT * FROM MOBIL WHERE TYPE_MOBIL_ID="+value+" ORDER BY CODE")
				.addEntity(domainClass)
				//.createCriteria(domainClass).add(Restrictions.eq("typeMobil",value))
				//.setFetchMode("typeMobil", FetchMode.SELECT)
				//.addOrder(defaultOrder)
				.list();
		
//		Query query = sess.createSQLQuery("SELECT * FROM MOBIL WHERE TYPE_MOBIL_ID="+value).addEntity(domainClass);
//		List pusList = query.setString(0, "Pus%").list();
//		     
//		query = sess.createSQLQuery("SELECT * FROM CATS WHERE NAME like :name").addEntity(Cat.class);
//		List pusList = query.setString("name", "Pus%").list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Mobil> getAllByColumn(String column,String value,String column2,String value2,int start, int num) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass)
				.add( Restrictions.ilike(column, value))
				.createCriteria("typeMobil")
				.add( Restrictions.ilike(column2, value2))
				.addOrder( defaultOrder )
				.setFirstResult(start).setMaxResults(num).list();
	}
	
	public String[] getStatusData() {
		String a1 = App.getT("Tersedia");
		String ta = App.getT("Tidak Tersedia");
		String[] stringStatus = { a1, ta };
		return stringStatus;
	}

	@Override
	public void delete(Mobil domain) {
		// cek apakah mobil di sewa
		// mengurangi jumlah tersedia
		// mengurangi jumlah mobil
		if (domain.getStatus()!=STATUS_DISEWA) {
			TypeMobil tmp=domain.getTypeMobil();
			tmp.setJumlah(tmp.getJumlah()-1);
			tmp.setJumlahTersedia(tmp.getJumlahTersedia()-1);
			sessionFactory.getCurrentSession().update(tmp);
			sessionFactory.getCurrentSession().delete(domain);
		}
	}
	
	
}
