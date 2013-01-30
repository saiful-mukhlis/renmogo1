package com.praktikum.table.model;


import org.basic.comp.abst.TableModelDefault;
import com.global.App;
import com.praktikum.db.Pegawai;
import com.praktikum.lang.LPegawai;

public class PegawaiTMS extends TableModelDefault<Pegawai> {

	protected final int CODE = 0;
	protected final int NAMA = 1;
	

	public void initNamaKolom() {
		namaKolom = new String[2];
		namaKolom[CODE] = LPegawai.CODE;
		namaKolom[NAMA] = LPegawai.NAMA;
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
		Pegawai m = model.get(rowIndex);
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
		dao = App.getPegawaiDao();
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	private String textSearch;
}
