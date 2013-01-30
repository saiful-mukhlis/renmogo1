package com.praktikum.db;

import javax.persistence.*;
import org.bmb.dao.adapter.IdListener;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;


/**
 * The persistent class for the hak_akses database table.
 * 
 */
@Entity
@Table(name="hak_akses")
public class HakAks implements IdListener, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hak_akses_id")
	private int hakAksesId;

	private String code;

	@Column(name="hak_akses")
	private String hakAkses;

	private String nama;

	//bi-directional many-to-one association to Pegawai
	@OneToMany(mappedBy="hakAks")
	@Cascade(CascadeType.REMOVE)
	private Set<Pegawai> pegawais;

	
	
    public HakAks(String code, String hakAkses, String nama ) {
		super();
		this.code = code;
		this.hakAkses = hakAkses;
		this.nama = nama;
	}

	public HakAks() {
    }

	public int getHakAksesId() {
		return this.hakAksesId;
	}

	public void setHakAksesId(int hakAksesId) {
		this.hakAksesId = hakAksesId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHakAkses() {
		return this.hakAkses;
	}

	public void setHakAkses(String hakAkses) {
		this.hakAkses = hakAkses;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Set<Pegawai> getPegawais() {
		return this.pegawais;
	}

	public void setPegawais(Set<Pegawai> pegawais) {
		this.pegawais = pegawais;
	}

	@Override
	public void setId(Long id) {
		setHakAksesId(id.intValue());
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((hakAkses == null) ? 0 : hakAkses.hashCode());
		result = prime * result + hakAksesId;
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HakAks other = (HakAks) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (hakAkses == null) {
			if (other.hakAkses != null)
				return false;
		} else if (!hakAkses.equals(other.hakAkses))
			return false;
		if (hakAksesId != other.hakAksesId)
			return false;
		if (nama == null) {
			if (other.nama != null)
				return false;
		} else if (!nama.equals(other.nama))
			return false;
		return true;
	}

	@Override
	public HakAks clone() throws CloneNotSupportedException {
		return (HakAks) super.clone();
	}

	@Override
	public String toString() {
		return getNama();
	}


	
}