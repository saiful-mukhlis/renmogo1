package com.praktikum.view.edit;


import org.basic.comp.abst.DialogEditDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.TypeMobilM;
import com.praktikum.dao.TypeMobilDao;
import com.praktikum.db.TypeMobil;
import com.praktikum.lang.LTypeMobil;



public class MobilDE extends DialogEditDefault {
	protected TextField code;
	protected TextArea ket;
	protected ScrollPane sket;
	

	@Override
	public void load(Object o) {
		
		if (o==null) {
			code.setText("");
			ket.setText("");
		}else if(o instanceof TypeMobil) {
			TypeMobil model=(TypeMobil) o;
			this.model=model;
			try {
				this.old=model.clone();
			} catch (CloneNotSupportedException e) {
				App.printErr(e);
			}
			code.setText(model.getCode());
			ket.setText(model.getKet());
		}
	}

	@Override
	public void actionReset() {
		actionReload();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		code.requestFocus();
	}



	public void initComponent() {
		code = new TextField();
		ket = new TextArea();
		sket = new ScrollPane(ket);
	}
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, ket);
		setFocusEnter(ket, save);
		setFocusEnter(save, code);
		
	}


	@Override
	public void init() {
		icon=TypeMobilM.ICON_48;
		super.init();
		labelTitle.setText("Edit Mobil");
		labelNote.setText("Isi data dengan benar");
		initComponent();
		buildForm();
	}

	public void buildForm() {
		initComponent();

		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
//		col.append("20px,");
//		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,15dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
				b.append(LTypeMobil.CODE, code, 2, 2, 1);
				b.append(LTypeMobil.KET, sket, 2, 4, 1);


		panelForm = b.getPanel();

	}


	@Override
	public void save() {
		TypeMobil model=(TypeMobil) this.model;
		model.setCode(code.getText().trim());
		model.setKet(ket.getText());
		
		TypeMobilDao d=App.getTypeMobilDao();
		d.update(model);
	}

	@Override
	public boolean validate() {
		TypeMobilDao d=App.getTypeMobilDao();
		if (code.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LTypeMobil.CODE);
			return false;
		}
		if (old instanceof TypeMobil) {
			if (!((TypeMobil) old).getCode().equalsIgnoreCase(code.getText().trim())) {
				long tmp=d.countByColumn("code", code.getText().trim());
				if (tmp>0) {
					App.showErrorDataSudahAda(LTypeMobil.CODE);
					return false;
				}
			}
		}
		
		return true;
	}
	
}
