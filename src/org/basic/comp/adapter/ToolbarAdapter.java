package org.basic.comp.adapter;

import javax.swing.JPanel;



public interface ToolbarAdapter extends HakAksesListener{
	public void build();
	public JPanel getPanel();
	public void setWindow(WindowInterfaces window);
	public void showMaxi();
	public void showMini();
}
