package com.basic.comp.model;

import java.util.List;

import org.jdesktop.swingx.treetable.TreeTableNode;

public class DefaultTreeTableModel extends org.jdesktop.swingx.treetable.DefaultTreeTableModel{
	
	public DefaultTreeTableModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DefaultTreeTableModel(TreeTableNode root, List<?> columnNames) {
		super(root, columnNames);
		// TODO Auto-generated constructor stub
	}

	public DefaultTreeTableModel(TreeTableNode root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 1) {
			return Boolean.class;
		}
		return super.getColumnClass(column);
	}
}
