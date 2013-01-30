package org.basic.comp.listener;

import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

import com.orientechnologies.orient.core.record.impl.ODocument;

public interface ODocumentToStringNodeInterface {
	public ODocument getO();

	public void setO(ODocument userObject);
	public MutableTreeTableNode getNode();
	public void setNode(MutableTreeTableNode node);
}
