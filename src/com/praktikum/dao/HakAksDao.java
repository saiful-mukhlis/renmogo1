package com.praktikum.dao;

import org.basic.comp.base.TextField;
import org.bmb.dao.entity.DaoIdListener;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Bos;
import com.basic.lang.LHakAks;
import com.global.App;
import com.praktikum.db.HakAks;

@Repository
@Transactional
public class HakAksDao extends DaoIdListener<HakAks> {
	public HakAksDao() {
		super(HakAks.class);
		defaultOrderToString=" order by nama asc";
		defaultOrder=Order.asc("nama");
	}
	@SuppressWarnings("unchecked")
	public HakAks getById(int id) {
		return (HakAks) sessionFactory.getCurrentSession().get(domainClass, id);
	}
	public boolean validate(TextField code, TextField nama){
		if (nama.getText().trim().equals("")) {
			App.showErrorFieldEmpty(LHakAks.NAMA);
			return false;
		}
		return true;
	}
	
	public void create1st(){ 
		StringBuffer tmp = new StringBuffer();
		for (int i = 1; i < 50; i++) {
			tmp.append("x" + i + "x");
		}
		HakAks h=new HakAks("Auto", tmp.toString(), "Administrator");
		saveAutoCode(h);
		h=new HakAks("Auto", "", "Pegawai");
		try {
			saveAutoCode(h);
			
		} catch (Exception e) {
			App.printErr(e);
			System.exit(1);
		}
}
}
