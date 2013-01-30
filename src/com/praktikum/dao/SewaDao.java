package com.praktikum.dao;

import java.util.Date;
import java.util.List;

import org.bmb.dao.entity.DaoIdListener;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.App;
import com.praktikum.db.Mobil;
import com.praktikum.db.Pegawai;
import com.praktikum.db.Pelanggan;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;
import com.praktikum.db.TypeMobil;


@Repository
@Transactional
public class SewaDao extends DaoIdListener<Sewa> {
	
	public static int STATUS_BELUM_DIKEMBALIKAN_SEMUA=0;
	public static int STATUS_SUDAH_DIKEMBALIKAN_SEMUA=1;
	
	public SewaDao() {
		super(Sewa.class);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Sewa> getAllByColumn(String column,String value,String column2,String value2,int start, int num) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass)
				.add( Restrictions.ilike(column, value))
				.add( Restrictions.eq("status", SewaDao.STATUS_BELUM_DIKEMBALIKAN_SEMUA))
				.createCriteria("pelanggan")
				.add( Restrictions.ilike(column2, value2))
				.addOrder( defaultOrder )
				.setFirstResult(start).setMaxResults(num).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Sewa> getAllByColumnLaporan(Date tglStart, Date tglEnd, Pelanggan modelPelanggan, Pegawai modelPegawai, TypeMobil modelTypeMobil, Mobil modelMobil) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(domainClass);
		if (tglStart!=null) {
			if (tglEnd==null) {
				tglEnd=tglStart;
			}
			c.add(Restrictions.between("tglTrx", tglStart, tglEnd));
		}
		if (modelPelanggan!=null) {
			c.add(Restrictions.eq("pelanggan", modelPelanggan));
		}
		if (modelPegawai!=null) {
			c.add(Restrictions.eq("pegawai", modelPegawai));
		}
		if (modelTypeMobil!=null || modelMobil!=null) {
			if (modelMobil!=null && modelTypeMobil==null) {
				c.createCriteria("sewads").add(Property.forName("mobil").eq(modelMobil));
			}else
			if (modelTypeMobil!=null && modelMobil==null) {
				c.createCriteria("sewads").createCriteria("mobil").add(Property.forName("typeMobil").eq(modelTypeMobil));
			}else{
				c.createCriteria("sewads").add(Property.forName("mobil").eq(modelMobil)).createCriteria("mobil").add(Property.forName("typeMobil").eq(modelTypeMobil));
			}
		}
		return c.list();
	}
	
	@Transactional(readOnly = false)
	public void saveTransaksi(Sewa sewa, List<Sewad> ss){
		SewadDao d=App.getSewadDao();
		generateAutoCode(sewa);
		sessionFactory.getCurrentSession().save(sewa);
		for (Sewad sewad : ss) {
			sewad.setSewa(sewa);
			d.save(sewad);
			
			Mobil tmpmobil=sewad.getMobil();
			tmpmobil.setStatus(MobilDao.STATUS_DISEWA);
			sessionFactory.getCurrentSession().update(tmpmobil);
			
			TypeMobil tm=tmpmobil.getTypeMobil();
			tm.setJumlahTersedia(tm.getJumlahTersedia()-1);
			sessionFactory.getCurrentSession().update(tm);
		}
		
	}
	
	


	
}
