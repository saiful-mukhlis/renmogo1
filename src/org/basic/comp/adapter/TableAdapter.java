package org.basic.comp.adapter;


import javax.swing.JPanel;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.PanelBottomInterface;
import org.basic.comp.listener.WidgetInterface;
import org.jdesktop.swingx.JXTable;



public class TableAdapter implements TableInterfaces{

	@Override
	public void reload() {
		getTableModel().reload();
		selected();
	}

	@Override
	public void addModel(Object model) {
		getTableModel().addModel(model);
	}

	@Override
	public void editModel(Object model, int index) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public SplitButton getItemSearch() {
		return getTableModel().getItemSearch();
	}

	@Override
	public TextFieldSearch getFieldSearch() {
		return getTableModel().getFieldSearch();
	}

	@Override
	public void aksiDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getModelSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndexRowSelected() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSearching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MasterInterface getMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaster(MasterInterface master) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PanelBottomInterface getPanelBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(Object model) {
		// TODO Auto-generated method stub
		getTableModel().load();
		selected();
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JXTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableModelInterface getTableModel() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setTypeEfectWidget(int typeEfectWidget) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTableModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShowAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSimple() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WindowInterfaces getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getNamaKolom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getBind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWidget(WidgetInterface widget) {
		// TODO Auto-generated method stub
		
	}
	
}
