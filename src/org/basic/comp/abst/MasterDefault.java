package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.basic.comp.adapter.DetailWidgetInterface;
import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.adapter.MasterAdapter;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.base.SplitPane;
import org.basic.comp.base.ToolbarSmallAdapter;
import org.basic.comp.base.ToolbarSmallRLTEDP;
import org.basic.comp.base.ToolbarSmallRLTED;
import org.basic.comp.base.ToolbarSmallRLTEDPPEW;
import org.basic.comp.base.ToolbarSmallRLTEDPPEWWIthSearch;
import org.basic.comp.listener.AddDialogInterface;
import org.basic.comp.listener.EditDialogInterface;
import org.basic.comp.listener.MasterDefaultInterface;
import org.basic.comp.listener.ToolbarSmallInterface;

import com.basic.print.interfaces.PrintInterface;
import com.global.App;

public abstract  class MasterDefault extends MasterAdapter implements MasterDefaultInterface{


	protected ListInterfaces table;
	
	protected AddDialogInterface addWidget;
	protected EditDialogInterface editWidget;
	protected DetailWidgetInterface detailWidget;

	protected SplitPane splitPane;

	protected JPanel panelAction;

	protected ToolbarSmallInterface toolBar;
	protected double lebar;
	protected String title;
	
	protected PrintInterface printer;
	
	public int getDevide() {
		Double tmp = App.getW()*lebar;
		return tmp.intValue();
	}
	
	
	public void init(){
		panel=new JPanel();
		table.build();
		table.setMaster(this);
		toolBar = new ToolbarSmallRLTEDPPEWWIthSearch(this);
		toolBar.setFieldSearch(table.getFieldSearch());
		toolBar.setItemSearch(table.getItemSearch());
		toolBar.build();
		table.addWidget(toolBar);
		
	}
	
	@Override
	public void build() {
		setDetailWidget();
		detailWidget.build();
		setTableWidget();
		table.addWidget(detailWidget);
		table.setMaster(this);
		init();
		buildBody();
		setLayout();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), detailWidget.getPanel());
		

		//splitPane.setDividerSize(20);
		
		//splitPane.setOneTouchExpandable(true);
		//splitPane.setBorder(App.borderWhite);
		perspectiveDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar.getPanel(), BorderLayout.NORTH);
		
		
	}

	public ListInterfaces getTable() {
		return table;
	}
	
	public void actionReload() {
		table.reload();
	}
	
	public void actionDel(){
		if (isDel()) {
			table.aksiDelete();
		}
	}

	public void perspectiveForm() {
		splitPane.setDividerLocation(getDevide());
	}

	

	public void buildBody(){
		detailWidget.build();
	}
	
	



	
	

	public void actionView() {
		perspectiveForm();
		splitPane.setRightComponent(detailWidget.getPanel());
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	

	
	public void actionEdit() {
		if (isEdit()) {

			if (editWidget==null) {
				setEditWidget();
			    editWidget.build();
			    editWidget.setListWidget(table);
			    table.addWidget(editWidget);
			    table.selected();
			}
			editWidget.setIndex(table.getIndexRowSelected());
			JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(editWidget.getPanel());
			d.pack();
			setCenterDialog(d);
			d.setVisible(true);
			table.selected();
		
		}
	}
	
	public void actionAdd() {
		if (isAdd()) {
			if (addWidget==null) {
				setAddWidget();
				addWidget.build();
				addWidget.setListWidget(table);
			}
			
			JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(addWidget.getPanel());
			d.pack();
			setCenterDialog(d);
			addWidget.actionReset();
			d.setVisible(true);
			if (table instanceof TableInterfaces) {
				((TableInterfaces) table).getTable().addRowSelectionInterval(((TableInterfaces) table).getTableModel().getRowCount()-1, ((TableInterfaces) table).getTableModel().getRowCount()-1);
			}
			table.selected();
		}
	}
	public void initPrefernce() {
		perspectiveDefault();
	}
	public void perspectiveDefault() {
		perspectiveDefault=true;
		table.getPanel().setVisible(true);
		table.setSimple();
		
//		if (table instanceof ListInterfaces) {
//			((TableInterfaces) table).setSimple();
//		}

		detailWidget.getPanel().setVisible(true);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(true);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(getDevide());
	}
	public void perspective1() {
		perspectiveDefault=false;
		table.getPanel().setVisible(true);
		if (table instanceof TableInterfaces) {
			((TableInterfaces) table).setShowAll();
		}
		
		splitPane.setDividerLocation(1.0);
		detailWidget.getPanel().setVisible(false);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(false);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(false);
		}
	}
	public void perspective2() {
		perspectiveDefault=false;
		table.getPanel().setVisible(false);
		detailWidget.getPanel().setVisible(true);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(true);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(0.0);
	}
	


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getUrlIcon() {
//		return urlIcon;
//	}
//
//	public void setUrlIcon(String urlIcon) {
//		this.urlIcon = urlIcon;
//	}

	public boolean isPerspectiveDefault() {
		return perspectiveDefault;
	}


	public void setPerspectiveDefault(boolean perspectiveDefault) {
		this.perspectiveDefault = perspectiveDefault;
	}
	
	
	
	
	

	
	
	@Override
	public void actionPrint() {
		if (printer!=null) {
			printer.runPrint();
		}
	}


	@Override
	public void actionPdf() {
		if (printer!=null) {
			printer.runPdf();
		}
	}


	@Override
	public void actionWord() {
		if (printer!=null) {
			printer.runWord();
		}
	}


	@Override
	public void actionExcel() {
		if (printer!=null) {
			printer.runExcel();
		}
	}


	@Override
	public void actionPrintPreview() {
		if (printer!=null) {
			printer.run();
		}
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
	public void requestDefaultSelected() {
		if (table!=null) {
			if (table instanceof TableInterfaces) {
				try {
					((TableInterfaces) table).getTable().addRowSelectionInterval(0, 0);
				} catch (Exception e) {
				}
			}
			table.selected();
		}
	}
	
	
	
}
