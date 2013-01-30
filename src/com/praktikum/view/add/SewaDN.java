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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
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
import com.basic.lang.LDialog;
import com.global.App;
import com.global.DataUser;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.dao.SewaDao;
import com.praktikum.dao.SewadDao;
import com.praktikum.db.Mobil;
import com.praktikum.db.Pelanggan;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;
import com.praktikum.lang.LSewa;
import com.praktikum.table.model.MobilTMS;
import com.praktikum.table.model.PelangganTMS;
import com.praktikum.table.model.SewadTM;

public class SewaDN extends DialogDefault {
	protected TextField code;
	protected TextField pelanggan;
	protected TextField mobil;
	protected DatePicker tgl;
	protected TextFieldAmount harga;
	protected TextField jumlah;
	protected TextFieldAmount hargaTotal;
	protected DatePicker tglStart;
	protected JSpinner timeStart;
	protected DatePicker tglEnd;
	protected JSpinner timeEnd;
	
	protected SpinnerDateModel sdm1;
	protected SpinnerDateModel sdm2;
	
	protected TextFieldAmount total;
	protected TextFieldAmount bayar;
	protected TextFieldAmount kembali;
	
	protected JButton proses;
	
	protected JXTable table;
	protected SewadTM tableModel;
	
	protected JPanel panelTable;
	protected JPanel panelTotal;
	
	protected BigDecimal totalSewa;
	

	@Override
	public void actionReset() {
		code.setText("Auto");
		mobil.setText("");
		
		Date skr=new Date();
		
		tgl.setDate(skr);
		harga.setText("0");
		tglStart.setDate(skr);
		hargaTotal.setText("0");
//		sdm1=new SpinnerDateModel(skr, null, null, Calendar.HOUR_OF_DAY);
//		timeStart.setModel(sdm1);
//		sdm2=new SpinnerDateModel(skr, null, null, Calendar.HOUR_OF_DAY);
//		timeEnd.setModel(sdm2);
		
		sdm1.setValue(skr);
		sdm2.setValue(skr);
		tglEnd.setDate(skr);
		
		jumlah.setText("0");
		
		
		
		
		requestDefaultFocus();
	}

	@Override
	public void requestDefaultFocus() {
		mobil.requestFocus();
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
		b.append(save, 6, 2);
		b.append(reset, 8, 2);
		panelButton = new JPanel(new BorderLayout());
		panelButton.add(b.getPanel(), BorderLayout.CENTER);
//		panelButton.add(new JSeparator(), BorderLayout.NORTH);
	}

	public void initComponent() {
		tableModel = new SewadTM();
		table=new JXTable(tableModel);
		
		setAksiListenerTable();
		
		proses=new JButton(LApp.PROSES);
		proses.setEnabled(false);
		proses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				proses();
				actionCancel();
			}
		});
		
		Date date = new Date();
		  sdm1 = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		  timeStart = new JSpinner(sdm1);
		  JSpinner.DateEditor de = new JSpinner.DateEditor(timeStart, "HH:mm");
		  timeStart.setEditor(de);
		  
		  sdm2 = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		  timeEnd = new JSpinner(sdm2);
		  JSpinner.DateEditor de2 = new JSpinner.DateEditor(timeEnd, "HH:mm");
		  timeEnd.setEditor(de2);
		
		code = new TextField();
		mobil = new TextField();
		pelanggan = new TextField();
		tgl = new DatePicker(date);
		
		
		harga = new TextFieldAmount();
		jumlah=new TextField();
		hargaTotal=new TextFieldAmount();
		
		tglStart = new DatePicker(date);
		tglEnd = new DatePicker(date);
		
		total=new TextFieldAmount();
		bayar=new TextFieldAmount();
		kembali=new TextFieldAmount();
		
		tgl.setEnabled(false);
		tgl.getEditor().setEnabled(false);
		tglEnd.setEnabled(false);
		tglEnd.getEditor().setEnabled(false);
		timeEnd.setEnabled(false);
		timeEnd.getEditor().setEnabled(false);
		harga.setEditable(false);
		total.setEditable(false);
		kembali.setEditable(false);
		
		bayar.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				BigDecimal tmp1=total.getValue(total);
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
		
		jumlah.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (modelMobil!=null) {
					try {
						int tmp=Integer.parseInt(jumlah.getText());
						if (tmp>0) {
							BigDecimal tmpbig=modelMobil.getTypeMobil().getHarga().multiply(new BigDecimal(tmp));
							hargaTotal.setText(App.paymentFormat.format(tmpbig));
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		timeStart.getEditor().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				Date tmp = tglStart.getDate();
				Date tmp2= sdm1.getDate();
				
				Calendar cal=Calendar.getInstance();
				 cal.setTime(tmp);
				 Calendar cal2=Calendar.getInstance();
				 cal2.setTime(tmp2);
				 cal.set(Calendar.HOUR, cal2.get(Calendar.HOUR));
				 cal.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
				 cal.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
				 
				 Date tmp3=cal.getTime();
				 tglStart.setDate(tmp3);
				 sdm1.setValue(tmp3);
				 try {
					 int jml=Integer.parseInt(jumlah.getText());
					 cal.add(Calendar.HOUR, jml);
					 Date tmp4=cal.getTime();
					 tglEnd.setDate(tmp4);
					 sdm2.setValue(tmp4);
				} catch (Exception e) {
					// TODO: handle exception
					App.printErr(e);
				}
				 
				 
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void setFocusEnter() {
		setFocusEnter(code, pelanggan);
		setFocusEnter(pelanggan, mobil);
		setFocusEnter(mobil, jumlah);
		setFocusEnter(jumlah, tglStart);
		setFocusEnter(tglStart.getEditor(), timeStart.getEditor());
		setFocusEnter(timeStart.getEditor(), save);
		setFocusEnter(save, code);
		
		setFocusEnter(total, bayar);
		setFocusEnter(bayar, kembali);
		setFocusEnter(kembali, proses);
	}

	@Override
	public void init() {
		icon = SewaM.ICON_48;
		super.init();
		labelTitle.setText("Sewa");
		labelNote.setText("Isi data dengan benar");
		initComponent();
		buildForm();
		initPopupPelanggan();
		initPopupMobil();
	}
	public void buildButton() {

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionSave();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionCancel();
			}
		});

		setLayoutButton();
	}
	public void actionCancel() {
		tableModel.getModels().clear();
		tableModel.clear();
		modelPelanggan=null;
		modelMobil=null;
		actionReset();
		total.setText("0");
		bayar.setText("0");
		kembali.setText("0");
		pelanggan.setText("");
		proses.setEnabled(false);
		pelanggan.setEnabled(true);
		pelanggan.requestFocus();
	}
	public void afterSave(){
		actionReset();
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
		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
//		row.append("p,15dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
				b.append(LSewa.CODE, code, 2, 2, 1);
				b.append(LSewa.TGL_SEWA,tgl , 6, 2, 1);
				b.append(LSewa.PELANGGAN, pelanggan, 2, 4, 1);
				b.append(LSewa.MOBIL, mobil, 2, 6, 1);
				b.append(LSewa.HARGA,harga , 6, 6, 1);
				b.append(LSewa.JUMLAH_WAKTU, jumlah , 2, 8, 1);
				b.append(LSewa.TOTAL,hargaTotal , 6, 8, 1);
				b.append(LSewa.TGL_START,tglStart, 2, 10, 1);
				b.append(LSewa.WAKTU_START,timeStart, 2, 12, 1);
				b.append(LSewa.TGL_END,tglEnd, 6, 10, 1);
				b.append(LSewa.WAKTU_END, timeEnd, 6, 12, 1);


				panelForm=new JPanel(new BorderLayout());
				//panelForm.add(panelTitle, BorderLayout.NORTH);
				panelForm.add(b.getPanel(), BorderLayout.CENTER);
				panelForm.add(panelButton, BorderLayout.SOUTH);

	}
	
	public void buildTable(){
		panelTable=new JPanel(new BorderLayout());
		panelTable.add(new ScrollPane(table), BorderLayout.CENTER);
	}

	public void buildTotal(){
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
		b.append(LSewa.TOTAL, total, 4, 2, 1);
		b.append(LSewa.BAYAR, bayar, 4, 4, 1);
		b.append(LSewa.KEMBALI, kembali, 4, 6, 1);
		
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
		buildTotal();
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
		try {
			int tmp=Integer.parseInt(jumlah.getText());
			BigDecimal tmpJml=new BigDecimal(tmp);
			BigDecimal tmptotal=modelMobil.getTypeMobil().getHarga().multiply(tmpJml);
			Sewad s=new Sewad(tmpJml, tglStart.getDate(), tglEnd.getDate(), (Date)timeEnd.getValue(), (Date)timeStart.getValue(), modelMobil, SewadDao.STATUS_BELUM_DIKEMBALIKAN,tmptotal);
			boolean tmpb=true;
			List<Sewad> ss=tableModel.getModels();
			for (Sewad sewad : ss) {
				if (sewad.getMobil().getMobilId()==modelMobil.getMobilId()) {
					tmpb=false;
				}
			}
			if (tmpb) {
				if (totalSewa==null) {
					totalSewa=new BigDecimal(0);
				}
				BigDecimal tmpbig=modelMobil.getTypeMobil().getHarga().multiply(new BigDecimal(tmp));
				totalSewa=totalSewa.add(tmpbig);
				total.setText(App.paymentFormat.format(totalSewa.doubleValue()));
				tableModel.addModel(s);
				pelanggan.setEnabled(false);
			}else{
				App.showErrorDataSudahAda(LSewa.MOBIL);
			}
		} catch (Exception e) {
			App.printErr(e);
		}
	}
	
	public void proses(){
		// 1=belum kembali semua
		Sewa s=new Sewa("Auto", tgl.getDate(), modelPelanggan, DataUser.getUsr(), SewaDao.STATUS_BELUM_DIKEMBALIKAN_SEMUA, totalSewa);
		List<Sewad> ss=tableModel.getModels();
		App.getSewaDao().saveTransaksi(s, ss);
	}

	@Override
	public boolean validate() {
		if (modelPelanggan==null) {
			App.showErrorFieldEmpty(LSewa.PELANGGAN);
			pelanggan.requestFocus();
			return false;
		}
		if (modelMobil==null) {
			App.showErrorFieldEmpty(LSewa.MOBIL);
			mobil.requestFocus();
			return false;
		}
		try {
			int tmp=Integer.parseInt(jumlah.getText());
			if (tmp<=0) {
				App.showErrorNilaiHarusDiAtasNol(LSewa.JUMLAH_WAKTU);
				jumlah.requestFocus();
				return false;
			}
		} catch (Exception e) {
			App.printErr(e);
			jumlah.setText("0");
			App.showErrorNilaiHarusDiAtasNol(LSewa.JUMLAH_WAKTU);
			jumlah.requestFocus();
			return false;
		}
		if (tglStart.getDate()==null) {
			App.showErrorFieldEmpty(LSewa.TGL_START);
			tglStart.requestFocus();
			return false;
		}
		if (sdm1.getDate()==null) {
			App.showErrorFieldEmpty(LSewa.WAKTU_START);
			timeStart.requestFocus();
			return false;
		}
//		SewaDao d=App.getSewaDao();
//		if (code.getText().trim().equalsIgnoreCase("")) {
//			App.showErrorFieldEmpty(LSewa.CODE);
//			return false;
//		}else{
//			long tmp=d.countByColumn("code", code.getText().trim());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(LSewa.CODE);
//				return false;
//			}
//		}
//		
//		
//		if (mobil.getText().trim().equalsIgnoreCase("")) {
//			App.showErrorFieldEmpty(LSewa.NAMA);
//			return false;
//		}
//		
//		if (tgl.getText().trim().equalsIgnoreCase("")) {
//			App.showErrorFieldEmpty(LSewa.USERNAME);
//			return false;
//		}else{
//			long tmp=d.countByColumn("username", tgl.getText().trim());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(LSewa.USERNAME);
//				return false;
//			}
//		}
//		
//		if (harga.getPassword().length > 0) {
//			if (harga.getPassword().length == tglStart.getPassword().length
//					&& Arrays.equals(harga.getPassword(), tglStart.getPassword())) {
//
//			} else {
//				App.showErrorPasswordTidakSamadenganKonfirmasi();
//				tglStart.requestFocus();
//				return false;
//			}
//		} else {
//			App.showErrorFieldEmpty(LSewa.PASSWORD);
//			harga.requestFocus();
//			return false;
//		}
		
		return true;
	}
	
	
	public void aksiDelete() {
		if (table != null) {
			int i = table.getSelectedRow();
			if (i != -1) {
					int itmp=table.convertRowIndexToModel(i);
					Object tmp=tableModel.getModels().get(itmp	);
					try {
//						tmp.delete();
//						tableModel.getDao().delete(tmp);
						BigDecimal ttmp=tableModel.getTotal(itmp);
						if (ttmp!=null) {
							totalSewa=totalSewa.add(ttmp.negate());
							total.setText(App.paymentFormat.format(totalSewa.doubleValue()));
						}
						tableModel.getModels().remove(itmp);
						tableModel.fireTableDataChanged();
//						selected();
					} catch (Exception e) {
						App.printErr(e);
					}
			} 
		}

	}
	
	private boolean isDoubleClick = true;

	public void setAksiListenerTable() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
//				clickedOnTable(e);
				if (e.getClickCount() == 2) {
					if (isDoubleClick) {
//						doubleClickedOnTable(e);
						isDoubleClick = false;
					} else {
						isDoubleClick = true;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
//				mouseReleasedOnTable(e);
			}

		});
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				keyPressedOnTable(e);
			}
		});
	}
	public void keyPressedOnTable(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			aksiDelete();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	protected Pelanggan modelPelanggan;
	protected JXTable tPopupPelanggan;
	protected PelangganTMS tModelPelanggan;
	protected ScrollPane scrolPanePopupPelanggan;

	private JPopupMenu popupPelanggan;
	protected boolean isPelangganFocus=false;

	public void initPopupPelanggan() {
		tModelPelanggan = new PelangganTMS();
		tPopupPelanggan = new JXTable(tModelPelanggan);
		tPopupPelanggan.setHighlighters(HighlighterFactory.createAlternateStriping());
		tPopupPelanggan.setShowHorizontalLines(false);
		tPopupPelanggan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopupPelanggan.setTableHeader(null);

		popupPelanggan = new JPopupMenu();
		popupPelanggan.setBackground(Color.WHITE);

		scrolPanePopupPelanggan = new ScrollPane(tPopupPelanggan);
		scrolPanePopupPelanggan.setBorder(BorderFactory.createLineBorder(App.selected));

		popupPelanggan.setBorder(BorderFactory.createEmptyBorder());
		popupPelanggan.add(scrolPanePopupPelanggan);

		pelanggan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (modelPelanggan != null) {
						String pel = pelanggan.getText();
						if (pel.equalsIgnoreCase("")) {
							modelPelanggan = null;
						} else {
							String[] tmp = pel.split(" | ");
							if (tmp.length < 2) {
								modelPelanggan = null;
							} else {
								String codep = modelPelanggan.getCode();
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
									pelanggan.requestFocus();
								} else {
									modelPelanggan = null;
									showPopupPelanggan(e);
								}
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tPopupPelanggan.isVisible()) {
						tPopupPelanggan.requestFocus();
						if (tPopupPelanggan.getRowCount() > 0) {
							tPopupPelanggan.getSelectionModel().setSelectionInterval(0,
									0);
						}
					}

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (popupPelanggan.isVisible()) {
						popupPelanggan.setVisible(false);
						isPelangganFocus=true;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (modelPelanggan == null) {
					showPopupPelanggan(e);
				} else if (e.getKeyCode() != KeyEvent.VK_ESCAPE){
					String pel = pelanggan.getText();
					String[] tmp = pel.split(" | ");
					if (tmp.length < 2) {
						modelPelanggan = null;
						showPopupPelanggan(e);
					}
				}

			}

		});

		tPopupPelanggan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// App.info(tPopup.getSelectedRow()+"y");
				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
				String tmp1 = e.getKeyChar() + "";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp = pelanggan.getText() + e.getKeyChar();
					pelanggan.setText(tmp);
					tModelPelanggan.setTextSearch(pelanggan.getText().toLowerCase());
					tModelPelanggan.reload();
					modelPelanggan = null;
					pelanggan.requestFocus();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// App.info(tPopup.getSelectedRow()+"x");
						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
						if (tPopupPelanggan.getSelectedRow() != -1) {
							// App.info(tPopup.getSelectedRow()+"");
							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
							Pelanggan o = (Pelanggan) tModelPelanggan.getModels().get(
									tPopupPelanggan.convertRowIndexToModel(tPopupPelanggan
											.getSelectedRow()));
							if (o != null) {
								String codex = o.getCode();
								String namex = o.getNama();
								pelanggan.setText(codex + " | " + namex);
								modelPelanggan = o;
								
							} else {
								modelPelanggan = null;
//								paket.setEditable(true);
							}

						}
						popupPelanggan.setVisible(false);
						pelanggan.requestFocus();
					}if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						isPelangganFocus=true;
					}

				}

			}

		});
		
//		popupPelanggan.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//					pelanggan.requestFocus();
//				}
//			}
//
//		});

		pelanggan.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (modelPelanggan != null) {
					String pel = pelanggan.getText();
					if (pel.equalsIgnoreCase("")) {
						modelPelanggan = null;
//						paket.setEditable(true);
					} else {
						String[] tmp = pel.split(" | ");
						if (tmp.length == 0) {
							modelPelanggan = null;
//							paket.setEditable(true);
						} else {
							String codep = modelPelanggan.getCode();
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								modelPelanggan = null;
								pelanggan.setText("");
//								paket.setEditable(true);
							}
						}
					}
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// harga.setValue(DataUser.getProduct().field(ProductDao.harga));
			}
		});
	}

	public void showPopupPelanggan(KeyEvent e) {

		String tmp = pelanggan.getText() + e.getKeyChar();
		tmp = tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp = null;
		}
		tModelPelanggan.setTextSearch(tmp);
		tModelPelanggan.reload();
		Dimension d1 = scrolPanePopupPelanggan.getPreferredSize();
		Dimension d2 = pelanggan.getSize();// .getPreferredSize();
		d1.width = d2.width;
		d1.height = 150;
		scrolPanePopupPelanggan.setPreferredSize(d1);
		tPopupPelanggan.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopupPelanggan.getColumnModel().getColumn(1).setPreferredWidth(300);

		tPopupPelanggan.packAll();
		popupPelanggan.show(pelanggan, 0, 20);
		pelanggan.requestFocus();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	protected Mobil modelMobil;
	protected JXTable tPopupMobil;
	protected MobilTMS tModelMobil;
	protected ScrollPane scrolPanePopupMobil;

	private JPopupMenu popupMobil;
	protected boolean isMobilFocus=false;

	public void initPopupMobil() {
		tModelMobil = new MobilTMS();
		tPopupMobil = new JXTable(tModelMobil);
		tPopupMobil.setHighlighters(HighlighterFactory.createAlternateStriping());
		tPopupMobil.setShowHorizontalLines(false);
		tPopupMobil.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopupMobil.setTableHeader(null);

		popupMobil = new JPopupMenu();
		popupMobil.setBackground(Color.WHITE);

		scrolPanePopupMobil = new ScrollPane(tPopupMobil);
		scrolPanePopupMobil.setBorder(BorderFactory.createLineBorder(App.selected));

		popupMobil.setBorder(BorderFactory.createEmptyBorder());
		popupMobil.add(scrolPanePopupMobil);

		mobil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (modelMobil != null) {
						String pel = mobil.getText();
						if (pel.equalsIgnoreCase("")) {
							modelMobil = null;
						} else {
							String[] tmp = pel.split(" | ");
							if (tmp.length < 2) {
								modelMobil = null;
							} else {
								String codep = modelMobil.getCode();
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
									mobil.requestFocus();
								} else {
									modelMobil = null;
									showPopupMobil(e);
								}
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tPopupMobil.isVisible()) {
						tPopupMobil.requestFocus();
						if (tPopupMobil.getRowCount() > 0) {
							tPopupMobil.getSelectionModel().setSelectionInterval(0,
									0);
						}
					}

				} 

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (popupPelanggan.isVisible()) {
						popupPelanggan.setVisible(false);
						isMobilFocus=true;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (modelMobil == null) {
					showPopupMobil(e);
				} else if (e.getKeyCode() != KeyEvent.VK_ESCAPE){
					String pel = mobil.getText();
					String[] tmp = pel.split(" | ");
					if (tmp.length < 2) {
						modelMobil = null;
						showPopupMobil(e);
					}
				}
			}

		});

		tPopupMobil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// App.info(tPopup.getSelectedRow()+"y");
				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
				String tmp1 = e.getKeyChar() + "";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp = mobil.getText() + e.getKeyChar();
					mobil.setText(tmp);
					tModelMobil.setTextSearch(mobil.getText().toLowerCase());
					tModelMobil.reload();
					modelMobil = null;
					mobil.requestFocus();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// App.info(tPopup.getSelectedRow()+"x");
						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
						if (tPopupMobil.getSelectedRow() != -1) {
							// App.info(tPopup.getSelectedRow()+"");
							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
							Mobil o = (Mobil) tModelMobil.getModels().get(
									tPopupMobil.convertRowIndexToModel(tPopupMobil
											.getSelectedRow()));
							if (o != null) {
								String codex = o.getCode();
								String namex = o.getTypeMobil().getNama();
								mobil.setText(codex + " | " + namex);
								modelMobil = o;
								
//								oDocumentPaket=App.getMobilDao().getPaket(null, o);
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
								modelMobil = null;
//								paket.setEditable(true);
							}

						}
						popupMobil.setVisible(false);
						mobil.requestFocus();
					}if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						isMobilFocus=true;
					}


				}

			}

		});

		mobil.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (modelMobil != null) {
					String pel = mobil.getText();
					if (pel.equalsIgnoreCase("")) {
						modelMobil = null;
//						paket.setEditable(true);
					} else {
						String[] tmp = pel.split(" | ");
						if (tmp.length == 0) {
							modelMobil = null;
//							paket.setEditable(true);
						} else {
							String codep = modelMobil.getCode();
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								modelMobil = null;
								mobil.setText("");
//								paket.setEditable(true);
							}
						}
					}
				}
				
				if (modelMobil!=null) {

					harga.setText(App.paymentFormat.format(modelMobil.getTypeMobil().getHarga()));
					try {
						int tmp=Integer.parseInt(jumlah.getText());
						if (tmp>0) {
							BigDecimal tmpbig=modelMobil.getTypeMobil().getHarga().multiply(new BigDecimal(tmp));
							hargaTotal.setText(App.paymentFormat.format(tmpbig));
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (isPelangganFocus) {
					pelanggan.requestFocus();
					isPelangganFocus=false;
				}
			}
		});
		
		jumlah.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {}

			@Override
			public void focusGained(FocusEvent e) {
				if (isMobilFocus) {
					mobil.requestFocus();
					isMobilFocus=false;
				}
			}
		});
	}

	public void showPopupMobil(KeyEvent e) {

		String tmp = mobil.getText() + e.getKeyChar();
		tmp = tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp = null;
		}
		tModelMobil.setTextSearch(tmp);
		tModelMobil.reload();
		Dimension d1 = scrolPanePopupMobil.getPreferredSize();
		Dimension d2 = mobil.getSize();// .getPreferredSize();
		d1.width = d2.width;
		d1.height = 150;
		scrolPanePopupMobil.setPreferredSize(d1);
		tPopupMobil.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopupMobil.getColumnModel().getColumn(1).setPreferredWidth(300);

		tPopupMobil.packAll();
		popupMobil.show(mobil, 0, 20);
		mobil.requestFocus();
	}
}
