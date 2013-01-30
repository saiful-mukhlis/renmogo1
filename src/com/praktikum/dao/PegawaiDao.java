package com.praktikum.dao;

import org.bmb.dao.entity.DaoIdListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.App;
import com.global.util.UtilAccount;
import com.praktikum.db.HakAks;
import com.praktikum.db.Pegawai;

@Repository
@Transactional
public class PegawaiDao extends DaoIdListener<Pegawai> {
	public PegawaiDao() {
		super(Pegawai.class);
	}

	public void create1st() {
		HakAks h = App.getHakAks().getById(1);
		if (h == null) {
			App.info("Hak Akses No Generate");
			System.exit(0);
		}
		UtilAccount u = new UtilAccount();
		try {
			Pegawai p = new Pegawai("-", "Auto", "-", "-", "-", "Admin", "-",
					u.md5("admin"), 2, "admin", h);
			saveAutoCode(p);
		} catch (Exception e) {
			App.printErr(e);
		}
		// HakAks h=new HakAks("Auto", tmp.toString(), "Administrator");
		// saveAutoCode(h);
		// h=new HakAks("Auto", "", "Pegawai");
		// saveAutoCode(h);
	}
	
	public String[] getStatusData() {
		String a1 = App.getT("Pilih Status Pegawai");
		String ta = App.getT("Tidak Aktif");
		String a = App.getT("Aktif");
		String[] stringStatus = { a1, ta, a };
		return stringStatus;
	}
}
