package org.basic.comp.listener;

import javax.swing.JPanel;

public interface WidgetInterface {
	public void build();
	public void load(Object model);
	public JPanel getPanel();
}
