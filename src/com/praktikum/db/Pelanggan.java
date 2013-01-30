package com.praktikum.db;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.basic.db.IdAdapter;

import java.util.Set;


/**
 * The persistent class for the pelanggan database table.
 * 
 */
@Entity
public class Pelanggan extends IdAdapter implements Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pelanggan_id")
	private long pelangganId;

	private String alamat;

	private String code;

	private String hp;

	@Column(name="jenis_identitas")
	private String jenisIdentitas;

	private String kota;

	private String nama;

	@Column(name="no_identitas")
	private String noIdentitas;

	private int status;

	//bi-directional many-to-one association to Sewa
	@OneToMany(mappedBy="pelanggan")
	@Cascade(CascadeType.REMOVE)
	private Set<Sewa> sewas;

    public Pelanggan() {
    }

	public long getPelangganId() {
		return this.pelangganId;
	}

	public void setPelangganId(long pelangganId) {
		this.pelangganId = pelangganId;
	}

	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHp() {
		return this.hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getJenisIdentitas() {
		return this.jenisIdentitas;
	}

	public void setJenisIdentitas(String jenisIdentitas) {
		this.jenisIdentitas = jenisIdentitas;
	}

	public String getKota() {
		return this.kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoIdentitas() {
		return this.noIdentitas;
	}

	public void setNoIdentitas(String noIdentitas) {
		this.noIdentitas = noIdentitas;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Sewa> getSewas() {
		return this.sewas;
	}

	public void setSewas(Set<Sewa> sewas) {
		this.sewas = sewas;
	}

	@Override
	public void setId(Long id) {
		setPelangganId(id);
	}

	@Override
	public Pelanggan clone() throws CloneNotSupportedException {
		return (Pelanggan) super.clone();
	}
	
	
	
}