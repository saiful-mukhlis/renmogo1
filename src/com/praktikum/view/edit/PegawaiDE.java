package com.praktikum.view.edit;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.basic.comp.abst.DialogEditDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

import com.global.App;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.praktikum.comp.impl.master.PegawaiM;
import com.praktikum.dao.PegawaiDao;
import com.praktikum.db.HakAks;
import com.praktikum.db.Pegawai;
import com.praktikum.lang.LPegawai;



public class PegawaiDE extends DialogEditDefault {
	protected TextField code;
	protected TextField nama;
	protected TextField username;
	protected PasswordField password;
	protected PasswordField ulang;
	protected TextField jenisIdentitas;
	protected TextField noIdentitas;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField hp;
	protected ComboBox status;
	protected ComboBox hakAkses;
	protected DefaultComboBoxModel hakAksesModel;
	

	@Override
	public void load(Object o) {
		
		if (o==null) {
			code.setText("");
			nama.setText("");
			username.setText("");
			password.setText("");
			ulang.setText("");
			jenisIdentitas.setText("");
			noIdentitas.setText("");
			alamat.setText("");
			kota.setText("");
			hp.setText("");
			status.setSelectedIndex(0);
		}else if(o instanceof Pegawai){
			Pegawai model=(Pegawai) o;
			this.model=model;
			try {
				this.old=model.clone();
			} catch (CloneNotSupportedException e) {
				App.printErr(e);
			}
			code.setText(model.getCode());
			nama.setText(model.getNama());
			username.setText(model.getUsername());
			password.setText("");
			ulang.setText("");
			jenisIdentitas.setText(model.getJenisIdentitas());
			noIdentitas.setText(model.getNoIdentitas());
			alamat.setText(model.getAlamat());
			kota.setText(model.getKota());
			hp.setText(model.getHp());
			status.setSelectedIndex(model.getStatus());
			List<Object> os=App.getHakAks().getAll();
			os.add(0, App.getT("Pilih Hak Akses"));
			hakAksesModel=new DefaultComboBoxModel<Object>(os.toArray());
			hakAkses.setModel(hakAksesModel);
			hakAkses.setSelectedItem(model.getHakAks());
		}
	}

	@Override
	public void actionReset() {
		actionReload();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}



	public void initComponent() {
		code = new TextField();
		nama = new TextField();
		username = new TextField();
		password = new PasswordField();
		ulang = new PasswordField();
		jenisIdentitas = new TextField();
		noIdentitas = new TextField();
		alamat = new TextArea();
		salamat = new ScrollPane(alamat);
		kota = new TextField();
		hp = new TextField();
		status = new ComboBox(App.getPegawaiDao().getStatusData());
		List<Object> os=App.getHakAks().getAll();
		os.add(0, App.getT("Pilih Hak Akses"));
		hakAksesModel=new DefaultComboBoxModel<Object>(os.toArray());
		hakAkses=new ComboBox(hakAksesModel);
		hakAkses.setSelectedIndex(0);
	}
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, status);
		setFocusEnter(status, nama);
		setFocusEnter(nama, username);
		setFocusEnter(username, password);
		setFocusEnter(password, ulang);
		setFocusEnter(ulang, hakAkses);
		setFocusEnter(hakAkses, status);
		setFocusEnter(status, jenisIdentitas);
		setFocusEnter(jenisIdentitas, noIdentitas);
		setFocusEnter(noIdentitas, alamat);
		setFocusEnter(alamat, kota);
		setFocusEnter(kota, hp);
		setFocusEnter(hp, save);
		setFocusEnter(save, code);
		
	}


	@Override
	public void init() {
		icon=PegawaiM.ICON_48;
		super.init();
		labelTitle.setText("Edit Pegawai");
		labelNote.setText("Isi data dengan benar");
		initComponent();
		buildForm();
	}

	public void buildForm() {
		initComponent();

		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:200px:g,");
		col.append("10px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,15dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LPegawai.CODE, code, 2, 2, 1);
		b.append(LPegawai.STATUS,status , 2, 14, 1);
		b.append(LPegawai.NAMA, nama, 2, 4, 1);
		b.append(LPegawai.USERNAME,username , 2, 6, 1);
		b.append(LPegawai.PASSWORD,password , 2, 8, 1);
		b.append(LPegawai.ULANG_PASSWORD,ulang , 2, 10, 1);
		b.append(LPegawai.JENIS_IDENTITAS,jenisIdentitas , 6, 2, 1);
		b.append(LPegawai.NO_IDENTITAS, noIdentitas, 6, 4, 1);
		b.append(LPegawai.ALAMAT,salamat , 6, 6, 1,5);
		b.append(LPegawai.KOTA,kota , 6, 12, 1);
		b.append(LPegawai.HP, hp, 6, 14, 1);
		b.append(LPegawai.HAK_AKSES, hakAkses, 2, 12, 1);


		panelForm = b.getPanel();

	}


	@Override
	public void save() {
		Pegawai model=(Pegawai) this.model;
		model.setCode(code.getText().trim());
		model.setNama(nama.getText().trim());
		model.setUsername(username.getText().trim());
		if (password.getPassword().length>0) {
			UtilAccount u=new UtilAccount();
			String p;
			try {
				p = u.md5(new String(password.getPassword()));
				model.setPassword(p);
			} catch (Exception e) {
				App.printErr(e);
			}
		}
		model.setJenisIdentitas(jenisIdentitas.getText().trim());
		model.setNoIdentitas(noIdentitas.getText().trim());
		model.setAlamat(alamat.getText().trim());
		model.setKota(kota.getText().trim());
		model.setHp(hp.getText().trim());
		model.setStatus(status.getSelectedIndex());
		try {
			HakAks h=(HakAks) hakAkses.getSelectedItem();
			model.setHakAks(h);
		} catch (Exception e) {
			App.printErr(e);
		}
		PegawaiDao d=App.getPegawaiDao();
		d.update(model);
	}

	@Override
	public boolean validate() {
		PegawaiDao d=App.getPegawaiDao();
		if (code.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LPegawai.CODE);
			return false;
		}
		if (old instanceof Pegawai) {
			if (!((Pegawai) old).getCode().equalsIgnoreCase(code.getText().trim())) {
				long tmp=d.countByColumn("code", code.getText().trim());
				if (tmp>0) {
					App.showErrorDataSudahAda(LPegawai.CODE);
					return false;
				}
			}
		}
		
		
		if (nama.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(LPegawai.NAMA);
			return false;
		}
//		if (!old.getNama().equalsIgnoreCase(nama.getText().trim())) {
//			long tmp=d.countByColumn("nama", nama.getText().trim());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(LPegawai.NAMA);
//				return false;
//			}
//		}
		if (password.getPassword().length > 0) {
			if (password.getPassword().length == ulang.getPassword().length
					&& Arrays.equals(password.getPassword(), ulang.getPassword())) {

			} else {
				App.showErrorPasswordTidakSamadenganKonfirmasi();
				ulang.requestFocus();
				return false;
			}
		}
		if (hakAkses.getSelectedIndex()==0) {
			App.showErrorEmptySelect(LPegawai.HAK_AKSES);
			return false;
		}
		return true;
	}
	
}
