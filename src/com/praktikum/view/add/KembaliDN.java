package com.praktikum.view.add;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.basic.lang.LApp;
import com.global.App;
import com.global.DataUser;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.dao.PegawaiDao;
import com.praktikum.db.Kembali;
import com.praktikum.db.Kembalid;
import com.praktikum.db.Pegawai;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;
import com.praktikum.lang.LKembali;
import com.praktikum.lang.LPegawai;
import com.praktikum.lang.LSewa;
import com.praktikum.table.model.KembaliTM;
import com.praktikum.table.model.SewadTM;
import com.praktikum.table.model.SewaTMS;

public class KembaliDN extends DialogDefault {
	protected TextField code;
	protected TextField sewa;
	protected TextField pelanggan;
	protected DatePicker tgl;
	protected DatePicker tglKembali;
	protected JSpinner waktu;
	protected TextFieldAmount denda;
	protected TextFieldAmount bayar;
	protected TextFieldAmount kembali;
	
	protected JButton proses;
	
	protected SpinnerDateModel sdm1;
	
	protected JXTable table;
	protected KembaliTM tableModel;
	
	protected JPanel panelTable;
	protected JPanel panelTotal;

	@Override
	public void actionReset() {
		Date d=new Date();
		code.setText("Auto");
		tgl.setDate(d);
		pelanggan.setText("");
		sewa.setText("");
		denda.setText("0");
		bayar.setText("0");
		kembali.setText("0");
		
		sdm1.setValue(d);
		
		tglKembali.setEditable(true);
		waktu.setEnabled(true);
		tableModel.setModels(new ArrayList<Sewad>());
		requestDefaultFocus();
	}
	public void actionCancel() {
		tableModel.setModels(new ArrayList<Sewad>());
		modelSewa=null;
		actionReset();
		denda.setText("0");
		bayar.setText("0");
		kembali.setText("0");
		pelanggan.setText("");
		//proses.setEnabled(false);
		sewa.requestFocus();
	}

	@Override
	public void requestDefaultFocus() {
		sewa.requestFocus();
	}

	public void initComponent() {
		Date d=new Date();
		code=new TextField();
		pelanggan=new TextField();
		pelanggan.setEditable(false);
		sewa=new TextField();
		tgl=new DatePicker(d);
		tgl.setEditable(false);
		tgl.getEditor().setEditable(false);
		tglKembali=new DatePicker(d);
		sdm1=new SpinnerDateModel(d, null, null, Calendar.HOUR_OF_DAY);
		waktu=new JSpinner(sdm1);
		JSpinner.DateEditor de = new JSpinner.DateEditor(waktu, "HH:mm");
		  waktu.setEditor(de);
		denda=new TextFieldAmount();
		bayar=new TextFieldAmount();
		kembali=new TextFieldAmount();
		
		proses=new JButton(LApp.PROSES);
		//proses.setEnabled(false);
		proses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				proses();
				actionCancel();
			}
		});
		tableModel = new KembaliTM(this);
		table=new JXTable(tableModel);
		
		bayar.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				BigDecimal tmp1=denda.getValue(denda);
				BigDecimal tmp2=bayar.getValue(bayar);
				BigDecimal tmp3=tmp2.add(tmp1.negate());
				kembali.setText(App.paymentFormat.format(tmp3.doubleValue()));
				
				int tmp4=tmp1.compareTo(tmp2);
				if (tmp4<-1) {
					proses.setEnabled(false);
				}else{
					proses.setEnabled(true);
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	protected void proses() {
		Date tmp=(Date) tglKembali.getDate();
		Date tmp2=(Date) waktu.getValue();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(tmp);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(tmp2);
		
		cal2.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
		cal2.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		cal2.set(Calendar.YEAR, cal.get(Calendar.YEAR));

		Date tmp3 = cal2.getTime();
		
		Kembali k=new Kembali("Auto", tableModel.getTotalDenda(), tmp, tmp3, DataUser.getUsr(), modelSewa);
		List<Kembalid> ss=tableModel.getDataYgAkanDiKembalikan();
		App.getKembaliDao().saveTransaksi(k, ss);
		
	}
	@Override
	public void setFocusEnter() {

		setFocusEnter(code, sewa);
		//setFocusEnter(sewa, tglKembali);
		setFocusEnter(tglKembali.getEditor(), waktu);
		setFocusEnter(waktu.getEditor(), table);
		setFocusEnter(waktu, table);
		
		
		setFocusEnter(denda, bayar);
		setFocusEnter(bayar, kembali);
		setFocusEnter(kembali, proses);
	}
	public void buildButton() {

//		save.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				actionSave();
//			}
//		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
			}
		});
//		cancel.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				actionCancel();
//			}
//		});

		setLayoutButton();
	}
	public void setLayoutButton() {
		StringBuilder c = new StringBuilder();
		c.append("30px,");
		c.append("d:g,10px,");
		c.append("f:80px,10px,");// 4
		c.append("f:80px,10px,");// 6
		c.append("f:80px,30px,");// 7

		StringBuilder r = new StringBuilder();
		r.append("5dlu,");
		r.append("p,");
		r.append("5dlu,");

		FormLayout l = new FormLayout(c.toString(), r.toString());
		FormBuilder b = new FormBuilder(l);

//		b.append(save, 4, 2);
//		b.append(save, 6, 2);
		b.append(reset, 8, 2);
		panelButton = new JPanel(new BorderLayout());
		panelButton.add(b.getPanel(), BorderLayout.CENTER);
//		panelButton.add(new JSeparator(), BorderLayout.NORTH);
	}
	@Override
	public void init() {
		icon = SewaM.ICON2_48;
		super.init();
		labelTitle.setText("Pengembalian");
		labelNote.setText("Isi data dengan benar");
		initComponent();
		buildForm();
		initPopupSewa();
	}

	public void buildForm() {
		initComponent();

		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
				b.append(LKembali.CODE, code, 2, 2, 1);
				b.append(LKembali.TGL_TRX,tgl , 6, 2, 1);
				b.append(LKembali.SEWA, sewa, 2, 4, 1);
				b.append(LKembali.NAMA_PELANGGAN, pelanggan, 6, 4, 1);
				b.append(LKembali.TGL_KEMBALI,tglKembali , 2, 6, 1);
				b.append(LKembali.WAKTU_KEMBALI,waktu , 6, 6, 1);


				panelForm=new JPanel(new BorderLayout());
				//panelForm.add(panelTitle, BorderLayout.NORTH);
				panelForm.add(b.getPanel(), BorderLayout.CENTER);
				panelForm.add(panelButton, BorderLayout.SOUTH);

	}

	public void buildTable(){
		panelTable=new JPanel(new BorderLayout());
		panelTable.add(new ScrollPane(table), BorderLayout.CENTER);
	}
	
	public void buildDenda(){
		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("f:200px:g,");
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("5dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");


		FormLayout l = new FormLayout(col.toString(), row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);
		//append(String i8n, Component c, int x, int y, int w)
		b.append(LKembali.DENDA, denda, 4, 2, 1);
		b.append(LKembali.BAYAR, bayar, 4, 4, 1);
		b.append(LKembali.KEMBALI, kembali, 4, 6, 1);
		
		StringBuilder c = new StringBuilder();
		c.append("30px,");
		c.append("d:g,10px,");
		c.append("f:80px,10px,");// 4
		c.append("f:80px,10px,");// 6
		c.append("f:80px,10px,");// 7

		StringBuilder r = new StringBuilder();
		r.append("5dlu,");
		r.append("p,");
		r.append("5dlu,");

		FormLayout l2 = new FormLayout(c.toString(), r.toString());
		FormBuilder b2 = new FormBuilder(l2);

//		b2.append(save, 4, 2);
		b2.append(proses, 6, 2);
		b2.append(cancel, 8, 2);
		JPanel panelButtonProses = new JPanel(new BorderLayout());
		panelButtonProses.add(b2.getPanel(), BorderLayout.CENTER);
//		panelButtonProses.add(new JSeparator(), BorderLayout.NORTH);
		
		panelTotal=new JPanel(new BorderLayout());
		panelTotal.add(b.getPanel(), BorderLayout.CENTER);
		panelTotal.add(panelButtonProses, BorderLayout.SOUTH);
		
	}
	
	public void buildPanel() {
		buildTable();
		buildDenda();
		panel = new JPanel(new BorderLayout());
		pane = new ScrollPane(panelForm);
		pane.setBorder(null);
		panel.add(pane, BorderLayout.NORTH);
		panel.add(panelTable, BorderLayout.CENTER);
		panel.add(panelTotal, BorderLayout.SOUTH);
		actionReset();
	}
	
	@Override
	public void save() {
//		Pegawai model = new Pegawai();
//		model.setCode(tgl.getText().trim());
//		model.setNama(sewa.getText().trim());
//		model.setUsername(waktu.getText().trim());
//		UtilAccount u=new UtilAccount();
//		String p;
//		try {
//			p = u.md5(new String(jumlahLebih.getPassword()));
//			model.setPassword(p);
//		} catch (Exception e) {
//			App.printErr(e);
//		}
//		model.setJenisIdentitas(bayar.getText().trim());
//		model.setNoIdentitas(kembali.getText().trim());
//		model.setAlamat(proses.getText().trim());
//		model.setKota(kota.getText().trim());
//		model.setHp(hp.getText().trim());
//		model.setStatus(status.getSelectedIndex());
//		PegawaiDao d=App.getPegawaiDao();
//		d.saveAutoCode(model);
//		this.model=model;
	}

	@Override
	public boolean validate() {
//		PegawaiDao d=App.getPegawaiDao();
//		if (tgl.getText().trim().equalsIgnoreCase("")) {
//			App.showErrorFieldEmpty(LPegawai.CODE);
//			return false;
//		}else{
//			long tmp=d.countByColumn("code", tgl.getText().trim());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(LPegawai.CODE);
//				return false;
//			}
//		}
//		
//		
//		if (sewa.getText().trim().equalsIgnoreCase("")) {
//			App.showErrorFieldEmpty(LPegawai.NAMA);
//			return false;
//		}
//		
//		if (waktu.getText().trim().equalsIgnoreCase("")) {
//			App.showErrorFieldEmpty(LPegawai.USERNAME);
//			return false;
//		}else{
//			long tmp=d.countByColumn("username", waktu.getText().trim());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(LPegawai.USERNAME);
//				return false;
//			}
//		}
//		
//		if (jumlahLebih.getPassword().length > 0) {
//			if (jumlahLebih.getPassword().length == denda.getPassword().length
//					&& Arrays.equals(jumlahLebih.getPassword(), denda.getPassword())) {
//
//			} else {
//				App.showErrorPasswordTidakSamadenganKonfirmasi();
//				denda.requestFocus();
//				return false;
//			}
//		} else {
//			App.showErrorFieldEmpty(LPegawai.PASSWORD);
//			jumlahLebih.requestFocus();
//			return false;
//		}
		
		return true;
	}
	
	
	
	
	
	
	protected Sewa modelSewa;
	protected JXTable tPopupSewa;
	protected SewaTMS tModelSewa;
	protected ScrollPane scrolPanePopupSewa;

	private JPopupMenu popupSewa;
	protected boolean isSewaFocus=false;

	public void initPopupSewa() {
		tModelSewa = new SewaTMS();
		tPopupSewa = new JXTable(tModelSewa);
		tPopupSewa.setHighlighters(HighlighterFactory.createAlternateStriping());
		tPopupSewa.setShowHorizontalLines(false);
		tPopupSewa.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopupSewa.setTableHeader(null);

		popupSewa = new JPopupMenu();
		popupSewa.setBackground(Color.WHITE);

		scrolPanePopupSewa = new ScrollPane(tPopupSewa);
		scrolPanePopupSewa.setBorder(BorderFactory.createLineBorder(App.selected));

		popupSewa.setBorder(BorderFactory.createEmptyBorder());
		popupSewa.add(scrolPanePopupSewa);

		sewa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (modelSewa != null) {
						String pel = sewa.getText();
						if (pel.equalsIgnoreCase("")) {
							modelSewa = null;
						} else {
							String[] tmp = pel.split(" | ");
							if (tmp.length < 2) {
								modelSewa = null;
							} else {
								String codep = modelSewa.getCode();
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
//									sewa.requestFocus();
									tglKembali.requestFocus();
								} else {
									modelSewa = null;
									showPopupSewa(e);
								}
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tPopupSewa.isVisible()) {
						tPopupSewa.requestFocus();
						if (tPopupSewa.getRowCount() > 0) {
							tPopupSewa.getSelectionModel().setSelectionInterval(0,
									0);
						}
					}

				} 

			}

			@Override
			public void keyReleased(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						if (popupSewa.isVisible()) {
							popupSewa.setVisible(false);
							isSewaFocus=true;
						}
					}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (modelSewa == null) {
					showPopupSewa(e);
				} else if (e.getKeyCode() != KeyEvent.VK_ESCAPE){
					String pel = sewa.getText();
					String[] tmp = pel.split(" | ");
					if (tmp.length < 2) {
						modelSewa = null;
						showPopupSewa(e);
					}
				}
			}

		});

		tPopupSewa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// App.info(tPopup.getSelectedRow()+"y");
				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
				String tmp1 = e.getKeyChar() + "";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp = sewa.getText() + e.getKeyChar();
					sewa.setText(tmp);
					tModelSewa.setTextSearch(sewa.getText().toLowerCase());
					tModelSewa.reload();
					modelSewa = null;
					sewa.requestFocus();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// App.info(tPopup.getSelectedRow()+"x");
						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
						if (tPopupSewa.getSelectedRow() != -1) {
							// App.info(tPopup.getSelectedRow()+"");
							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
							Sewa o = (Sewa) tModelSewa.getModels().get(
									tPopupSewa.convertRowIndexToModel(tPopupSewa
											.getSelectedRow()));
							if (o != null) {
								String codex = o.getCode();
								String namex = o.getPelanggan().getNama();
								sewa.setText(codex + " | " + namex);
								modelSewa = o;
								
//								oDocumentPaket=App.getSewaDao().getPaket(null, o);
//								String codex2 = App.getPaketDao().getCode(oDocumentPaket);
//								String namex2 = App.getPaketDao().getNama(oDocumentPaket);
//								paket.setText(codex2 + " | " + namex2);
//								
//								paket.setEditable(false);
//								BigDecimal b=App.getPaketDao().getHarga(oDocumentPaket);
//								if (b!=null) {
//									String h=App.paymentFormat2.format(b);
//									harga.setText(h);
//								}else{
//									harga.setText("0,00");
//								}
								
							} else {
								modelSewa = null;
//								paket.setEditable(true);
							}

						}
						popupSewa.setVisible(false);
						sewa.requestFocus();
					}if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						isSewaFocus=true;
					}

				}

			}

		});
		
		tglKembali.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {}

			@Override
			public void focusGained(FocusEvent e) {
				if (isSewaFocus) {
					sewa.requestFocus();
					isSewaFocus=false;
				}
			}
		});

		sewa.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (modelSewa != null) {
					String pel = sewa.getText();
					if (pel.equalsIgnoreCase("")) {
						modelSewa = null;
//						paket.setEditable(true);
					} else {
						String[] tmp = pel.split(" | ");
						if (tmp.length == 0) {
							modelSewa = null;
//							paket.setEditable(true);
						} else {
							String codep = modelSewa.getCode();
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								modelSewa = null;
								sewa.setText("");
//								paket.setEditable(true);
							}
						}
					}
				}
				
				if (modelSewa!=null) {
					pelanggan.setText(modelSewa.getPelanggan().getNama());
					tableModel.setModels(App.getSewadDao().getAllBySewa(modelSewa.getSewaId()));
					table.packAll();
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// harga.setValue(DataUser.getProduct().field(ProductDao.harga));
			}
		});
	}

	public void showPopupSewa(KeyEvent e) {

		String tmp = sewa.getText() + e.getKeyChar();
		tmp = tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp = null;
		}
		tModelSewa.setTextSearch(tmp);
		tModelSewa.reload();
		Dimension d1 = scrolPanePopupSewa.getPreferredSize();
		Dimension d2 = sewa.getSize();// .getPreferredSize();
		d1.width = d2.width;
		d1.height = 150;
		scrolPanePopupSewa.setPreferredSize(d1);
		tPopupSewa.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopupSewa.getColumnModel().getColumn(1).setPreferredWidth(300);

		tPopupSewa.packAll();
		popupSewa.show(sewa, 0, 20);
		sewa.requestFocus();
	}

	public DatePicker getTglKembali() {
		return tglKembali;
	}

	public void setTglKembali(DatePicker tglKembali) {
		this.tglKembali = tglKembali;
	}

	public JSpinner getWaktu() {
		return waktu;
	}

	public void setWaktu(JSpinner waktu) {
		this.waktu = waktu;
	}

	public TextFieldAmount getDenda() {
		return denda;
	}

	public void setDenda(TextFieldAmount denda) {
		this.denda = denda;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
