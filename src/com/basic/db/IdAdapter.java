package com.basic.db;

import java.io.Serializable;
import javax.persistence.*;

import org.bmb.dao.adapter.IdListener;


/**
 * The persistent class for the pegawai database table.
 * 
 */

public class IdAdapter implements Serializable, IdListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCode(String kode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	
}