package org.basic.comp.base;
import java.awt.Component;
import java.util.List;

import org.basic.comp.adapter.WindowInterfaces;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.yasaf.plaf.view.MapViewContext;

public class Context  extends MapViewContext {

	public enum ActionKey {
		NEST_TOOLMANAGER,
	}



	protected ToolWindowManager toolWindowManager;
	
	protected WindowInterfaces window;

	

	public Context(final WindowInterfaces window, ToolWindowManager toolWindowManager,
			final Component parentComponent) {
		this.window=window;
		this.toolWindowManager=toolWindowManager;
		List<Komponent> komponents=window.getKomponents();
		for (Komponent komponent : komponents) {
			addViewContextChangeListener(komponent.getTypeMaster()	, komponent.getAdd());
		}
//		addViewContextChangeListener(UsrMaster.class, window.getUsrAca());
//		addViewContextChangeListener(KandangMaster.class, window.getKandangAca());
		addViewContextChangeListener(ImagePanel.class, window.getWelcomeAca());
		

	}

	@Override
	public Object put(Object key, Object value) {
		return super.put(key, value);
	}
}
