package org.basic.comp.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.basic.comp.listener.MasterInterface;

import com.basic.lang.LApp;
import com.global.App;
import com.jgoodies.forms.layout.CellConstraints;


public class ToolbarSmallRP extends ToolbarSmallAdapter {

	protected JButton reload;
	protected JButton print;
	
	
	public ToolbarSmallRP(MasterInterface master) {
		super(master);
		build();
		buildActions();
	}

//	public ToolbarSmallRP() {
//		super();
//		build();
//	}
	
	public void build(){
		initComponent();
		JLabel label = new JLabel(App.getIcon(master.getUrlIcon()));
		label.setText(master.getTitle());
		CellConstraints cc = new CellConstraints();
		add(label, cc.xy(2, 1));
		add(reload, cc.xy(12, 1));
		add(print, cc.xy(16, 1));
	}
	
	public void initComponent(){
		reload = new JButton(App.getIcon(LApp.iconReload16));
		print = new JButton(App.getIcon(LApp.iconTambah16));
	}
	
	public void buildActions(){


		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionPrint();
			}
		});

		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionReload();
			}
		});
		
		
	}

	public JButton getReload() {
		return reload;
	}

	public void setReload(JButton reload) {
		this.reload = reload;
	}




	public JButton getPrint() {
		return print;
	}

	public void setPrint(JButton print) {
		this.print = print;
	}


	
}
