package com.praktikum.table.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.basic.comp.abst.TableModelDefault;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.basic.lang.LWindow;
import com.bmb.util.Db;
import com.global.App;
import com.praktikum.dao.PegawaiDao;
import com.praktikum.db.Pegawai;
import com.praktikum.lang.LPegawai;

public class PegawaiTM extends TableModelDefault<Pegawai> {

	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	protected final int JENIS_IDENTITAS = 3;
	protected final int NO_IDENTITAS = 4;
	protected final int ALAMAT = 5;
	protected final int KOTA = 6;
	protected final int HP = 7;
	protected final int STATUS = 8;
	

	public void initNamaKolom() {
		namaKolom = new String[9];
		namaKolom[NO] = LPegawai.NO;
		namaKolom[CODE] = LPegawai.CODE;
		namaKolom[NAMA] = LPegawai.NAMA;
		namaKolom[JENIS_IDENTITAS] = LPegawai.JENIS_IDENTITAS;
		namaKolom[NO_IDENTITAS] = LPegawai.NO_IDENTITAS;
		namaKolom[ALAMAT] = LPegawai.ALAMAT;
		namaKolom[KOTA] = LPegawai.KOTA;
		namaKolom[HP] = LPegawai.HP;
		namaKolom[STATUS] = LPegawai.STATUS;
	}

	public void load() {
		loadJumlahData();
		loadDataModel();
		super.load();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pegawai m = model.get(rowIndex);
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} else if (columnIndex == CODE) {
			return m.getCode();
		}
		else if (columnIndex == NAMA) {
			return m.getNama();
		}
		else if (columnIndex == JENIS_IDENTITAS) {
			return m.getJenisIdentitas();
		}
		else if (columnIndex == NO_IDENTITAS) {
			return m.getNoIdentitas();
		}
		else if (columnIndex == ALAMAT) {
			return m.getAlamat();
		}
		else if (columnIndex == KOTA) {
			return m.getKota();
		}
		else if (columnIndex == HP) {
			return m.getHp();
		}
		else if (columnIndex == STATUS) {
			if (m.getStatus()==0) {
				return "-";
			}else{
				String tmp[]=App.getPegawaiDao().getStatusData();
				return tmp[m.getStatus()];
			}
		}
		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = App.getPegawaiDao();
	}

	public void actionSearchOneField(String col, String value) {
		if (col.equalsIgnoreCase("status")) {
			int tmp=0;
			if (value.equalsIgnoreCase(App.getT("Aktif"))) {
				tmp=2;
			}else if (value.equalsIgnoreCase(App.getT("Tidak Aktif"))) {
				tmp=1;
			}
			loadJumlahDataSearch(col, tmp);
			loadDataModelSearch(col, tmp);
		}else{
			loadJumlahDataSearchLike(col, value);
			loadDataModelSearchLike(col, value);
		}
		
		fireTableDataChanged();
		if (getTable() != null) {
			getTable().selected();
		}
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = "nama";
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String tmp = fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					} else {
						reload();
					}
				}
			});
		}
		return fieldSearch;
	}

	public SplitButton getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LPegawai.NAMA);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.CODE);
					colSearch = "code";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.NAMA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.NAMA);
					colSearch = "nama";
				}
			});
			menuItemSearch.add(item);
			
			//jenis iden
			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.JENIS_IDENTITAS);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.JENIS_IDENTITAS);
					colSearch = "jenisIdentitas";
				}
			});
			menuItemSearch.add(item);
			
			//alamat
			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.ALAMAT);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.ALAMAT);
					colSearch = "alamat";
				}
			});
			menuItemSearch.add(item);
			
			//username
			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.USERNAME);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.USERNAME);
					colSearch = "username";
				}
			});
			menuItemSearch.add(item);
			
			//no i
			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.NO_IDENTITAS);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.NO_IDENTITAS);
					colSearch = "noIdentitas";
				}
			});
			menuItemSearch.add(item);
			
			//hp
			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.HP);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.HP);
					colSearch = "hp";
				}
			});
			menuItemSearch.add(item);
			
			//status
			item = new JMenuItem(LWindow.KET_SEARCH + LPegawai.STATUS);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPegawai.STATUS);
					colSearch = "status";
				}
			});
			menuItemSearch.add(item);

			itemSearch.setDropDownMenu(menuItemSearch);
			itemSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String tmp = fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					} else {
						reload();
					}

				}
			});

		}
		return itemSearch;
	}
}
