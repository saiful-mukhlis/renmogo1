package org.basic.comp.base;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.basic.comp.adapter.DetailAdapter;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.model.HakAksesTreeModel;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import com.basic.comp.model.DefaultTreeTableModel;
import com.basic.icon.IconBase;
import com.basic.lang.LWindow;
import com.global.App;
import com.global.DataUser;
import com.jgoodies.looks.Options;
import com.praktikum.db.HakAks;

public class TreeHakAkses extends DetailAdapter {
	public final static int WIDGET_1 = 0;
	public final static int WIDGET_2 = 1;
	public final static int WIDGET_3 = 2;
	protected MasterInterface master;
	
	protected int typeMasterInterface = WIDGET_2;

	protected JPanel panel;
	protected JXTreeTable treeTable;

	protected HakAks group = null;

	protected DefaultTreeTableModel model;

	protected JPopupMenu popup;

	public void setLayout() {
		JScrollPane ss = new JScrollPane(treeTable);
		ss.setBorder(App.borderWhite);
		panel.add(ss, BorderLayout.CENTER);
	}

	public void buildTable() {

		popup = new JPopupMenu();

		
		final JMenuItem a = new JMenuItem("Show Control Column");
		JMenuItem b = new JMenuItem("Expand All");
		JMenuItem c = new JMenuItem("Collapse All");

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
		popup.add(a);
		popup.add(b);
		popup.add(c);

		treeTable = new JXTreeTable(model);

		// DefaultTreeCellRenderer renderer2 = new DefaultTreeCellRenderer();
		// renderer2.setOpenIcon(null);
		// renderer2.setClosedIcon(null);
		// renderer2.setLeafIcon(null);
		// treeTable.setTreeCellRenderer(renderer2);

		treeTable.putClientProperty(Options.TREE_LINE_STYLE_KEY,
				Options.TREE_LINE_STYLE_NONE_VALUE);

		treeTable.setHorizontalScrollEnabled(true);
		treeTable.setColumnControlVisible(true);
		treeTable.setHighlighters(HighlighterFactory.createSimpleStriping());

		treeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		treeTable.setSelectionBackground(App.selected);
		treeTable.expandAll();
		treeTable.setColumnControlVisible(false);

		treeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					;
				}
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		treeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
				// if (e.isPopupTrigger() && e.getComponent() instanceof JTable
				// ) {
				// JPopupMenu popup = createYourPopUp();
				// popup.show(e.getComponent(), e.getX(), e.getY());
				// }
			}
		});

		// treeTable.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent e) {
		// if (e.getClickCount() == 2) {
		// if (master.isPerspectiveDefault()) {
		// if (typeMasterInterface==WIDGET_1) {
		// master.perspective1();
		// }else if(typeMasterInterface==WIDGET_2){
		// master.perspective2();
		// }else if (typeMasterInterface==WIDGET_3) {
		// master.perspective3();
		// }
		// }else{
		// master.perspectiveDefault();
		// }
		// }
		// }
		// public void mouseReleased(MouseEvent e) {}
		// });

		// treeTable.getTableHeader().addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent e) {
		// if (e.getClickCount() == 2) {
		// if (master.isPerspectiveDefault()) {
		// if (typeMasterInterface==WIDGET_1) {
		// master.perspective1();
		// }else if(typeMasterInterface==WIDGET_2){
		// master.perspective2();
		// }else if (typeMasterInterface==WIDGET_3) {
		// master.perspective3();
		// }
		// }else{
		// master.perspectiveDefault();
		// }
		// }
		// }
		// public void mouseReleased(MouseEvent e) {}
		// });
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void load(Object o) {
		if (o instanceof HakAks) {
			HakAks object=(HakAks) o;
			group = object;
			HakAksesTreeModel top = buildHakakses();
			model.setRoot(top);
			treeTable.expandAll();
		}
	}

	@Override
	public void build() {
		initComponent()	;
		buildTable();
		setLayout();

	}

	public void initComponent() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		initModel();

	}

	public void initModel() {
		List<String> colNames = new ArrayList<String>();
		colNames.add("Component");
		colNames.add("Status");
		DefaultMutableTreeTableNode root = buildHakakses();

		model = new DefaultTreeTableModel(root, colNames);
	}

	public MasterInterface getMaster() {
		return master;
	}

	public void setMasterMasterInterface(MasterInterface master) {
		this.master = master;
		
	}

	public int getTypeMasterInterface() {
		return typeMasterInterface;
	}

	public void setTypeMasterInterface(int typeMasterInterface) {
		this.typeMasterInterface = typeMasterInterface;
	}

	public HakAksesTreeModel buildHakakses() {
		HakAksesTreeModel top = new HakAksesTreeModel("Root", DataUser.ROOT,
				null);

		// Hak Akses===============================================

		HakAksesTreeModel perent = new HakAksesTreeModel("Hak Akses",
				DataUser.HAK_AKSES_VIEW, group);
		top.add(perent);

		HakAksesTreeModel anak = new HakAksesTreeModel("Tambah Hak Akses",
				DataUser.HAK_AKSES_ADD, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Edit Hak Akses",
				DataUser.HAK_AKSES_EDIT, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Hapus Hak Akses",
				DataUser.HAK_AKSES_HAPUS, group);
		perent.add(anak);

		// Pegawai
		perent = new HakAksesTreeModel("Pegawai", DataUser.USR_VIEW, group);
		top.add(perent);

		anak = new HakAksesTreeModel("Tambah Pegawai", DataUser.USR_ADD, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Edit Pegawai", DataUser.USR_EDIT, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Hapus Pegawai", DataUser.USR_DEL, group);
		perent.add(anak);



		return top;

	}


}
