package com.basic.view.edit;



import java.util.Date;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.DialogEditDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.basic.comp.model.ODocumentToString;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.UsrM;
import com.basic.dao.impl.GrpDao;
import com.basic.dao.impl.UsrDao;
import com.basic.db.Grp;
import com.basic.db.Usr;
import com.basic.lang.LHakAks;
import com.basic.lang.LUsr;
import com.global.App;
import com.global.DataUser;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class UsrDE extends DialogEditDefault {
	protected TextField code;
	protected TextField username;
	protected TextField nama;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField noIdentitas;
	protected TextField jenisIdentitas;
	protected TextField kotaLahir;
	protected TextField noTelp;
	protected TextField noHp1;
	protected TextField noHp2;
	protected TextField pinBb;
	protected TextFieldAmount gaji;
	protected TextField pendidikanTerakhir;
	protected PasswordField password;
	protected PasswordField ulang;
	protected DatePicker tglLahir;
	protected DatePicker tglMasuk;
	protected ComboBox jenisKelamin;
	protected ComboBox jenisPekerjaan;
	protected ComboBox status;
	protected ComboBox grp;
	
	protected ODocumentToString [] jenisPekerjaanModel;
	protected ODocumentToString [] grpModel;
	
	
	


	@Override
	public void load(ODocument model) {
		this.model=model;
		if (model==null) {
			code.setText("");
			username.setText("");
			grp.setSelectedIndex(0);
			nama.setText("");
			alamat.setText("");
			
			kota.setText("");
			noIdentitas.setText("");
			jenisIdentitas.setText("");
			kotaLahir.setText("");
			tglLahir.setDate(new Date());
			jenisKelamin.setSelectedIndex(0);
			noTelp.setText("");
			noHp1.setText("");
			noHp2.setText("");
			pinBb.setText("");
			tglMasuk.setDate(new Date());
			gaji.setText("");
			jenisPekerjaan.setSelectedIndex(0);
			pendidikanTerakhir.setText("");
			status.setSelectedIndex(0);
			requestDefaultFocus();
		}else if (model.field("@class").equals(Usr.TABLE)) {
			UsrDao d = App.getUsrDao();
			code.setText(d.getCode(model));
			nama.setText(d.getNama(model));
			alamat.setText(d.getAlamat(model));
			
			username.setText(d.getUsername(model));
			
			kota.setText(d.getKota(model));
			noIdentitas.setText(d.getNoIdentitas(model));
			jenisIdentitas.setText(d.getJenisIdentitas(model));
			kotaLahir.setText(d.getKotaLahir(model));
			tglLahir.setDate(d.getTglLahir(model));
			jenisKelamin.setSelectedIndex(App.getUsrDao().getJenisKelamin(model));
			status.setSelectedIndex(App.getUsrDao().getStatus(model));
			noTelp.setText(d.getNoTelp(model));
			noHp1.setText(d.getNoHp1(model));
			noHp2.setText(d.getNoHp2(model));
			pinBb.setText(d.getPinBb(model));
			tglMasuk.setDate(d.getTglMasuk(model));
			gaji.setText(d.gajiToString(model));
			pendidikanTerakhir.setText(d.getPendidikanTerakhir(model));
			
			ODocument tmp1=d.getGrp(model);
			if (tmp1!=null) {
				for (ODocumentToString os : grpModel) {
					ODocument o=os.getO();
					if (o!=null) {
						if (o.getIdentity().equals(tmp1.getIdentity())) {
							grp.setSelectedItem(os);
							break;
						}
					}
				}
			}
			
			
			ODocument tmp2=d.getJenisPekerjaan(model);
			if (tmp2!=null) {
				for (ODocumentToString os : jenisPekerjaanModel) {
					ODocument o=os.getO();
					if (o!=null) {
						if (o.getIdentity().equals(tmp2.getIdentity())) {
							grp.setSelectedItem(os);
							break;
						}
					}
				}
			}
			
			
		}
	}

	@Override
	public void actionReset() {
		actionReload();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		username.requestFocus();
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code=new TextField();
		username=new TextField();
		nama=new TextField();
		alamat=new TextArea();
		salamat=new ScrollPane(alamat);
		
		kota=new TextField();
		noIdentitas=new TextField();
		jenisIdentitas=new TextField();
		kotaLahir=new TextField();
		noTelp=new TextField();
		noHp1=new TextField();
		noHp2=new TextField();
		pinBb=new TextField();
		gaji=new TextFieldAmount();
		pendidikanTerakhir=new TextField();
		
		password=new PasswordField();
		ulang=new PasswordField();
		tglLahir=new DatePicker();
		tglMasuk=new DatePicker();

		jenisPekerjaanModel=App.getUsrDao().getJenisPekerjaanData(db);
		jenisKelamin=new ComboBox(App.getUsrDao().getJenisKelaminData());
		status=new ComboBox(App.getUsrDao().getStatusData());
		
		grpModel=App.getUsrDao().getGrpData(db);
		grp=new ComboBox(grpModel);
		jenisPekerjaan=new ComboBox(jenisPekerjaanModel);
		
	}
	
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, username);
		setFocusEnter(username, password);
		setFocusEnter(password, ulang);
		setFocusEnter(ulang, grp);
//		setFocusEnter(status, grp);
		setFocusEnter(grp, status);
		setFocusEnter(status, nama);

		setFocusEnter(nama, jenisIdentitas);
		setFocusEnter(jenisIdentitas, noIdentitas);
		setFocusEnter(noIdentitas, kotaLahir);
		setFocusEnter(kotaLahir, tglLahir.getEditor());
		setFocusEnter(tglLahir.getEditor(), jenisKelamin);
		setFocusEnter(jenisKelamin, alamat);
		setFocusEnter(alamat, kota);
		setFocusEnter(kota, noTelp);
		setFocusEnter(noTelp, pinBb);
		setFocusEnter(pinBb, noHp1);
		setFocusEnter(noHp1, noHp2);
		setFocusEnter(noHp2, pendidikanTerakhir);
		setFocusEnter(pendidikanTerakhir, tglMasuk.getEditor());

		setFocusEnter(tglMasuk.getEditor(), gaji);
		setFocusEnter(gaji, jenisPekerjaan);
		setFocusEnter(jenisPekerjaan, save);
		
//		setFocusEnter(save, reset);
		setFocusEnter(reset, code);
	}


	@Override
	public void init(ODatabaseDocumentTx db) {
		icon=UsrM.ICON_48;
		super.init(db);
		labelTitle.setText("Edit Pegawai");
		labelNote.setText("Isi data dengan benar");
		initComponent(db);
		buildForm(db);
	}

	public void buildForm(ODatabaseDocumentTx db) {
		initComponent(db);
		

		

	
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		//row.append("f:40dlu:g,15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LHakAks.CODE, code, 2, 2, 1);
		b.append( LUsr.USERNAME, username, 2, 4, 5);
		
		b.append( LUsr.PASSWORD, password, 2, 6, 5);
		b.append( LUsr.ULANG, ulang, 2, 8, 5);
		
		b.append( LUsr.GRP, grp, 2, 10, 3);
		b.append( LUsr.STATUS, status, 2, 12, 3);

		b.append( LUsr.TGL_MASUK, tglMasuk, 2, 16, 1);
		b.append( LUsr.GAJI, gaji, 2, 18, 5);
		b.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 2, 20, 3);
		
		
		
		
		b.append( LUsr.NAMA, nama, 10, 2, 5);
		b.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 10, 4, 1);
		b.append( LUsr.NO_IDENTITAS, noIdentitas, 14, 4, 1);
		b.append( LUsr.KOTA_LAHIR, kotaLahir, 10, 6, 1);
		b.append( LUsr.TGL_LAHIR, tglLahir , 14, 6, 1);
		b.append( LUsr.JENIS_KELAMIN, jenisKelamin, 10, 8, 3);
		b.append( LUsr.ALAMAT, salamat, 10, 10, 5, 5);
		b.append( LUsr.KOTA, kota, 10, 16, 5);
		b.append( LUsr.NO_TELP, noTelp, 10, 18, 1);
		b.append( LUsr.PIN_BB, pinBb, 14, 18, 1);
		b.append( LUsr.NO_HP1, noHp1, 10, 20, 1);
		b.append( LUsr.NO_HP2, noHp2, 14, 20, 1);
		b.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 10, 22, 5);
		
		
		panelForm=b.getPanel();

	}


	@Override
	public void save(ODatabaseDocumentTx db) {
		String jsonOld=model.toJSON();
		UsrDao d=App.getUsrDao();
		d.setCode(model, code).
		setUsername(model, username).
		setGrp(model, ((ODocumentToString)grp.getSelectedItem()).getO()).
		setNama(model, nama).
		setAlamat(model, alamat).
		setKota(model, kota).
		setNoIdentitas(model, noIdentitas).
		setJenisIdentitas(model, jenisIdentitas).
		setKotaLahir(model, kotaLahir).
		setTglLahir(model, tglLahir.getDate()).
		setJenisKelamin(model, jenisKelamin.getSelectedIndex()).
		setNoTelp(model, noTelp).
		setNoHp1(model, noHp1).
		setNoHp2(model, noHp2).
		setPinBb(model, pinBb).
		setTglMasuk(model, tglMasuk.getDate()).
		setGaji(model, gaji).
		setJenisPekerjaan(model, ((ODocumentToString)grp.getSelectedItem()).getO()).
		setPendidikanTerakhir(model, pendidikanTerakhir).
		setStatus(model, status.getSelectedIndex());
		if (password.getPassword().length!=0) {
			UtilAccount u=new UtilAccount();
			String p;
			try {
				p = u.md5(new String(password.getPassword()));
				d.setPassword(model,p );
			} catch (Exception e) {
				App.printErr(e);
			}
		}
		d.setUpdateAt(model, new Date());
		d.setUpdateBy(model, DataUser.getUsr());
		d.update(db, model, jsonOld);
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		UsrDao d=App.getUsrDao();
		if (!d.vCode(db, code, model)) {
			return false;
		}
		
		if (!d.vUsername(db, username, model)) {
			return false;
		}
		
		if (!d.vPassword(db, password, ulang, model)) {
			return false;
		}
		
		
		if (grp.getSelectedIndex()==0) {
			App.showErrorFieldEmpty(db, LUsr.GRP);
			grp.requestFocus();
			return false;
		}
		
		
		if (!d.vNama(db, nama, model)) {
			return false;
		}
		
		
		if (status.getSelectedIndex()==0) {
			App.showErrorFieldEmpty(db, LUsr.STATUS);
			status.requestFocus();
			return false;
		}
		
		return true;
	}
	
}
