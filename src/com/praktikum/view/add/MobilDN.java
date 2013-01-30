package com.praktikum.view.add;


import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.TextField;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.TypeMobilM;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;
import com.praktikum.lang.LTypeMobil;

public class MobilDN extends DialogDefault {
	protected TextField jumlah;

	@Override
	public void actionReset() {
		jumlah.setText("");
		requestDefaultFocus();
	}

	@Override
	public void requestDefaultFocus() {
		jumlah.requestFocus();
	}

	public void initComponent() {
		jumlah = new TextField();
	}

	@Override
	public void setFocusEnter() {
		setFocusEnter(jumlah, save);
		setFocusEnter(save, jumlah);
		
	}

	@Override
	public void init() {
		icon = TypeMobilM.ICON_48;
		super.init();
		labelTitle.setText("Tambah Mobil");
		labelNote.setText("Isi data dengan benar");
		initComponent();
		buildForm();
	}

	public void buildForm() {
		initComponent();

		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,15dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LTypeMobil.JUMLAH,jumlah , 2, 2, 1);


		panelForm = b.getPanel();

	}

	@Override
	public void save() {
		if (typeMobil!=null) {
			int tmp=0;
			try {
				tmp=Integer.parseInt(jumlah.getText());
			} catch (Exception e) {
			}
			if (tmp>0) {
				for (int i = 0; i < tmp; i++) {
						Mobil m = new Mobil("Auto", "-", 1, typeMobil);
						App.getMobilDao().saveAutoCode(m);
				}
			}
		}
	}

	@Override
	public boolean validate() {
		int tmp=0;
		try {
			tmp=Integer.parseInt(jumlah.getText());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	protected TypeMobil typeMobil;
	@Override
	public void load(Object model) {
		if (model instanceof TypeMobil) {
			typeMobil=(TypeMobil) model;
		}
	}
	
	

}
