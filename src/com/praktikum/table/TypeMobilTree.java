package com.praktikum.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.basic.comp.abst.TreeTableDefault;
import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.jdesktop.swingx.table.TableColumnExt;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LDialog;
import com.basic.lang.LWindow;
import com.global.App;
import com.praktikum.comp.impl.master.TypeMobilM;
import com.praktikum.db.Mobil;
import com.praktikum.db.TypeMobil;
import com.praktikum.lang.LTypeMobil;
import com.praktikum.table.model.TypeMobilTreeTableNodeModel;
import com.praktikum.view.add.MobilDN;
import com.praktikum.view.edit.MobilDE;

public class TypeMobilTree extends TreeTableDefault {

	public void initTreeModel() {
		colNames.add(App.getT("Mobil"));
		treeModel = new DefaultTreeTableModel(root, colNames);
	}

	public void load() {
		super.load();
		root = new DefaultMutableTreeTableNode(TypeMobilM.TITLE_MENU);
		for (Object o : models) {
			TypeMobilTreeTableNodeModel p = new TypeMobilTreeTableNodeModel(o);
			root.add(p);
		}
		if (treeModel != null) {
			treeModel.setRoot(root);
		}
	}

	@Override
	public void actionSearchOneField(String col, String value) {
		// TODO Auto-generated method stub
		if (col.equalsIgnoreCase("code")) {
			loadJumlahDataSearchLike(col, value);
			loadDataModelSearchLike(col, value);
		} else if (col.equalsIgnoreCase("nama")) {
			loadJumlahDataSearchLike(col, value);
			loadDataModelSearchLike(col, value);
		} else if (col.equalsIgnoreCase("denda")) {
			BigDecimal b = new BigDecimal(value);
			loadJumlahDataSearch(col, b);
			loadDataModelSearch(col, b);
		} else if (col.equalsIgnoreCase("harga")) {
			BigDecimal b = new BigDecimal(value);
			loadJumlahDataSearch(col, b);
			loadDataModelSearch(col, b);
		} else if (col.equalsIgnoreCase("jumlah")) {
			try {
				int b = Integer.parseInt(value);
				loadJumlahDataSearch(col, b);
				loadDataModelSearch(col, b);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} else if (col.equalsIgnoreCase("jumlahTersedia")) {

		} else if (col.equalsIgnoreCase("ket")) {
			loadJumlahDataSearchLike(col, value);
			loadDataModelSearchLike(col, value);
		}
		root = new DefaultMutableTreeTableNode(TypeMobilM.TITLE_MENU);
		for (Object o : models) {
			TypeMobilTreeTableNodeModel p = new TypeMobilTreeTableNodeModel(o);
			root.add(p);
		}
		treeModel.setRoot(root);

		if (showSimple) {
			setSimple();
			showSimple = true;
			showColumn.setText("Show All");
		} else {

		}

		if (defaultIsExspan) {
			treeTable.expandAll();
		} else {
			treeTable.collapseAll();
		}
	}

	@Override
	public void reload() {
		super.reload();
		root = new DefaultMutableTreeTableNode(TypeMobilM.TITLE_MENU);
		for (Object o : models) {
			TypeMobilTreeTableNodeModel p = new TypeMobilTreeTableNodeModel(o);
			root.add(p);
		}
		treeModel.setRoot(root);

		if (showSimple) {
			setSimple();
			showSimple = true;
			showColumn.setText("Show All");
		} else {

		}

		if (defaultIsExspan) {
			treeTable.expandAll();
		} else {
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
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LTypeMobil.NAMA);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LTypeMobil.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LTypeMobil.CODE);
					colSearch = "code";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LTypeMobil.NAMA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LTypeMobil.NAMA);
					colSearch = "nama";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LTypeMobil.DENDA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LTypeMobil.DENDA);
					colSearch = "denda";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LTypeMobil.HARGA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LTypeMobil.HARGA);
					colSearch = "harga";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LTypeMobil.JUMLAH);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LTypeMobil.JUMLAH);
					colSearch = "jumlah";
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH
					+ LTypeMobil.JUMLAH_TERSEDIA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH
							+ LTypeMobil.JUMLAH_TERSEDIA);
					colSearch = "jumlahTersedia";
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
		dao = App.getTypeMobilDao();
	}

	public MutableTreeTableNode createNode(TypeMobil o) {
		return new TypeMobilTreeTableNodeModel(o);
	}

	// protected boolean addCol = true;
	protected boolean showSimple = true;
	protected JMenuItem reload;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem showControl;
	protected JMenuItem expandAll;
	protected JMenuItem collapseAll;
	protected JMenuItem showColumn;

	protected JMenuItem addThisMobil;

	protected ListInterfaces table = this;

	protected boolean defaultIsExspan = false;

	protected MobilDE mobilEdit;
	protected MobilDN mobilAdd;

	protected void createPopup() {

		popup = new JPopupMenu();
		showControl = new JMenuItem(LActions.SHOW_CONTROL);
		expandAll = new JMenuItem(LActions.EXPAND_ALL);
		collapseAll = new JMenuItem(LActions.COLLAPSE_ALL);
		showColumn = new JMenuItem(LActions.SHOW_COLUMN_ALL);

		reload = new JMenuItem(LWindow.RELOAD);
		add = new JMenuItem(LWindow.ADD);
		edit = new JMenuItem(LWindow.EDIT);
		del = new JMenuItem(LWindow.DELETE);

		addThisMobil = new JMenuItem(LTypeMobil.ADD_THIS_MOBIL);

		reload.setIcon(IconBase.RELOAD);
		add.setIcon(IconBase.ADD);
		edit.setIcon(IconBase.EDIT);
		del.setIcon(IconBase.DEL);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionAdd();
				}
			}
		});

		addThisMobil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					if (master.isAdd()) {
						if (nodeSelected != null) {
							Object tmp = nodeSelected.getUserObject();
							TypeMobil tmp2 = null;
							if (tmp instanceof TypeMobil) {
								tmp2 = (TypeMobil) tmp;
							}
							if (tmp instanceof Mobil) {
								tmp2 = ((Mobil) tmp).getTypeMobil();
							}

							if (mobilAdd == null) {
								mobilAdd = new MobilDN();
								mobilAdd.build();
								mobilAdd.setListWidget(table);
								table.addWidget(mobilAdd);
							}
							if (tmp2 != null) {
								mobilAdd.load(tmp2);
								JDialog d = new JDialog(getWindow(panel),
										ModalityType.APPLICATION_MODAL);
								d.getContentPane().add(mobilAdd.getPanel());
								d.pack();
								setCenterDialog(d);
								d.setVisible(true);
								table.selected();
							}
						}
					}
				}
			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nodeSelected != null) {
					Object tmp = nodeSelected.getUserObject();
					if (tmp instanceof TypeMobil) {
						if (master != null) {
							master.actionEdit();
						}
					}
					if (tmp instanceof Mobil) {

						if (master.isEdit()) {

							if (mobilEdit == null) {
								mobilEdit = new MobilDE();
								mobilEdit.build();
								mobilEdit.setListWidget(table);
								table.addWidget(mobilEdit);
							}
							mobilEdit.setIndex(table.getIndexRowSelected());
							JDialog d = new JDialog(getWindow(panel),
									ModalityType.APPLICATION_MODAL);
							d.getContentPane().add(mobilEdit.getPanel());
							d.pack();
							setCenterDialog(d);
							d.setVisible(true);
							table.selected();

						}

					}
				}

			}
		});

		reload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionReload();
				}

			}
		});

		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionDel();
				}
			}
		});

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
				defaultIsExspan = true;
			}
		});
		collapseAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.collapseAll();
				defaultIsExspan = false;
			}
		});
		showColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (showSimple) {
					// if (addCol) {
					setColShowAll();
					treeModel.setRoot(root);
					// setSimple();
					// addCol = false;

					// }
					// else {
					// for (int i = 1; i < colNames.size(); i++) {
					// TableColumnExt tcx = treeTable
					// .getColumnExt(colNames.get(i));
					// if (tcx != null) {
					// tcx.setVisible(false);
					// }
					// }
					// }
					showSimple = false;
					showColumn.setText("Show Simple");
				} else {
					setSimple();
					treeModel.setRoot(root);
					showSimple = true;
					showColumn.setText("Show All");
				}

			}
		});

		popup.add(reload);
		popup.add(add);
		popup.add(edit);
		popup.add(del);

		popup.addSeparator();

		popup.addSeparator();

		popup.add(showControl);
		popup.add(expandAll);
		popup.add(collapseAll);
		popup.add(showColumn);

		treeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						int row = treeTable.rowAtPoint(e.getPoint());
						treeTable.clearSelection();
						treeTable.addRowSelectionInterval(row, row);
						selected();

						// TypeMobilTreeTableNodeModel
						// x=(TypeMobilTreeTableNodeModel)nodeSelected;
						// Object tmpo=x.getUserObject();
						// if (tmpo instanceof TypeMobil) {
						// TypeMobil tmp=(TypeMobil) x.getUserObject();
						// }
						edit.setEnabled(true);
						del.setEnabled(true);
					} catch (Exception e2) {
						edit.setEnabled(false);
						del.setEnabled(false);
					}

					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void setColShowAll() {
		colNames.add(App.getT("Code"));
		colNames.add(App.getT("Nama Member"));
		colNames.add(App.getT("Pemilik"));
		colNames.add(App.getT("Jenis Identitas"));
		colNames.add(App.getT("No Identitas"));
		colNames.add(App.getT("Alamat"));
		colNames.add(App.getT("Kota"));
		colNames.add(App.getT("No Telp"));
		colNames.add(App.getT("No Fax"));
		colNames.add(App.getT("No Hp1"));
		colNames.add(App.getT("No Hp2"));
		colNames.add(App.getT("Pin Bb1"));
		colNames.add(App.getT("Pin Bb2"));
		colNames.add(App.getT("Status"));
		// treeModel.setColumnIdentifiers(colNames);
	}

	public void setSimple() {
		int tmp = colNames.size();
		for (int i = 1; i < tmp; i++) {
			TableColumnExt tcx = treeTable.getColumnExt(colNames.get(i));
			if (tcx != null) {
				tcx.setVisible(false);
			}
		}

		int jml = colNames.size() - 1;
		if (jml > 1) {

			for (int i = jml; i >= 1; i--) {
				colNames.remove(i);
			}
		}
		// treeModel.setColumnIdentifiers(colNames);

	}

	public Window getWindow(Object o) {
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return getWindow(((Component) o).getParent());
			} else {
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
		// try {
		// treeTable.setRowSelectionInterval(indexRowSelected+1,
		// indexRowSelected+1);
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		selected();
		// PaketTreeTableNodeModel n=new PaketTreeTableNodeModel(o);
		// root.add(n);
		// if (o.field("@class").equals(TypeMobil.TABLE)) {
		// treeModel.insertNodeInto(createNode(o), root, root.getChildCount());
		// treeTable.setRowSelectionInterval(treeTable.getRowCount()-1,
		// treeTable.getRowCount()-1);
		// }else{
		// treeModel.insertNodeInto(createNode(o), nodeSelected,
		// nodeSelected.getChildCount());
		// if (indexRowSelected!=-1) {
		// if ((indexRowSelected+1)<treeTable.getRowCount()) {
		// treeTable.setRowSelectionInterval(indexRowSelected+1,
		// indexRowSelected+1);
		// }else{
		// treeTable.setRowSelectionInterval(treeTable.getRowCount()-1,
		// treeTable.getRowCount()-1);
		// }
		// }else{
		// treeTable.setRowSelectionInterval(treeTable.getRowCount()-1,
		// treeTable.getRowCount()-1);
		// }
		//
		//
		// }

	}

	@Override
	public void aksiDelete() {
		Object tmp = nodeSelected.getUserObject();
		if (tmp != null) {
			if (JOptionPane.showConfirmDialog(null, LDialog.INGIN_MENGHAPUS,
					LDialog.TITLE_MENGHAPUS, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
				if (tmp instanceof Mobil) {
					App.getMobilDao().delete((Mobil) tmp);
				} else if (tmp instanceof TypeMobil) {
					App.getTypeMobilDao().delete((TypeMobil) tmp);
				}
			}

			reload();
		}

	}

}
