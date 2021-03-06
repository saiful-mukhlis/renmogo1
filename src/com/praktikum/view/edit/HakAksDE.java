package com.praktikum.view.edit;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.basic.comp.abst.DialogEditDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

import com.basic.lang.LHakAks;
import com.global.App;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.dao.HakAksDao;
import com.praktikum.db.HakAks;
import com.praktikum.db.HakAks;



public class HakAksDE extends DialogEditDefault {
	protected TextField code;
	protected TextField nama;

	@Override
	public void load(Object o) {
		
		if (o==null) {
			code.setText("");
			nama.setText("");
		}else if(o instanceof HakAks){
			HakAks model=(HakAks) o;
			this.model=model;
			try {
				this.old=model.clone();
			} catch (CloneNotSupportedException e) {
				App.printErr(e);
			}
			code.setText(model.getCode());
			nama.setText(model.getNama());
		}
	}

	@Override
	public void actionReset() {
		actionReload();
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
		icon=HakAksM.ICON_48;
		super.init();
		labelTitle.setText("Edit Hak Akses");
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

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LHakAks.CODE, code, 2, 2, 1);
		b.append(LHakAks.NAMA, nama, 2, 4, 1);


		panelForm = b.getPanel();

	}


	@Override
	public void save() {
		HakAks model=(HakAks) this.model;
		model.setCode(code.getText().trim());
		model.setNama(nama.getText().trim());
		HakAksDao d=App.getHakAks();
		d.update(model);
	}

	@Override
	public boolean validate() {
		HakAksDao d=App.getHakAks();
		if (code.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LHakAks.CODE);
			return false;
		}
		if (old instanceof HakAks) {
			if (!((HakAks) old).getCode().equalsIgnoreCase(code.getText().trim())) {
				long tmp=d.countByColumn("code", code.getText().trim());
				if (tmp>0) {
					App.showErrorDataSudahAda(LHakAks.CODE);
					return false;
				}
			}
		}
		
		
		if (nama.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LHakAks.NAMA);
			return false;
		}
//		if (!old.getNama().equalsIgnoreCase(nama.getText().trim())) {
//			long tmp=d.countByColumn("nama", nama.getText().trim());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(LHakAks.NAMA);
//				return false;
//			}
//		}
		return true;
	}
	
}
