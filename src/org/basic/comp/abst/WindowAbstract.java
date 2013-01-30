package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.basic.comp.adapter.HakAksesListener;
import org.basic.comp.adapter.MenuAdapter;
import org.basic.comp.adapter.MenuInterface;
import org.basic.comp.adapter.ToolbarAdapter;
import org.basic.comp.adapter.WindowAdapter;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.base.BosOne;
import org.basic.comp.base.PanelBottom;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManagerListener;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.event.ContentManagerEvent;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.noos.xing.mydoggy.plaf.ui.cmp.ExtendedTableLayout;
import org.noos.xing.yasaf.view.ViewContext;

import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;

public abstract class WindowAbstract extends WindowAdapter{
	protected ToolbarAdapter toolbar;
	protected MenuAdapter menu;
	protected ToolWindowManager toolWindowManager;
	protected ViewContext myDoggySetContext;
	protected JFrame frame;
	protected PanelBottom panelBottom;
	
	protected JProgressBar progressBar;
	protected boolean useBar=false;
	protected BosOne bosOne;
	
	protected List<HakAksesListener> cangeHakAkses = new ArrayList<HakAksesListener>();
	
	protected String idSelected="";
	
	@Override
	public void build() {
		init();
		
	}
	public void init(){
		setValueBar(75);
		seta();
		setValueBar(80);
		initFrame();
		initToolWindowManager();
		buildMaster();
		initContext();
		buildActions();
		initMenu();
		initToolbar();
		
		setLocationFrame();
		setAvailableWindow();
		setValueBar(90);
		manageWelcome();
		showWelcome();
		setValueBar(99);
		showFrame();
	}
	public void manageWelcome() {
		toolWindowManager.getContentManager().addContentManagerListener(
				new ContentManagerListener() {

					@Override
					public void contentSelected(ContentManagerEvent arg0) {
						// TODO Auto-generated method stub
						idSelected=arg0.getContent().getId();
						if (getKomponentSeledcted()!=null) {
							getKomponentSeledcted().getWidgetTop().requestDefaultSelected();
						}
						
					}

					@Override
					public void contentRemoved(ContentManagerEvent arg0) {
						// TODO Auto-generated method stub
						if (arg0.getContent().getId().equals(idSelected)) {
							idSelected="";
						}
						if (toolWindowManager.getContentManager()
								.getContentCount() == 0) {
							showWelcome();
						}
					}

					@Override
					public void contentAdded(ContentManagerEvent arg0) {
						// TODO Auto-generated method stub
						idSelected=arg0.getContent().getId();
						if (toolWindowManager.getContentManager()
								.getContentCount() > 1) {
							try {
								Content c = toolWindowManager.getContentManager()
								.getContentByComponent(getComponentWelcome());
						toolWindowManager.getContentManager()
								.removeContent(c);
							} catch (Exception e) {
								// TODO: handle exception
							}
							
						}
						
					}
				});
	}
	public void setAvailableWindow() {
		for (ToolWindow window : toolWindowManager.getToolWindows()) {
			window.setAvailable(true);
		}
	}
	public void initToolWindowManager() {
		JPanel p = new JPanel();
		p.setLayout(new ExtendedTableLayout(new double[][] { { 0, -1, 0 },
				{ 0, -1, 0 } }));

		final MyDoggyToolWindowManager myDoggyToolWindowManager = new MyDoggyToolWindowManager();
		toolWindowManager = myDoggyToolWindowManager;
		
		p.add(myDoggyToolWindowManager, "1,1,");
		frame.add(p, BorderLayout.CENTER);

	}
	
	
	public void setValueBar(int value){
		if (useBar) {
			progressBar.setValue(value);
		}
	}
	
	public void initFrame() {
//		frame = new JFrame(LWindow.title+" "+bosOne.getLoc());
		frame = new JFrame(LWindow.title);
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setIconImage(App.getImage(LApp.iconApp16).getImage());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelBottom=new PanelBottom();
		panelBottom.build();
		frame.getContentPane().add(panelBottom.getPanel(), BorderLayout.SOUTH);
	}
	
	public void setLocationFrame() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenResolution = toolkit.getScreenResolution();
		float zoom = screenResolution / 72.0F;

		int height = (int) (550.0F * zoom);
		if (height > screenSize.getHeight()) {
			height = (int) screenSize.getHeight();
		}
		int width = (int) (750.0F * zoom);
		if (width > screenSize.getWidth()) {
			width = (int) screenSize.getWidth();
		}

		Dimension dimension = new Dimension(width, height);
		frame.setSize(dimension);
		frame.setLocation((screenSize.width - width) / 2,
				(screenSize.height - height) / 2);
	}
	public void showFrame() {
		frame.setVisible(true);
	}
	
	public AddContentAction factoryContentAction(String title, Icon icon,
			Component component, int shortCut) {
		return new AddContentAction(toolWindowManager,
				title, title,
				icon, component, title, shortCut);
	}
	public ToolbarAdapter getToolbar() {
		return toolbar;
	}
	public void setToolbar(ToolbarAdapter toolbar) {
		this.toolbar = toolbar;
	}
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	public ToolWindowManager getToolWindowManager() {
		return toolWindowManager;
	}
	public void setToolWindowManager(ToolWindowManager toolWindowManager) {
		this.toolWindowManager = toolWindowManager;
	}
	public String getIdSelected() {
		return idSelected;
	}
	public void setIdSelected(String idSelected) {
		this.idSelected = idSelected;
	}
	public MenuAdapter getMenu() {
		return menu;
	}
	public void setMenu(MenuAdapter menu) {
		this.menu = menu;
	}
	public PanelBottom getPanelBottom() {
		return panelBottom;
	}
	public void setPanelBottom(PanelBottom panelBottom) {
		this.panelBottom = panelBottom;
	}
	
	
	
}
