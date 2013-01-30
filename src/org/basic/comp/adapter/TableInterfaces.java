package org.basic.comp.adapter;
import org.basic.comp.listener.MasterInterface;
import org.jdesktop.swingx.JXTable;
public interface TableInterfaces extends ListInterfaces {
	public JXTable getTable();

	public TableModelInterface getTableModel();

	public void setMaster(MasterInterface master);

	public void setTypeEfectWidget(int typeEfectWidget);

	public void initTableModel();

	public void setShowAll();

	public void setSimple();

	public WindowInterfaces getWindow();

	public void setWindow(WindowInterfaces window);

	public String[] getNamaKolom();

	public void setBind();

	public Object getBind();
}
