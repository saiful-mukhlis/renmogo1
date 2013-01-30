package com.basic.view.def;



import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;

import com.basic.dao.impl.UsrDao;
import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.lang.LUsr;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrC extends DetailAbstract{
	
	protected TextField code;
	protected TextField username;
	protected TextField grp;
	protected TextField nama;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField noIdentitas;
	protected TextField jenisIdentitas;
	protected TextField kotaLahir;
	protected TextField tglLahir;
	protected TextField jenisKelamin;
	protected TextField noTelp;
	protected TextField noHp1;
	protected TextField noHp2;
	protected TextField pinBb;
	protected TextField tglMasuk;
	protected TextFieldAmount gaji;
	protected TextField jenisPekerjaan;
	protected TextField pendidikanTerakhir;
	protected TextField status;
	
	
	public void initComponent(ODatabaseDocumentTx db){
		code=new TextField();
		username=new TextField();
		grp=new TextField();
		nama=new TextField();
		alamat=new TextArea();
		salamat=new ScrollPane(alamat);
		
		kota=new TextField();
		noIdentitas=new TextField();
		jenisIdentitas=new TextField();
		kotaLahir=new TextField();
		tglLahir=new TextField();
		jenisKelamin=new TextField();
		noTelp=new TextField();
		noHp1=new TextField();
		noHp2=new TextField();
		pinBb=new TextField();
		tglMasuk=new TextField();
		gaji=new TextFieldAmount();
		jenisPekerjaan=new TextField();
		pendidikanTerakhir=new TextField();
		status=new TextField();
	}
	
	public void resetContentComponent(){
		code.setText("");
		username.setText("");
		grp.setText("");
		nama.setText("");
		alamat.setText("");
		
		kota.setText("");
		noIdentitas.setText("");
		jenisIdentitas.setText("");
		kotaLahir.setText("");
		tglLahir.setText("");
		jenisKelamin.setText("");
		noTelp.setText("");
		noHp1.setText("");
		noHp2.setText("");
		pinBb.setText("");
		tglMasuk.setText("");
		gaji.setText("");
		jenisPekerjaan.setText("");
		pendidikanTerakhir.setText("");
		status.setText("");
		
	}
	
	public void setColorView(){
		
		code.setBackground(App.whiteSmoke);
		username.setBackground(App.whiteSmoke);
		grp.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
		alamat.setBackground(App.whiteSmoke);
		salamat.setBackground(App.whiteSmoke);
		
		kota.setBackground(App.whiteSmoke);
		noIdentitas.setBackground(App.whiteSmoke);
		jenisIdentitas.setBackground(App.whiteSmoke);
		kotaLahir.setBackground(App.whiteSmoke);
		tglLahir.setBackground(App.whiteSmoke);
		jenisKelamin.setBackground(App.whiteSmoke);
		noTelp.setBackground(App.whiteSmoke);
		noHp1.setBackground(App.whiteSmoke);
		noHp2.setBackground(App.whiteSmoke);
		pinBb.setBackground(App.whiteSmoke);
		tglMasuk.setBackground(App.whiteSmoke);
		gaji.setBackground(App.whiteSmoke);
		jenisPekerjaan.setBackground(App.whiteSmoke);
		pendidikanTerakhir.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		
		code.setEditable(isEdit);
		username.setEditable(isEdit);
		grp.setEditable(isEdit);
		nama.setEditable(isEdit);
		alamat.setEditable(isEdit);
		
		kota.setEditable(isEdit);
		noIdentitas.setEditable(isEdit);
		jenisIdentitas.setEditable(isEdit);
		kotaLahir.setEditable(isEdit);
		tglLahir.setEditable(isEdit);
		jenisKelamin.setEditable(isEdit);
		noTelp.setEditable(isEdit);
		noHp1.setEditable(isEdit);
		noHp2.setEditable(isEdit);
		pinBb.setEditable(isEdit);
		tglMasuk.setEditable(isEdit);
		gaji.setEditable(isEdit);
		jenisPekerjaan.setEditable(isEdit);
		pendidikanTerakhir.setEditable(isEdit);
		status.setEditable(isEdit);
		
	}
	
	public void setContentComponent(ODocument o){
		 if (o!=null && modelIsTrue(o)){
			 UsrDao d=App.getUsrDao();
			 code.setText(d.getCode(o));
			 
			 username.setText(d.getUsername(o));
//				grp.setText(model.field(Usr.)+"");
				nama.setText(d.getNama(o));
				alamat.setText(d.getAlamat(o));
				
				kota.setText(d.getKota(o));
				noIdentitas.setText(d.getNoIdentitas(o));
				jenisIdentitas.setText(d.getJenisIdentitas(o));
				kotaLahir.setText(d.getKotaLahir(o));
				tglLahir.setText(d.tglLahirToString(o));
				jenisKelamin.setText(d.jenisKelaminToString(o));
				noTelp.setText(d.getNoTelp(o));
				noHp1.setText(d.getNoHp1(o));
				noHp2.setText(d.getNoHp2(o));
				pinBb.setText(d.getPinBb(o));
				tglMasuk.setText(d.tglMasukToString(o));
				gaji.setText(d.gajiToString(o));
//				jenisPekerjaan.setText(model.field(Usr.JENIS_PEKERJAAN)+"");
				pendidikanTerakhir.setText(d.getPendidikanTerakhir(o));
				status.setText(d.statusToString(o));
				
		 }else if (o!=null && o.field("@class").equals(Grp.TABLE)) {
			 grp.setText(o.field(Grp.NAME)+"");
		}else if (o!=null && o.field("@class").equals(JenisPekerjaan.TABLE)) {
			 jenisPekerjaan.setText(o.field(JenisPekerjaan.NAMA)+"");
		}
	}
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.002;
		dao=App.getUsrDao();
		initComponent(db);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		Double tmp = App.getW()*lebar;
		layout = new FormLayout(
				//"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px," +
						"r:p,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,	10dlu,	r:p,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,	r:p,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,",
				

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  2dlu,   t:30dlu,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		 layout.setColumnGroups(new int[][] { { 3,5,10,14 } });
		builder = new FormBuilder(layout, true);

		builder.append( LUsr.CODE, code, 1, 1, 1);
		builder.append( LUsr.USERNAME, username, 1, 3, 3);
		
		builder.append( LUsr.GRP, grp, 1, 5, 3);
		builder.append( LUsr.STATUS, status, 1, 7, 3);

		builder.append( LUsr.TGL_MASUK, tglMasuk, 1, 12, 3);
		builder.append( LUsr.GAJI, gaji, 1, 14, 3);
		builder.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 1, 16, 3);
		
		
		
		
		builder.append( LUsr.NAMA, nama, 8, 1, 5);
		builder.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 8, 3, 1);
		builder.append( LUsr.NO_IDENTITAS, noIdentitas, 12, 3, 1);
		builder.append( LUsr.KOTA_LAHIR, kotaLahir, 8, 5, 1);
		builder.append( LUsr.TGL_LAHIR, tglLahir , 12, 5, 1);
		builder.append( LUsr.JENIS_KELAMIN, jenisKelamin, 8, 7, 1);
		builder.appendTa( LUsr.ALAMAT, salamat, 8, 9, 5, 4);
		builder.append( LUsr.KOTA, kota, 8, 14, 5);
		builder.append( LUsr.NO_TELP, noTelp, 8, 16, 1);
		builder.append( LUsr.PIN_BB, pinBb, 12, 16, 1);
		builder.append( LUsr.NO_HP1, noHp1, 8, 18, 1);
		builder.append( LUsr.NO_HP2, noHp2, 12, 18, 1);
		builder.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 8, 20, 5);
		
	}


	public void requestDefaultFocus(){
		code.requestFocus();
	}
	
	
	
	//untuk new
}
