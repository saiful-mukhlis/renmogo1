package com.praktikum.table.model;

import java.math.BigDecimal;
import java.util.List;

import org.basic.comp.model.DefaultTreeNodeModel;

import com.global.App;
import com.praktikum.dao.SewaDao;
import com.praktikum.db.Sewad;
import com.praktikum.db.Sewa;

public class SewaTreeTableNodeModel extends DefaultTreeNodeModel {

	protected final int TGL_TRX = 1;
	protected final int PEGAWAI = 2;
	protected final int PELANGGAN = 3;
	protected final int MOBIL = 4;
	protected final int WAKTU_AWAL = 5;
	protected final int WAKTU_AKHIR =6;
	protected final int JUMLAH_JAM = 7;
	protected final int HARGA = 8;
	protected final int TOTAL = 9;

	public SewaTreeTableNodeModel(Object userObject) {
		super(userObject);
		if (userObject instanceof Sewa) {
			List<Sewad> sewads=App.getSewadDao().getAllBySewa(((Sewa) userObject).getSewaId());
			int tmp=1;
			for (Sewad sewad : sewads) {
				sewad.setNumber(tmp);
				sewad.setSewa((Sewa) userObject);
				add(new SewaTreeTableNodeModel(sewad));
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
			SewaDao d = App.getSewaDao();
			if (getUserObject() instanceof Sewa) {
				Sewa t=(Sewa) getUserObject();
				 if (column == TGL_TRX) {
					return App.dateFormat.format(t.getTglTrx());
				} 
				 else if (column == PEGAWAI) {
						return t.getPegawai().getNama();
					}
				 else if (column == PELANGGAN) {
					return t.getPelanggan().getNama();
				}
				else if (column == TOTAL) {
					return t.getTotal();
				} 
			}else if (getUserObject() instanceof Sewad) {
				Sewad m=(Sewad) getUserObject();
				
				if (column == MOBIL) {
					return m.getMobil().getTypeMobil().getNama()+"  "+m.getMobil().getCode();
				} 
				if (column == JUMLAH_JAM) {
					return m.getJumlahWaktu();
				} 
				if (column == WAKTU_AWAL) {
					return App.dateFormat.format(m.getWaktuStart())+ " "+App.timeFormat.format(m.getWaktuStart());
				} 
				if (column == WAKTU_AKHIR) {
					return App.dateFormat.format(m.getWaktuEnd())+ " "+App.timeFormat.format(m.getWaktuEnd());
				} 
				if (column == HARGA) {
					return m.getMobil().getTypeMobil().getHarga();
				} 
				if (column == TOTAL) {
					BigDecimal tmp = m.getTotal();
					return App.paymentFormat2.format(tmp.doubleValue());
				} 
			}
		
		return "";
	}

}
