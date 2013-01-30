package org.basic.comp.abst;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;

import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.adapter.PagingInterface;
import org.basic.comp.adapter.ParentPagingInterface;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.def.PagingDefault;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.PanelBottomInterface;
import org.basic.comp.listener.WidgetInterface;
import org.basic.comp.model.DefaultTreeNodeModel;
import org.basic.dao.adapter.Dao;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

import com.basic.icon.IconBase;
import com.basic.lang.LDialog;
import com.basic.lang.LWindow;
import com.global.App;
import com.praktikum.db.Mobil;
import com.praktikum.db.Pegawai;
import com.praktikum.db.Pelanggan;
import com.praktikum.db.TypeMobil;
import com.praktikum.table.model.TypeMobilTreeTableNodeModel;



public class TreeTableDefault implements ListInterfaces, ParentPagingInterface{
	protected JPanel panel;
	protected JXTreeTable treeTable; 
	protected DefaultMutableTreeTableNode root;
	protected DefaultTreeTableModel treeModel;
	protected List models;
	protected List<WidgetInterface> widgets = new ArrayList<WidgetInterface>();
	protected PagingInterface paging;
	public final static int WIDGET_1=0;
	public final static int WIDGET_2=1;
	public final static int WIDGET_3=2;
	public final static int WIDGET_4=3;
	protected int typeEfectWidget=WIDGET_1;
	protected MasterInterface master;
	protected int indexRowSelected=-1;
	protected DefaultTreeNodeModel nodeSelected; 
	protected int colMasterModel=0;
	protected JPopupMenu popup;
	protected Dao dao;
	protected List<String> colNames= new ArrayList<String>();

	@Override
	public void build() {
		initDao();
		load();
		initPaging();
		initComponent();
		initTreeModel();
		initTreeTable();
		setAksiListenerTable();
		if (paging!=null) {
			paging.loadFirst();
		}
		setLayout();
	}
	public void initTreeModel() {
		// TODO Auto-generated method stub
		
	}
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		JScrollPane ss=new JScrollPane(treeTable);
		ss.setBorder(null);
		
//		ss.getViewport().setBackground(Color.BLACK);
		ss.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panel.add(ss, BorderLayout.CENTER);
		if (paging!=null) {
			panel.add(paging.getPanelPaging(), BorderLayout.SOUTH);
		}
	}
	public void setAksiListenerTable() {
		treeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickedOnTable(e);
				if (e.getClickCount() == 2) {
					if (isDoubleClick) {
						doubleClickedOnTable(e);
						isDoubleClick = false;
					} else {
						isDoubleClick = true;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				mouseReleasedOnTable(e);
			}

		});
		treeTable.addKeyListener(new KeyListener() {
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
	
	protected void keyPressedOnTable(KeyEvent e) {
		selected();

	}

	protected void mouseReleasedOnTable(MouseEvent e) {
		 selected();

	}

	protected void doubleClickedOnTable(MouseEvent e) {
		selected();

	}

	protected void clickedOnTable(MouseEvent e) {
		selected();
	}
	
	protected void createPopup(){
		JMenuItem reload = new JMenuItem(LWindow.RELOAD);
		JMenuItem add = new JMenuItem(LWindow.ADD);
		final JMenuItem edit = new JMenuItem(LWindow.EDIT);
		final JMenuItem del = new JMenuItem(LWindow.DELETE);

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

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionEdit();
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

		
		
		popup = new JPopupMenu();
		final JMenuItem a = new JMenuItem("Show Control Column");
		JMenuItem b = new JMenuItem("Expand All");
		JMenuItem c = new JMenuItem("Collapse All");
		JMenuItem d = new JMenuItem("Show Header");

		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (treeTable.isColumnControlVisible()) {
					treeTable.setColumnControlVisible(false);
					a.setText("Show Control Column");
				} else {
					treeTable.setColumnControlVisible(true);
					a.setText("Hidden Control Column");
				}
			}
		});

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.expandAll();
			}
		});
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.collapseAll();
			}
		});
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		popup.add(reload);
		popup.add(add);
		popup.add(edit);
		popup.add(del);
		
		popup.addSeparator();
		
		popup.add(a);
		popup.add(b);
		popup.add(c);
		popup.add(d);
		
		treeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						int row = treeTable.rowAtPoint(e.getPoint());
						treeTable.clearSelection();
						treeTable.addRowSelectionInterval(row,row);
						selected();
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
	
	public void initTreeTable() {
		
		
		treeTable=new JXTreeTable(treeModel);
		createPopup();
//		header=(JXTableHeader) treeTable.getTableHeader();
//		header.setVisible(false);
		//header.setBackground(Color.RED);
		//header.getParent().setBackground(Color.RED);
//		th=new JXTableHeader();
//		JPanel p=new JPanel(new BorderLayout());
//		th.setLayout(new BorderLayout());
//		p.add(new JLabel("xxxxxxxxxxxxxxx"), BorderLayout.CENTER);
//		th.add(p, BorderLayout.NORTH);
//		th.add(header, BorderLayout.SOUTH);
//		header.setVisible(false);
//		treeTable.setTableHeader(th);
		setJXTreeTable(treeTable);
		
		
		

		
		if (master!=null) {
			treeTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
			treeTable.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
		}
		
		setSimple();
	}
	public void setSimple() {
	}
	
	
	public void initComponent() {
		panel = new JPanel();
	}





	public void initPaging() {
		paging = new PagingDefault(this);
	}







	private boolean isDoubleClick = true;
	
	protected void keyPressedOnTree(KeyEvent e) {
		selected();

	}

	protected void mouseReleasedOnTree(MouseEvent e) {
		 selected();

	}

	protected void doubleClickedOnTree(MouseEvent e) {
		selected();

	}

	protected void clickedOnTree(MouseEvent e) {
		selected();
	}
	
	public void selectedNotValid() {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				if (view!=null) {
					view.load(null);
				}
			}
		}
	}
	
	public void selected() {
		if (treeTable != null) {
			int i = treeTable.getSelectedRow();
			if (i != -1) {
				indexRowSelected=i;
                Object x =treeTable . getModel() . getValueAt( treeTable .convertRowIndexToModel ( treeTable. getSelectedRow ()), colMasterModel) ;
                if (x instanceof TreeTableNode) {
                	nodeSelected= (DefaultTreeNodeModel) x;
                	selectedValid( ((TreeTableNode) x).getUserObject());
				}
//                else{
//					if (x instanceof Mobil) {
//						selectedValid(x);
//						nodeSelected=null;
//					}
//				}
			} else {
				indexRowSelected=-1;
				nodeSelected=null;
				selectedNotValid();
			}

		}
	}

	
	public void selectedValid(Object o) {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				if (view!=null) {
					view.load(o);
				}
			}
		}
	}

	@Override
	public void load(Object model) {}
	
	
	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch="nama";
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		return null;
	}

	public SplitButton getItemSearch() {
		return null;
	}
	
	
	public void actionSearchOneField(String col, String value){
		loadJumlahDataSearchLike(col, value);
		loadDataModelSearchLike(col, value);
		
//		if (paging != null) {
//			paging.loadFirst();
//		}
	}
	
	
	
	

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void reload() {
		loadJumlahData();
		loadDataModel();
	}
	


	public void load() {
		loadJumlahData();
		loadDataModel();
		if (paging != null) {
			paging.loadFirst();
		}else{
		}
	}
	
	@Override
	public void addModel(Object o) {
//		PaketTreeTableNodeModel n=new PaketTreeTableNodeModel(o);
		//root.add(n);
		treeModel.insertNodeInto(createNode(o), root, root.getChildCount()); 	
		treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
	}
	
	public MutableTreeTableNode createNode(Object o){
		return new TypeMobilTreeTableNodeModel(o);
	}

	@Override
	public void editModel(Object model, int index) {
//		TreeTableNode t=nodeSelected.getParent();
//		treeModel.removeNodeFromParent(nodeSelected);
//		if (t instanceof DefaultMutableTreeTableNode) {
//			((DefaultMutableTreeTableNode) t).add(nodeSelected);
//		}
		treeModel.setValueAt(((DefaultMutableTreeTableNode) nodeSelected).getValueAt(0), nodeSelected, 0);
//		treeModel.setRoot(root);
//		treeTable.setRowSelectionInterval(-1, -1);
//		treeTable.setRowSelectionInterval(index, index);
	}

	@Override
	public void addWidget(WidgetInterface widget) {
		this.widgets.add(widget);
	}



	@Override
	public void aksiDelete() {
		Object tmp=nodeSelected.getUserObject();
		if (tmp!=null) {
			if (JOptionPane.showConfirmDialog(null,
					LDialog.INGIN_MENGHAPUS,
					 LDialog.TITLE_MENGHAPUS,
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					dao.delete(tmp);
			}
			
			
			reload();
		}
		
	}


	@Override
	public Object getModelSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndexRowSelected() {
		return indexRowSelected;
	}

	@Override
	public boolean isSearching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MasterInterface getMaster() {
		// TODO Auto-generated method stub
		return master;
	}

	@Override
	public void setMaster(MasterInterface master) {
			this.master= master;
	}

	@Override
	public PanelBottomInterface getPanelBottom() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setJXTreeTable(JXTreeTable treeTable) {
		treeTable.setHorizontalScrollEnabled(true);
		treeTable.setColumnControlVisible(false);
		treeTable.setHighlighters(HighlighterFactory.createSimpleStriping());

		treeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		treeTable.setSelectionBackground(App.selected);
		treeTable.setShowHorizontalLines(false);
		treeTable.setBorder(null);
		// table.setSelectionForeground(SystemColor.BLACK);
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPaging(PagingInterface paging) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public void loadDataModel() {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			models = dao.getAll( tmp
					,
					paging.getJumlahPerHalaman());
		} else {
			models = (List) dao.getAll();
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
	
//	public void loadDataModelSearch(T o) {
//		if (paging != null) {
//			int tmp=(paging.getCurentHalaman()-1)
//					* paging.getJumlahPerHalaman();
//			if (tmp<0) {
//				tmp=0;
//			}
//			models = dao.getAllSearch( o, tmp
//					,
//					paging.getJumlahPerHalaman());
//			
//		} else {
//			models = (List<T>) dao.getAllSearch(o);
//		}
//	}
//
//	public void loadJumlahDataSearch(, T o) {
//		if (paging != null) {
//			paging.setJumlahData(dao.getCountSearch(o));
//			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
//				paging.getPanelPaging().setVisible(true);
//			} else {
//				paging.getPanelPaging().setVisible(false);
//			}
//		}
//		
//	}
	
	
	

	
	
	public void loadDataModelSearch(String col, String value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			models = dao.getAllByColumn( col, value, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			models = (List) dao.getAllByColumn( col, value);
		}
	}

	public void loadJumlahDataSearch(String col, String value) {
		if (paging != null) {
			paging.setJumlahData((Long) dao.countByColumn(col, value));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	public void loadDataModelSearch(String col, BigDecimal value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			models = dao.getAllByColumn( col, value, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			models = (List) dao.getAllByColumn( col, value);
		}
	}

	public void loadJumlahDataSearch(String col, BigDecimal value) {
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
			models = dao.getAllByColumn( col, value, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			models = (List) dao.getAllByColumn( col, value);
		}
	}

	public void loadJumlahDataSearch(String col, int value) {
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
			models = dao.getAllByColumn( col, value+"%", tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			models = (List) dao.getAllByColumn( col, value+"%");
		}
		
	}

	public void loadJumlahDataSearchLike(String col, String value) {
		if (paging != null) {
			paging.setJumlahData((Long) dao.countByColumn(col, value+"%"));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	public void initDao(){
		
	}
	
}
