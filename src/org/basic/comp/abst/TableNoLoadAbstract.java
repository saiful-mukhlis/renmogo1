package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.basic.comp.adapter.PagingInterface;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.adapter.TableModelInterface;
import org.basic.comp.def.PagingDefault;
import org.basic.comp.listener.WidgetInterface;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public abstract class TableNoLoadAbstract implements TableInterfaces {
	protected JPanel panel;
	protected JTable table;
	protected TableModelInterface tableModel;

	// protected WindowAdapter window;
	protected List<WidgetInterface> widgets = new ArrayList<WidgetInterface>();

	protected PagingInterface paging;

	public void init(ODatabaseDocumentTx db) {
		initTableModel(db);
		tableModel.load(db);
		initPaging();
		initComponent();
		initTable();
		setAksiListenerTable();
	}

	public void build(ODatabaseDocumentTx db) {
		init(db);

		paging.loadFirst(db);
		setLayout();
	}

	public void initComponent() {
		panel = new JPanel();
		initTable();

	}

	public void initPaging() {
		paging = new PagingDefault(tableModel);
	}

	public void initTable() {
		setTable(new JXTable(tableModel));
		if (getTable() instanceof JXTable) {
			setJXTable((JXTable) getTable());
		}
	}

	// private boolean isDoubleKlik=true;
	// public void setAksiListenerTable() {
	// table.addMouseListener(new MouseAdapter() {
	// public void mouseClicked(MouseEvent e) {
	// kliked();
	// if (e.getClickCount() == 2) {
	// if (isDoubleKlik) {
	// doubleKlik();
	// isDoubleKlik=false;
	// }else{
	// isDoubleKlik=true;
	// }
	// }
	// }
	//
	// public void mouseReleased(MouseEvent e) {
	// selected();
	// }
	//
	// });
	// table.addKeyListener(new KeyListener() {
	// @Override
	// public void keyTyped(KeyEvent e) {
	// }
	//
	// @Override
	// public void keyReleased(KeyEvent e) {
	// }
	//
	// @Override
	// public void keyPressed(KeyEvent e) {
	// selected();
	// }
	// });
	// }

	public void setJXTable(JXTable table) {
		table.setHorizontalScrollEnabled(true);
		table.setColumnControlVisible(true);
		table.setHighlighters(HighlighterFactory.createSimpleStriping());

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(135, 206, 250));
		// table.setSelectionForeground(SystemColor.BLACK);
	}

	private boolean isDoubleClick = true;

	public void setAksiListenerTable() {
		getTable().addMouseListener(new MouseAdapter() {
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
		getTable().addKeyListener(new KeyListener() {
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
		// selected();

	}

	protected void doubleClickedOnTable(MouseEvent e) {
		selected();

	}

	protected void clickedOnTable(MouseEvent e) {
		selected();

	}

	public void setLayout() {
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(getTable()), BorderLayout.CENTER);
		panel.add(paging.getPanelPaging(), BorderLayout.SOUTH);
	}

	public void selected() {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				selectedValid(i);
			} else {
				selectedNotValid();
			}

		}
	}

	public void aksiDelete(ODatabaseDocumentTx db) {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				if (JOptionPane.showConfirmDialog(null,
						"Apakah Anda yakin akan menghapus?",
						"Konfirmasi Delete",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					int itmp=table.convertRowIndexToModel(i);
					ODocument tmp=(ODocument) tableModel.getModels().get(itmp	);
					try {
						tmp.delete();
						tableModel.getModels().remove(itmp);
						tableModel.fireTableDataChanged();
					} catch (Exception e) {
						App.printErr(e);
					}
				}
			} 
		}

	}

	public void selectedNotValid() {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				view.modelWidgetChange(null);
			}
		}
	}

	public void selectedValid(int i) {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				view.modelWidgetChange((ODocument) tableModel.getModels().get(
						table.convertRowIndexToModel(i)));
			}
		}
	}

	public void addObjOnTableModel(ODocument model) {
		tableModel.addObjModel(model);
		selected();
	}

	public void editObjOnTableModel(ODocument model) {
		tableModel.editObjModel(model);
		selected();
	}

	public void load(ODatabaseDocumentTx db) {
		tableModel.load(db);
		selected();
	}

	public void reload(ODatabaseDocumentTx db) {
		tableModel.reload(db);
		selected();
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

	public JFrame getFrame(Object o) {
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return getFrame(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	public Container getContainer(Object o) {
		if (o instanceof Container) {
			return ((Container) o);
		} else {
			if (o instanceof Component) {
				return getContainer(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void addWidget(WidgetInterface widget) {
		widgets.add(widget);
	}
	
	@Override
	public void modelWidgetAdd(ODocument model) {
			tableModel.addObjModel(model);
		
	}
}
