package com.praktikum.table.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.basic.comp.abst.TableModelDefault;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.basic.lang.LHakAks;
import com.basic.lang.LWindow;
import com.bmb.util.Db;
import com.praktikum.dao.HakAksDao;
import com.praktikum.db.HakAks;

public class HakAksTM extends TableModelDefault<HakAks> {

	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;

	public void initNamaKolom() {
		namaKolom = new String[3];
		namaKolom[NO] = LHakAks.NO;
		namaKolom[CODE] = LHakAks.CODE;
		namaKolom[NAMA] = LHakAks.NAMA;
	}

	public void load() {
		loadJumlahData();
		loadDataModel();
		super.load();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		HakAks m = model.get(rowIndex);
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} else if (columnIndex == CODE) {
			return m.getCode();
		} else if (columnIndex == NAMA) {
			return m.getNama();
		} else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = (HakAksDao) Db.HAK_AKS.get();
	}

	public void actionSearchOneField(String col, String value) {
		loadJumlahDataSearchLike(col, value);
		loadDataModelSearchLike(col, value);
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
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LHakAks.NAMA);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LHakAks.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LHakAks.CODE);
					colSearch = "code";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LHakAks.NAMA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LHakAks.NAMA);
					colSearch = "nama";
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
