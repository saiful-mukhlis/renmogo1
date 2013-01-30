package com.basic.db;

import javax.persistence.*;

/**
 * The persistent class for the pegawai database table.
 * 
 */
@Entity
public class Bos extends IdAdapter {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bos_id")
	private long bosId;

	private String name;

	private int jml;

	public Bos() {
	}

	public Bos(int bosId, String name, int jml) {
		super();
		this.bosId = bosId;
		this.name = name;
		this.jml = jml;
	}



	public long getBosId() {
		return bosId;
	}

	public void setBosId(long bosId) {
		this.bosId = bosId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getJml() {
		return jml;
	}

	public void setJml(int jml) {
		this.jml = jml;
	}


}