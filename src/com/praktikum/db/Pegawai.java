package com.praktikum.db;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.basic.db.IdAdapter;
import java.util.Set;


/**
 * The persistent class for the pegawai database table.
 * 
 */
@Entity
public class Pegawai extends IdAdapter implements Cloneable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pegawai_id")
	private long pegawaiId;

	private String alamat;

	private String code;

	private String hp;

	@Column(name="jenis_identitas")
	private String jenisIdentitas;

	private String kota;

	private String nama;

	@Column(name="no_identitas")
	private String noIdentitas;

	private String password;

	private int status;

	private String username;

	//bi-directional many-to-one association to Kembali
	@OneToMany(mappedBy="pegawai")
	@Cascade(CascadeType.REMOVE)
	private Set<Kembali> kembalis;

	//bi-directional many-to-one association to HakAks
    @ManyToOne
	@JoinColumn(name="hak_akses_id")
	private HakAks hakAks;

	//bi-directional many-to-one association to Sewa
	@OneToMany(mappedBy="pegawai")
	@Cascade(CascadeType.REMOVE)
	private Set<Sewa> sewas;

    public Pegawai() {
    }

	public Pegawai(String alamat, String code, String hp,
			String jenisIdentitas, String kota, String nama,
			String noIdentitas, String password, int status, String username,
			 HakAks hakAks) {
		super();
		this.alamat = alamat;
		this.code = code;
		this.hp = hp;
		this.jenisIdentitas = jenisIdentitas;
		this.kota = kota;
		this.nama = nama;
		this.noIdentitas = noIdentitas;
		this.password = password;
		this.status = status;
		this.username = username;
		this.kembalis = kembalis;
		this.hakAks = hakAks;
	}

	public long getPegawaiId() {
		return this.pegawaiId;
	}

	public void setPegawaiId(long pegawaiId) {
		this.pegawaiId = pegawaiId;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Kembali> getKembalis() {
		return this.kembalis;
	}

	public void setKembalis(Set<Kembali> kembalis) {
		this.kembalis = kembalis;
	}
	
	public HakAks getHakAks() {
		return this.hakAks;
	}

	public void setHakAks(HakAks hakAks) {
		this.hakAks = hakAks;
	}
	
	public Set<Sewa> getSewas() {
		return this.sewas;
	}

	public void setSewas(Set<Sewa> sewas) {
		this.sewas = sewas;
	}

	@Override
	public void setId(Long id) {
		setPegawaiId(id);
	}
	
	@Override
	public Pegawai clone() throws CloneNotSupportedException {
		return (Pegawai) super.clone();
	}
	
}