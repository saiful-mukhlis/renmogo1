package org.basic.comp.adapter;

import java.util.List;

import javax.swing.table.TableModel;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.dao.adapter.Dao;

public interface TableModelInterface<T> extends TableModel {
	public void addModel(T model);

	public void editModel(T model, int index);

	public void setTableParent(TableInterfaces table);

	public TableInterfaces getTableParent();

	public boolean isSearching();

	public void reload();

	public void setPaging(PagingInterface paging);

	public void fireTableDataChanged();

	public void loadDataModel();

	public void loadJumlahData();

	public void initDao();

	public void initNamaKolom();

	public List getModels();

	public void setModels(List model);

	public Dao getDao();

	public String[] getNamaKolom();

	public SplitButton getItemSearch();

	public TextFieldSearch getFieldSearch();

	public void setBind();

	public T getBind();
	
	public void load();
	
	public void clear();
}
