package com.praktikum.table.model;

import java.util.List;

import org.basic.comp.model.DefaultTreeNodeModel;

import com.global.App;
import com.praktikum.dao.TypeMobilDao;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;

public class TypeMobilTreeTableNodeModel extends DefaultTreeNodeModel {

	protected final int TYPE_MOBIL = 0;
	protected final int CODE = 1;
	protected final int KET = 2;
	protected final int JUMLAH = 3;
	protected final int JUMLAH_TERSEDIA = 4;
	protected final int DENDA = 5;
	protected final int STATUS = 6;

	public TypeMobilTreeTableNodeModel(Object userObject) {
		super(userObject);
		if (userObject instanceof TypeMobil) {
			List<Mobil> mobils=App.getMobilDao().getAllByTypeMobil(((TypeMobil) userObject).getTypeMobilId());
			for (Mobil mobil : mobils) {
				mobil.setTypeMobil((TypeMobil) userObject);
				add(new TypeMobilTreeTableNodeModel(mobil));
			}
		}
		
		columnCount = 7;
	}

	@Override
	public Object getValueAt(int column) {
		if (column == 0) {
			return this;
		} 
			TypeMobilDao d = App.getTypeMobilDao();
			if (getUserObject() instanceof TypeMobil) {
				TypeMobil t=(TypeMobil) getUserObject();
				if (column == CODE) {
					return t.getCode();
				} else if (column == KET) {
					return t.getKet();
				} else if (column == JUMLAH) {
					return t.getJumlah();
				} else if (column == JUMLAH_TERSEDIA) {
					return t.getJumlahTersedia();
				} else if (column == DENDA) {
					return t.getDenda();
				} 
			}
		
		return "";
	}

}
