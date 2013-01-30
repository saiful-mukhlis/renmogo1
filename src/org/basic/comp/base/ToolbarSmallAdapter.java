package org.basic.comp.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.basic.comp.adapter.HakAksesListener;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.ToolbarSmallInterface;
import org.basic.comp.listener.WidgetInterface;


public class ToolbarSmallAdapter extends JToolBar implements ToolbarSmallInterface, HakAksesListener {
	public MasterInterface master;
	public ToolbarSmallAdapter(MasterInterface master) {
		super();
		this.master=master;
//		FormLayout layout = new FormLayout(
//				" 4dlu,  	f:p,  4dlu,   p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,  p,  4dlu,  	p:g,  4dlu,  	p,  4dlu,   	"
//						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",
//
//				"p,3dlu");
//		setLayout(layout);

		setBackground(Color.WHITE);
	}


	public MasterInterface getMaster() {
		return master;
	}

	public void setMaster(MasterInterface master) {
		this.master = master;
	}


	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}



	protected JPanel panel;
	@Override
	public JPanel getPanel() {
		if (panel==null) {
			panel=new JPanel(new BorderLayout());
			panel.add(this, BorderLayout.CENTER);
		}
		return panel;
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
	public void setItemSearch(SplitButton splitButton) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setFieldSearch(TextFieldSearch fieldSearch) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changeHakAkses() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void load(Object model) {
		// TODO Auto-generated method stub
		
	}


	


	
	
}
