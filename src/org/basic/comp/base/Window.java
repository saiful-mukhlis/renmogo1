package org.basic.comp.base;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.basic.comp.abst.WindowAbstract;
import org.basic.comp.adapter.HakAksesListener;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;
import com.global.DataUser;
import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.comp.impl.master.PegawaiM;

public class Window extends WindowAbstract{
	
	protected List<Komponent> komponents=new ArrayList<Komponent>();
	protected List<HakAksesListener> cangeHakAkses = new ArrayList<HakAksesListener>();
	protected HashMap<String, Komponent> komponentMaps=new HashMap<String, Komponent>();
	
	
	protected AddContentAction windowAca;
	protected ViewContextAction windowVca;
	
	protected Component welcomeComponent;
	
	
	public static String HAK_AKSES=HakAksM.class.getName();
	public static String PEGAWAI=PegawaiM.class.getName();
	
	
	@Override
	public void buildMaster() {
		
		Komponent hakAkses=new Komponent(this, HAK_AKSES, LWindow.HAK_AKSES, 'H', HakAksM.ICON_16, HakAksM.ICON_32);
		komponentMaps.put(HAK_AKSES, hakAkses);
		komponents.add(hakAkses);
		
		Komponent pegawai=new Komponent(this, PEGAWAI, LWindow.PEGAWAI, 'P', PegawaiM.ICON_16, PegawaiM.ICON_32);
		komponentMaps.put(PEGAWAI, pegawai);
		komponents.add(pegawai);
		
		
		
		for (Object komponent : getKomponents()) {
			cangeHakAkses.add(((Komponent) komponent).getHakAksesListener());
		}
		
		
//		JPanel panel = new ImagePanel(
//				new FlowLayout(FlowLayout.CENTER, 50, 180));
		JComponent panel = new BasicTextLabelIntro().buildPanel();
		welcomeComponent = panel;

		
		windowAca = factoryContentAction("Welcome", App.getIcon(LApp.iconApp16),
				welcomeComponent, (int) 'W');
		
	}


	@Override
	public void buildActions() {
		for (Object komponent : getKomponents()) {
			((Komponent) komponent).setView( ((Komponent) komponent).getTypeMaster(), myDoggySetContext);
		}
		
		windowVca = new ViewContextAction(
				"Welcome", App.getIcon(LApp.iconApp16),
				myDoggySetContext, ImagePanel.class);
		
	}
	
	@Override
	public void initContext() {
		myDoggySetContext = new Context(this, toolWindowManager, frame);
	}
	

	@Override
	public void initToolbar() {
		setToolbar(new Toolbar());
		getToolbar().setWindow(this);
		getToolbar().build();
		frame.getContentPane().add(getToolbar().getPanel(), BorderLayout.NORTH);
		cangeHakAkses.add(toolbar);
		toolbar.getPanel().setVisible(false);
	}

	@Override
	public void initMenu() {
		menu=new Menu();
		menu.setWindow(this);
		menu.build();
		cangeHakAkses.add(menu);
		frame.setJMenuBar(menu.getMenu());
	}


	@Override
	public void showWelcome() {
		myDoggySetContext.put(ImagePanel.class, null);
		
	}

	@Override
	public Component getComponentWelcome() {
		return welcomeComponent;
	}

	@Override
	public void seta() {
		bosOne=new BosOne();
		bosOne.seta();
		
	}



	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}




	
	public AddContentAction getWelcomeAca() {
		return windowAca;
	}


	@Override
	public void showToolbar() {
		if (toolbar.getPanel().isVisible()) {
			toolbar.getPanel().setVisible(false);
		}else{
			toolbar.getPanel().setVisible(true);
		}
		
	}


	public List getKomponents() {
		return komponents;
	}


	public void setKomponents(List<Komponent> komponents) {
		this.komponents = komponents;
	}


	public void login() {
		
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			form.buildComponent();
			JDialog d = new JDialog(frame);
			d.setModalityType(ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(form.getPanel());
			d.pack();
			setCenterDialog(d);
			d.setVisible(true);
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			closeAllWindow();
			
		}
		DataUser.setAkses();
		for (HakAksesListener hakAksesListener : cangeHakAkses) {
			if (hakAksesListener!=null) {
				hakAksesListener.changeHakAkses();
			}
		}
	}
	
	public void closeAllWindow() {
		toolWindowManager.getContentManager().removeAllContents();

	}
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}


	public HashMap<String, Komponent> getKomponentMaps() {
		return komponentMaps;
	}


	public void setKomponentMaps(HashMap<String, Komponent> komponentMaps) {
		this.komponentMaps = komponentMaps;
	}
	
	public Komponent getKomponentSeledcted(){
		return komponentMaps.get(idSelected);
	}
	
	public void actionClose(){
		ContentManager c=toolWindowManager.getContentManager();
		Content co=c.getSelectedContent();
		if (co!=null) {
			c.removeContent(co);
		}
	}


	@Override
	public void actionExit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPrint() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionPrint();
		}
	}


	@Override
	public void actionAdd() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionAdd();
		}
	}


	@Override
	public void actionEdit() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionEdit();
		}
	}


	@Override
	public void actionDel() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionDel();
		}
	}


	@Override
	public void actionView() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionView();
		}
	}
	
	@Override
	public void actionReload() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionReload();
		}
	}
	
	public void actionReg(){
		FormRegistrasi form=new FormRegistrasi();
		form.buildComponent();
		JDialog d = new JDialog(getWindow(getPanel()), ModalityType.APPLICATION_MODAL);
		d.setIconImage(App.getImage(LApp.iconApp16).getImage());
		d.getContentPane().add(form.getPanel());
		d.setFocusTraversalPolicy(form.getFocus());
		d.pack();
		setCenterDialog(d);
		d.setVisible(true);
	}

	public void actionAbout(){
		AboutDialog form=new AboutDialog();
		form.buildComponent();
		JDialog d = new JDialog(getWindow(getPanel()), ModalityType.APPLICATION_MODAL);
		d.setIconImage(App.getImage(LApp.iconApp16).getImage());
		d.getContentPane().add(form.getPanel());
		d.setSize(480, 360);
		//d.pack();
		d.setResizable(false);
		setCenterDialog(d);
		d.setVisible(true);
	}
	
	public java.awt.Window getWindow(Object o){
		if (o instanceof Window) {
			return ((java.awt.Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}





	

}
