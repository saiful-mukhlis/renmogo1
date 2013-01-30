package com.praktikum.table.model;


import org.basic.comp.abst.TableModelDefault;
import com.global.App;
import com.praktikum.db.Pelanggan;
import com.praktikum.lang.LPelanggan;

public class PelangganTMS extends TableModelDefault<Pelanggan> {

	protected final int CODE = 0;
	protected final int NAMA = 1;
	

	public void initNamaKolom() {
		namaKolom = new String[2];
		namaKolom[CODE] = LPelanggan.CODE;
		namaKolom[NAMA] = LPelanggan.NAMA;
	}

	public void load() {
		loadJumlahData();
		loadDataModel();
		super.load();
	}
	
	@Override
	public void loadDataModel() {
		if (textSearch == null || textSearch.equalsIgnoreCase("")) {
			model = dao.getAll(0, 50);
		} else {
			model = dao.getAllByColumn("code","%"+textSearch+"%","nama","%"+textSearch+"%", 0, 50);
			if (model.size() == 0) {
				model = dao.getAll(0, 50);
			}
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pelanggan m = model.get(rowIndex);
		 if (columnIndex == CODE) {
			return m.getCode();
		}
		else if (columnIndex == NAMA) {
			return m.getNama();
		}
		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = App.getPelangganDao();
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	private String textSearch;
}
