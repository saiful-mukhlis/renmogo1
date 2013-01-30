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
public class Sewa extends IdAdapter  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sewa_id")
	private long sewaId;

	private String code;


    @Temporal( TemporalType.DATE)
	@Column(name="tgl_trx")
	private Date tglTrx;

	//bi-directional many-to-one association to Kembali
	@OneToMany(mappedBy="sewa")
	private Set<Kembali> kembalis;
	
	//bi-directional many-to-one association to Sewad
		@OneToMany(mappedBy="sewa")
		@Cascade(CascadeType.REMOVE)
		private Set<Sewad> sewads;

	//bi-directional many-to-one association to Pelanggan
    @ManyToOne
	@JoinColumn(name="pelanggan_id")
	private Pelanggan pelanggan;

	//bi-directional many-to-one association to Pegawai
    @ManyToOne
	@JoinColumn(name="pegawai_id")
	private Pegawai pegawai;
    
    private int status;
    
    private BigDecimal total;

    public Sewa() {
    }

	public Sewa(String code, Date tglTrx, 
			Pelanggan pelanggan, Pegawai pegawai, int status, BigDecimal total) {
		super();
		this.code = code;
		this.tglTrx = tglTrx;
		this.pelanggan = pelanggan;
		this.pegawai = pegawai;
		this.status = status;
		this.total=total;
	}

	public long getSewaId() {
		return this.sewaId;
	}

	public void setSewaId(long sewaId) {
		this.sewaId = sewaId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}




	public Date getTglTrx() {
		return tglTrx;
	}

	public void setTglTrx(Date tglTrx) {
		this.tglTrx = tglTrx;
	}

	public Set<Kembali> getKembalis() {
		return this.kembalis;
	}

	public void setKembalis(Set<Kembali> kembalis) {
		this.kembalis = kembalis;
	}
	
	public Pelanggan getPelanggan() {
		return this.pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
	}
	
	public Pegawai getPegawai() {
		return this.pegawai;
	}

	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}

	@Override
	public void setId(Long id) {
		setSewaId(id);
	}


	public Set<Sewad> getSewads() {
		return sewads;
	}

	public void setSewads(Set<Sewad> sewads) {
		this.sewads = sewads;
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
		return getCode();
	}
	
	
}