package com.basic.db;

import javax.persistence.*;


/**
 * The persistent class for the tableid database table.
 * 
 */
@Entity
public class Tableid  extends IdAdapter {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nama_table")
	private String namaTable;

	private int incr;

	private long no;

	private String pref;

	@Column(name="start_no")
	private long start;



	public Tableid(String namaTable, int incr, long no, String pref, long start) {
		super();
		this.namaTable = namaTable;
		this.incr = incr;
		this.no = no;
		this.pref = pref;
		this.start = start;
	}

	public Tableid() {
    }

	public String getNamaTable() {
		return this.namaTable;
	}

	public void setNamaTable(String namaTable) {
		this.namaTable = namaTable;
	}

	public int getIncr() {
		return this.incr;
	}

	public void setIncr(int incr) {
		this.incr = incr;
	}

	public long getNo() {
		return this.no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getPref() {
		return this.pref;
	}

	public void setPref(String pref) {
		this.pref = pref;
	}

	public long getStart() {
		return this.start;
	}

	public void setStart(long start) {
		this.start = start;
	}

}