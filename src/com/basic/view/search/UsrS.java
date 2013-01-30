package com.basic.view.search;


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

public class UsrS extends UsrN {
	
	
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
	

	public boolean validate(ODatabaseDocumentTx db){
		return true;
	}
	public void actionSave() {
		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			
			ODocument o=new ODocument(Usr.TABLE);
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
			
			
			try {
				for (WidgetInterface w: widgets) {
					w.modelWidgetSearch(o);
//					w.modelWidgetSearch(((ODocumentToString) grp.getSelectedItem()).getO());
//					w.modelWidgetSearch(((ODocumentToString) jenisPekerjaan.getSelectedItem()).getO());
				}
				
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
