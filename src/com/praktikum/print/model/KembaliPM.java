package com.praktikum.print.model;

import com.global.App;
import com.praktikum.db.Kembali;
import com.praktikum.db.Kembalid;

public class KembaliPM {
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
	
	

	public KembaliPM( Kembali s) {
		super();
		this.no = s.getCode();
		this.tgl = App.dateFormat.format(s.getTglKembali());
		this.pegawai = s.getPegawai().getNama();
		this.pelanggan = s.getSewa().getPelanggan().getNama();
		this.mobil = "";
		this.start = "";
		this.end = "";
		this.jml = "";
		this.harga = "";
		if (s.getTotalDenda()==null) {
			this.total="";
		}else{
			this.total = App.paymentFormat2.format(s.getTotalDenda().doubleValue());
		}
	}
	public KembaliPM( Kembalid s) {
		super();
		this.no = s.getNumber()+"";
		this.tgl = "";
		this.pegawai = "";
		this.pelanggan = "";
		this.mobil = s.getSewad().getMobil().getTypeMobil().getNama()+"  "+s.getSewad().getMobil().getCode();
		this.start = App.dateTimeFormat.format(s.getSewad().getWaktuEnd());
		this.end = App.dateTimeFormat.format(s.getKembali().getWaktuKembali());
		this.jml = s.getWaktuLebih()+"";
		this.harga = App.paymentFormat2.format(s.getSewad().getMobil().getTypeMobil().getDenda().doubleValue());
		this.total = App.paymentFormat2.format(s.getDenda().doubleValue());
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
