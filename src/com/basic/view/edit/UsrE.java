package com.basic.view.edit;


import java.util.Date;

import org.basic.comp.listener.WidgetInterface;
import org.basic.comp.model.ODocumentToString;

import com.basic.comp.impl.master.UsrM;
import com.basic.dao.impl.UsrDao;
import com.basic.db.JenisPekerjaan;
import com.basic.db.Logdb;
import com.basic.db.Usr;
import com.basic.icon.IconBase;
import com.basic.lang.LUsr;
import com.basic.view.add.UsrN;
import com.global.App;
import com.global.DataUser;
import com.global.util.UtilAccount;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrE extends UsrN {
	
	protected ODocument model;
	
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = UsrM.TITLE_EDIT;
		icon = IconBase.EDIT;
		
		initComponent(db);
		buildLabel(db);
		buildForm(db);
		buildPanel();
		setFocusEnter();
	}
	
	

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
			
			password.setText("");
			ulang.setText("");
			tglLahir.setDate(new Date());
			tglMasuk.setDate(new Date());
			jenisKelamin.setSelectedIndex(0);
			jenisPekerjaan.setSelectedIndex(0);
			status.setSelectedIndex(0);
			grp.setSelectedIndex(0);
		}else
		if (modelIsTrue(model)) {
			this.model=model;
			setContentComponent(model);
			password.setText("");
			ulang.setText("");
			tglLahir.setDate((Date) model.field(Usr.TGL_LAHIR));
			tglMasuk.setDate((Date) model.field(Usr.TGL_MASUK));
			jenisKelamin.setSelectedIndex(App.getUsrDao().getJenisKelamin(model));
			status.setSelectedIndex(App.getUsrDao().getStatus(model));
			
		}else if (model.field("@class").equals(App.getGrpDao().getClassName())) {
			for (ODocumentToString os : grpModel) {
				ODocument o=os.getO();
				if (o!=null) {
					if (o.getIdentity().equals(model.getIdentity())) {
						grp.setSelectedItem(os);
						break;
					}
				}
			}
		}else if (model.field("@class").equals(JenisPekerjaan.TABLE)) {
			for (ODocumentToString os : jenisPekerjaanModel) {
				ODocument o=os.getO();
				if (o!=null) {
					if (o.getIdentity().equals(model.getIdentity())) {
						jenisPekerjaan.setSelectedItem(os);
						break;
					}
				}
			}
		}
		
	}

	
	
	/**
	 *  harus ada di new
	 * @return
	 */

	public boolean validate(ODatabaseDocumentTx db){
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
	public void actionSave() {
		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			
			ODocument logdb=model.field(Usr.LOGDB);
			logdb.field(Logdb.UPDATE_BY, DataUser.getUsr().field(Usr.NAMA) );
			logdb.field(Logdb.UPDATE_AT, new Date(), OType.DATE);
			
			ODocument o=model;
			UsrDao d=App.getUsrDao();
			d.setCode(o, code).
			setUsername(o, username).
			setGrp(o, ((ODocumentToString)grp.getSelectedItem()).getO()).
			setNama(o, nama).
			setAlamat(o, alamat).
			setKota(o, kota).
			setNoIdentitas(o, noIdentitas).
			setJenisIdentitas(o, jenisIdentitas).
			setKotaLahir(o, kotaLahir).
			setTglLahir(o, tglLahir.getDate()).
			setJenisKelamin(o, jenisKelamin.getSelectedIndex()).
			setNoTelp(o, noTelp).
			setNoHp1(o, noHp1).
			setNoHp2(o, noHp2).
			setPinBb(o, pinBb).
			setTglMasuk(o, tglMasuk.getDate()).
			setGaji(o, gaji).
			setJenisPekerjaan(o, ((ODocumentToString)jenisPekerjaan.getSelectedItem()).getO()).
			setPendidikanTerakhir(o, pendidikanTerakhir.getText()).
			setStatus(o, status.getSelectedIndex());
			
			if (password.getPassword().length!=0) {
				UtilAccount u=new UtilAccount();
				String p;
				try {
					p = u.md5(new String(password.getPassword()));
					d.setPassword(o,p );
				} catch (Exception e) {
					App.printErr(e);
				}
			}
			
			d.setLogdb(o, logdb);
			
			
			
			
			
			
			try {
				o.save();
				for (WidgetInterface w: widgets) {
					w.modelWidgetChange(o);
					w.modelWidgetChange(((ODocumentToString) grp.getSelectedItem()).getO());
					w.modelWidgetChange(((ODocumentToString) jenisPekerjaan.getSelectedItem()).getO());
				}
				App.showSaveOk();
				
			} catch (Exception e) {
				App.printErr(e);
			}finally{
				db.close();
			}
			
		}
	
		
	}



	@Override
	public void setContentComponent(ODocument o) {
		super.setContentComponent(o);
//		if (o!=null && modelIsTrue(o)){
//			UsrDao d=App.getUsrDao();
//			gaji.setText(d.getGaji(o, App.paymentFormat0));
//		}
	}
	
	



	
}
