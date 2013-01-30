//package org.basic.comp.base;
//
//import java.awt.BorderLayout;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.ListSelectionModel;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.tree.DefaultTreeCellRenderer;
//
//import org.basic.comp.abst.TableModelDefault;
//import org.basic.comp.adapter.DetailAdapter;
//import org.basic.comp.adapter.EfectWidget;
//import org.basic.comp.listener.WidgetInterface;
//import org.basic.comp.model.HakAksesModel;
//import org.jdesktop.swingx.JXTreeTable;
//import org.jdesktop.swingx.decorator.HighlighterFactory;
//
//import com.global.App;
//import com.jgoodies.looks.Options;
//import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
//import com.orientechnologies.orient.core.record.impl.ODocument;
//
//public class TreeHakAkses2 extends DetailAdapter{
//	public final static int WIDGET_1=0;
//	public final static int WIDGET_2=1;
//	public final static int WIDGET_3=2;
//	protected EfectWidget master;
//	
//	protected int typeEfectWidget=WIDGET_2;
//	
//	protected JPanel panel;
//	protected JXTreeTable treeTable;
//	
//	
//	
//	protected ODocument group=null;
//	
//	protected HakAksesModel model;
//	
//	
//
//	
//	public void setLayout(){
//		JScrollPane ss=new JScrollPane(treeTable);
//		ss.setBorder(App.borderWhite);
//		panel.add(ss, BorderLayout.CENTER);
//	}
//	public void buildTable(){
//		treeTable=new JXTreeTable(model);
//		
////		DefaultTreeCellRenderer renderer2 = new DefaultTreeCellRenderer();
////	    renderer2.setOpenIcon(null);
////	    renderer2.setClosedIcon(null);
////	    renderer2.setLeafIcon(null);
////	    treeTable.setTreeCellRenderer(renderer2);
//		
//		treeTable.putClientProperty(Options.TREE_LINE_STYLE_KEY, 
//                Options.TREE_LINE_STYLE_NONE_VALUE);
//		
//		treeTable.setHorizontalScrollEnabled(true);
//		treeTable.setColumnControlVisible(true);
//		treeTable.setHighlighters(HighlighterFactory.createSimpleStriping());
//		 
//		treeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		treeTable.setSelectionBackground(App.selected);
//		treeTable.expandAll();
//		
//		treeTable.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (master.isPerspectiveDefault()) {
//						if (typeEfectWidget==WIDGET_1) {
//							master.perspective1();
//						}else if(typeEfectWidget==WIDGET_2){
//							master.perspective2();
//						}else if (typeEfectWidget==WIDGET_3) {
//							master.perspective3();
//						}
//					}else{
//						master.perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		
//		treeTable.getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (master.isPerspectiveDefault()) {
//						if (typeEfectWidget==WIDGET_1) {
//							master.perspective1();
//						}else if(typeEfectWidget==WIDGET_2){
//							master.perspective2();
//						}else if (typeEfectWidget==WIDGET_3) {
//							master.perspective3();
//						}
//					}else{
//						master.perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//	}
//	
//	
//
//
//	public JPanel getPanel() {
//		return panel;
//	}
//
//	@Override
//	public void load(ODocument object) {
//		if (object==null || object.field("@class").equals(App.getGrpDao().getClassName())) {
//			model.setGroup(object);
//			treeTable.validate();
//			treeTable.repaint();
////			treeTable.updateUI();
//		}
//		
//	}
//
//	@Override
//	public void build(ODatabaseDocumentTx db) {
//		initComponent(db);
//		buildTable();
//		setLayout();
//		
//	}
//
//
//
//
//	public void initComponent(ODatabaseDocumentTx db) {
//		panel=new JPanel();
//		panel.setLayout(new BorderLayout());
//		
//		initModel();
//		
//	}
//	
//	public void initModel(){
//		model=new HakAksesModel(group);
//	}
//	
//	public EfectWidget getMaster() {
//		return master;
//	}
//	public void setMasterEfectWidget(EfectWidget master) {
//		this.master = master;
//	}
//	public int getTypeEfectWidget() {
//		return typeEfectWidget;
//	}
//	public void setTypeEfectWidget(int typeEfectWidget) {
//		this.typeEfectWidget = typeEfectWidget;
//	}
//	
//}
