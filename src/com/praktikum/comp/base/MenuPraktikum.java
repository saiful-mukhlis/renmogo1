package com.praktikum.comp.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicMenuBarUI;

import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.base.Menu;
import org.basic.comp.base.Window;
import org.basic.comp.listener.MasterInterface;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.basic.lang.LMenu;
import com.basic.lang.LWindow;
import com.jgoodies.looks.BorderStyle;
import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.comp.impl.master.LaporanM;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.comp.impl.master.PelangganM;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.comp.impl.master.TypeMobilM;

import javax.swing.JComponent;
public class MenuPraktikum extends Menu {
	protected JMenuItem pelanggan;
	protected JMenuItem typeMobil;
	protected JMenuItem sewa;
	protected JMenuItem laporan;
//	protected JMenuItem paket;
	@Override
	public void changeHakAkses() {
		pegawai.setEnabled(PegawaiM.VIEW);
		hakAks.setEnabled(HakAksM.VIEW);
		pelanggan.setEnabled(PelangganM.VIEW);
		typeMobil.setEnabled(PelangganM.VIEW);
		sewa.setEnabled(SewaM.VIEW);
		laporan.setEnabled(LaporanM.VIEW);
		
		setFalseAll();
		if (com.global.DataUser.getUsr()!=null) {
			login.setText(LApp.LOGOUT);
			login.setIcon(IconBase.LOGOUT);
		}else{
			login.setText(LApp.LOGIN);
			login.setIcon(IconBase.LOGIN);
		}
	}

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

//		editMenu = new JMenu();
//		add = new JMenuItem();
//		edit = new JMenuItem();
//		del = new JMenuItem();
//		view = new JMenuItem();
//		reload=new JMenuItem();
//		
//		editMenu.add(add);
//		editMenu.add(edit);
//		editMenu.add(del);
//		editMenu.add(view);
//		editMenu.add(reload);

		master = new JMenu();
		hakAks=new JMenuItem();
		pegawai=new JMenuItem();
		pelanggan=new JMenuItem();
		typeMobil=new JMenuItem();
		sewa=new JMenuItem();
		laporan=new JMenuItem();
//		jenisToko=new JMenuItem();
//		statusPelanggan=new JMenuItem();

		master.add(hakAks);
		master.add(pegawai);
		master.addSeparator();
		master.add(pelanggan);
		master.add(typeMobil);
		master.add(sewa);
		master.add(laporan);
		
//		master.add(jenisToko);
//		master.add(statusPelanggan);
		
//		input = new JMenu();
		

		

		help = new JMenu();
//		registrasi = new JMenuItem();
		about = new JMenuItem();
		
//		help.add(registrasi);
		help.add(about);
		
		menu.add(file);
//		menu.add(editMenu);
		menu.add(master);
//		menu.add(input);
		//menu.add(laporan);
		//menu.add(setting);
		menu.add(help);

		initMenu(file, LApp.FILE);
		initMenu(login, LApp.LOGIN, KeyEvent.VK_L, KeyEvent.VK_L, ActionEvent.CTRL_MASK, IconBase.LOGIN);
		initMenu(close, LApp.CLOSE, KeyEvent.VK_C, KeyEvent.VK_W, ActionEvent.CTRL_MASK, IconBase.CLOSE );
		initMenu(print, LApp.PRINT, KeyEvent.VK_P, KeyEvent.VK_P, ActionEvent.CTRL_MASK, IconBase.PRINT);
		initMenu(exit, LApp.EXIT, KeyEvent.VK_E, KeyEvent.VK_X,
				ActionEvent.CTRL_MASK, IconBase.EXIT);
//		initMenu(editMenu, LApp.EDIT);
//		initMenu(add, LApp.ADD, KeyEvent.VK_T, KeyEvent.VK_N, ActionEvent.CTRL_MASK, IconBase.ADD);
//		initMenu(edit, LApp.EDIT, KeyEvent.VK_E, KeyEvent.VK_E, ActionEvent.CTRL_MASK, IconBase.EDIT);
//		initMenu(del, LApp.DEL, KeyEvent.VK_H, KeyEvent.VK_DELETE, 0, IconBase.DEL);
//		initMenu(view, LApp.VIEW, KeyEvent.VK_L, KeyEvent.VK_W, ActionEvent.CTRL_MASK, IconBase.VIEW);
//		initMenu(reload, LApp.RELOAD, KeyEvent.VK_R, KeyEvent.VK_F5, 0, IconBase.RELOAD);
		
		initMenu(master, LWindow.master);
		initMenu(hakAks, LMenu.HAK_AKS,KeyEvent.VK_H, KeyEvent.VK_H, ActionEvent.CTRL_MASK, HakAksM.ICON_16 );
		initMenu(pegawai, LMenu.PEGAWAI,KeyEvent.VK_G, KeyEvent.VK_G, ActionEvent.CTRL_MASK, PegawaiM.ICON_16);
		initMenu(pelanggan, LMenu.PELANGGAN,KeyEvent.VK_R, KeyEvent.VK_R, ActionEvent.CTRL_MASK, PelangganM.ICON_16);
		initMenu(typeMobil, LMenu.TYPE_MOBIL,KeyEvent.VK_M, KeyEvent.VK_M, ActionEvent.CTRL_MASK, TypeMobilM.ICON_16);
		initMenu(sewa, LMenu.SEWA,KeyEvent.VK_T, KeyEvent.VK_T, ActionEvent.CTRL_MASK, SewaM.ICON_16);
		initMenu(laporan, LMenu.LAPORAN,KeyEvent.VK_O, KeyEvent.VK_O, ActionEvent.CTRL_MASK, LaporanM.ICON_16);
		
//		initMenu(input, LWindow.input);
		
		initMenu(help, LWindow.help);
//		initMenu(registrasi, LWindow.registrasi, IconBase.REGISTRASI);
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
		
//		add.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				window.actionAdd();
//			}
//		});
//		
//		edit.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				window.actionEdit();
//			}
//		});
//		
//		del.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				window.actionDel();
//			}
//		});
//		
//		view.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				window.actionView();
//			}
//		});
//		
//		reload.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				window.actionReload();
//			}
//		});
		
		hakAks.addActionListener(window.getKomponentMaps().get(Window.HAK_AKSES).getAdd());
		pegawai.addActionListener(window.getKomponentMaps().get(Window.PEGAWAI).getAdd());
		pelanggan.addActionListener(window.getKomponentMaps().get(WindowPraktikum.PELANGGAN).getAdd());
		typeMobil.addActionListener(window.getKomponentMaps().get(WindowPraktikum.TYPE_MOBIL).getAdd());
		sewa.addActionListener(window.getKomponentMaps().get(WindowPraktikum.SEWA).getAdd());
		laporan.addActionListener(window.getKomponentMaps().get(WindowPraktikum.LAPORAN).getAdd());
		
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
		
//		registrasi.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				window.actionReg();
//			}
//		});
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionAbout();
			}
		});
		
	}

}
