package com.basic.view.detail;


import com.basic.comp.impl.master.UsrM;
import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.icon.IconBase;
import com.basic.view.def.UsrC;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrV extends UsrC {
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = UsrM.TITLE_VIEW;
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
	
	
	
	
	
	
	
	
	
//	public void setContentComponent(ODocument model){
//		 if (modelIsTrue(model)){
//			 code.setText(model.field(UsrDao.nikName)+"");
//				username.setText(model.field(UsrDao.username)+"");
//				password.setText("-");
//				byte tmp=model.field(UsrDao.status);
//				ODatabaseDocumentTx db=App.getDbd();
//				 ODatabaseRecordThreadLocal. INSTANCE.set(db);
//				if (tmp==1) {
//					status.setText("Aktif");
//				}else{
//					status.setText("Tidak Aktif");
//				}
//				db.close();
//		 }else if (model.field("@class").equals(App.getGrpDao().getClassName())) {
//				grp.setText(model.field(GrpDao.name)+"");
//			}
//	}
//	
//	public void initComponent(ODatabaseDocumentTx db){
//		code=new TextField();
//		username=new TextField();
//		password=new PasswordField();
//		status=new  TextField();
//		grp=new TextField();
//	}
//	
//	public void buildForm(ODatabaseDocumentTx db) {
//		
//		super.buildForm(db);
//		builder.append( "Status", status, 1, 7, 5);
//		builder.append( "Hak Akses", grp, 1, 9, 5);
//		
//	}


	
}
