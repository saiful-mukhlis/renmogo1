package com.praktikum.view.def;

import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;
import com.praktikum.lang.LTypeMobil;

public class TypeMobilC extends DetailAbstract {
	protected TextField code;
	protected TextField denda;
	protected TextField harga;
	protected TextField jumlah;
	protected TextField jumlahTersedia;
	protected TextArea ket;
	protected ScrollPane sket;
	protected TextField nama;
	protected TextField codeMobil;
	protected TextField ketMobil;
	protected TextField statusMobil;
	
	
	protected String[] dataStatus=App.getMobilDao().getStatusData();
	
	public void init() {
		lebar = 0.002;
		initComponent();
	}
	public void resetContentComponent() {
		code.setText("Auto");
		denda.setText("");
		harga.setText("");
		jumlah.setText("");
		jumlahTersedia.setText("");
		ket.setText("");
		nama.setText("");
		codeMobil.setText("");
		ketMobil.setText("");
		statusMobil.setText("");
	}

	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		denda.setBackground(App.whiteSmoke);
		harga.setBackground(App.whiteSmoke);
		
		jumlah.setBackground(App.whiteSmoke);
		jumlahTersedia.setBackground(App.whiteSmoke);
		ket.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
		codeMobil.setBackground(App.whiteSmoke);
		ketMobil.setBackground(App.whiteSmoke);
		statusMobil.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		denda.setEditable(false);
		harga.setEditable(false);
		
		jumlah.setEditable(false);
		jumlahTersedia.setEditable(false);
		ket.setEditable(false);
		nama.setEditable(false);
		codeMobil.setEditable(false);
		ketMobil.setEditable(false);
		statusMobil.setEditable(false);
	}

	
	@Override
	public void load(Object o) {
		if (o==null) {
			resetContentComponent();
		}else  if(o instanceof TypeMobil){
			TypeMobil model=(TypeMobil) o;
			code.setText(model.getCode());
			denda.setText(model.getDendaToString());
			harga.setText(model.getHargaToString());
			
			jumlah.setText(model.getJumlah()+"");
			jumlahTersedia.setText(model.getJumlahTersedia()+"");
			ket.setText(model.getKet());
			nama.setText(model.getNama());
		}else if (o instanceof Mobil) {
			Mobil m=((Mobil) o);
			codeMobil.setText(m.getCode());
			ketMobil.setText(m.getKet());
			statusMobil.setText(dataStatus[m.getStatus()]);
			
			TypeMobil model=m.getTypeMobil();
			code.setText(model.getCode());
			denda.setText(model.getDendaToString());
			harga.setText(model.getHargaToString());
			
			jumlah.setText(model.getJumlah()+"");
			jumlahTersedia.setText(model.getJumlahTersedia()+"");
			ket.setText(model.getKet());
			nama.setText(model.getNama());
		}
	}
	public void initComponent() {
		code = new TextField();
		denda = new TextField();
		harga= new TextField();
		jumlah = new TextField();
		jumlahTersedia = new TextField();
		ket = new TextArea();
		sket = new ScrollPane(ket);
		nama = new TextField();
		codeMobil = new TextField();
		ketMobil = new TextField();
		statusMobil = new TextField();
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
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		builder = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		builder.addSeparator(App.getT("Data Mobil"), 2, 2, 7);
		builder.append(LTypeMobil.NAMA,nama , 2, 4, 1);
		builder.append(LTypeMobil.CODE, code, 6, 4, 1);
		builder.append(LTypeMobil.KET, sket, 2, 6, 1, 3);
		builder.append(LTypeMobil.HARGA, harga, 2, 10, 1);
		builder.append(LTypeMobil.JUMLAH,jumlah , 6, 6, 1);
		builder.append(LTypeMobil.JUMLAH_TERSEDIA,jumlahTersedia , 6, 8, 1);
		builder.append(LTypeMobil.DENDA, denda, 6, 10, 1);
		builder.addSeparator(App.getT("Data Mobil"), 2, 12, 7);
		builder.append(LTypeMobil.CODE_MOBIL,codeMobil , 2, 14, 1);
		builder.append(LTypeMobil.STATUS_MOBIL,statusMobil , 6, 14, 1);
		builder.append(LTypeMobil.KET_MOBIL, ketMobil, 2, 16, 1);
	}

	@Override
	public void requestDefaultFocus() {
		denda.requestFocus();
	}

}
