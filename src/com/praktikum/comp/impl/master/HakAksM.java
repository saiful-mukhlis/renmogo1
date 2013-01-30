package com.praktikum.comp.impl.master;



import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;

import com.basic.view.detail.HakAksV;
import com.global.App;
import com.praktikum.db.HakAks;
import com.praktikum.table.HakAksT;
import com.praktikum.view.add.HakAksDN;
import com.praktikum.view.edit.HakAksDE;

public class HakAksM extends MasterDefault {
	
	public static String TITLE_ADD=App.getT("Tambah Data Hak Akses");
	public static String TITLE_EDIT=App.getT("Edit Data Hak Akses");
	public static String TITLE_VIEW=App.getT("Data Hak Akses");
	
	public static String TITLE=App.getT("Master Hak Akses");
	public static String TITLE_MENU=App.getT("Hak Akses");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/hakakses-16.png";
	public static String URL_ICON_32="/image/hakakses-32.png";
	public static String URL_ICON_48="/image/hakakses-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;

	public HakAksM() {
		super();
		lebar=0.2;
		title=TITLE_MENU;
	}

	@Override
	public void setDetailWidget() {
		detailWidget=new HakAksV();
	}

	@Override
	public void setEditWidget() {
		editWidget=new HakAksDE();
		
	}

	@Override
	public void setAddWidget() {
		addWidget=new HakAksDN();
	}

	@Override
	public void setTableWidget() {
		table =new HakAksT();
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
