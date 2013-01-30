package org.basic.comp.adapter;

import javax.swing.table.TableModel;

public interface ParentPagingInterface extends TableModel {
	public void setPaging(PagingInterface paging);

	public void loadJumlahData();

	public void reload();
}
