package org.basic.comp.base;

import java.awt.Component;

import javax.swing.Icon;

import org.basic.comp.abst.AddContentAction;
import org.basic.comp.adapter.HakAksesListener;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.listener.MasterInterface;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;
import org.noos.xing.yasaf.view.ViewContext;

import com.global.App;
import com.praktikum.comp.impl.master.HakAksM;

public class Komponent {
	private MasterInterface widgetTop;
	private Component component;
	private AddContentAction add;
	private ViewContextAction view;
	private ToolWindowManager toolWindowManager;
	private HakAksesListener hakAksesListener;
	private String typeMaster;
	private WindowInterfaces window;
	
	
	private String title;
	private char shortCut;
//	private String urlIcon;
//	private String urlIcon32;
	private Icon icon16;
	private Icon icon32;
	
	
	public Komponent() {
		super();
	}
	
	public Komponent(WindowInterfaces window, String typeMaster, String title, char shortCut,
			Icon icon16,Icon icon32) {
		super();
		this.window=window;
		this.typeMaster = typeMaster;
		this.setHakAksesListener((HakAksesListener) widgetTop);
		this.setTitle(title);
		this.shortCut = shortCut;
//		this.setUrlIcon(urlIcon);
//		this.setUrlIcon32(urlIcon32);
		this.toolWindowManager=window.getToolWindowManager();
		this.icon16=icon16;
		this.icon32=icon32;
		
//		widgetTop.build(db);
//		component=widgetTop.getPanel();
		
		
		setAdd(new AddContentAction(this));
	}

	public MasterInterface getWidgetTop() {
		return widgetTop;
	}
	public void setMaster(MasterInterface widgetTop) {
		this.widgetTop = widgetTop;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public ViewContextAction getView() {
		return view;
	}
	public void setView( Object key, ViewContext myDoggySetContext) {
		this.view = new ViewContextAction(
				getTitle(), icon16,
				myDoggySetContext, key);
	}
	
//	public AddContentAction factoryContentAction() {
//		return new AddContentAction(this);
//	}

	public AddContentAction getAdd() {
		return add;
	}

	public void setAdd(AddContentAction add) {
		this.add = add;
	}

	public HakAksesListener getHakAksesListener() {
		return hakAksesListener;
	}

	public void setHakAksesListener(HakAksesListener hakAksesListener) {
		this.hakAksesListener = hakAksesListener;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

//	public String getIcon() {
//		return urlIcon;
//	}
//
//	public void setIcon(String icon) {
//		this.urlIcon = icon;
//	}
//
//	public String getIcon32() {
//		return urlIcon32;
//	}
//
//	public void setIcon32(String icon32) {
//		this.urlIcon32 = icon32;
//	}

	
	
	public Icon getIcon16() {
		return icon16;
	}

	public void setIcon16(Icon icon16) {
		this.icon16 = icon16;
	}

	public Icon getIcon32() {
		return icon32;
	}

	public void setIcon32(Icon icon32) {
		this.icon32 = icon32;
	}

	public String getTypeMaster() {
		return typeMaster;
	}

	public WindowInterfaces getWindow() {
		return window;
	}

	public void setWindow(WindowInterfaces window) {
		this.window = window;
	}

	public char getShortCut() {
		return shortCut;
	}

	public void setShortCut(char shortCut) {
		this.shortCut = shortCut;
	}

//	public String getUrlIcon() {
//		return urlIcon;
//	}
//
//	public void setUrlIcon(String urlIcon) {
//		this.urlIcon = urlIcon;
//	}
//
//	public String getUrlIcon32() {
//		return urlIcon32;
//	}
//
//	public void setUrlIcon32(String urlIcon32) {
//		this.urlIcon32 = urlIcon32;
//	}

	public void setWidgetTop(MasterInterface widgetTop) {
		this.widgetTop = widgetTop;
	}

	public void setView(ViewContextAction view) {
		this.view = view;
	}

	public void setTypeMaster(String typeMaster) {
		this.typeMaster = typeMaster;
	}

	public ToolWindowManager getToolWindowManager() {
		return toolWindowManager;
	}

	public void setToolWindowManager(ToolWindowManager toolWindowManager) {
		this.toolWindowManager = toolWindowManager;
	}

//	public Icon getIcon() {
//		return icon;
//	}
//
//	public void setIcon(Icon icon) {
//		this.icon = icon;
//	}

	
	public Komponent createComponent(Komponent komponent){
	    String type=komponent.getTypeMaster();
		if (type.equals(Window.HAK_AKSES)) {
			komponent.setWidgetTop(new HakAksM());
		}
		
		
		
		if (komponent.getWidgetTop()!=null) {
			//komponent.getWidgetTop().getChangeStateActions().add(window.getMenu());
			komponent.getWidgetTop().build();
			komponent.setComponent(komponent.getWidgetTop().getPanel());
		}
		return komponent;
	}
	
}
