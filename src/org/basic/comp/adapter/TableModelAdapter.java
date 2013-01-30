package org.basic.comp.adapter;


import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.dao.adapter.Dao;

public class TableModelAdapter<T> extends AbstractTableModel implements TableModelInterface<T>, ParentPagingInterface {

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addModel(T model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editModel(T model, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTableParent(TableInterfaces table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TableInterfaces getTableParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSearching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPaging(PagingInterface paging) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadDataModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadJumlahData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initDao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initNamaKolom() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModels(List model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNamaKolom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SplitButton getItemSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextFieldSearch getFieldSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getBind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}}
