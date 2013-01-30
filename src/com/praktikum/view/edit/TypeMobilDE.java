package com.praktikum.view.edit;

import java.math.BigDecimal;

import org.basic.comp.abst.DialogEditDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;

import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.TypeMobilM;
import com.praktikum.dao.TypeMobilDao;
import com.praktikum.db.TypeMobil;
import com.praktikum.lang.LTypeMobil;



public class TypeMobilDE extends DialogEditDefault {
	protected TextField code;
	protected TextFieldAmount denda;
	protected TextFieldAmount harga;
	protected TextArea ket;
	protected ScrollPane sket;
	protected TextField nama;
	

	@Override
	public void load(Object o) {
		
		if (o==null) {
			code.setText("");
			denda.setText("");
			harga.setText("");
			ket.setText("");
			nama.setText("");
		}else if(o instanceof TypeMobil) {
			TypeMobil model=(TypeMobil) o;
			this.model=model;
			try {
				this.old=model.clone();
			} catch (CloneNotSupportedException e) {
				App.printErr(e);
			}
			code.setText(model.getCode());
			nama.setText(model.getNama());
			denda.setText(model.getDenda()+"");
			harga.setText(model.getHarga()+"");
			ket.setText(model.getKet());
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
		denda = new TextFieldAmount();
		harga = new TextFieldAmount();
		ket = new TextArea();
		sket = new ScrollPane(ket);
		nama = new TextField();
		
	}
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, nama);
		setFocusEnter(nama, ket);
		setFocusEnter(ket, harga);
		setFocusEnter(harga, denda);
		setFocusEnter(denda, save);
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
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
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

		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
				b.append(LTypeMobil.NAMA,nama , 2, 2, 1);
				b.append(LTypeMobil.CODE, code, 6, 2, 1);
				b.append(LTypeMobil.KET, sket, 2, 4, 1, 7);
				b.append(LTypeMobil.HARGA, harga, 6, 4, 1);
				b.append(LTypeMobil.DENDA, denda, 6, 6, 1);


		panelForm = b.getPanel();

	}


	@Override
	public void save() {
		TypeMobil model=(TypeMobil) this.model;
		model.setCode(code.getText().trim());
		model.setNama(nama.getText().trim());
		
		model.setHarga(TextFieldAmount.getValue(harga));
		model.setDenda(TextFieldAmount.getValue(denda));
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
		
		
		if (nama.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LTypeMobil.NAMA);
			return false;
		}
		int tmp=TextFieldAmount.getValue(harga).compareTo(new BigDecimal(0));
		if (tmp==0 || tmp<0) {
			App.showErrorFieldEmpty(LTypeMobil.HARGA);
			return false;
		}
		
		return true;
	}
	
}
