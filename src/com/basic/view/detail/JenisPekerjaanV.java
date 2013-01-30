package com.basic.view.detail;


import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.icon.IconBase;
import com.basic.view.def.JenisPekerjaanC;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaanV extends JenisPekerjaanC  {
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = JenisPekerjaanM.TITLE_VIEW;
		icon = IconBase.VIEW;
		initComponent(db);
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else if (modelIsTrue(model)) {
			setContentComponent(model);
		}else if(model.field("@class").equals(Grp.TABLE)){
			setContentComponent(model);
		}else if(model.field("@class").equals(JenisPekerjaan.TABLE)){
			setContentComponent(model);
		}
		
	}
	

	
}
