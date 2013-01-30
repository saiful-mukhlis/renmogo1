package com.praktikum.db;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.basic.db.IdAdapter;


/**
 * The persistent class for the mobil database table.
 * 
 */
@Entity
public class Mobil extends IdAdapter implements Cloneable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mobil_id")
	private long mobilId;

	private String code;

	private String ket;

	private int status;

	//bi-directional many-to-one association to TypeMobil
    @ManyToOne
	@JoinColumn(name="type_mobil_id")
	private TypeMobil typeMobil;
    
  //bi-directional many-to-one association to Sewa
  	@OneToMany(mappedBy="mobil")
  	@Cascade(CascadeType.REMOVE)
  	private Set<Sewad> sewads;

    public Mobil() {
    }

	public Mobil( String code, String ket, int status,
			TypeMobil typeMobil) {
		super();
		this.code = code;
		this.ket = ket;
		this.status = status;
		this.typeMobil = typeMobil;
	}

	public long getMobilId() {
		return this.mobilId;
	}

	public void setMobilId(long mobilId) {
		this.mobilId = mobilId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKet() {
		return this.ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TypeMobil getTypeMobil() {
		return this.typeMobil;
	}

	public void setTypeMobil(TypeMobil typeMobil) {
		this.typeMobil = typeMobil;
	}
	

	@Override
	public void setId(Long id) {
		setMobilId(id);
	}

	@Override
	public Mobil clone() throws CloneNotSupportedException {
		return (Mobil) super.clone();
	}

	@Override
	public String toString() {
		return code;
	}
	
	
}