package org.basic.comp.listener;

import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

public interface ToolbarSmallInterface extends WidgetInterface {
	public MasterInterface getMaster();
	public void setMaster(MasterInterface master);
	public WindowInterfaces getWindow();
	public void setWindow(WindowInterfaces window);
	public void setItemSearch(SplitButton splitButton);
	public void setFieldSearch(TextFieldSearch fieldSearch);
}
