package com.praktikum.comp.impl.master;



import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;

import com.global.App;
import com.praktikum.db.Pegawai;
import com.praktikum.table.PegawaiT;
import com.praktikum.view.add.PegawaiDN;
import com.praktikum.view.detail.PegawaiV;
import com.praktikum.view.edit.PegawaiDE;

public class PegawaiM extends MasterDefault {
	
	public static String TITLE_ADD=App.getT("Tambah Data Pegawai");
	public static String TITLE_EDIT=App.getT("Edit Data Pegawai");
	public static String TITLE_VIEW=App.getT("Data Pegawai");
	
	public static String TITLE=App.getT("Master Pegawai");
	public static String TITLE_MENU=App.getT("Pegawai");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/pegawai-16.png";
	public static String URL_ICON_32="/image/pegawai-32.png";
	public static String URL_ICON_48="/image/pegawai-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;

	public PegawaiM() {
		super();
		lebar=0.2;
		title=TITLE_MENU;
	}

	@Override
	public void setDetailWidget() {
		detailWidget=new PegawaiV();
	}

	@Override
	public void setEditWidget() {
		editWidget=new PegawaiDE();
		
	}

	@Override
	public void setAddWidget() {
		addWidget=new PegawaiDN();
	}

	@Override
	public void setTableWidget() {
		table =new PegawaiT();
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
