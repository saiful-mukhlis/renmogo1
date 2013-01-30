package com.praktikum.table.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPopupMenu;

import org.basic.comp.abst.TableModelDefault;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.global.App;
import com.praktikum.db.Kembali;
import com.praktikum.db.Kembalid;
import com.praktikum.db.Sewad;
import com.praktikum.lang.LKembali;
import com.praktikum.view.add.KembaliDN;

public class KembaliTM extends TableModelDefault<Sewad> {

	protected final int NO = 0;
	protected final int TYPE_MOBIL = 1;
	protected final int WAKTU = 2;
	protected final int JUMLAH_JAM = 3;
	protected final int STATUS = 4;
	protected final int LAMA_DENDA = 5;
	protected final int JML_DENDA = 6;
	protected final int KEMBALI = 7;

	protected String[] dataStatus = App.getSewadDao().getStatusData();
	protected List<Boolean> dataKembalis = new ArrayList<Boolean>();
	protected List<BigDecimal> lamaDenda = new ArrayList<BigDecimal>();
	protected List<BigDecimal> jmlDenda = new ArrayList<BigDecimal>();
	protected List<Kembalid> dataYgAkanDiKembalikan = new ArrayList<Kembalid>();
	protected KembaliDN kembaliDN;
	protected BigDecimal totalDenda = new BigDecimal(0);

	public KembaliTM(KembaliDN kembaliDN) {
		super();
		this.kembaliDN = kembaliDN;
	}

	public void initNamaKolom() {
		namaKolom = new String[8];
		namaKolom[NO] = LKembali.NO;
		namaKolom[TYPE_MOBIL] = LKembali.TYPE_MOBIL;
		namaKolom[JUMLAH_JAM] = LKembali.LAMA_SEWA;
		namaKolom[WAKTU] = LKembali.WAKTU_AWAL;
		namaKolom[STATUS] = LKembali.STATUS;
		namaKolom[LAMA_DENDA] = LKembali.LAMA_DENDA;
		namaKolom[JML_DENDA] = LKembali.DENDA;
		namaKolom[KEMBALI] = LKembali.KEMBALI;
	}

	public void load() {
		// loadJumlahData();
		// loadDataModel();
		// super.load();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Sewad m = model.get(rowIndex);
		if (columnIndex == NO) {
			return getNo(rowIndex);
		}
		if (columnIndex == TYPE_MOBIL) {
			return m.getMobil().getTypeMobil().getNama()+" : "+m.getMobil().getCode();
		}
		if (columnIndex == JUMLAH_JAM) {
			return m.getJumlahWaktu();
		}
		if (columnIndex == WAKTU) {
			return App.dateFormat.format(m.getWaktuStart()) + " "
					+ App.timeFormat.format(m.getWaktuStart())+" - "+App.dateFormat.format(m.getWaktuEnd()) + " "
					+ App.timeFormat.format(m.getWaktuEnd());
		}
		if (columnIndex == STATUS) {
			return dataStatus[m.getStatus()];
		}
		if (columnIndex == LAMA_DENDA) {
			return lamaDenda.get(rowIndex);
		}
		if (columnIndex == JML_DENDA) {
			return jmlDenda.get(rowIndex);
		}
		if (columnIndex == KEMBALI) {
			return dataKembalis.get(rowIndex);
		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		if (arg2 == KEMBALI) {
			Boolean btmp=(Boolean) arg0;
			Sewad m = model.get(arg1);
			
			if (btmp) {
				System.out.println("ierutiueroit");
				boolean tidakAda=true;
				for (Kembalid kembali : dataYgAkanDiKembalikan) {
					if (kembali.getSewad().getSewadId()==m.getSewadId()) {
						tidakAda=false;
					}
				}
					if (tidakAda) {
						Date tmp = kembaliDN.getTglKembali().getDate();
						Date tmp2 = (Date) kembaliDN.getWaktu().getValue();
						if (tmp != null && tmp2 != null) {

							Calendar cal = Calendar.getInstance();
							cal.setTime(tmp);
							Calendar cal2 = Calendar.getInstance();
							cal2.setTime(tmp2);
							
//							cal.set(Calendar.HOUR, cal2.get(Calendar.HOUR));
//							cal.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
//							cal.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
							
							cal2.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
							cal2.set(Calendar.MONTH, cal.get(Calendar.MONTH));
							cal2.set(Calendar.YEAR, cal.get(Calendar.YEAR));

							Date tmp3 = cal2.getTime();

							dataKembalis.set(arg1, (Boolean) arg0);
							
							Date tmpd = m.getWaktuEnd();
							Calendar cald = Calendar.getInstance();
							cald.setTime(tmpd);
							long milliseconds1 = cald.getTimeInMillis();
							long milliseconds2 = cal2.getTimeInMillis();
							long diff = milliseconds2 - milliseconds1;
							long diffHours = diff / (60 * 60 * 1000);
							
							
							Kembalid k = null;
							if (milliseconds2 > milliseconds1) {
								// terlambat
								BigDecimal denda = m.getMobil().getTypeMobil().getDenda()
										.multiply(new BigDecimal(diffHours));

								k = new Kembalid(denda, new BigDecimal(diffHours), m);
								totalDenda = totalDenda.add(denda);
								lamaDenda.set(arg1, new BigDecimal(diffHours));
								jmlDenda.set(arg1, denda);
								kembaliDN.getDenda().setText(
										App.paymentFormat.format(totalDenda.doubleValue()));
							} else {
								// tdk terlambat
								k = new Kembalid(new BigDecimal(0), new BigDecimal(0), m);
							}
							dataYgAkanDiKembalikan.add(k);
							fireTableCellUpdated(arg1, arg2);
							kembaliDN.getTglKembali().setEditable(false);
							kembaliDN.getWaktu().setEnabled(false);
						}
					}
				
			}else{
				for (Kembalid k : dataYgAkanDiKembalikan) {
					if (k.getSewad().getSewadId()==m.getSewadId()) {
						totalDenda=totalDenda.add(k.getDenda().negate());
						kembaliDN.getDenda().setText(
								App.paymentFormat.format(totalDenda.doubleValue()));
						dataKembalis.set(arg1, (Boolean) arg0);
						dataYgAkanDiKembalikan.remove(k);
						break;
					}
				}
				fireTableCellUpdated(arg1, arg2);
			}
			
			
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		
		if (arg1 == KEMBALI) {
			
			Sewad m = model.get(arg0);
			if (m.getStatus() == 0) {
				System.out.println(arg1);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void initDao() {
		dao = App.getKembaliDao();
	}

	public void actionSearchOneField(String col, String value) {
		// loadJumlahDataSearchLike(col, value);
		// loadDataModelSearchLike(col, value);
		// fireTableDataChanged();
		// if (getTable() != null) {
		// getTable().selected();
		// }
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = "nama";
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		// if (fieldSearch == null) {
		// fieldSearch = new TextFieldSearch();
		// fieldSearch.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// String tmp = fieldSearch.getText();
		// if (!tmp.trim().equalsIgnoreCase("")) {
		// actionSearchOneField(colSearch, tmp);
		// } else {
		// reload();
		// }
		// }
		// });
		// }
		return fieldSearch;
	}

	@Override
	public void setModels(List model) {
		this.model = model;
		dataKembalis = new ArrayList<Boolean>();
		lamaDenda = new ArrayList<BigDecimal>();
		jmlDenda = new ArrayList<BigDecimal>();
		dataYgAkanDiKembalikan = new ArrayList<Kembalid>();
		for (Sewad s : this.model) {
			if (s.getStatus() == 0) {
				dataKembalis.add(false);
			} else {
				dataKembalis.add(true);
			}
			lamaDenda.add(new BigDecimal(0));
			jmlDenda.add(new BigDecimal(0));
		}
		fireTableDataChanged();

	}

	public Class getColumnClass(int column) {
		if (column == KEMBALI) {
			return Boolean.class;
		} else {
			return super.getColumnClass(column);
		}
	}

	public BigDecimal getTotalDenda() {
		return totalDenda;
	}

	public void setTotalDenda(BigDecimal totalDenda) {
		this.totalDenda = totalDenda;
	}

	public List<Kembalid> getDataYgAkanDiKembalikan() {
		return dataYgAkanDiKembalikan;
	}

	public void setDataYgAkanDiKembalikan(List<Kembalid> dataYgAkanDiKembalikan) {
		this.dataYgAkanDiKembalikan = dataYgAkanDiKembalikan;
	}

}
