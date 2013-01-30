package com.praktikum.comp.base;

import javax.swing.Icon;

import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.base.Komponent;
import org.basic.comp.base.Window;

import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.comp.impl.master.LaporanM;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.comp.impl.master.PelangganM;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.comp.impl.master.TypeMobilM;

public class KomponentPraktikum extends Komponent{
	
	public KomponentPraktikum() {
		super();
	}

	public KomponentPraktikum(WindowInterfaces window, String typeMaster,
			String title, char shortCut, Icon icon16, Icon icon32) {
		super(window, typeMaster, title, shortCut, icon16, icon32);
	}

	public Komponent createComponent(Komponent komponent){
	    String type=komponent.getTypeMaster();
	    if (type.equals(Window.PEGAWAI)) {
			komponent.setWidgetTop(new PegawaiM());
		}else if (type.equals(Window.HAK_AKSES)) {
			komponent.setWidgetTop(new HakAksM());
		}
		else if (type.equals(WindowPraktikum.PELANGGAN)) {
			komponent.setWidgetTop(new PelangganM());
		}
		else if (type.equals(WindowPraktikum.TYPE_MOBIL)) {
			komponent.setWidgetTop(new TypeMobilM());
		}
		else if (type.equals(WindowPraktikum.SEWA)) {
			komponent.setWidgetTop(new SewaM());
		}
		else if (type.equals(WindowPraktikum.LAPORAN)) {
			komponent.setWidgetTop(new LaporanM());
		}
		
		if (komponent.getWidgetTop()!=null) {
			//komponent.getWidgetTop().getChangeStateActions().add(window.getMenu());
			komponent.getWidgetTop().build();
			komponent.setComponent(komponent.getWidgetTop().getPanel());
		}
		return komponent;
	}
}
