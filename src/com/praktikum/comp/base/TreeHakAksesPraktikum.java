package com.praktikum.comp.base;

import org.basic.comp.base.TreeHakAkses;
import org.basic.comp.model.HakAksesTreeModel;
import com.global.DataUser;

public class TreeHakAksesPraktikum extends TreeHakAkses{
	public HakAksesTreeModel buildHakakses() {
		HakAksesTreeModel top = new HakAksesTreeModel("Root", DataUser.ROOT,
				null);

		// Hak Akses===============================================

		HakAksesTreeModel perent = new HakAksesTreeModel("Hak Akses",
				DataUser.HAK_AKSES_VIEW, group);
		top.add(perent);

		HakAksesTreeModel anak = new HakAksesTreeModel("Tambah Hak Akses",
				DataUser.HAK_AKSES_ADD, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Edit Hak Akses",
				DataUser.HAK_AKSES_EDIT, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Hapus Hak Akses",
				DataUser.HAK_AKSES_HAPUS, group);
		perent.add(anak);

		// Pegawai
		perent = new HakAksesTreeModel("Pegawai", DataUser.USR_VIEW, group);
		top.add(perent);

		anak = new HakAksesTreeModel("Tambah Pegawai", DataUser.USR_ADD, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Edit Pegawai", DataUser.USR_EDIT, group);
		perent.add(anak);

		anak = new HakAksesTreeModel("Hapus Pegawai", DataUser.USR_DEL, group);
		perent.add(anak);
		
		
		// Pelanggan
				perent = new HakAksesTreeModel("Pelanggan", DataUser.PELANGGAN_VIEW, group);
				top.add(perent);

				anak = new HakAksesTreeModel("Tambah Pelanggan", DataUser.PELANGGAN_ADD, group);
				perent.add(anak);

				anak = new HakAksesTreeModel("Edit Pelanggan", DataUser.PELANGGAN_EDIT, group);
				perent.add(anak);

				anak = new HakAksesTreeModel("Hapus Pelanggan", DataUser.PELANGGAN_DEL, group);
				perent.add(anak);
				
				// Mobil
				perent = new HakAksesTreeModel("Mobil", DataUser.TYPE_MOBIL_VIEW, group);
				top.add(perent);

				anak = new HakAksesTreeModel("Tambah Mobil", DataUser.TYPE_MOBIL_ADD, group);
				perent.add(anak);

				anak = new HakAksesTreeModel("Edit Mobil", DataUser.TYPE_MOBIL_EDIT, group);
				perent.add(anak);

				anak = new HakAksesTreeModel("Hapus Mobil", DataUser.TYPE_MOBIL_DEL, group);
				perent.add(anak);
				
				// Transaksi
				perent = new HakAksesTreeModel("Transaksi", DataUser.SEWA_VIEW, group);
				top.add(perent);

				anak = new HakAksesTreeModel("Tambah Transaksi", DataUser.SEWA_ADD, group);
				perent.add(anak);

				anak = new HakAksesTreeModel("Hapus Transaksi", DataUser.SEWA_DEL, group);
				perent.add(anak);
				
				// Transaksi
				perent = new HakAksesTreeModel("Laporan", DataUser.LAP_VIEW, group);
				top.add(perent);


		return top;

	}
}
