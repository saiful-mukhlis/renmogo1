package com.praktikum.dao;

import org.bmb.dao.entity.DaoIdListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.App;
import com.praktikum.db.Pelanggan;


@Repository
@Transactional
public class PelangganDao extends DaoIdListener<Pelanggan> {
	public PelangganDao() {
		super(Pelanggan.class);
	}
	public String[] getStatusData() {
		String a1 = App.getT("Pilih Status Pelanggan");
		String ta = App.getT("Tidak Aktif");
		String a = App.getT("Aktif");
		String[] stringStatus = { a1, ta, a };
		return stringStatus;
	}
}
