package org.basic.comp.adapter;

import javax.swing.Icon;
import javax.swing.JPanel;

import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.WiddgetSyncInterface;

public class MasterAdapter implements MasterInterface, HakAksesListener {

	protected JPanel panel;
	protected boolean perspectiveDefault = true;
	protected WindowInterfaces window;

	@Override
	public void build() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(Object model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void perspectiveDefault() {
		// TODO Auto-generated method stub

	}

	@Override
	public void perspective1() {
		// TODO Auto-generated method stub

	}

	@Override
	public void perspective2() {
		// TODO Auto-generated method stub

	}

	@Override
	public void perspective3() {
		// TODO Auto-generated method stub

	}

	@Override
	public void perspective4() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeHakAkses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionAdd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionEdit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionReload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionDel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPrint() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAdd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isView() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEdit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrint() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrlIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	// public List<ToolbarSmallAdapter> getChangeStateActions() {
	// return changeStateActions;
	// }
	//
	// public void setChangeStateActions(List<ToolbarSmallAdapter>
	// changeStateActions) {
	// this.changeStateActions = changeStateActions;
	// }

	@Override
	public WindowInterfaces getWindow() {
		return window;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		this.window = window;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public boolean isPerspectiveDefault() {
		return perspectiveDefault;
	}

	public void setPerspectiveDefault(boolean perspectiveDefault) {
		this.perspectiveDefault = perspectiveDefault;
	}

	@Override
	public Icon getIcon16() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Icon getIcon32() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPdf() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionWord() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionExcel() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSearch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPdf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWord() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPrintPreview() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitleToolBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSync(WiddgetSyncInterface widgetSync, int code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void syncWidget(Object o, int code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestDefaultSelected() {
		// TODO Auto-generated method stub

	}

}
