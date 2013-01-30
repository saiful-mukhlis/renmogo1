package org.basic.comp.model;

import org.basic.dao.adapter.DaoInterface;

import com.orientechnologies.orient.core.record.impl.ODocument;

public class ODocumentToString {
	private DaoInterface dao;
	private ODocument o;
	private String ket="";
	public ODocument getO() {
		return o;
	}

	public void setO(ODocument o) {
		this.o = o;
	}

	public String toString() {
		if (o != null) {
			if (dao.getNameFielsToString() != null) {
				String tmp = o.field(dao.getNameFielsToString());
				if (tmp != null) {
					return tmp;
				} else {
					return "";
				}
			}
		}

		return ket;
	}

	public ODocumentToString(DaoInterface dao, ODocument o) {
		super();
		this.dao = dao;
		this.o = o;
	}

	public ODocumentToString(DaoInterface dao, String ket) {
		super();
		this.dao = dao;
		this.ket = ket;
	}
	

}
