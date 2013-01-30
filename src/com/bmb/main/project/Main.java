package com.bmb.main.project;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.basic.dao.adapter.Dao;

import com.basic.icon.IconBase;
import com.basic.view.screen.SplashScreen;
import com.bmb.dao.entity.DaoUsr;
import com.bmb.entity.model.Usr;
import com.bmb.util.Db;
import com.global.App;
import com.jgoodies.looks.FontPolicies;
import com.jgoodies.looks.FontPolicy;
import com.jgoodies.looks.FontSet;
import com.jgoodies.looks.FontSets;
import com.jgoodies.looks.Fonts;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.praktikum.comp.base.WindowPraktikum;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;

public class Main {
	public static void main(String[] args) {
		
		m1();
	}
	public static void t3(){
		Calendar calendar1 = Calendar.getInstance();
		  Calendar calendar2 = Calendar.getInstance();
		  calendar1.set(2007, 01, 01);
		  calendar2.set(2007, 01, 02);
		  long milliseconds1 = calendar1.getTimeInMillis();
		  long milliseconds2 = calendar2.getTimeInMillis();
		  long diff = milliseconds2 - milliseconds1;
		  long diffHours = diff / (60 * 60 * 1000); long diffSeconds = diff / 1000;
		  System.out.println(diffHours);
	}
	public static void t2(){
		List<TypeMobil> ts=App.getTypeMobilDao().getAll();
		System.out.println("code");
		for (TypeMobil typeMobil : ts) {
			System.out.println(typeMobil.getCode());
			
		}
	}
	
	public static void m1(){
		try {
			UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");

			UIManager.put("Tree.leafIcon", IconBase.TREEP);
			UIManager.put("Tree.openIcon", IconBase.TREEO);
			UIManager.put("Tree.closedIcon", IconBase.TREEA);
			
			
			UIManager.put("TaskPaneContainer.useGradient", Boolean.FALSE);
			UIManager.put("TaskPaneContainer.background", Color.WHITE);

			UIManager.put("TaskPane.titleForeground", Color.WHITE);
			UIManager.put("TaskPane.titleOver", Color.LIGHT_GRAY.darker());
			UIManager.put("TaskPane.background", Color.WHITE);

			UIManager.put("TaskPane.titleBackgroundGradientStart", Color.BLACK);
			UIManager.put("TaskPane.titleBackgroundGradientEnd", Color.BLACK);
			
			UIManager.put("SplitPaneDivider.border", App.borderBlackKananKiri);
			UIManager.put("SplitPaneDivider.draggingColor", App.aqua);
			
			UIManager.put("SplitPane.background", Color.WHITE);
			
			
			
			UIDefaults ui = UIManager.getLookAndFeelDefaults();
			
			
			ui.put("PopupMenuSeparator.background", Color.RED);
			
			
			ui.put("Menu.background", Color.WHITE);
			
			ui.put("OptionPane.background", Color.WHITE);
			ui.put("MenuBar.opaque", true);
			
			ui.put("Panel.background", Color.WHITE);
			ui.put("RootPane.background", Color.WHITE);
			ui.put("DesktopPane.background", Color.WHITE);
			
			ui.put("Menu.opaque", true);
			ui.put("Menu.foreground", Color.BLACK); 

			
			ui.put("TabbedPane.background", Color.WHITE);
			
			UIManager.put("jgoodies.popupDropShadowEnabled", Boolean.TRUE);
			Options.setSelectOnFocusGainEnabled(true);
			
			System.setProperty("Windows.controlFont", "Segoe UI-plain-15");
			
			Font controlFont = Fonts.WINDOWS_VISTA_96DPI_NORMAL;
			FontSet fontSet = FontSets.createDefaultFontSet(controlFont);
			FontPolicy fontPolicy = FontPolicies.createFixedPolicy(fontSet);
			WindowsLookAndFeel.setFontPolicy(fontPolicy);
			WindowsLookAndFeel.setFontPolicy(FontPolicies.getLooks1xWindowsPolicy());

		} catch (Exception e) {
			App.printErr(e);
		}
		
		
		
		try {

			SplashScreen splash = new SplashScreen();
			splash.setVisible(true);
			splash.getBar().setValue(30);
			WindowPraktikum w = new WindowPraktikum();
			splash.getBar().setValue(40);
			w.setProgressBar(splash.getBar());
			splash.getBar().setValue(70);
			w.build();



			splash.setVisible(false);
			splash.dispose();

		} catch (Exception e) {
			App.showErrSementara(e.getMessage());
			App.printErr(e);
			System.exit(0);
		}
	}
	
	public static void t1(){

		System.out.println("test");
		DaoUsr d = (DaoUsr) Db.USR.get();

		// Usr u=new Usr();
		// u.setUsername("admin");
		// d.save(u);

		List<Usr> us = d.getAll();
		for (Usr usr : us) {
			System.out.println(usr.getUsername());
		}

		System.out.println("test");
		Dao dx;
		dx = (DaoUsr) Db.USR.get();
		List<Usr> usx = dx.getAll();
		for (Usr usr : usx) {
			System.out.println(usr.getUsername());
		}

		System.out.println("test");
		DaoUsr ds = (DaoUsr) Db.USR.get();
		List<Usr> uss = ds.getAll();
		for (Usr usr : uss) {
			System.out.println(usr.getUsername());
		}
	
	}
}
