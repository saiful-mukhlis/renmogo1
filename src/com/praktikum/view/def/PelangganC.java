package com.praktikum.view.def;

import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.db.Pegawai;
import com.praktikum.db.Pelanggan;
import com.praktikum.lang.LPegawai;
import com.praktikum.lang.LPelanggan;

public class PelangganC extends DetailAbstract {

	protected TextField code;
	protected TextField nama;
	protected TextField jenisIdentitas;
	protected TextField noIdentitas;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField hp;
	protected TextField status;
	
	protected String [] dataStatus=App.getPelangganDao().getStatusData();
	
	public void init() {
		lebar = 0.002;
		initComponent();
	}

	public void resetContentComponent() {
		code.setText("Auto");
		nama.setText("");
		jenisIdentitas.setText("");
		noIdentitas.setText("");
		alamat.setText("");
		kota.setText("");
		hp.setText("");
		status.setText("");
	}

	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
		jenisIdentitas.setBackground(App.whiteSmoke);
		noIdentitas.setBackground(App.whiteSmoke);
		alamat.setBackground(App.whiteSmoke);
		kota.setBackground(App.whiteSmoke);
		hp.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		nama.setEditable(false);
		jenisIdentitas.setEditable(false);
		noIdentitas.setEditable(false);
		alamat.setEditable(false);
		kota.setEditable(false);
		hp.setEditable(false);
		status.setEditable(false);
	}

	

	@Override
	public void load(Object o) {
		if (o==null) {
			resetContentComponent();
		}else  {
			if (o instanceof Pelanggan) {
				Pelanggan model=(Pelanggan) o;
				code.setText(model.getCode());
				nama.setText(model.getNama());
				jenisIdentitas.setText(model.getJenisIdentitas());
				noIdentitas.setText(model.getNoIdentitas());
				alamat.setText(model.getAlamat());
				kota.setText(model.getKota());
				hp.setText(model.getHp());
				if (model.getStatus()==0) {
					status.setText("");
				}else{
					status.setText(dataStatus[model.getStatus()]);
				}
			}
			
		}
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
		status = new TextField();
	}

	public void buildForm() {

		//Double tmp = App.getW() * lebar;// 0.51
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
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
		builder.append(LPelanggan.CODE, code, 2, 2, 1);
		builder.append(LPelanggan.STATUS,status , 6, 2, 1);
		builder.append(LPelanggan.NAMA, nama, 2, 4, 1);
		builder.append(LPelanggan.JENIS_IDENTITAS,jenisIdentitas , 2, 6, 1);
		builder.append(LPelanggan.NO_IDENTITAS, noIdentitas, 2, 8, 1);
		builder.append(LPelanggan.ALAMAT,salamat , 6, 4, 1,5);
		builder.append(LPelanggan.KOTA,kota , 6, 10, 1);
		builder.append(LPelanggan.HP, hp, 2, 10, 1);
		
		
		

	}

	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}

}
