package com.basic.view.def;


import javax.swing.JSplitPane;

import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TreeHakAkses;

import com.basic.lang.LHakAks;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.base.TreeHakAksesPraktikum;
import com.praktikum.db.HakAks;

public class HakAksC extends DetailAbstract {

	protected TextField code;
	protected TextField nama;
	
	protected TreeHakAkses tree;
	protected JSplitPane splitPane;

	public void init() {
		lebar = 0.002;
		dao = App.getHakAks();
		tree=new TreeHakAksesPraktikum();
		
		initComponent();
	}

	public void resetContentComponent() {
		code.setText("");
		nama.setText("");
	}

	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		nama.setEditable(false);
	}

//	public void setContentComponent(ODocument model) {
//		if (modelIsTrue(model)) {
//			GrpDao d = App.getGrpDao();
//			code.setText(d.getCode(model));
//			name.setText(d.getName(model));
//			note.setText(d.getNote(model));
//			createAt.setText(d.getCreateAt(model));
//			updateAt.setText(d.getUpdateAt(model));
//		}
//	}
	
	

	@Override
	public void load(Object o) {
		if (o==null) {
			code.setText("");
			nama.setText("");
		}else if(o instanceof HakAks) {
			HakAks model=(HakAks) o;
			code.setText(model.getCode());
			nama.setText(model.getNama());
		}
		
		tree.load(o);
	}



	public void initComponent() {
		code = new TextField();
		nama = new TextField();
	}

	public void buildForm() {

		//Double tmp = App.getW() * lebar;// 0.51
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
//		col.append("20px,");
//		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");
		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
//		row.append("p,3dlu,");
//		row.append("p,3dlu,");
//		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

//		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LHakAks.CODE, code, 2, 2, 1);
		b.append(LHakAks.NAMA, nama, 2, 4, 1);
		
		
		
		StringBuilder c=new StringBuilder();
		StringBuilder r=new StringBuilder();
		c.append("5px,f:0px:g,1px,");
		
		r.append("0dlu,");
		r.append("f:1000dlu:g,0dlu,");
//		r.append("p,3dlu,");
		layout=new FormLayout(c.toString(), r.toString());
		builder=new FormBuilder(layout);
		
		tree.build();
//		tree.getPanel().setBorder(App.borderBlack);
		splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, b.getPanel(), tree.getPanel());
//		splitPane.setDividerSize(20);
		Double tmp=App.getW()*0.25;
		splitPane.setDividerLocation(tmp.intValue());
//		splitPane.setOneTouchExpandable(false);
		splitPane.setOpaque(false);
		splitPane.setBorder(null);
//		splitPane.setBackground(Color.red);
		builder.append(splitPane, 2, 2);
//		builder.append(splitPane, 2, 4);

	}

	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}

}
