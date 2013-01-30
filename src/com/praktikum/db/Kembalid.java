package com.praktikum.db;

import javax.persistence.*;

import com.basic.db.IdAdapter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the kembali database table.
 * 
 */
@Entity
public class Kembalid extends IdAdapter {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="kembalid_id")
	private long kembalidId;

	private BigDecimal denda;

	@Column(name="waktu_lebih")
	private BigDecimal waktuLebih;

	//bi-directional many-to-one association to Kembali
    @ManyToOne
	@JoinColumn(name="kembali_id")
	private Kembali kembali;
    
    @OneToOne
	@JoinColumn(name="sewad_id")
	private Sewad sewad;


    public Kembalid() {
    }

	public Kembalid(BigDecimal denda, BigDecimal waktuLebih, 
			Sewad sewad) {
		super();
		this.denda = denda;
		this.waktuLebih = waktuLebih;
		this.sewad = sewad;
	}

	public long getKembalidId() {
		return this.kembalidId;
	}

	public void setKembalidId(long kembalidId) {
		this.kembalidId = kembalidId;
	}

	public BigDecimal getDenda() {
		return this.denda;
	}

	public void setDenda(BigDecimal denda) {
		this.denda = denda;
	}


	public BigDecimal getWaktuLebih() {
		return this.waktuLebih;
	}

	public void setWaktuLebih(BigDecimal waktuLebih) {
		this.waktuLebih = waktuLebih;
	}


	@Override
	public void setId(Long id) {
		setKembalidId(id);
	}

	public Kembali getKembali() {
		return kembali;
	}

	public void setKembali(Kembali kembali) {
		this.kembali = kembali;
	}

	public Sewad getSewad() {
		return sewad;
	}

	public void setSewad(Sewad sewad) {
		this.sewad = sewad;
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