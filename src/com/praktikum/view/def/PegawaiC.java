package com.praktikum.view.def;

import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.db.Pegawai;
import com.praktikum.lang.LPegawai;

public class PegawaiC extends DetailAbstract {

	protected TextField code;
	protected TextField nama;
	protected TextField username;
	protected TextField jenisIdentitas;
	protected TextField noIdentitas;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField hp;
	protected TextField status;
	
	public void init() {
		lebar = 0.002;
		initComponent();
	}

	public void resetContentComponent() {
		code.setText("Auto");
		nama.setText("");
		username.setText("");
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
		
		username.setBackground(App.whiteSmoke);
		jenisIdentitas.setBackground(App.whiteSmoke);
		noIdentitas.setBackground(App.whiteSmoke);
		alamat.setBackground(App.whiteSmoke);
		salamat.setBackground(App.whiteSmoke);
		kota.setBackground(App.whiteSmoke);
		hp.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		nama.setEditable(false);
		
		username.setEditable(false);
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
			if (o instanceof Pegawai) {
				Pegawai model=(Pegawai) o;
				code.setText(model.getCode());
				nama.setText(model.getNama());
				
				username.setText(model.getUsername());
				jenisIdentitas.setText(model.getJenisIdentitas());
				noIdentitas.setText(model.getNoIdentitas());
				alamat.setText(model.getAlamat());
				kota.setText(model.getKota());
				hp.setText(model.getHp());
				if (model.getStatus()==0) {
					status.setText("-");
				}else{
					String tmp[]=App.getPegawaiDao().getStatusData();
					status.setText(tmp[model.getStatus()]);
				}
			}
			
			
		}
	}
	public void initComponent() {
		code = new TextField();
		nama = new TextField();
		username = new TextField();
		jenisIdentitas = new TextField();
		noIdentitas = new TextField();
		alamat = new TextArea();
		salamat = new ScrollPane(alamat);
		kota = new TextField();
		hp = new TextField();
		status = new TextField();
	}

	public void buildForm() {
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
		builder.append(LPegawai.CODE, code, 2, 2, 1);
		builder.append(LPegawai.STATUS,status , 6, 2, 1);
		builder.append(LPegawai.NAMA, nama, 2, 4, 1);
		builder.append(LPegawai.USERNAME,username , 6, 4, 1);
		builder.append(LPegawai.JENIS_IDENTITAS,jenisIdentitas , 2, 6, 1);
		builder.append(LPegawai.NO_IDENTITAS, noIdentitas, 6, 6, 1);
		builder.append(LPegawai.ALAMAT,salamat , 2, 8, 1,3);
		builder.append(LPegawai.KOTA,kota , 6, 8, 1);
		builder.append(LPegawai.HP, hp, 6, 10, 1);
		
		
		

	}

	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}

}
