package com.praktikum.comp.impl.master;



import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.basic.comp.adapter.MasterAdapter;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.SplitPane;

import com.global.App;
import com.praktikum.table.KembaliTree;
import com.praktikum.table.SewaTree;
import com.praktikum.view.add.KembaliDN;
import com.praktikum.view.add.SewaDN;

public class LaporanM extends MasterAdapter {
	
	public static String TITLE_ADD=App.getT("Tambah Data Sewa");
	public static String TITLE_EDIT=App.getT("Edit Data Sewa");
	public static String TITLE_VIEW=App.getT("Data Sewa");
	
	public static String TITLE=App.getT("Laporan");
	public static String TITLE_MENU=App.getT("Transaksi");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/laporan-16.png";
	public static String URL_ICON_32="/image/laporan-32.png";
	public static String URL_ICON_48="/image/laporan-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;
	
	public static String URL_ICON2_16="/image/format-16.png";
	public static String URL_ICON2_32="/image/format-32.png";
	public static String URL_ICON2_48="/image/format-48.png";
	public static Icon ICON2_16=App.getIcon(URL_ICON2_16);
	public static Icon ICON2_32=App.getIcon(URL_ICON2_32);
	public static Icon ICON2_48=App.getIcon(URL_ICON2_48);
	
	protected JPanel panelSewa;
	protected JPanel panelKembali;
	protected SewaTree sewa;
	protected KembaliTree kembali;
	

	protected double lebar;
	
	public LaporanM() {
		super();
		lebar=0.5;
	}
	@Override
	public void build() {
		sewa=new SewaTree();
		sewa.build();
		panelSewa=sewa.getPanel();
		kembali=new KembaliTree();
		kembali.build();
		panelKembali=kembali.getPanel();
		createLayout();
	}
	public void createLayout(){
//		SplitPane s=new SplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(panelSewa), new JScrollPane(panelKembali));
//		s.setOneTouchExpandable(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder( null);
        tabbedPane.setBackground(Color. WHITE);
        tabbedPane.addTab( "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Sewa</body></html>", ICON_16, panelSewa);
        tabbedPane.addTab( "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Kembali</body></html>", ICON2_16, panelKembali);

        
		panel=new JPanel(new BorderLayout());
		panel.add(tabbedPane, BorderLayout.CENTER);
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
