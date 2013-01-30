package com.praktikum.db;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.basic.db.IdAdapter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the kembali database table.
 * 
 */
@Entity
public class Kembali extends IdAdapter {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="kembali_id")
	private long kembaliId;
	
	private String code;

	@Column(name="total_denda")
	private BigDecimal totalDenda;

    @Temporal( TemporalType.DATE)
	@Column(name="tgl_kembali")
	private Date tglKembali;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="waktu_kembali")
	private Date waktuKembali;


	//bi-directional many-to-one association to Pegawai
    @ManyToOne
	@JoinColumn(name="pegawai_id")
	private Pegawai pegawai;

	//bi-directional many-to-one association to Sewa
    @ManyToOne
	@JoinColumn(name="sewa_id")
	private Sewa sewa;

  //bi-directional many-to-one association to Sewad
  		@OneToMany(mappedBy="kembali")
  		@Cascade(CascadeType.REMOVE)
  		private Set<Kembalid> kembalids;

    public Kembali() {
    }

	public Kembali(String code, BigDecimal totalDenda, Date tglKembali,
			Date waktuKembali, Pegawai pegawai, Sewa sewa) {
		super();
		this.code = code;
		this.totalDenda = totalDenda;
		this.tglKembali = tglKembali;
		this.waktuKembali = waktuKembali;
		this.pegawai = pegawai;
		this.sewa = sewa;
	}

	public long getKembaliId() {
		return this.kembaliId;
	}

	public void setKembaliId(long kembaliId) {
		this.kembaliId = kembaliId;
	}


	public Date getTglKembali() {
		return this.tglKembali;
	}

	public void setTglKembali(Date tglKembali) {
		this.tglKembali = tglKembali;
	}

	public Date getWaktuKembali() {
		return this.waktuKembali;
	}

	public void setWaktuKembali(Date waktuKembali) {
		this.waktuKembali = waktuKembali;
	}


	public Pegawai getPegawai() {
		return this.pegawai;
	}

	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}
	
	public Sewa getSewa() {
		return this.sewa;
	}

	public void setSewa(Sewa sewa) {
		this.sewa = sewa;
	}
	

	@Override
	public void setId(Long id) {
		setKembaliId(id);
	}

	public BigDecimal getTotalDenda() {
		return totalDenda;
	}

	public void setTotalDenda(BigDecimal totalDenda) {
		this.totalDenda = totalDenda;
	}

	public Set<Kembalid> getKembalids() {
		return kembalids;
	}

	public void setKembalids(Set<Kembalid> kembalids) {
		this.kembalids = kembalids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return getCode();
	}

	
	
}