package com.praktikum.comp.base;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.basic.comp.base.BasicTextLabelIntro;
import org.basic.comp.base.ImagePanel;
import org.basic.comp.base.Window;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.basic.lang.LApp;
import com.global.App;
import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.comp.impl.master.LaporanM;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.comp.impl.master.PelangganM;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.comp.impl.master.TypeMobilM;


public class WindowPraktikum extends Window{
	public static String PELANGGAN=PelangganM.class.getName();
	public static String TYPE_MOBIL=TypeMobilM.class.getName();
	public static String SEWA=SewaM.class.getName();
	public static String LAPORAN=LaporanM.class.getName();
	@Override
	public void buildMaster() {
		
		KomponentPraktikum pegawai=new KomponentPraktikum(this, PEGAWAI, PegawaiM.TITLE, 'P', PegawaiM.ICON_16, PegawaiM.ICON_32);
		KomponentPraktikum hakAkses=new KomponentPraktikum(this, HAK_AKSES, HakAksM.TITLE, 'H', HakAksM.ICON_16, HakAksM.ICON_32);
		KomponentPraktikum pelanggan=new KomponentPraktikum(this, PELANGGAN, PelangganM.TITLE, 'P', PelangganM.ICON_16, PelangganM.ICON_32);
		KomponentPraktikum typeMobil=new KomponentPraktikum(this, TYPE_MOBIL, TypeMobilM.TITLE, 'P', TypeMobilM.ICON_16, TypeMobilM.ICON_32);
		KomponentPraktikum sewa=new KomponentPraktikum(this, SEWA, SewaM.TITLE, 'P', SewaM.ICON_16, SewaM.ICON_32);
		KomponentPraktikum laporan=new KomponentPraktikum(this, LAPORAN, LaporanM.TITLE, 'P', LaporanM.ICON_16, LaporanM.ICON_32);
		
		komponentMaps.put(PEGAWAI, pegawai);
		komponentMaps.put(HAK_AKSES, hakAkses);
		komponentMaps.put(PELANGGAN, pelanggan);
		komponentMaps.put(TYPE_MOBIL, typeMobil);
		komponentMaps.put(SEWA, sewa);
		komponentMaps.put(LAPORAN, laporan);
		
		komponents.add(pegawai);
		komponents.add(hakAkses);
		komponents.add(pelanggan);
		komponents.add(typeMobil);
		komponents.add(sewa);
		komponents.add(laporan);
		
		
		for (Object komponent : getKomponents()) {
			cangeHakAkses.add(((KomponentPraktikum) komponent).getHakAksesListener());
		}
		
		
//		JPanel panel = new ImagePanel(
//				new FlowLayout(FlowLayout.CENTER, 50, 180));
		JComponent panel = new BasicTextLabelIntro().buildPanel();
		welcomeComponent = panel;

		
		windowAca = factoryContentAction("Welcome", App.getIcon(LApp.iconApp16),
				welcomeComponent, (int) 'W');
		
	}
	
	@Override
	public void buildActions() {
		for (Object komponent : getKomponents()) {
			((KomponentPraktikum) komponent).setView( ((KomponentPraktikum) komponent).getTypeMaster(), myDoggySetContext);
		}
		
		windowVca = new ViewContextAction(
				"Welcome", App.getIcon(LApp.iconApp16),
				myDoggySetContext, ImagePanel.class);
		
	}
	
	@Override
	public void initMenu() {
		menu=new MenuPraktikum();
		menu.setWindow(this);
		menu.build();
		cangeHakAkses.add(menu);
		frame.setJMenuBar(menu.getMenu());
	}
	
}
