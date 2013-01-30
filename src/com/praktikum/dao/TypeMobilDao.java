package com.praktikum.dao;

import org.bmb.dao.entity.DaoIdListener;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.App;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;


@Repository
@Transactional
public class TypeMobilDao extends DaoIdListener<TypeMobil> {
	public TypeMobilDao() {
		super(TypeMobil.class);
		defaultOrder=Order.asc("nama");
	}
	@Transactional(readOnly = false)
	public void saveAutoCode(TypeMobil domain) {
		generateAutoCode(domain);
		sessionFactory.getCurrentSession().save(domain);
		
		//create mobil sebanyk jumlah mobil
		for (int i = 0; i < domain.getJumlah(); i++) {
			Mobil m = new Mobil("Auto", "-", MobilDao.STATUS_SUDAH_DIKEMBALIKAN, domain);
			App.getMobilDao().generateAutoCode(m);
			sessionFactory.getCurrentSession().save(m);
		}
	}

	
	
}
