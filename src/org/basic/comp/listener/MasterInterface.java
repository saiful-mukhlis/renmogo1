package org.basic.comp.listener;

import javax.swing.Icon;
import org.basic.comp.adapter.WindowInterfaces;


public interface MasterInterface extends WidgetInterface {
	public void actionAdd();
	public void actionEdit();
	public void actionView();
	public void actionReload();
	public void actionDel();
	public void actionPrint();
	public void actionPrintPreview();
	public void actionSearch();
	public void actionPdf();
	public void actionWord();
	public void actionExcel();
	
	
	public boolean isAdd();
	public boolean isDel();
	public boolean isView();
	public boolean isEdit();
	public boolean isPrint();
	public boolean isSearch();
	public boolean isPdf();
	public boolean isWord();
	public boolean isExcel();
	
	public String getTitle();
	public String getUrlIcon();
	
	public Icon getIcon16();
	public Icon getIcon32();
	
	
	public WindowInterfaces getWindow();
	public void setWindow(WindowInterfaces window);
	public String getTitleToolBar();
	
	public void addSync(WiddgetSyncInterface widgetSync, int code);
	public void syncWidget(Object o, int code);
	public void requestDefaultSelected();
	
	
	public void perspectiveDefault();
	public void perspective1();
	public void perspective2();
	public void perspective3();
	public void perspective4();
	public boolean isPerspectiveDefault() ;
}
