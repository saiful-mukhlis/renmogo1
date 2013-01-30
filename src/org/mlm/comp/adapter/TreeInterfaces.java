package org.mlm.comp.adapter;

import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.listener.WidgetInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;


public interface TreeInterfaces extends ListInterfaces {

	TextFieldSearch getFieldSearch();

	SplitButton getItemSearch();

	void addWidget(WidgetInterface widget);

	void aksiDelete(ODatabaseDocumentTx db);

	
}
