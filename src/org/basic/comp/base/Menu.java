package org.basic.comp.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicMenuBarUI;

import org.basic.comp.adapter.MenuAdapter;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.listener.MasterInterface;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.basic.lang.LMenu;
import com.basic.lang.LWindow;
import com.global.App;
import com.jgoodies.looks.BorderStyle;
import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.comp.impl.master.PegawaiM;

public class Menu extends MenuAdapter {

	@Override
	public void changeHakAkses() {
		pegawai.setEnabled(PegawaiM.VIEW);
		hakAks.setEnabled(HakAksM.VIEW);
		
		setFalseAll();
		if (com.global.DataUser.getUsr()!=null) {
			login.setText(LApp.LOGOUT);
			login.setIcon(IconBase.LOGOUT);
		}else{
			login.setText(LApp.LOGIN);
			login.setIcon(IconBase.LOGIN);
		}
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		init();
		changeHakAkses();
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return menu;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		// TODO Auto-generated method stub
		this.window=window;
	}

	protected JMenuBar menu;

	protected JMenu file;
	protected JMenuItem login;
	protected JMenuItem close;
	protected JMenuItem print;
	protected JMenuItem exit;
	protected JMenuItem reload;
	

	protected JMenu editMenu;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem view;
	
	

	protected JMenu master;
	protected JMenuItem hakAks;
	protected JMenuItem pegawai;

	protected JMenu help;
	protected JMenuItem registrasi;
	protected JMenuItem about;
	

	protected WindowInterfaces window;
	
	

	public void init() {
		menu = new JMenuBar();
		menu.setUI ( new BasicMenuBarUI ()
	    {
	        public void paint ( Graphics g, JComponent c )
	        {
	            g.setColor ( Color.WHITE );
	            g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
	        }
	    } );
		menu.putClientProperty (Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);
		menu.putClientProperty (Options.HEADER_STYLE_KEY, HeaderStyle.SINGLE);
		menu.putClientProperty(Options.NO_ICONS_KEY, Boolean.TRUE);
		menu.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY, BorderStyle.SEPARATOR);
		
		file = new JMenu();
		login = new JMenuItem();
		close = new JMenuItem();
		print = new JMenuItem();
		exit = new JMenuItem();
		
		file.add(login);
		file.add(close);
		file.add(print);
		file.addSeparator();
		file.add(exit);

		editMenu = new JMenu();
		add = new JMenuItem();
		edit = new JMenuItem();
		del = new JMenuItem();
		view = new JMenuItem();
		reload=new JMenuItem();
		
		editMenu.add(add);
		editMenu.add(edit);
		editMenu.add(del);
		editMenu.add(view);
		editMenu.add(reload);

		master = new JMenu();
		hakAks=new JMenuItem();
		pegawai=new JMenuItem();

		master.add(hakAks);
		master.add(pegawai);
		
		

		help = new JMenu();
		registrasi = new JMenuItem();
		about = new JMenuItem();
		
		help.add(registrasi);
		help.add(about);
		
		menu.add(file);
		menu.add(editMenu);
		menu.add(master);
		menu.add(help);

		initMenu(file, LApp.FILE);
		initMenu(login, LApp.LOGIN, KeyEvent.VK_L, KeyEvent.VK_L, ActionEvent.CTRL_MASK, IconBase.LOGIN);
		initMenu(close, LApp.CLOSE, KeyEvent.VK_C, KeyEvent.VK_W, ActionEvent.CTRL_MASK, IconBase.CLOSE );
		initMenu(print, LApp.PRINT, KeyEvent.VK_P, KeyEvent.VK_P, ActionEvent.CTRL_MASK, IconBase.PRINT);
		initMenu(exit, LApp.EXIT, KeyEvent.VK_E, KeyEvent.VK_X,
				ActionEvent.CTRL_MASK, IconBase.EXIT);
		initMenu(editMenu, LApp.EDIT);
		initMenu(add, LApp.ADD, KeyEvent.VK_T, KeyEvent.VK_N, ActionEvent.CTRL_MASK, IconBase.ADD);
		initMenu(edit, LApp.EDIT, KeyEvent.VK_E, KeyEvent.VK_E, ActionEvent.CTRL_MASK, IconBase.EDIT);
		initMenu(del, LApp.DEL, KeyEvent.VK_H, KeyEvent.VK_DELETE, 0, IconBase.DEL);
		initMenu(view, LApp.VIEW, KeyEvent.VK_L, KeyEvent.VK_W, ActionEvent.CTRL_MASK, IconBase.VIEW);
		initMenu(reload, LApp.RELOAD, KeyEvent.VK_R, KeyEvent.VK_F5, 0, IconBase.RELOAD);
		
		initMenu(master, LWindow.master);
		initMenu(hakAks, LMenu.HAK_AKS, HakAksM.ICON_16 );
		initMenu(pegawai, LMenu.PEGAWAI, PegawaiM.ICON_16);
		
		initMenu(help, LWindow.help);
		initMenu(registrasi, LWindow.registrasi, IconBase.REGISTRASI);
		initMenu(about, LWindow.about, IconBase.ABAOUT);
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.login();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionClose();
			}
		});
		
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionPrint();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionExit();
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionAdd();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionEdit();
			}
		});
		
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionDel();
			}
		});
		
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionView();
			}
		});
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionReload();
			}
		});
		
		hakAks.addActionListener(window.getKomponentMaps().get(Window.HAK_AKSES).getAdd());
		pegawai.addActionListener(window.getKomponentMaps().get(Window.PEGAWAI).getAdd());
		
//		showToolbar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				window.showToolbar();
//				if (showToolbar.getText().equals(LWindow.showToolbar)) {
//					showToolbar.setText(LWindow.hideToolbar);
//				}else{
//					showToolbar.setText(LWindow.showToolbar);
//				}
//			}
//		});
		
		registrasi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionReg();
			}
		});
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionAbout();
			}
		});
		
	}

	public void initMenu(JMenu m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	public void initMenu(JMenu m, String nama) {
		m.setText(App.getT(nama));
	}

	public void initMenu(JMenuItem m, String nama) {
		m.setText(App.getT(nama));
	}

	public void initMenu(JMenuItem m, String nama, int key, int ke, int ae) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
	}

	public void initMenu(JMenuItem m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	public void initMenu(JMenuItem m, String nama, String urlIcon16) {
		m.setText(App.getT(nama));
		m.setIcon(App.getIcon(urlIcon16));
	}

	public void initMenu(JMenuItem m, String nama, int key, int ke, int ae,
			String urlIcon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(App.getIcon(urlIcon16));
	}

	public void initMenu(JMenuItem m, String nama, int key, String urlIcon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setIcon(App.getIcon(urlIcon16));
	}
	
	public void initMenu(JMenuItem m, String nama, Icon icon16) {
		m.setText(App.getT(nama));
		m.setIcon(icon16);
	}

	public void initMenu(JMenuItem m, String nama, int key, int ke, int ae,
			Icon icon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(icon16);
	}

	public void initMenu(JMenuItem m, String nama, int key, Icon icon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setIcon(icon16);
	}


	public void setFalseAll() {
//		add.setEnabled(false);
//		edit.setEnabled(false);
//		view.setEnabled(false);
//		del.setEnabled(false);
//		print.setEnabled(false);
	}

	public void setStateByHakAkses() {
//		if (window.getKomponentSeledcted()!=null) {
//			MasterInterface master=window.getKomponentSeledcted().getWidgetTop();
//			add.setEnabled(master.isAdd());
//			edit.setEnabled(master.isEdit());
//			view.setEnabled(master.isView());
//			del.setEnabled(master.isDel());
//			print.setEnabled(master.isPrint());
//		}else{
//			setFalseAll();
//		}
	}

}
