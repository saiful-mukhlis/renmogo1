package org.basic.comp.abst;

import java.util.ArrayList;
import java.util.List;


import org.basic.comp.adapter.PagingInterface;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.adapter.TableModelAdapter;
import org.basic.dao.adapter.Dao;

public class TableModelDefault<T> extends TableModelAdapter<T> {

	protected String[] namaKolom;
	protected List<T> model=new ArrayList<T>();

	protected PagingInterface paging;
	protected TableInterfaces table;
	protected Dao dao;

	public TableModelDefault() {
		super();
		initDao();
		initNamaKolom();
	}

	public int getColumnCount() {
		return namaKolom.length;
	}
	


	public int getRowCount() {
		return model.size();
	}

	public String getColumnName(int col) {
		return namaKolom[col];
	}

	/* 
	 *  tanpa q tes sya + loadJumlahData
	 */
	public void reload() {
		loadJumlahData();
		loadDataModel();
		fireTableDataChanged();
		if (getTable()!=null) {
			getTable().selected();
		}
	}

	public void load() {
		loadJumlahData();
		loadDataModel();
		if (paging != null) {
			paging.loadFirst();
		}
	}

	public void setPaging(PagingInterface paging) {
		this.paging = paging;
	}

	public List getModels() {
		return model;
	}

	public TableInterfaces getTable() {
		return table;
	}

	public void setTableParent(TableInterfaces table) {
		this.table = table;
	}

	public Dao getDao() {
		return dao;
	}
	
	@Override
	public void loadDataModel() {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAll(tmp
					,
					paging.getJumlahPerHalaman());
		} else {
			model = (List<T>) getDao().getAll();
		}
	}

	@Override
	public void loadJumlahData() {
		if (paging != null) {
			paging.setJumlahData((Long) dao.count());
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	
	
	
	
	
	
	public void loadDataModelSearch(String col, String value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAllByColumn(col, value, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			model = (List<T>) getDao().getAllByColumn(col, value);
		}
	}

	public void loadJumlahDataSearch( String col, String value) {
		if (paging != null) {
			paging.setJumlahData((Long) dao.countByColumn(col, value));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	public void loadDataModelSearch(String col, int value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAllByColumn(col, value, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			model = (List<T>) getDao().getAllByColumn(col, value);
		}
	}

	public void loadJumlahDataSearch( String col, int value) {
		if (paging != null) {
			paging.setJumlahData((Long) dao.countByColumn(col, value));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	public void loadDataModelSearchLike(String col, String value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAllByColumn( col, value+"%", tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			model = (List<T>) getDao().getAllByColumn(col, value+"%");
		}
	}

	public void loadJumlahDataSearchLike( String col, String value) {
		if (paging != null) {
			paging.setJumlahData((Long)dao.countByColumn(col, value+"%"));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	
	
	
	

	@Override
	public void addModel(T model) {
			this.model.add(model);
			fireTableDataChanged();
	}

	

	@Override
	public void clear() {
		this.model.clear();
		fireTableDataChanged();
	}

	@Override
	public void editModel(T model, int index) {
			this.model.set(index, model);
			fireTableDataChanged();
	}

	public String[] getNamaKolom() {
		return namaKolom;
	}

	public void setNamaKolom(String[] namaKolom) {
		this.namaKolom = namaKolom;
	}
	
	public int getNo(int rowIndex){
		int no = rowIndex + 1;
		if (paging != null) {
				no += ((paging.getCurentHalaman()-1) * paging.getJumlahPerHalaman());
		}
		return no;
	}
	

	
	
//	
//	@Override
//	public void setDefaultLebar(JTable table) {
////		if (table!=null) {
////			TableColumnModel t=table.getColumnModel();
////			t.getColumn(NO).setPreferredWidth(27);
////			t.getColumn(CODE).setPreferredWidth(27);
////			t.getColumn(NAME).setPreferredWidth(100);
////			t.getColumn(NAME_PEMILIK).setPreferredWidth(100);
//////			t.getColumn(TELP).setPreferredWidth(27);
//////			t.getColumn(ADDRESS).setPreferredWidth(27);
////			}
//		
//	}
	
}
