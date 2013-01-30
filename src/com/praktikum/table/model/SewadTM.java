package com.praktikum.table.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JPopupMenu;

import org.basic.comp.abst.TableModelDefault;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.global.App;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;
import com.praktikum.lang.LSewa;

public class SewadTM extends TableModelDefault<Sewad> {

	protected final int NO = 0;
	protected final int TYPE_MOBIL = 1;
	protected final int MOBIL = 2;
	protected final int JUMLAH_JAM = 3;
	protected final int WAKTU_AWAL = 4;
	protected final int WAKTU_AKHIR = 5;
	protected final int HARGA = 6;
	protected final int TOTAL = 7;
	

	public void initNamaKolom() {
		namaKolom = new String[8];
		namaKolom[NO] = LSewa.NO;
		namaKolom[TYPE_MOBIL] = LSewa.TYPE_MOBIL;
		namaKolom[MOBIL] = LSewa.MOBIL;
		namaKolom[JUMLAH_JAM] = LSewa.JUMLAH_WAKTU;
		namaKolom[WAKTU_AWAL] = LSewa.WAKTU_START;
		namaKolom[WAKTU_AKHIR] = LSewa.WAKTU_END;
		namaKolom[HARGA] = LSewa.HARGA;
		namaKolom[TOTAL] = LSewa.TOTAL;
	}

	public void load() {
//		loadJumlahData();
//		loadDataModel();
//		super.load();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Sewad m = model.get(rowIndex);
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} 
		if (columnIndex == TYPE_MOBIL) {
			return m.getMobil().getTypeMobil().getNama();
		} 
		if (columnIndex == MOBIL) {
			return m.getMobil().getCode();
		} 
		if (columnIndex == JUMLAH_JAM) {
			return m.getJumlahWaktu();
		} 
		if (columnIndex == WAKTU_AWAL) {
			return App.dateFormat.format(m.getWaktuStart())+ " "+App.timeFormat.format(m.getWaktuStart());
		} 
		if (columnIndex == WAKTU_AKHIR) {
			return App.dateFormat.format(m.getWaktuEnd())+ " "+App.timeFormat.format(m.getWaktuEnd());
		} 
		if (columnIndex == HARGA) {
			return m.getMobil().getTypeMobil().getHarga();
		} 
		if (columnIndex == TOTAL) {
			BigDecimal tmp = m.getTotal();
			return App.paymentFormat2.format(tmp.doubleValue());
		} 
				else {
			return null;
		}
	}

	public BigDecimal getTotal(int rowIndex){
		Sewad m = model.get(rowIndex);
		BigDecimal tmp = m.getMobil().getTypeMobil().getHarga().multiply(m.getJumlahWaktu());
		return tmp;
	}
	
	@Override
	public void initDao() {
		dao = App.getSewaDao();
	}

	public void actionSearchOneField(String col, String value) {
//		loadJumlahDataSearchLike(col, value);
//		loadDataModelSearchLike(col, value);
//		fireTableDataChanged();
//		if (getTable() != null) {
//			getTable().selected();
//		}
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = "nama";
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
//		if (fieldSearch == null) {
//			fieldSearch = new TextFieldSearch();
//			fieldSearch.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					String tmp = fieldSearch.getText();
//					if (!tmp.trim().equalsIgnoreCase("")) {
//						actionSearchOneField(colSearch, tmp);
//					} else {
//						reload();
//					}
//				}
//			});
//		}
		return fieldSearch;
	}

}
