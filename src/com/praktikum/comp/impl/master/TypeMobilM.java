package com.praktikum.comp.impl.master;



import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;

import com.global.App;
import com.praktikum.db.TypeMobil;
import com.praktikum.table.TypeMobilTree;
import com.praktikum.view.add.TypeMobilDN;
import com.praktikum.view.detail.TypeMobilV;
import com.praktikum.view.edit.TypeMobilDE;

public class TypeMobilM extends MasterDefault {
	
	public static String TITLE_ADD=App.getT("Tambah Data Mobil");
	public static String TITLE_EDIT=App.getT("Edit Data Mobil");
	public static String TITLE_VIEW=App.getT("Data Mobil");
	
	public static String TITLE=App.getT("Master Mobil");
	public static String TITLE_MENU=App.getT("Mobil");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/mobil-16.png";
	public static String URL_ICON_32="/image/mobil-32.png";
	public static String URL_ICON_48="/image/mobil-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;

	public TypeMobilM() {
		super();
		lebar=0.2;
		title=TITLE_MENU;
	}

	@Override
	public void setDetailWidget() {
		detailWidget=new TypeMobilV();
	}

	@Override
	public void setEditWidget() {
		editWidget=new TypeMobilDE();
		
	}

	@Override
	public void setAddWidget() {
		addWidget=new TypeMobilDN();
	}

	@Override
	public void setTableWidget() {
		table =new TypeMobilTree();
	}
	
	public boolean isAdd() {
		return ADD;
	}
	
	public boolean isDel() {
		return DEL;
	}

	public boolean isView() {
		return VIEW;
	}

	public boolean isEdit() {
		return EDIT;
	}
	
	@Override
	public Icon getIcon16() {
		return ICON_16;
	}

	@Override
	public Icon getIcon32() {
		return ICON_32;
	}


	@Override
	public String getTitleToolBar() {
		return TITLE.toUpperCase();
	}






}
