package org.basic.comp.abst;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.basic.comp.adapter.PagingInterface;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.adapter.TableModelInterface;



public abstract class TableModelSearchAbstract extends AbstractTableModel implements TableModelInterface{

	protected List<String> namaKolom = new ArrayList<String>();
	protected List model;

	protected boolean searching = false;
	protected String textSearch;
	protected boolean isLoadFirst = true;

	protected PagingInterface paging;
	private TableInterfaces table;
	
	

	public TableModelSearchAbstract() {
		super();
	}

	public int getColumnCount() {
		return namaKolom.size();
	}

	public int getRowCount() {
		return model.size();
	}

	public String getColumnName(int col) {
		return namaKolom.get(col);
	}

	public void reload() {
		loadDataModel();
		loadJumlahData();
		fireTableStructureChanged();
		fireTableDataChanged();
		setDefaultLebar(getTable().getTable());
		getTable().selected();
	}

	public void load() {
		//loadJumlahData();
		if (paging != null) {
			paging.setCurentHalaman(0);
		}
		reload();
		if (paging != null) {
			//paging.loadFirst();
		}

	}

	protected void loadDataModel() {
		
	}

	protected void loadJumlahData() {
	}

	public void setPaging(PagingInterface paging) {
		this.paging = paging;
	}

	public boolean isSearching() {
		return searching;
	}

	public void setSearching(boolean searching) {
		this.searching = searching;
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	public List getModels() {
		return model;
	}

	public TableInterfaces getTable() {
		return table;
	}

	public void setTableParent(TableInterfaces table) {
		this.table = table;
	}

}
