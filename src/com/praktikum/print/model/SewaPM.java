package com.praktikum.print.model;

import com.global.App;
import com.praktikum.db.Sewa;
import com.praktikum.db.Sewad;

public class SewaPM {
	private String no;
	private String tgl;
	private String pegawai;
	private String pelanggan;
	private String mobil;
	private String start;
	private String end;
	private String jml;
	private String harga;
	private String total;
	
	

	public SewaPM( Sewa s) {
		super();
		this.no = s.getCode();
		this.tgl = App.dateFormat.format(s.getTglTrx());
		this.pegawai = s.getPegawai().getNama();
		this.pelanggan = s.getPelanggan().getNama();
		this.mobil = "";
		this.start = "";
		this.end = "";
		this.jml = "";
		this.harga = "";
		if (s.getTotal()==null) {
			this.total="";
		}else{
			this.total = App.paymentFormat2.format(s.getTotal().doubleValue());
		}
	}
	public SewaPM( Sewad s) {
		super();
		this.no = s.getNumber()+"";
		this.tgl = "";
		this.pegawai = "";
		this.pelanggan = "";
		this.mobil = s.getMobil().getTypeMobil().getNama()+"  "+s.getMobil().getCode();
		this.start = App.dateTimeFormat.format(s.getWaktuStart());
		this.end = App.dateTimeFormat.format(s.getWaktuEnd());
		this.jml = s.getJumlahWaktu()+"";
		this.harga = App.paymentFormat2.format(s.getMobil().getTypeMobil().getHarga().doubleValue());
		this.total = App.paymentFormat2.format(s.getTotal().doubleValue());
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTgl() {
		return tgl;
	}

	public void setTgl(String tgl) {
		this.tgl = tgl;
	}

	public String getPegawai() {
		return pegawai;
	}

	public void setPegawai(String pegawai) {
		this.pegawai = pegawai;
	}

	public String getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(String pelanggan) {
		this.pelanggan = pelanggan;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getJml() {
		return jml;
	}

	public void setJml(String jml) {
		this.jml = jml;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
