package org.basic.comp.model;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

public class DefaultTreeNodeModel extends DefaultMutableTreeTableNode {

	protected int columnCount = 1;

	public DefaultTreeNodeModel(Object userObject) {
		super(userObject);
	}
	@Override
	public int getColumnCount() {
		return columnCount;
	}
	public boolean isEditable(int column) {
		return false;
	}

}
