package com.praktikum.table.model;

import java.math.BigDecimal;
import java.util.List;

import org.basic.comp.model.DefaultTreeNodeModel;

import com.global.App;
import com.praktikum.dao.KembaliDao;
import com.praktikum.db.Kembalid;
import com.praktikum.db.Kembali;

public class KembaliTreeTableNodeModel extends DefaultTreeNodeModel {

	protected final int TGL_TRX = 1;
	protected final int PEGAWAI = 2;
	protected final int PELANGGAN = 3;
	protected final int MOBIL = 4;
	protected final int WAKTU_AWAL = 5;
	protected final int WAKTU_AKHIR =6;
	protected final int JUMLAH_JAM = 7;
	protected final int HARGA = 8;
	protected final int TOTAL = 9;

	public KembaliTreeTableNodeModel(Object userObject) {
		super(userObject);
		if (userObject instanceof Kembali) {
			List<Kembalid> sewads=App.getKembalidDao().getAllByKembali(((Kembali) userObject).getKembaliId());
			int tmp=1;
			for (Kembalid sewad : sewads) {
				sewad.setNumber(tmp);
				sewad.setKembali((Kembali) userObject);
				add(new KembaliTreeTableNodeModel(sewad));
				tmp++;
			}
		}
		
		columnCount = 10;
	}

	@Override
	public Object getValueAt(int column) {
		if (column == 0) {
			return this;
		} 
			KembaliDao d = App.getKembaliDao();
			if (getUserObject() instanceof Kembali) {
				Kembali t=(Kembali) getUserObject();
				 if (column == TGL_TRX) {
					return App.dateFormat.format(t.getTglKembali());
				} 
				 else if (column == PEGAWAI) {
						return t.getPegawai().getNama();
					}
				 else if (column == PELANGGAN) {
					return t.getSewa().getPelanggan().getNama();
				}
				else if (column == TOTAL) {
					return t.getTotalDenda();
				} 
			}else if (getUserObject() instanceof Kembalid) {
				Kembalid m=(Kembalid) getUserObject();
				
				if (column == MOBIL) {
					return m.getSewad().getMobil().getTypeMobil().getNama()+"  "+m.getSewad().getMobil().getCode();
				} 
				if (column == JUMLAH_JAM) {
					return m.getWaktuLebih();
				} 
				if (column == WAKTU_AWAL) {
					return App.dateFormat.format(m.getSewad().getWaktuEnd())+ " "+App.timeFormat.format(m.getSewad().getWaktuEnd());
				} 
				if (column == WAKTU_AKHIR) {
					return App.dateFormat.format(m.getKembali().getWaktuKembali())+ " "+App.timeFormat.format(m.getKembali().getWaktuKembali());
				} 
				if (column == HARGA) {
					return m.getSewad().getMobil().getTypeMobil().getDenda();
				} 
				if (column == TOTAL) {
					BigDecimal tmp = m.getDenda();
					return App.paymentFormat2.format(tmp.doubleValue());
				} 
			}
		
		return "";
	}

}
