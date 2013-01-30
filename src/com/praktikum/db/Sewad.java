package com.praktikum.db;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.basic.db.IdAdapter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the sewa database table.
 * 
 */
@Entity
public class Sewad extends IdAdapter  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sewad_id")
	private long sewadId;

	@Column(name="jumlah_waktu")
	private BigDecimal jumlahWaktu;

    @Temporal( TemporalType.DATE)
	@Column(name="tgl_sewa")
	private Date tglSewa;
    
    @Temporal( TemporalType.DATE)
	@Column(name="tgl_kembali")
	private Date tglKembali;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="waktu_end")
	private Date waktuEnd;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="waktu_start")
	private Date waktuStart;


	//bi-directional many-to-one association to Mobil
    @ManyToOne
	@JoinColumn(name="mobil_id")
	private Mobil mobil;

  //bi-directional many-to-one association to Sewa
    @ManyToOne
	@JoinColumn(name="sewa_id")
	private Sewa sewa;
    
  		@OneToOne(mappedBy="sewad")
  		@Cascade(CascadeType.REMOVE)
  		private Kembalid kembalid;
    
    private int status;
    
    private BigDecimal total;

    public Sewad() {
    }




	public Sewad(BigDecimal jumlahWaktu, Date tglSewa, Date tglKembali,
			Date waktuEnd, Date waktuStart, Mobil mobil, int status, BigDecimal total) {
		super();
		this.jumlahWaktu = jumlahWaktu;
		this.tglSewa = tglSewa;
		this.tglKembali = tglKembali;
		this.waktuEnd = waktuEnd;
		this.waktuStart = waktuStart;
		this.mobil = mobil;
		this.status = status;
		this.total=total;
	}




	public BigDecimal getJumlahWaktu() {
		return this.jumlahWaktu;
	}

	public void setJumlahWaktu(BigDecimal jumlahWaktu) {
		this.jumlahWaktu = jumlahWaktu;
	}

	public Date getTglSewa() {
		return this.tglSewa;
	}

	public void setTglSewa(Date tglSewa) {
		this.tglSewa = tglSewa;
	}

	public Date getWaktuEnd() {
		return this.waktuEnd;
	}

	public void setWaktuEnd(Date waktuEnd) {
		this.waktuEnd = waktuEnd;
	}

	public Date getWaktuStart() {
		return this.waktuStart;
	}

	public void setWaktuStart(Date waktuStart) {
		this.waktuStart = waktuStart;
	}

	
	
	public Mobil getMobil() {
		return this.mobil;
	}

	public void setMobil(Mobil mobil) {
		this.mobil = mobil;
	}




	public long getSewadId() {
		return sewadId;
	}




	public void setSewadId(long sewadId) {
		this.sewadId = sewadId;
	}




	public Date getTglKembali() {
		return tglKembali;
	}




	public void setTglKembali(Date tglKembali) {
		this.tglKembali = tglKembali;
	}




	@Override
	public void setId(Long id) {
		setSewadId(id);
	}




	@Override
	public void setCode(String kode) {
	}




	public Sewa getSewa() {
		return sewa;
	}




	public void setSewa(Sewa sewa) {
		this.sewa = sewa;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public BigDecimal getTotal() {
		return total;
	}




	public void setTotal(BigDecimal total) {
		this.total = total;
	}




	@Override
	public String toString() {
		return number+"";
	}
	
	@Transient
	private int number=1;

	public int getNumber() {
		return number;
	}




	public void setNumber(int number) {
		this.number = number;
	}
	
	
}