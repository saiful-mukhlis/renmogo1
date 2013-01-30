package com.global;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.basic.comp.base.NumberRenderer;

import com.basic.comp.impl.NamaBulan;
import com.basic.dao.BosDao;
import com.basic.dao.TableidDao;
import com.basic.lang.Lang;
import com.bmb.util.Db;
import com.praktikum.dao.HakAksDao;
import com.praktikum.dao.KembaliDao;
import com.praktikum.dao.KembalidDao;
import com.praktikum.dao.MobilDao;
import com.praktikum.dao.PegawaiDao;
import com.praktikum.dao.PelangganDao;
import com.praktikum.dao.SewaDao;
import com.praktikum.dao.SewadDao;
import com.praktikum.dao.TypeMobilDao;

/**
 * @author toyib Class ini di gunalan untuk variable yang di access secara
 *         global
 */
public class App {
	private App() {
	}

	private static Double w;
	private static Double h;

	public static Double getW() {
		if (w == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			w = screenSize.getWidth();
		}
		return w;
	}

	public static Double getH() {
		if (h == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			h = screenSize.getHeight();
		}
		return h;
	}

	public static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	public static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	public static DateFormat dateTimeFormat = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm:ss");
	public static DecimalFormat paymentFormat = new DecimalFormat(
			"###,###,##0.##");
	public static DecimalFormat paymentFormat0 = new DecimalFormat(
			"#########.##");
	public static DecimalFormat paymentFormat1 = new DecimalFormat(
			"###,###,##0.0");
	public static DecimalFormat paymentFormat2 = new DecimalFormat(
			"###,###,##0.00");
	public static NumberRenderer tablePayment = new NumberRenderer(
			App.paymentFormat2);
	public static Color whiteSmoke = new Color(253, 253, 253);

	public static Color selected = UIManager.getDefaults().getColor(
			"List.selectionBackground");// new Color(0, 136, 204);
	public static Color aqua = new Color(0, 255, 255);
	public static Color gainsboro = new Color(220, 220, 220);
	public static Color blacksmoot = new Color(176, 176, 176);

	public static Border border = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(App.gainsboro, 1),
			BorderFactory.createEmptyBorder(3, 3, 3, 3));
	public static Border borderSelected = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(App.selected, 1),
			BorderFactory.createEmptyBorder(3, 3, 3, 3));
	public static Border borderWhite = BorderFactory
			.createLineBorder(Color.WHITE);
	public static Border borderBlack = BorderFactory
			.createLineBorder(App.blacksmoot);
	public static Border borderBlackBottom5555 = BorderFactory
			.createCompoundBorder(
					BorderFactory.createMatteBorder(0, 0, 1, 0, App.blacksmoot),
					BorderFactory.createEmptyBorder(5, 5, 5, 5));
	public static Border borderBlackBottom3000 = BorderFactory
			.createCompoundBorder(
					BorderFactory.createMatteBorder(0, 0, 1, 0, App.blacksmoot),
					BorderFactory.createEmptyBorder(3, 0, 0, 0));

	public static Border borderBlackKananKiri = BorderFactory
			.createMatteBorder(0, 1, 0, 1, App.blacksmoot);

	public static String[] bln = { "Januari", "Februari", "Maret", "April",
			"Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
			"November", "Desember" };

	public static NamaBulan[] getBulan() {
		NamaBulan nb[] = new NamaBulan[12];
		for (int i = 0; i < 12; i++) {
			nb[i] = new NamaBulan(i, bln[i]);
		}
		return nb;
	}

	private static Lang lang;

	public static Lang getLang() {
		if (lang == null) {
			lang = new Lang();
		}
		return lang;
	}

	public static String getT(String category, String text) {
		return getLang().getText("com.bmb.app.lang." + category, text);
	}

	public static String getT(String text) {
		return getLang().getText("com.basic.lang.app", text);
	}

	public static Icon getIcon(String nameIcon) {
		// App.info(nameIcon);
		return new ImageIcon(App.class.getResource(nameIcon));
	}

	public static ImageIcon getImage(String nameIcon) {
		return new ImageIcon(App.class.getResource(nameIcon));
	}

	public static void showErrorEmptySelect(String namaField) {
		JOptionPane.showMessageDialog(null, namaField + " belum di pilih");
	}

	public static void showErrorFieldEmpty(String namaField) {
		JOptionPane.showMessageDialog(null, namaField + " belum di isi");
	}

	public static void showErrorNotValid(String namaField) {
		JOptionPane.showMessageDialog(null, namaField + " tidak valid");
	}

	public static void showErrorNilaiHarusDiAtasNol(String namaField) {
		JOptionPane.showMessageDialog(null, namaField
				+ " harus lebih besar dari nol");
	}

	public static void showSaveOk() {
		JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
	}

	public static void showErrorJumlahTidakFalid() {
		JOptionPane.showMessageDialog(null,
				"Jumlah tidak valid. Jumlah tidak boleh kurang dari 1.");
	}

	public static void showPelangganTidakDapatDiHapus() {
		JOptionPane.showMessageDialog(null,
				"Pelanggan masih punya Piutang.\nData tidak dapat di Hapus.");
	}

	public static void showErrorPasswordTidakSamadenganKonfirmasi() {
		JOptionPane.showMessageDialog(null,
				"Password dan Ketik Ulang tidak sesuai");
	}

	public static void showErrorUsernameTidakTerdaftar() {
		JOptionPane.showMessageDialog(null, "Username tidak terdaftar");
	}

	public static void showErrorPasswordSalah() {
		JOptionPane.showMessageDialog(null,
				"Silahkan ulangi, password yang Anda inputkan tidak sesuai.");
	}

	public static void showErrorSN() {
		JOptionPane.showMessageDialog(null,
				"Kode Aktifasi yang Anda masukkan tidak sesuai.");
	}

	public static void showErrorDataSudahAda(String namaField) {
		JOptionPane.showMessageDialog(null, namaField + " sudah terdaftar");
	}

	public static void showErrSementara(String x) {
		JOptionPane.showMessageDialog(null, x);
	}

	public static void printErr(Exception e) {
		e.printStackTrace();
	}

	public static void info(String note) {
		System.out.println(note);
	}

	public static TableidDao getTableidDao(){
		return (TableidDao) Db.TABLEID.get();
	}
	public static BosDao getBosDao(){
		return (BosDao) Db.BOS.get();
	}
	public static SewaDao getSewaDao(){
		return (SewaDao) Db.SEWA.get();
	}
	public static SewadDao getSewadDao(){
		return (SewadDao) Db.SEWAD.get();
	}
	public static KembaliDao getKembaliDao(){
		return (KembaliDao) Db.KEMBALI.get();
	}
	public static KembalidDao getKembalidDao(){
		return (KembalidDao) Db.KEMBALID.get();
	}
	public static PegawaiDao getPegawaiDao(){
		return (PegawaiDao) Db.PEGAWAI.get();
	}
	public static TypeMobilDao getTypeMobilDao(){
		return (TypeMobilDao) Db.TYPE_MOBIL.get();
	}
	public static PelangganDao getPelangganDao(){
		return (PelangganDao) Db.PELANGGAN.get();
	}
	public static MobilDao getMobilDao(){
		return (MobilDao) Db.MOBIL.get();
	}
	public static HakAksDao getHakAks(){
		return (HakAksDao) Db.HAK_AKS.get();
	}
}
