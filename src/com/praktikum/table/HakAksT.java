package com.praktikum.table;

import javax.swing.JOptionPane;

import org.basic.comp.abst.TableDefault;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.basic.lang.LHakAks;
import com.global.App;
import com.praktikum.db.HakAks;
import com.praktikum.table.model.HakAksTM;

public class HakAksT extends TableDefault {

	@Override
	public void initTableModel() {
		tableModel = new HakAksTM();
		tableModel.setTableParent(this);

	}

	public void aksiDelete() {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				if (JOptionPane.showConfirmDialog(null,
						LHakAks.KET_KONFIRM_DELETE, LHakAks.KONFIRM_DELETE,
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					int itmp = table.convertRowIndexToModel(i);
					HakAks tmp = (HakAks) tableModel.getModels().get(itmp);
					try {
						// tmp.delete();
						tableModel.getDao().delete(tmp);
						tableModel.getModels().remove(itmp);
						tableModel.fireTableDataChanged();
						selected();
					} catch (Exception e) {
						App.printErr(e);
					}
				}
			}
		}

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
