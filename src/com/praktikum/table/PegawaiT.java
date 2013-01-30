package com.praktikum.table;
import org.basic.comp.abst.TableDefault;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.praktikum.db.Pegawai;
import com.praktikum.table.model.PegawaiTM;

public class PegawaiT extends TableDefault {

	@Override
	public void initTableModel() {
		tableModel = new PegawaiTM();
		tableModel.setTableParent(this);

	}


	public void setSimple() {
		if (getTable() instanceof JXTable) {
			JXTable t = (JXTable) getTable();
			String[] x = tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx = t.getColumnExt(string);
				if (tcx != null) {
					tcx.setVisible(false);
				}
			}

			TableColumnExt tcx = t.getColumnExt(x[1]);
			if (tcx != null) {
				tcx.setVisible(true);
			}

			tcx = t.getColumnExt(x[2]);
			if (tcx != null) {
				tcx.setVisible(true);
			}

		}
	}

	public void setShowAll() {
		if (getTable() instanceof JXTable) {
			JXTable t = (JXTable) getTable();
			String[] x = tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx = t.getColumnExt(string);
				if (tcx != null) {
					tcx.setVisible(true);
				}
			}

		}
	}

}
