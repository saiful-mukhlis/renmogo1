package com.praktikum.view.detail;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.basic.icon.IconBase;
import com.global.App;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.view.def.PegawaiC;

public class PegawaiV extends PegawaiC {
	
	public void init() {
		super.init();
		title = PegawaiM.TITLE_VIEW;
		icon = IconBase.VIEW;
		initComponent();
		setColorView();
		setEditable(false);
		buildLabel();
		buildForm();
		buildPanel();
	}

	public void buildPanel(){
		panel=new JPanel(new BorderLayout());
		panelForm = builder.getPanel();
		
		panelForm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (master!=null) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}else{
						App.showErrSementara("Master null");
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		pane=new JScrollPane(panelForm);
//		pane.setBackground(Color.WHITE);
//		panel.setBackground(Color.WHITE);
//		panelForm.setBackground(Color.WHITE);
		
		
		pane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panel.add(pane, BorderLayout.CENTER);
		panel.add(label, BorderLayout.NORTH);
	}
	
	
}
