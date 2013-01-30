package com.praktikum.table.model;


import org.basic.comp.abst.TableModelDefault;
import com.global.App;
import com.praktikum.dao.SewaDao;
import com.praktikum.db.Pelanggan;
import com.praktikum.db.Sewa;
import com.praktikum.lang.LPelanggan;
import com.praktikum.lang.LSewa;

public class SewaTMS extends TableModelDefault<Sewa> {

	protected final int CODE = 0;
	protected final int NAMA = 1;
	

	public void initNamaKolom() {
		namaKolom = new String[2];
		namaKolom[CODE] = LSewa.CODE;
		namaKolom[NAMA] = LSewa.PELANGGAN_NAMA;
	}

	public void load() {
		loadJumlahData();
		loadDataModel();
		super.load();
	}
	
	@Override
	public void loadDataModel() {
		if (textSearch == null || textSearch.equalsIgnoreCase("")) {
			model = dao.getAllByColumn("status", SewaDao.STATUS_BELUM_DIKEMBALIKAN_SEMUA, 0, 50);
		} else {
			model = dao.getAllByColumn("code","%"+textSearch+"%","nama","%"+textSearch+"%", 0, 50);
			if (model.size() == 0) {
				model = dao.getAllByColumn("status", SewaDao.STATUS_BELUM_DIKEMBALIKAN_SEMUA, 0, 50);
			}
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Sewa m = model.get(rowIndex);
		 if (columnIndex == CODE) {
			return m.getCode();
		}
		else if (columnIndex == NAMA) {
			return m.getPelanggan().getNama();
		}
		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = App.getSewaDao();
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	private String textSearch;
}
