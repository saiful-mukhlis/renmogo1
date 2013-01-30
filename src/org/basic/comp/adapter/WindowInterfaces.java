package org.basic.comp.adapter;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;

import org.basic.comp.base.Komponent;
import org.basic.comp.base.PanelBottom;
import org.basic.comp.listener.WidgetInterface;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;


public interface WindowInterfaces extends WidgetInterface {
public void initToolbar();
public void initMenu();
public MenuInterface getMenu();
public void initContext();
public void showWelcome();
public Component getComponentWelcome();
public void seta();
public void buildMaster();
public void buildActions();
public ToolWindowManager getToolWindowManager();

//public AddContentAction getUsrAca();
public AddContentAction getWelcomeAca();
//public AddContentAction getKandangAca();

public List<Komponent> getKomponents();
public HashMap<String, Komponent> getKomponentMaps();
public Komponent getKomponentSeledcted();

public ToolbarAdapter getToolbar();
public PanelBottom getPanelBottom();
public void login();

public void actionClose();
public void actionExit();
public void actionPrint();
public void actionAdd();
public void actionEdit();
public void actionDel();
public void actionView();
public void actionReload();

public void actionReg();
public void actionAbout();


public void showToolbar();

}
