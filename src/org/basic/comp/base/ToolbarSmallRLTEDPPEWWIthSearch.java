package org.basic.comp.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import org.basic.comp.listener.MasterInterface;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LUsr;
import com.basic.lang.LWindow;
import com.global.App;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class ToolbarSmallRLTEDPPEWWIthSearch extends ToolbarSmallAdapter {

	protected JButton reload;
	protected JButton add;
	protected JButton edit;
	protected JButton del;
//	protected JButton view;
//	protected SplitButton print;
	
	
//	protected JMenuItem menuItemReload;
//	protected JMenuItem menuItemSearch;
	protected JMenuItem menuItemPrint;
	protected JMenuItem menuItemPrintPreview;
	protected JMenuItem menuItemPdf;
	protected JMenuItem menuItemExcel;
	protected JMenuItem menuItemWord;
	
	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	
	
	

	@Override
	public void load(Object model) {
		if (model==null) {
			setFalseAll();
		}else{
			setStateByHakAkses();
		}
	}

	public void setFalseAll() {
		add.setEnabled(master.isAdd());
		edit.setEnabled(false);
		del.setEnabled(false);
//		view.setEnabled(false);
	}

	public void setStateByHakAkses() {
		add.setEnabled(master.isAdd());
		edit.setEnabled(master.isEdit());
		del.setEnabled(master.isDel());
//		view.setEnabled(master.isView());
	}

	public ToolbarSmallRLTEDPPEWWIthSearch(MasterInterface master) {
		super(master);
	}
	

//	public ToolbarSmallRLTED() {
//		super();
//		build();
//	}
	
//	public ToolbarSmallRLTEDPPEWWIthSearch(MasterInterface master,
//			TextFieldSearch fieldSearch, SplitButton itemSearch) {
//		super(master);
//		this.fieldSearch = fieldSearch;
//		this.itemSearch = itemSearch;
//		build();
//		buildActions();
//	}

	public void build(){
		StringBuilder col=new StringBuilder();
		col.append("1px,");//
		col.append("f:p,10px,");//
		col.append("p,10px,");//
		col.append("c:p:g,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		FormLayout layout = new FormLayout(col.toString(),
				"p,3dlu");
		setLayout(layout);
		initComponent();
		JLabel label = new JLabel(master.getTitleToolBar());
		Font f=label.getFont();
		Font f2=new Font(f.getName(), Font.BOLD, f.getSize()+2);
		label.setFont(f2);
//		label.setText(master.getTitle());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		CellConstraints cc = new CellConstraints();
		if (fieldSearch!=null) {
			add(fieldSearch, cc.xy(2, 1));
		}
		if (itemSearch!=null) {
			add(itemSearch, cc.xy(4, 1));
		}
		add(label, cc.xy(6, 1));
		add(reload, cc.xy(10, 1));//8
//		add(view, cc.xy(10, 1));
		add(add, cc.xy(12, 1));
		add(edit, cc.xy(14, 1));
		add(del, cc.xy(16, 1));
//		add(print, cc.xy(18, 1));
		
		setBorder(App.borderBlackBottom3000);
		buildActions();
	}
	
	public void initComponent(){
		reload = new JButton(IconBase.RELOAD);
		add = new JButton(IconBase.ADD);
		edit = new JButton(IconBase.EDIT);
		del = new JButton(IconBase.DEL);
//		view = new JButton(IconBase.VIEW);
//		print = new SplitButton(IconBase.PRINT);
		
		reload.setText(LWindow.RELOAD);
		add.setText(LWindow.ADD);
		edit.setText(LWindow.EDIT);
//		view.setText(LWindow.VIEW);
//		print.setText(LWindow.PRINT);
		del.setText(LWindow.DELETE);
		
//		menuItemReload=new JMenuItem(LActions.RELOAD);
//		menuItemSearch=new JMenuItem(LActions.SEARCH);
		menuItemPrint=new JMenuItem(LActions.PRINT);
		menuItemPrintPreview=new JMenuItem(LActions.PRINT_PREVIEW);
		menuItemPdf=new JMenuItem(LActions.PDF);
		menuItemExcel=new JMenuItem(LActions.EXCEL);
		menuItemWord=new JMenuItem(LActions.WORD);
		
		menuItemExcel.setIcon(IconBase.EXCEL);
		menuItemPdf.setIcon(IconBase.PDF);
		menuItemPrint.setIcon(IconBase.PRINT);
		menuItemPrintPreview.setIcon(IconBase.PRINT_PREVIEW);
//		menuItemReload.setIcon(IconBase.RELOAD);
//		menuItemSearch.setIcon(IconBase.SEARCH);
		menuItemWord.setIcon(IconBase.WORD);
		
		reload.setBackground(Color.WHITE);
		add.setBackground(Color.WHITE);
		edit.setBackground(Color.WHITE);
//		view.setBackground(Color.WHITE);
		del.setBackground(Color.WHITE);
//		print.setBackground(Color.WHITE);
		
		JPopupMenu menuReload=new JPopupMenu();
//		menuReload.add(menuItemReload);
//		menuReload.add(menuItemSearch);
		
		JPopupMenu menuPrint=new JPopupMenu();
		menuPrint.add(menuItemPrint);
		menuPrint.add(menuItemPrintPreview);
		menuPrint.add(menuItemPdf);
		menuPrint.add(menuItemExcel);
		menuPrint.add(menuItemWord);
		
//		reload.setDropDownMenu(menuReload);
//		print.setDropDownMenu(menuPrint);
		
//		fieldSearch=new TextFieldSearch();
//		itemSearch=new SplitButton("Search By Nama");
//		itemSearch.setBackground(Color.WHITE);
//		
//		JPopupMenu menuItemSearch=new JPopupMenu();
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.NAMA));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.CODE));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.USERNAME));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.PASSWORD));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.GRP));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.NAMA));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.ALAMAT));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.KOTA));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.NO_IDENTITAS));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.JENIS_IDENTITAS));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.KOTA_LAHIR));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.TGL_LAHIR));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.JENIS_KELAMIN));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.NO_TELP));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.NO_HP1));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.NO_HP2));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.PIN_BB));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.LOGDB));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.TGL_MASUK));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.GAJI));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.JENIS_PEKERJAAN));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.PENDIDIKAN_TERAKHIR));
//		menuItemSearch.add(new JMenuItem(LWindow.KET_SEARCH+LUsr.STATUS));
//		
//		itemSearch.setDropDownMenu(menuItemSearch);
		
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

//		view.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				master.actionView();
//			}
//		});
		
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
		
//		print.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (print.getIcon().equals(IconBase.PRINT)) {
//					master.actionPrint();
//				}else if (print.getIcon().equals(IconBase.PRINT_PREVIEW)) {
//					master.actionPrintPreview();
//				}else if (print.getIcon().equals(IconBase.PDF)) {
//					master.actionPdf();
//				}else if (print.getIcon().equals(IconBase.EXCEL)) {
//					master.actionExcel();
//				}else if (print.getIcon().equals(IconBase.WORD)) {
//					master.actionWord();
//				}
//				
//			}
//		});
		
//		menuItemReload.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				master.actionReload();
//				reload.setIcon(IconBase.RELOAD);
//			}
//		});
//		
//		menuItemSearch.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				master.actionSearch();
//				reload.setIcon(IconBase.SEARCH);
//			}
//		});
		
		menuItemPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPrint();
//				print.setIcon(IconBase.PRINT);
			}
		});
		
		menuItemPrintPreview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPrintPreview();
//				print.setIcon(IconBase.PRINT_PREVIEW);
			}
		});
		
		menuItemPdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPdf();
//				print.setIcon(IconBase.PDF);
			}
		});
		
		menuItemExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionExcel();
//				print.setIcon(IconBase.EXCEL);
			}
		});
		
		menuItemWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionWord();
//				print.setIcon(IconBase.WORD);
			}
		});
		
		
		edit.setEnabled(false);
//		view.setEnabled(false);
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

//	public JButton getView() {
//		return view;
//	}
//
//	public void setView(JButton view) {
//		this.view = view;
//	}

	public TextFieldSearch getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(TextFieldSearch fieldSearch) {
		this.fieldSearch = fieldSearch;
	}

	public SplitButton getItemSearch() {
		return itemSearch;
	}

	public void setItemSearch(SplitButton itemSearch) {
		this.itemSearch = itemSearch;
	}



	
}
