package com.praktikum.view.def;

import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.TextField;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.db.Kembali;
import com.praktikum.lang.LKembali;

public class KembaliC extends DetailAbstract {

	protected TextField codeSewa;
	protected TextField pelanggan;
	protected TextField typeMobil;
	protected TextField codeMobil;
	protected TextField tgl;
	protected TextField waktu;
	protected TextField lebih;
	protected TextField denda;
	protected TextField bayar;
	
	public void init() {
		lebar = 0.002;
		initComponent();
	}

	public void resetContentComponent() {
		codeSewa.setText("Auto");
		pelanggan.setText("");
		typeMobil.setText("");
		codeMobil.setText("");
		tgl.setText("");
		waktu.setText("");
		lebih.setText("");
		denda.setText("");
		bayar.setText("");
	}

	public void setColorView() {
		codeSewa.setBackground(App.whiteSmoke);
		pelanggan.setBackground(App.whiteSmoke);
		
		typeMobil.setBackground(App.whiteSmoke);
		codeMobil.setBackground(App.whiteSmoke);
		tgl.setBackground(App.whiteSmoke);
		waktu.setBackground(App.whiteSmoke);
		lebih.setBackground(App.whiteSmoke);
		denda.setBackground(App.whiteSmoke);
		bayar.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		codeSewa.setEditable(false);
		pelanggan.setEditable(false);
		
		typeMobil.setEditable(false);
		codeMobil.setEditable(false);
		tgl.setEditable(false);
		waktu.setEditable(false);
		lebih.setEditable(false);
		denda.setEditable(false);
		bayar.setEditable(false);
	}

	

	@Override
	public void load(Object o) {
		if (o==null) {
			resetContentComponent();
		}else  {
			Kembali model=(Kembali) o;
			codeSewa.setText(model.getCode());
			pelanggan.setText(model.getNama());
			
			typeMobil.setText(model.getUsername());
			codeMobil.setText(model.getJenisIdentitas());
			tgl.setText(model.getNoIdentitas());
			waktu.setText(model.getAlamat());
			lebih.setText(model.getKota());
			denda.setText(model.getHp());
			bayar.setText(model.getStatus()+"");
		}
	}
	public void initComponent() {
		codeSewa = new TextField();
		pelanggan = new TextField();
		typeMobil = new TextField();
		codeMobil = new TextField();
		tgl = new TextField();
		waktu = new TextField();
		lebih = new TextField();
		denda = new TextField();
		bayar = new TextField();
	}

	public void buildForm() {

		//Double tmp = App.getW() * lebar;// 0.51
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");
		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		builder = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		builder.append(LKembali.CODE, codeSewa, 2, 2, 1);
		builder.append(LKembali.NAMA, pelanggan, 2, 4, 1);
		builder.append(LKembali.USERNAME,typeMobil , 2, 6, 1);
		builder.append(LKembali.JENIS_IDENTITAS,codeMobil , 2, 8, 1);
		builder.append(LKembali.NO_IDENTITAS, tgl, 2, 10, 1);
		builder.append(LKembali.ALAMAT,waktu , 2, 12, 1);
		builder.append(LKembali.KOTA,lebih , 2, 14, 1);
		builder.append(LKembali.HP, denda, 2, 16, 1);
		builder.append(LKembali.STATUS,bayar , 2, 20, 1);
		
		
		

	}

	@Override
	public void requestDefaultFocus() {
		pelanggan.requestFocus();
	}

}
