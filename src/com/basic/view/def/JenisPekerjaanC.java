package com.basic.view.def;



import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.TextField;

import com.basic.dao.impl.JenisPekerjaanDao;
import com.basic.lang.LJenisPekerjaan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaanC extends DetailAbstract{
	
	protected TextField code;
	protected TextField nama;
	
	
	public void initComponent(ODatabaseDocumentTx db){
		code=new TextField();
		nama=new TextField();
	}
	
	public void resetContentComponent(){
		code.setText("");
		nama.setText("");
	}
	
	public void setColorView(){
		
		code.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		
		code.setEditable(isEdit);
		nama.setEditable(isEdit);
		
	}
	
	public void setContentComponent(ODocument o){
		 if (o!=null && modelIsTrue(o)){
			 JenisPekerjaanDao d=App.getJenisPekerjaanDao();
			 code.setText(d.getCode(o));
			 nama.setText(d.getNama(o));
				
		 }
	}
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.002;
		dao=App.getJenisPekerjaanDao();
		initComponent(db);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		Double tmp = App.getW()*lebar;
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p, 10dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append( LJenisPekerjaan.CODE, code, 1, 1, 5);
		builder.append( LJenisPekerjaan.NAMA, nama, 1, 3, 5);
		
	}


	public void requestDefaultFocus(){
		code.requestFocus();
	}
	
	

}
