package com.bmb.util;

public enum Db {
	USR("daoUsr"), HAK_AKS("hakAksDao"), BOS("bosDao"), TABLEID("tableidDao"), PEGAWAI(
			"pegawaiDao"), PELANGGAN("pelangganDao"), TYPE_MOBIL("typeMobilDao"),MOBIL("mobilDao"),SEWA("sewaDao"), 
			SEWAD("sewadDao"),KEMBALI("kembaliDao"),KEMBALID("kembalidDao"),;

	private String cls;

	private Db(String n) {
		cls = n;
	}

	public Object get() {
		return S.getBean(cls);
	}
}
