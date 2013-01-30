package com.praktikum.db;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;




import com.basic.db.IdAdapter;
import com.global.App;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the type_mobil database table.
 * 
 */
@Entity
@Table(name="type_mobil")
public class TypeMobil extends IdAdapter implements Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="type_mobil_id")
	private long typeMobilId;

	private String code;

	private BigDecimal denda;
	
	private BigDecimal harga;

	private int jumlah;

	@Column(name="jumlah_tersedia")
	private int jumlahTersedia;


	private String ket;

	private String nama;

	//bi-directional many-to-one association to Mobil
	@OneToMany(mappedBy="typeMobil")
	@Cascade(CascadeType.REMOVE)
	private Set<Mobil> mobils;

    public TypeMobil() {
    }

	public long getTypeMobilId() {
		return this.typeMobilId;
	}

	public void setTypeMobilId(long typeMobilId) {
		this.typeMobilId = typeMobilId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDenda() {
		return this.denda;
	}

	public String getDendaToString() {
		if (denda==null) {
			return "";
		}
		return App.paymentFormat2.format(this.denda.doubleValue());
	}
	public String getHargaToString() {
		if (harga==null) {
			return "";
		}
		return App.paymentFormat2.format(this.harga.doubleValue());
	}
	public void setDenda(BigDecimal denda) {
		this.denda = denda;
	}

	public int getJumlah() {
		return this.jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public int getJumlahTersedia() {
		return this.jumlahTersedia;
	}

	public void setJumlahTersedia(int jumlahTersedia) {
		this.jumlahTersedia = jumlahTersedia;
	}

	public String getKet() {
		return this.ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Set<Mobil> getMobils() {
		return this.mobils;
	}

	public void setMobils(Set<Mobil> mobils) {
		this.mobils = mobils;
	}

	@Override
	public void setId(Long id) {
		setTypeMobilId(id);
	}

	@Override
	public TypeMobil clone() throws CloneNotSupportedException {
		return (TypeMobil) super.clone();
	}

	@Override
	public String toString() {
		return nama;
	}

	public BigDecimal getHarga() {
		return harga;
	}

	public void setHarga(BigDecimal harga) {
		this.harga = harga;
	}
	
}