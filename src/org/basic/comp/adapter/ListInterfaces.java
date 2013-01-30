package org.basic.comp.adapter;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.listener.EditDialogInterface;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.PanelBottomInterface;
import org.basic.comp.listener.ToolbarSmallInterface;
import org.basic.comp.listener.WidgetInterface;


public interface ListInterfaces extends WidgetInterface {
	public void reload();

	public void addModel(Object model);
	
	public void editModel(Object model, int index);
	
	public SplitButton getItemSearch();

	public TextFieldSearch getFieldSearch();

	public void aksiDelete();
	
	public void selected();
	
	public Object getModelSelected();

	public int getIndexRowSelected();
	
	public boolean isSearching();
	
	public MasterInterface getMaster();

	public void setMaster(MasterInterface master);
	
	public PanelBottomInterface getPanelBottom();

	public void setSimple();

	public void addWidget(WidgetInterface widget);
	
}
