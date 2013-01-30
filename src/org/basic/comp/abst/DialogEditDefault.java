package org.basic.comp.abst;

import org.basic.comp.listener.EditDialogInterface;
import org.basic.comp.listener.MasterInterface;

import com.basic.lang.LWindow;

public  class DialogEditDefault extends DialogDefault implements EditDialogInterface {

	protected int index;
	protected MasterInterface master;
	
	

	@Override
	public void init() {
		super.init();
		reset.setText(LWindow.RELOAD);
	}
	

	@Override
	public void actionReload() {
		load(model);
	}

	@Override
	public void setMaster(MasterInterface master) {
		this.master=master;
	}

	@Override
	public void setIndex(int index) {
		this.index=index;
	}
	
	public void afterSave(){
		listWidget.editModel(model, index);
		dispose(panel);
	}


	
}
