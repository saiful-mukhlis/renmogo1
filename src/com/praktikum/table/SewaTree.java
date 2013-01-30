package com.praktikum.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.text.JTextComponent;

import org.basic.comp.abst.FormBuilder;
import org.basic.comp.abst.TreeTableDefault;
import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldSearch;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.table.TableColumnExt;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LDialog;
import com.basic.lang.LWindow;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.db.Mobil;
import com.praktikum.db.Pegawai;
import com.praktikum.db.Pelanggan;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;
import com.praktikum.db.TypeMobil;
import com.praktikum.lang.LPegawai;
import com.praktikum.lang.LSewa;
import com.praktikum.print.SewaPrint;
import com.praktikum.print.model.SewaPM;
import com.praktikum.table.model.MobilTMS;
import com.praktikum.table.model.PegawaiTMS;
import com.praktikum.table.model.PelangganTMS;
import com.praktikum.table.model.SewaTreeTableNodeModel;
import com.praktikum.table.model.TypeMobilTMS;
import com.praktikum.view.add.MobilDN;
import com.praktikum.view.edit.MobilDE;

public class SewaTree extends TreeTableDefault  {
	
	
	protected JPanel panelFormSearch;
	protected JPanel panelButton;
	
	protected DatePicker tglStart;
	protected DatePicker tglEnd;
	protected TextField pelanggan;
	protected TextField pegawai;
	protected TextField typeMobil;
	protected TextField mobil;
	
	protected TextField title1;
	protected TextField title2;
	protected TextField title3;
	
	protected JButton search;
	protected JButton print;
	protected JButton reset;
	
	public void actionPrint(){
		SewaPrint p=new SewaPrint(panel);
		actionSearch();
		List<SewaPM> pm=new ArrayList<SewaPM>();
		for (Object o : models) {
			if (o instanceof Sewa) {
				Sewa s=(Sewa) o;
				SewaPM tmp1=new SewaPM(s);
				pm.add(tmp1);
				List<Sewad> sewads=App.getSewadDao().getAllBySewa(s.getSewaId());
				int tmp=1;
				for (Sewad sewad : sewads) {
					sewad.setNumber(tmp);
					sewad.setSewa(s);
					SewaPM tmp2=new SewaPM(sewad);
					pm.add(tmp2);
					tmp++;
				}
			}
		}
		p.setPm(pm);
		p.setTitle(title1.getText()+"\\n"+title2.getText()+"\\n"+title3.getText());
		p.run();
	}
	
	public void actionSearch(){
		reloadSearchLap();
		root = new DefaultMutableTreeTableNode(SewaM.TITLE_MENU);
		for (Object o : models) {
			SewaTreeTableNodeModel p = new SewaTreeTableNodeModel(o);
			root.add(p);
		}
		treeModel.setRoot(root);
		
		if (showSimple) {
			setSimple();
			showSimple = true;
//			showColumn.setText("Show All");
		}else{
			
		}
		
		if (defaultIsExspan) {
			treeTable.expandAll();
		}else{
			treeTable.collapseAll();
		}
	}
	
	public void initComponentPanelFormSearch(){
		tglStart=new DatePicker();
		tglEnd=new DatePicker();
		pelanggan=new TextField();
		pegawai=new TextField();
		typeMobil=new TextField();
		mobil=new TextField();
		
		title1=new TextField();
		title2=new TextField();
		title3=new TextField();
		
		search=new JButton(LActions.SEARCH);
		print=new JButton(LActions.PRINT);
		reset=new JButton(LActions.RESET);
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionSearch();
			}
		});
		
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionPrint();
			}
		});
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionResetFormSearch();
			}
		});
	}
	
	public void actionResetFormSearch(){
		tglStart.setDate(null);
		tglEnd.setDate(null);
		pelanggan.setText("");
		pegawai.setText("");
		typeMobil.setText("");
		mobil.setText("");
		
		title1.setText("");
		title2.setText("");
		title3.setText("");
		
		//set null model
	}
	
	public void setFocusEnterFormSearch() {
		setFocusEnter(tglStart, tglEnd);
		setFocusEnter(tglEnd, pelanggan);
		setFocusEnter(pelanggan, pegawai);
		setFocusEnter(pegawai, typeMobil);
		setFocusEnter(typeMobil, mobil);
		setFocusEnter(mobil, title1);
		setFocusEnter(title1, title2);
		setFocusEnter(title2, title3);
		setFocusEnter(title3, search);
		setFocusEnter(search, tglStart);
		
	}
	
	public void buildFormSearch() {
		initComponentPanelFormSearch();
		
		
		
		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		l.setColumnGroups(new int[][] { { 4, 8, 12, 16 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append("Tanggal", tglStart, 2, 2, 1);
		b.append("Sampai", tglEnd, 6, 2, 1);
		b.append("Title 1", title1, 10, 2, 5);
		
		b.append("Pelanggan", pelanggan, 2, 4, 1);
		b.append("Pegawai", pegawai, 6, 4, 1);
		b.append("Title 2", title2, 10, 4, 5);

		b.append("Mobil", typeMobil, 2, 6, 1);
		b.append("Label", mobil, 6, 6, 1);
		b.append("Title 3", title3, 10, 6, 5);

		setLayoutButton();
		panelFormSearch=new JPanel(new BorderLayout());
		panelFormSearch.add(b.getPanel(), BorderLayout.CENTER);
		panelFormSearch.add(panelButton, BorderLayout.SOUTH);
		
		actionResetFormSearch();

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

		b.append(search, 4, 2);
		b.append(print, 6, 2);
		b.append(reset, 8, 2);
		panelButton = new JPanel(new BorderLayout());
		panelButton.add(b.getPanel(), BorderLayout.CENTER);
	}
	
	public void setLayout() {
		buildFormSearch();
		
		panel.setLayout(new BorderLayout());
		JScrollPane ss=new JScrollPane(treeTable);
		ss.setBorder(null);
		
//		ss.getViewport().setBackground(Color.BLACK);
		ss.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panel.add(ss, BorderLayout.CENTER);
		panel.add(panelFormSearch, BorderLayout.NORTH);
		if (paging!=null) {
			panel.add(paging.getPanelPaging(), BorderLayout.SOUTH);
		}
		
		initPopupPelanggan();
		initPopupPegawai();
		initPopupTypeMobil();
		initPopupMobil();
		
		setFocusEnterFormSearch();
	}

	public void initTreeModel() {
		colNames.add(App.getT("No"));
		colNames.add(App.getT("Tanggal"));
		colNames.add(App.getT("Pegawai"));
		colNames.add(App.getT("Pelanggan"));
		colNames.add(App.getT("Mobil"));
		colNames.add(App.getT("Start"));
		colNames.add(App.getT("End"));
		colNames.add(App.getT("Jumlah"));
		colNames.add(App.getT("Harga"));
		colNames.add(App.getT("Total"));
		treeModel = new DefaultTreeTableModel(root, colNames);
	}

	public void load() {
		super.load();
		root = new DefaultMutableTreeTableNode(SewaM.TITLE_MENU);
		for (Object o : models) {
			SewaTreeTableNodeModel p = new SewaTreeTableNodeModel(o);
			root.add(p);
		}
		if (treeModel != null) {
			treeModel.setRoot(root);
		}
	}
	
	

	@Override
	public void actionSearchOneField(String col, String value) {
		// TODO Auto-generated method stub
		super.actionSearchOneField(col, value);
		root = new DefaultMutableTreeTableNode(SewaM.TITLE_MENU);
		for (Object o : models) {
			SewaTreeTableNodeModel p = new SewaTreeTableNodeModel(o);
			root.add(p);
		}
		treeModel.setRoot(root);
		
		if (showSimple) {
			setSimple();
			showSimple = true;
//			showColumn.setText("Show All");
		}else{
			
		}
		
		if (defaultIsExspan) {
			treeTable.expandAll();
		}else{
			treeTable.collapseAll();
		}
	}

	@Override
	public void reload() {
		super.reload();
		root = new DefaultMutableTreeTableNode(SewaM.TITLE_MENU);
		for (Object o : models) {
			SewaTreeTableNodeModel p = new SewaTreeTableNodeModel(o);
			root.add(p);
		}
		treeModel.setRoot(root);
		
		if (showSimple) {
			setSimple();
			showSimple = true;
//			showColumn.setText("Show All");
		}else{
			
		}
		
		if (defaultIsExspan) {
			treeTable.expandAll();
		}else{
			treeTable.collapseAll();
		}
	}

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
			fieldSearch = new TextFieldSearch();
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String tmp = fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					} else {
						reload();
					}
				}
			});
		}
		return fieldSearch;
	}

	public SplitButton getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SplitButton(LWindow.KET_SEARCH
					+ LSewa.CODE);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LSewa.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LSewa.CODE);
					colSearch = "code";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LSewa.TGL_SEWA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH
							+ LSewa.TGL_SEWA);
					colSearch = "nama";
				}
			});
			menuItemSearch.add(item);

			itemSearch.setDropDownMenu(menuItemSearch);
			itemSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String tmp = fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					} else {
						reload();
					}

				}
			});

		}
		return itemSearch;
	}

	public void initDao() {
		dao = App.getSewaDao();
	}

	public MutableTreeTableNode createNode(Sewa o) {
		return new SewaTreeTableNodeModel(o);
	}

//	protected boolean addCol = true;
	protected boolean showSimple = true;
	protected JMenuItem reload;
//	protected JMenuItem add;
//	protected JMenuItem edit;
//	protected JMenuItem del;
	protected JMenuItem showControl;
	protected JMenuItem expandAll;
	protected JMenuItem collapseAll;
//	protected JMenuItem showColumn;
	
	protected ListInterfaces table=this;
	
	protected boolean defaultIsExspan=false;
	
	protected MobilDE mobilEdit;
	protected MobilDN mobilAdd;
	
	protected void createPopup() {
		
		popup = new JPopupMenu();
		showControl = new JMenuItem(LActions.SHOW_CONTROL);
		expandAll = new JMenuItem(LActions.EXPAND_ALL);
		collapseAll = new JMenuItem(LActions.COLLAPSE_ALL);
//		showColumn = new JMenuItem(LActions.SHOW_COLUMN_ALL);
		
		reload = new JMenuItem(LWindow.RELOAD);
//		add = new JMenuItem(LWindow.ADD);
//		edit = new JMenuItem(LWindow.EDIT);
//		del = new JMenuItem(LWindow.DELETE);
		

		reload.setIcon(IconBase.RELOAD);
//		add.setIcon(IconBase.ADD);
//		edit.setIcon(IconBase.EDIT);
//		del.setIcon(IconBase.DEL);

//		add.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (master != null) {
//					master.actionAdd();
//				}
//			}
//		});
		
		
		
		

//		edit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (nodeSelected!=null) {
//					Object tmp=nodeSelected.getUserObject();
//					if (tmp instanceof Sewa) {
//						if (master != null) {
//							master.actionEdit();
//						}
//					}if (tmp instanceof Mobil) {
//
//						if (master.isEdit()) {
//
//							if (mobilEdit==null) {
//								mobilEdit=new MobilDE();
//								mobilEdit.build();
//								mobilEdit.setListWidget(table);
//								table.addWidget(mobilEdit);
//							}
//							mobilEdit.setIndex(table.getIndexRowSelected());
//							JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
//							d.getContentPane().add(mobilEdit.getPanel());
//							d.pack();
//							setCenterDialog(d);
//							d.setVisible(true);
//							table.selected();
//						
//						}
//					
//					}
//				}
//				
//			}
//		});

		reload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionReload();
				}

			}
		});

//		del.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (master != null) {
//					master.actionDel();
//				}
//			}
//		});

		

		showControl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (treeTable.isColumnControlVisible()) {
					treeTable.setColumnControlVisible(false);

					showControl.setText("Show Control Column");
				} else {
					treeTable.setColumnControlVisible(true);
					showControl.setText("Hidden Control Column");
				}
			}
		});

		expandAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.expandAll();
				defaultIsExspan=true;
			}
		});
		collapseAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.collapseAll();
				defaultIsExspan=false;
			}
		});
//		showColumn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				if (showSimple) {
////					if (addCol) {
//						setColShowAll();
//						treeModel.setRoot(root);
////						setSimple();
////						addCol = false;
//
////					}
////					else {
////						for (int i = 1; i < colNames.size(); i++) {
////							TableColumnExt tcx = treeTable
////									.getColumnExt(colNames.get(i));
////							if (tcx != null) {
////								tcx.setVisible(false);
////							}
////						}
////					}
//					showSimple = false;
//					showColumn.setText("Show Simple");
//				} else {
//					setSimple();
//					treeModel.setRoot(root);
//					showSimple = true;
//					showColumn.setText("Show All");
//				}
//
//			}
//		});

		popup.add(reload);
//		popup.add(add);
//		popup.add(edit);
//		popup.add(del);

		popup.addSeparator();
		
		
		popup.addSeparator();

		popup.add(showControl);
		popup.add(expandAll);
		popup.add(collapseAll);
//		popup.add(showColumn);

		treeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						int row = treeTable.rowAtPoint(e.getPoint());
						treeTable.clearSelection();
						treeTable.addRowSelectionInterval(row, row);
						selected();
						
//						SewaTreeTableNodeModel x=(SewaTreeTableNodeModel)nodeSelected;
//						Object tmpo=x.getUserObject();
//						if (tmpo instanceof Sewa) {
//							Sewa tmp=(Sewa) x.getUserObject();
//						}
//						edit.setEnabled(true);
//						del.setEnabled(true);
					} catch (Exception e2) {
//						edit.setEnabled(false);
//						del.setEnabled(false);
					}

					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void setColShowAll(){
//		colNames.add(App.getT("Code"));
//		colNames.add(App.getT("Nama Member"));
//		colNames.add(App.getT("Pemilik"));
//		colNames.add(App.getT("Jenis Identitas"));
//		colNames.add(App.getT("No Identitas"));
//		colNames.add(App.getT("Alamat"));
//		colNames.add(App.getT("Kota"));
//		colNames.add(App.getT("No Telp"));
//		colNames.add(App.getT("No Fax"));
//		colNames.add(App.getT("No Hp1"));
//		colNames.add(App.getT("No Hp2"));
//		colNames.add(App.getT("Pin Bb1"));
//		colNames.add(App.getT("Pin Bb2"));
//		colNames.add(App.getT("Status"));
		
//		treeModel.setColumnIdentifiers(colNames);
	}
	
	public void setSimple() {
//		int tmp=colNames.size();
//		for (int i = 1; i < tmp; i++) {
//			TableColumnExt tcx = treeTable.getColumnExt(colNames.get(i));
//			if (tcx != null) {
//				tcx.setVisible(false);
//			}
//		}
//		
//		int jml=colNames.size()-1;
//		if (jml>1) {
//			
//			for (int i = jml; i >= 1; i--) {
//				colNames.remove(i);
//			}
//		}
		
		
		
		
//		treeModel.setColumnIdentifiers(colNames);
		


	}
	
	
	public Window getWindow(Object o){
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}
	
	@Override
	public void addModel(Object o) {
		reload();
//		try {
//			treeTable.setRowSelectionInterval(indexRowSelected+1, indexRowSelected+1);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		selected();
//		PaketTreeTableNodeModel n=new PaketTreeTableNodeModel(o);
		//root.add(n);
//		if (o.field("@class").equals(Sewa.TABLE)) {
//			treeModel.insertNodeInto(createNode(o), root, root.getChildCount()); 	
//			treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
//		}else{
//			treeModel.insertNodeInto(createNode(o), nodeSelected, nodeSelected.getChildCount()); 
//			if (indexRowSelected!=-1) {
//				if ((indexRowSelected+1)<treeTable.getRowCount()) {
//					treeTable.setRowSelectionInterval(indexRowSelected+1, indexRowSelected+1);
//				}else{
//					treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
//				}
//			}else{
//				treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
//			}
//			
//			
//		}
		
	}
	
	
	@Override
	public void aksiDelete() {
		Object tmp=nodeSelected.getUserObject();
		if (tmp!=null) {
			if (JOptionPane.showConfirmDialog(null,
					LDialog.INGIN_MENGHAPUS,
					 LDialog.TITLE_MENGHAPUS,
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
				if (tmp instanceof Mobil) {
					App.getMobilDao().delete((Mobil) tmp);
				}else if (tmp instanceof Sewa) {
					App.getSewaDao().delete((Sewa) tmp);
				}
			}
			
			
			reload();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public void setFocusEnter(JTextComponent sebelum,
			final JTextComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
					sesudah.selectAll();
				}
			}
		});
	}

	public void setFocusEnter(JTextComponent sebelum, final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}

	public void setFocusEnter(JComponent sebelum, final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}

	
	//popup
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

	//popup pegawai
	protected Pegawai modelPegawai;
	protected JXTable tPopupPegawai;
	protected PegawaiTMS tModelPegawai;
	protected ScrollPane scrolPanePopupPegawai;

	private JPopupMenu popupPegawai;
	protected boolean isPegawaiFocus=false;

	public void initPopupPegawai() {
		tModelPegawai = new PegawaiTMS();
		tPopupPegawai = new JXTable(tModelPegawai);
		tPopupPegawai.setHighlighters(HighlighterFactory.createAlternateStriping());
		tPopupPegawai.setShowHorizontalLines(false);
		tPopupPegawai.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopupPegawai.setTableHeader(null);

		popupPegawai = new JPopupMenu();
		popupPegawai.setBackground(Color.WHITE);

		scrolPanePopupPegawai = new ScrollPane(tPopupPegawai);
		scrolPanePopupPegawai.setBorder(BorderFactory.createLineBorder(App.selected));

		popupPegawai.setBorder(BorderFactory.createEmptyBorder());
		popupPegawai.add(scrolPanePopupPegawai);

		pegawai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (modelPegawai != null) {
						String pel = pegawai.getText();
						if (pel.equalsIgnoreCase("")) {
							modelPegawai = null;
						} else {
							String[] tmp = pel.split(" | ");
							if (tmp.length < 2) {
								modelPegawai = null;
							} else {
								String codep = modelPegawai.getCode();
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
									pegawai.requestFocus();
								} else {
									modelPegawai = null;
									showPopupPegawai(e);
								}
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tPopupPegawai.isVisible()) {
						tPopupPegawai.requestFocus();
						if (tPopupPegawai.getRowCount() > 0) {
							tPopupPegawai.getSelectionModel().setSelectionInterval(0,
									0);
						}
					}

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (popupPegawai.isVisible()) {
						popupPegawai.setVisible(false);
						isPegawaiFocus=true;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (modelPegawai == null) {
					showPopupPegawai(e);
				} else if (e.getKeyCode() != KeyEvent.VK_ESCAPE){
					String pel = pegawai.getText();
					String[] tmp = pel.split(" | ");
					if (tmp.length < 2) {
						modelPegawai = null;
						showPopupPegawai(e);
					}
				}

			}

		});

		tPopupPegawai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// App.info(tPopup.getSelectedRow()+"y");
				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
				String tmp1 = e.getKeyChar() + "";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp = pegawai.getText() + e.getKeyChar();
					pegawai.setText(tmp);
					tModelPegawai.setTextSearch(pegawai.getText().toLowerCase());
					tModelPegawai.reload();
					modelPegawai = null;
					pegawai.requestFocus();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// App.info(tPopup.getSelectedRow()+"x");
						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
						if (tPopupPegawai.getSelectedRow() != -1) {
							// App.info(tPopup.getSelectedRow()+"");
							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
							Pegawai o = (Pegawai) tModelPegawai.getModels().get(
									tPopupPegawai.convertRowIndexToModel(tPopupPegawai
											.getSelectedRow()));
							if (o != null) {
								String codex = o.getCode();
								String namex = o.getNama();
								pegawai.setText(codex + " | " + namex);
								modelPegawai = o;
								
							} else {
								modelPegawai = null;
//								paket.setEditable(true);
							}

						}
						popupPegawai.setVisible(false);
						pegawai.requestFocus();
					}if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						isPegawaiFocus=true;
					}

				}

			}

		});
		
//		popupPegawai.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//					pegawai.requestFocus();
//				}
//			}
//
//		});

		pegawai.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (modelPegawai != null) {
					String pel = pegawai.getText();
					if (pel.equalsIgnoreCase("")) {
						modelPegawai = null;
//						paket.setEditable(true);
					} else {
						String[] tmp = pel.split(" | ");
						if (tmp.length == 0) {
							modelPegawai = null;
//							paket.setEditable(true);
						} else {
							String codep = modelPegawai.getCode();
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								modelPegawai = null;
								pegawai.setText("");
//								paket.setEditable(true);
							}
						}
					}
				}

			}

			@Override
			public void focusGained(FocusEvent e) {				
				if (isPelangganFocus) {
				pelanggan.requestFocus();
				isPelangganFocus=false;
			}}
		});
	}

	public void showPopupPegawai(KeyEvent e) {

		String tmp = pegawai.getText() + e.getKeyChar();
		tmp = tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp = null;
		}
		tModelPegawai.setTextSearch(tmp);
		tModelPegawai.reload();
		Dimension d1 = scrolPanePopupPegawai.getPreferredSize();
		Dimension d2 = pegawai.getSize();// .getPreferredSize();
		d1.width = d2.width;
		d1.height = 150;
		scrolPanePopupPegawai.setPreferredSize(d1);
		tPopupPegawai.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopupPegawai.getColumnModel().getColumn(1).setPreferredWidth(300);

		tPopupPegawai.packAll();
		popupPegawai.show(pegawai, 0, 20);
		pegawai.requestFocus();
	}
	
	//popup type mobil
	protected TypeMobil modelTypeMobil;
	protected JXTable tPopupTypeMobil;
	protected TypeMobilTMS tModelTypeMobil;
	protected ScrollPane scrolPanePopupTypeMobil;

	private JPopupMenu popupTypeMobil;
	protected boolean isTypeMobilFocus=false;

	public void initPopupTypeMobil() {
		tModelTypeMobil = new TypeMobilTMS();
		tPopupTypeMobil = new JXTable(tModelTypeMobil);
		tPopupTypeMobil.setHighlighters(HighlighterFactory.createAlternateStriping());
		tPopupTypeMobil.setShowHorizontalLines(false);
		tPopupTypeMobil.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopupTypeMobil.setTableHeader(null);

		popupTypeMobil = new JPopupMenu();
		popupTypeMobil.setBackground(Color.WHITE);

		scrolPanePopupTypeMobil = new ScrollPane(tPopupTypeMobil);
		scrolPanePopupTypeMobil.setBorder(BorderFactory.createLineBorder(App.selected));

		popupTypeMobil.setBorder(BorderFactory.createEmptyBorder());
		popupTypeMobil.add(scrolPanePopupTypeMobil);

		typeMobil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (modelTypeMobil != null) {
						String pel = typeMobil.getText();
						if (pel.equalsIgnoreCase("")) {
							modelTypeMobil = null;
						} else {
							String[] tmp = pel.split(" | ");
							if (tmp.length < 2) {
								modelTypeMobil = null;
							} else {
								String codep = modelTypeMobil.getCode();
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
									typeMobil.requestFocus();
								} else {
									modelTypeMobil = null;
									showPopupTypeMobil(e);
								}
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tPopupTypeMobil.isVisible()) {
						tPopupTypeMobil.requestFocus();
						if (tPopupTypeMobil.getRowCount() > 0) {
							tPopupTypeMobil.getSelectionModel().setSelectionInterval(0,
									0);
						}
					}

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (popupTypeMobil.isVisible()) {
						popupTypeMobil.setVisible(false);
						isTypeMobilFocus=true;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (modelTypeMobil == null) {
					showPopupTypeMobil(e);
				} else if (e.getKeyCode() != KeyEvent.VK_ESCAPE){
					String pel = typeMobil.getText();
					String[] tmp = pel.split(" | ");
					if (tmp.length < 2) {
						modelTypeMobil = null;
						showPopupTypeMobil(e);
					}
				}

			}

		});

		tPopupTypeMobil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// App.info(tPopup.getSelectedRow()+"y");
				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
				String tmp1 = e.getKeyChar() + "";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp = typeMobil.getText() + e.getKeyChar();
					typeMobil.setText(tmp);
					tModelTypeMobil.setTextSearch(typeMobil.getText().toLowerCase());
					tModelTypeMobil.reload();
					modelTypeMobil = null;
					typeMobil.requestFocus();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// App.info(tPopup.getSelectedRow()+"x");
						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
						if (tPopupTypeMobil.getSelectedRow() != -1) {
							// App.info(tPopup.getSelectedRow()+"");
							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
							TypeMobil o = (TypeMobil) tModelTypeMobil.getModels().get(
									tPopupTypeMobil.convertRowIndexToModel(tPopupTypeMobil
											.getSelectedRow()));
							if (o != null) {
								String codex = o.getCode();
								String namex = o.getNama();
								typeMobil.setText(codex + " | " + namex);
								modelTypeMobil = o;
								
							} else {
								modelTypeMobil = null;
//								paket.setEditable(true);
							}

						}
						popupTypeMobil.setVisible(false);
						typeMobil.requestFocus();
					}if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						isTypeMobilFocus=true;
					}

				}

			}

		});
		
//		popupTypeMobil.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//					typeMobil.requestFocus();
//				}
//			}
//
//		});

		typeMobil.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (modelTypeMobil != null) {
					String pel = typeMobil.getText();
					if (pel.equalsIgnoreCase("")) {
						modelTypeMobil = null;
//						paket.setEditable(true);
					} else {
						String[] tmp = pel.split(" | ");
						if (tmp.length == 0) {
							modelTypeMobil = null;
//							paket.setEditable(true);
						} else {
							String codep = modelTypeMobil.getCode();
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								modelTypeMobil = null;
								typeMobil.setText("");
//								paket.setEditable(true);
							}
						}
					}
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (isTypeMobilFocus) {
					typeMobil.requestFocus();
					isTypeMobilFocus=false;
				}
			}
		});
		
	}

	public void showPopupTypeMobil(KeyEvent e) {

		String tmp = typeMobil.getText() + e.getKeyChar();
		tmp = tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp = null;
		}
		tModelTypeMobil.setTextSearch(tmp);
		tModelTypeMobil.reload();
		Dimension d1 = scrolPanePopupTypeMobil.getPreferredSize();
		Dimension d2 = typeMobil.getSize();// .getPreferredSize();
		d1.width = d2.width;
		d1.height = 150;
		scrolPanePopupTypeMobil.setPreferredSize(d1);
		tPopupTypeMobil.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopupTypeMobil.getColumnModel().getColumn(1).setPreferredWidth(300);

		tPopupTypeMobil.packAll();
		popupTypeMobil.show(typeMobil, 0, 20);
		typeMobil.requestFocus();
	}
	
	
	
	
	//popup mobil

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
				
//				if (modelMobil!=null) {
//
//					harga.setText(App.paymentFormat.format(modelMobil.getTypeMobil().getHarga()));
//					try {
//						int tmp=Integer.parseInt(jumlah.getText());
//						if (tmp>0) {
//							BigDecimal tmpbig=modelMobil.getTypeMobil().getHarga().multiply(new BigDecimal(tmp));
//							hargaTotal.setText(App.paymentFormat.format(tmpbig));
//						}
//					} catch (Exception e2) {
//						// TODO: handle exception
//					}
//				
//				}

			}

			@Override
			public void focusGained(FocusEvent e) {

				if (isTypeMobilFocus) {
					typeMobil.requestFocus();
					isTypeMobilFocus=false;
				}
			

			}
		});
		
		title1.addFocusListener(new FocusListener() {

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
	
	
	
	public void reloadSearchLap() {
		loadJumlahDataSearchLap();
		loadDataModelSearchLap(tglStart.getDate(), tglEnd.getDate(), modelPelanggan, modelPegawai, modelTypeMobil, modelMobil);
	}
	public void loadDataModelSearchLap(Date tglStart, Date tglEnd, Pelanggan modelPelanggan, Pegawai modelPegawai, TypeMobil modelTypeMobil, Mobil modelMobil) {
			models = App.getSewaDao().getAllByColumnLaporan(tglStart, tglEnd, modelPelanggan, modelPegawai, modelTypeMobil, modelMobil);
	}

	public void loadJumlahDataSearchLap() {
		paging.getPanelPaging().setVisible(false);
		
	}
	

}
