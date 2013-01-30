package com.global;

import com.praktikum.comp.impl.master.HakAksM;
import com.praktikum.comp.impl.master.LaporanM;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.comp.impl.master.PelangganM;
import com.praktikum.comp.impl.master.SewaM;
import com.praktikum.comp.impl.master.TypeMobilM;
import com.praktikum.db.HakAks;
import com.praktikum.db.Pegawai;



/**
 * Class ini untuk menampung session dari semua setting dari user
 * 
 * @author toyib
 */
public class DataUser {
	
	public static boolean ROOT_=true;
	
	public static int ROOT=0;
	public static int HAK_AKSES_VIEW=1;
	public static int HAK_AKSES_ADD=2;
	public static int HAK_AKSES_EDIT=3;
	public static int HAK_AKSES_HAPUS=4;
	
	
	public static int USR_VIEW=5;
	public static int USR_EDIT=6;
	public static int USR_ADD=7;
	public static int USR_DEL=8;
	
	
	public static int PELANGGAN_VIEW=9;
	public static int PELANGGAN_EDIT=10;
	public static int PELANGGAN_ADD=11;
	public static int PELANGGAN_DEL=12;
	
	public static int TYPE_MOBIL_VIEW=9;
	public static int TYPE_MOBIL_EDIT=10;
	public static int TYPE_MOBIL_ADD=11;
	public static int TYPE_MOBIL_DEL=12;
	
	public static int SEWA_VIEW=13;
	public static int SEWA_ADD=14;
	public static int SEWA_DEL=15;
	
	public static int LAP_VIEW=16;
	
	
	
	public static boolean getAkses(int id) {
		if (getGrp() != null ) {
			String kunci = getGrp().getHakAkses();
			if (kunci!=null&&kunci.indexOf("x" + id + "x") != -1) {
				return true;
			}
		}
		return false;

	}
	
	public static void setAkses(){
		
		ROOT_=getAkses(ROOT);
		HakAksM.VIEW=getAkses(HAK_AKSES_VIEW);
		HakAksM.ADD=getAkses(HAK_AKSES_ADD);
		HakAksM.EDIT=getAkses(HAK_AKSES_EDIT);
		HakAksM.DEL=getAkses(HAK_AKSES_HAPUS);
		
		PegawaiM.VIEW=getAkses(USR_VIEW);
		PegawaiM.EDIT=getAkses(USR_EDIT);
		PegawaiM.ADD=getAkses(USR_ADD);
		PegawaiM.DEL=getAkses(USR_DEL);
		
		PelangganM.VIEW=getAkses(PELANGGAN_VIEW);
		PelangganM.EDIT=getAkses(PELANGGAN_EDIT);
		PelangganM.ADD=getAkses(PELANGGAN_ADD);
		PelangganM.DEL=getAkses(PELANGGAN_DEL);
		
		
		TypeMobilM.VIEW=getAkses(TYPE_MOBIL_VIEW);
		TypeMobilM.EDIT=getAkses(TYPE_MOBIL_EDIT);
		TypeMobilM.ADD=getAkses(TYPE_MOBIL_ADD);
		TypeMobilM.DEL=getAkses(TYPE_MOBIL_DEL);
		
		SewaM.VIEW=getAkses(SEWA_VIEW);
		SewaM.ADD=getAkses(SEWA_ADD);
		SewaM.DEL=getAkses(SEWA_DEL);
		
		
		LaporanM.VIEW=getAkses(LAP_VIEW);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static HakAks hakAks;

	public static HakAks getGrp() {
		return hakAks;
	}
	
	
	/**
	 * User yang login
	 */
	private static Pegawai usr;

	/**
	 * Menggambil data user yang login
	 * 
	 * @return User
	 */
	public static synchronized Pegawai getUsr() {
		return usr;
	}
	
	public static synchronized Pegawai setUsr(Pegawai usra) {
		usr=usra;
		return usr;
	}
	public static synchronized HakAks setGrp(HakAks grpa) {
		hakAks=grpa;
		return hakAks;
	}

	
}
