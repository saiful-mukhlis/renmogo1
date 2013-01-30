package org.basic.comp.base;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.basic.comp.listener.MasterInterface;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.global.App;
import com.jgoodies.forms.layout.CellConstraints;


public class ToolbarSmallRLTEDPPEW extends ToolbarSmallAdapter {

	protected SplitButton reload;
	protected JButton add;
	protected JButton edit;
	protected JButton del;
	protected JButton view;
	protected SplitButton print;
	
	
	protected JMenuItem menuItemReload;
	protected JMenuItem menuItemSearch;
	protected JMenuItem menuItemPrint;
	protected JMenuItem menuItemPrintPreview;
	protected JMenuItem menuItemPdf;
	protected JMenuItem menuItemExcel;
	protected JMenuItem menuItemWord;
	
	
	
	@Override
	public void changeState() {
		super.changeState();
	}

	@Override
	public void setFalseAll() {
		add.setEnabled(master.isAdd());
		edit.setEnabled(false);
		del.setEnabled(false);
		view.setEnabled(false);
	}

	@Override
	public void setStateByHakAkses() {
		add.setEnabled(master.isAdd());
		edit.setEnabled(master.isEdit());
		del.setEnabled(master.isDel());
		view.setEnabled(master.isView());
	}

	public ToolbarSmallRLTEDPPEW(MasterInterface master) {
		super(master);
		build();
		buildActions();
	}

//	public ToolbarSmallRLTED() {
//		super();
//		build();
//	}
	
	public void build(){
		initComponent();
		JLabel label = new JLabel(master.getIcon16());
		label.setText(master.getTitle());
		CellConstraints cc = new CellConstraints();
		add(label, cc.xy(2, 1));
		add(reload, cc.xy(14, 1));
		add(view, cc.xy(16, 1));
		add(add, cc.xy(18, 1));
		add(edit, cc.xy(20, 1));
		add(del, cc.xy(22, 1));
		add(print, cc.xy(24, 1));
		
		setBorder(App.borderBlackBottom5555);
	}
	
	public void initComponent(){
		reload = new SplitButton(IconBase.RELOAD);
		add = new JButton(IconBase.ADD);
		edit = new JButton(IconBase.EDIT);
		del = new JButton(IconBase.DEL);
		view = new JButton(IconBase.VIEW);
		print = new SplitButton(IconBase.PRINT);
		
		menuItemReload=new JMenuItem(LActions.RELOAD);
		menuItemSearch=new JMenuItem(LActions.SEARCH);
		menuItemPrint=new JMenuItem(LActions.PRINT);
		menuItemPrintPreview=new JMenuItem(LActions.PRINT_PREVIEW);
		menuItemPdf=new JMenuItem(LActions.PDF);
		menuItemExcel=new JMenuItem(LActions.EXCEL);
		menuItemWord=new JMenuItem(LActions.WORD);
		
		menuItemExcel.setIcon(IconBase.EXCEL);
		menuItemPdf.setIcon(IconBase.PDF);
		menuItemPrint.setIcon(IconBase.PRINT);
		menuItemPrintPreview.setIcon(IconBase.PRINT_PREVIEW);
		menuItemReload.setIcon(IconBase.RELOAD);
		menuItemSearch.setIcon(IconBase.SEARCH);
		menuItemWord.setIcon(IconBase.WORD);
		
		reload.setBackground(Color.WHITE);
		add.setBackground(Color.WHITE);
		edit.setBackground(Color.WHITE);
		view.setBackground(Color.WHITE);
		del.setBackground(Color.WHITE);
		print.setBackground(Color.WHITE);
		
		JPopupMenu menuReload=new JPopupMenu();
		menuReload.add(menuItemReload);
		menuReload.add(menuItemSearch);
		
		JPopupMenu menuPrint=new JPopupMenu();
		menuPrint.add(menuItemPrint);
		menuPrint.add(menuItemPrintPreview);
		menuPrint.add(menuItemPdf);
		menuPrint.add(menuItemExcel);
		menuPrint.add(menuItemWord);
		
		reload.setDropDownMenu(menuReload);
		print.setDropDownMenu(menuPrint);
	}
	
	public void buildActions(){


		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionAdd();
			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionEdit();
			}
		});

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionView();
			}
		});
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (reload.getIcon().equals(IconBase.RELOAD)) {
					master.actionReload();
				}else if (reload.getIcon().equals(IconBase.SEARCH)) {
					master.actionSearch();
				}
			}
		});
		
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionDel();
				
			}
		});
		
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (print.getIcon().equals(IconBase.PRINT)) {
					master.actionPrint();
				}else if (print.getIcon().equals(IconBase.PRINT_PREVIEW)) {
					master.actionPrintPreview();
				}else if (print.getIcon().equals(IconBase.PDF)) {
					master.actionPdf();
				}else if (print.getIcon().equals(IconBase.EXCEL)) {
					master.actionExcel();
				}else if (print.getIcon().equals(IconBase.WORD)) {
					master.actionWord();
				}
				
			}
		});
		
		menuItemReload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionReload();
				reload.setIcon(IconBase.RELOAD);
			}
		});
		
		menuItemSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionSearch();
				reload.setIcon(IconBase.SEARCH);
			}
		});
		
		menuItemPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPrint();
				print.setIcon(IconBase.PRINT);
			}
		});
		
		menuItemPrintPreview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPrintPreview();
				print.setIcon(IconBase.PRINT_PREVIEW);
			}
		});
		
		menuItemPdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPdf();
				print.setIcon(IconBase.PDF);
			}
		});
		
		menuItemExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionExcel();
				print.setIcon(IconBase.EXCEL);
			}
		});
		
		menuItemWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionWord();
				print.setIcon(IconBase.WORD);
			}
		});
		
		
		edit.setEnabled(false);
		view.setEnabled(false);
		del.setEnabled(false);
	}

	public JButton getReload() {
		return reload;
	}


	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public JButton getEdit() {
		return edit;
	}

	public void setEdit(JButton edit) {
		this.edit = edit;
	}

	public JButton getDel() {
		return del;
	}

	public void setDel(JButton del) {
		this.del = del;
	}

	public JButton getView() {
		return view;
	}

	public void setView(JButton view) {
		this.view = view;
	}



	
}
