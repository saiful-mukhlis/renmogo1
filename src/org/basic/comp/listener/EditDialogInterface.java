package org.basic.comp.listener;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.dao.adapter.Dao;


public interface EditDialogInterface extends WidgetInterface{
	
	public void setIndex(int index);
	
	public void actionReload();

	public void actionCancel();

	public void actionSave();

	public void requestDefaultFocus();

	public void setMaster(MasterInterface master);

	public MasterInterface getMaster();

	public boolean beforeSave();

	public void afterSave();

	public void setListWidget(ListInterfaces table);

	public void addSync(WiddgetSyncInterface widgetSync);
	
	public boolean validate();

	public Icon getIcon();

	public void setIcon(Icon icon);

	public JLabel getLabelTitle();

	public void setLabelTitle(JLabel label);

	public JScrollPane getPane();

	public Dao getDao();

	public void setDao(Dao dao);

	public JPanel getPanelForm();

	public void setPanelForm(JPanel panelForm);

	public void setFocusEnter();

	public void buildPanel();

	public void buildButton();

	public void setLayoutButton();

	public void setLayoutTitle();

	public void build();

	public JPanel getPanel();
}
