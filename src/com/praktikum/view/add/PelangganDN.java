package com.praktikum.view.add;

import java.util.Arrays;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

import com.global.App;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.PelangganM;
import com.praktikum.dao.PelangganDao;
import com.praktikum.db.Pelanggan;
import com.praktikum.lang.LPelanggan;

public class PelangganDN extends DialogDefault {
	protected TextField code;
	protected TextField nama;
	protected TextField jenisIdentitas;
	protected TextField noIdentitas;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField hp;
	protected ComboBox status;

	@Override
	public void actionReset() {
		code.setText("Auto");
		nama.setText("");
		jenisIdentitas.setText("");
		noIdentitas.setText("");
		alamat.setText("");
		kota.setText("");
		hp.setText("");
		status.setSelectedIndex(0);
		requestDefaultFocus();
	}

	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}

	public void initComponent() {
		code = new TextField();
		nama = new TextField();
		jenisIdentitas = new TextField();
		noIdentitas = new TextField();
		alamat = new TextArea();
		salamat = new ScrollPane(alamat);
		kota = new TextField();
		hp = new TextField();
		status = new ComboBox(App.getPelangganDao().getStatusData());
	}

	@Override
	public void setFocusEnter() {
		setFocusEnter(code, status);
		setFocusEnter(status, nama);
		setFocusEnter(nama, jenisIdentitas);
		setFocusEnter(jenisIdentitas, noIdentitas);
		setFocusEnter(noIdentitas, alamat);
		setFocusEnter(alamat, kota);
		setFocusEnter(kota, hp);
		setFocusEnter(hp, save);
		setFocusEnter(save, code);
		
	}

	@Override
	public void init() {
		icon = PelangganM.ICON_48;
		super.init();
		labelTitle.setText("Tambah Pelanggan");
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
				b.append(LPelanggan.CODE, code, 2, 2, 1);
				b.append(LPelanggan.STATUS,status , 6, 2, 1);
				b.append(LPelanggan.NAMA, nama, 2, 4, 1);
				b.append(LPelanggan.JENIS_IDENTITAS,jenisIdentitas , 2, 6, 1);
				b.append(LPelanggan.NO_IDENTITAS, noIdentitas, 2, 8, 1);
				b.append(LPelanggan.ALAMAT,salamat , 6, 4, 1,5);
				b.append(LPelanggan.KOTA,kota , 6, 10, 1);
				b.append(LPelanggan.HP, hp, 2, 10, 1);


		panelForm = b.getPanel();

	}

	@Override
	public void save() {
		Pelanggan model = new Pelanggan();
		model.setCode(code.getText().trim());
		model.setNama(nama.getText().trim());
		model.setJenisIdentitas(jenisIdentitas.getText().trim());
		model.setNoIdentitas(noIdentitas.getText().trim());
		model.setAlamat(alamat.getText().trim());
		model.setKota(kota.getText().trim());
		model.setHp(hp.getText().trim());
		model.setStatus(status.getSelectedIndex());
		PelangganDao d=App.getPelangganDao();
		d.saveAutoCode(model);
		this.model=model;
	}

	@Override
	public boolean validate() {
		PelangganDao d=App.getPelangganDao();
		if (code.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LPelanggan.CODE);
			return false;
		}else{
			long tmp=d.countByColumn("code", code.getText().trim());
			if (tmp>0) {
				App.showErrorDataSudahAda(LPelanggan.CODE);
				return false;
			}
		}
		
		if (nama.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LPelanggan.NAMA);
			return false;
		}
		
		if (noIdentitas.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LPelanggan.NO_IDENTITAS);
			return false;
		}else{
			long tmp=d.countByColumn("noIdentitas", noIdentitas.getText().trim());
			if (tmp>0) {
				App.showErrorDataSudahAda(LPelanggan.NO_IDENTITAS);
				return false;
			}
		}
		
		if (hp.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LPelanggan.HP);
			return false;
		}
		
		
		return true;
	}

}
