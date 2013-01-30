package com.praktikum.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.bmb.dao.entity.DaoIdListener;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Tableid;
import com.global.App;
import com.praktikum.db.Kembali;
import com.praktikum.db.Kembalid;
import com.praktikum.db.Mobil;
import com.praktikum.db.Pegawai;
import com.praktikum.db.Pelanggan;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;
import com.praktikum.db.TypeMobil;


@Repository
@Transactional
public class KembaliDao extends DaoIdListener<Kembali> {
	public KembaliDao() {
		super(Kembali.class);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveAutoCode(Kembali domain) {
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
	public void saveTransaksi(Kembali k, List<Kembalid> ss) {
		// TODO Auto-generated method stub
		KembalidDao d=App.getKembalidDao();
		SewadDao dd=App.getSewadDao();
		generateAutoCode(k);
		sessionFactory.getCurrentSession().save(k);
		for (Kembalid kembalid: ss) {
			kembalid.setKembali(k);
			d.save(kembalid);
			Sewad tmp=kembalid.getSewad();
			tmp.setStatus(SewadDao.STATUS_SUDAH_DIKEMBALIKAN);
			sessionFactory.getCurrentSession().update(tmp);
			dd.update(tmp);
			
			Mobil tmpmobil=tmp.getMobil();
			tmpmobil.setStatus(MobilDao.STATUS_SUDAH_DIKEMBALIKAN);
			sessionFactory.getCurrentSession().update(tmpmobil);
			
			TypeMobil tm=tmpmobil.getTypeMobil();
			tm.setJumlahTersedia(tm.getJumlahTersedia()+1);
			sessionFactory.getCurrentSession().update(tm);
			
		}
		Sewa sewa1=k.getSewa();
		List<Sewad> sewads=dd.getAllBySewa(sewa1.getSewaId());
		boolean sudahkemabalisemua=true;
		for (Sewad sewad : sewads) {
			if (sewad.getStatus()==0) {
				sudahkemabalisemua=false;
			}
		}
		if (sudahkemabalisemua) {
			sewa1.setStatus(SewaDao.STATUS_SUDAH_DIKEMBALIKAN_SEMUA);
			sessionFactory.getCurrentSession().update(sewa1);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Kembali> getAllByColumnLaporan(Date tglStart, Date tglEnd, Pelanggan modelPelanggan, Pegawai modelPegawai, TypeMobil modelTypeMobil, Mobil modelMobil) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(domainClass);
		if (tglStart!=null) {
			if (tglEnd==null) {
				tglEnd=tglStart;
			}
			c.add(Restrictions.between("tglKembali", tglStart, tglEnd));
		}
		if (modelPegawai!=null) {
			c.add(Restrictions.eq("pegawai", modelPegawai));
		}
		
		if (modelPelanggan!=null && modelTypeMobil==null && modelMobil==null) {
			c.createCriteria("sewa").add(Property.forName("pelanggan").eq(modelPelanggan));
		}else if (modelPelanggan!=null && modelTypeMobil!=null && modelMobil==null) {
			c.createCriteria("sewa").add(Property.forName("pelanggan").eq(modelPelanggan)).createCriteria("sewads").createCriteria("mobil").add(Property.forName("typeMobil").eq(modelTypeMobil));
		}else if (modelPelanggan!=null && modelTypeMobil!=null && modelMobil!=null) {
			c.createCriteria("sewa").add(Property.forName("pelanggan").eq(modelPelanggan)).createCriteria("sewads").add(Property.forName("mobil").eq(modelMobil)).createCriteria("mobil").add(Property.forName("typeMobil").eq(modelTypeMobil));
		}else if (modelPelanggan==null && modelTypeMobil!=null && modelMobil!=null) {
			c.createCriteria("sewa").createCriteria("sewads").add(Property.forName("mobil").eq(modelMobil)).createCriteria("mobil").add(Property.forName("typeMobil").eq(modelTypeMobil));
		}else if (modelPelanggan==null && modelTypeMobil==null && modelMobil!=null) {
			c.createCriteria("sewa").createCriteria("sewads").add(Property.forName("mobil").eq(modelMobil));
		}
		
		return c.list();
	}
	
}
