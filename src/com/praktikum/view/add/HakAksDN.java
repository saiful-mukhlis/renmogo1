package com.praktikum.view.add;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.TextField;

import com.basic.lang.LHakAks;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.dao.HakAksDao;
import com.praktikum.db.HakAks;

public class HakAksDN extends DialogDefault {
	protected TextField code;
	protected TextField nama;
	

	@Override
	public void actionReset() {
		code.setText("Auto");
		nama.setText("");
		requestDefaultFocus();
	}

	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}

	public void initComponent() {
		code = new TextField();
		nama = new TextField();

	}

	@Override
	public void setFocusEnter() {
		setFocusEnter(code, nama);
		setFocusEnter(nama, save);
		setFocusEnter(save, code);
	}

	@Override
	public void init() {
		icon = HakAksM.ICON_48;
		super.init();
		labelTitle.setText("Tambah Hak Akses");
		labelNote.setText("Isi data dengan benar");
		initComponent();
		buildForm();
	}

	public void buildForm() {
		initComponent();

		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:400px:g,");
		col.append("30px,");

		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("f:40dlu:g,15dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		// l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		// append(String i8n, Component c, int x, int y, int w)
		b.append(LHakAks.CODE, code, 2, 2, 1);
		b.append(LHakAks.NAMA, nama, 2, 4, 1);


		panelForm = b.getPanel();

	}

	@Override
	public void save() {
		HakAks model = new HakAks();
		model.setCode(code.getText().trim());
		model.setNama(nama.getText().trim());
		
		HakAksDao d=App.getHakAks();
		d.saveAutoCode(model);
		this.model=model;
	}

	@Override
	public boolean validate() {
		HakAksDao d=App.getHakAks();
		if (code.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LHakAks.CODE);
			return false;
		}else{
			long tmp=d.countByColumn("code", code.getText().trim());
			if (tmp>0) {
				App.showErrorDataSudahAda(LHakAks.CODE);
				return false;
			}
		}
		
		
		if (nama.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LHakAks.NAMA);
			return false;
		}else{
			long tmp=d.countByColumn("nama", nama.getText().trim());
			if (tmp>0) {
				App.showErrorDataSudahAda(LHakAks.NAMA);
				return false;
			}
		}
		
		

		return true;
	}

}
