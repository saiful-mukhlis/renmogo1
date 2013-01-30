package com.basic.view.edit;


import org.basic.comp.listener.WidgetInterface;

import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.dao.impl.JenisPekerjaanDao;
import com.basic.icon.IconBase;
import com.basic.view.add.JenisPekerjaanN;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaanE extends JenisPekerjaanN {
	
	protected ODocument model;
	
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = JenisPekerjaanM.TITLE_EDIT;
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
		}else
		if (modelIsTrue(model)) {
			this.model=model;
			setContentComponent(model);
		}
		
	}

	
	
	/**
	 *  harus ada di new
	 * @return
	 */

	public boolean validate(ODatabaseDocumentTx db){
		JenisPekerjaanDao d=App.getJenisPekerjaanDao();
		if (!d.vCode(db, code, model)) {
			return false;
		}
		if (!d.vNama(db, nama, model)) {
			return false;
		}
		return true;
	}
	public void actionSave() {
		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument o=model;
			JenisPekerjaanDao d=App.getJenisPekerjaanDao();
			d.setCode(o, code); 
			d.setNama(o, nama); 
			try {
				o.save();
				for (WidgetInterface w: widgets) {
					w.modelWidgetChange(o);
				}
				App.showSaveOk();
				
			} catch (Exception e) {
				App.printErr(e);
			}finally{
				db.close();
			}
			
		}
	
		
	}
	
	



	
}
