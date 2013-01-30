package org.basic.comp.model;


import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;
import com.global.App;
import com.praktikum.db.HakAks;

public class HakAksesTreeModel extends DefaultMutableTreeTableNode {

	
	
	public HakAksesTreeModel(String nama, int key, HakAks h) {
		super(nama);
		hakAks=h;
		this.nama = nama;
		this.kode="x"+key+"x";
		if (hakAks!=null) {
			String data=hakAks.getHakAkses();
			if (data!=null) {
				if (data.indexOf(kode)==-1) {
					this.aktif=false;
				}else{
					this.aktif=true;
				}
			}
		}
	}

	private String nama;
	private String kode;
	private boolean aktif;
//	private int key;
	private HakAks hakAks;

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int column) {
		if (column == 0) {
			return nama;
		} else if (column == 1) {
			return aktif;
		}
		return super.getValueAt(column);
	}

	@Override
	public boolean isEditable(int column) {
		if (column == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int column) {
		if (column == 1) {
			if (hakAks != null) {
				try {
					if (aValue instanceof Boolean) {
						aktif = (Boolean) aValue;
						if (aktif) {
							String data = hakAks.getHakAkses();
							if (data != null) {
								if (data.indexOf(kode) == -1) {
									data = data + kode;
									hakAks.setHakAkses(data);
									App.getHakAks().save(hakAks);
								}
							} else {
								data = "";
								if (data.indexOf(kode) == -1) {
									data = data + kode;
									hakAks.setHakAkses(data);
									App.getHakAks().save(hakAks);
								}
							}
						} else {
							String data = hakAks.getHakAkses();
							if (data != null) {
								if (data.indexOf(kode) != -1) {
									data = data.replaceAll(kode, "");
									hakAks.setHakAkses(data);
									App.getHakAks().save(hakAks);
								}
							}
						}

						if ((Boolean) aValue) {
							TreeTableNode p = getParent();
							if (p instanceof HakAksesTreeModel) {
								HakAksesTreeModel parent = (HakAksesTreeModel) p;
								if (parent != null) {
									parent.setAktif((Boolean) aValue);
									String kode = ((HakAksesTreeModel) parent).getKode();
									String data = hakAks.getHakAkses();
									if (data != null) {
										if (data.indexOf(kode) == -1) {
											data = data + kode;
											hakAks.setHakAkses(data);
											App.getHakAks().save(hakAks);
										}
									} else {
										data = "";
										if (data.indexOf(kode) == -1) {
											data = data + kode;
											hakAks.setHakAkses(data);
											App.getHakAks().save(hakAks);
										}
									}

								}
							}

						}

						if (!(Boolean) aValue) {
							int jmlAnak = getChildCount();
							for (int i = 0; i < jmlAnak; i++) {
								TreeTableNode c = getChildAt(i);
								if (c instanceof HakAksesTreeModel) {
									HakAksesTreeModel ch = (HakAksesTreeModel) c;
									ch.setAktif((Boolean) aValue);

									String kode = ch.getKode();
									String data = hakAks.getHakAkses();
									if (data != null) {
										if (data.indexOf(kode) != -1) {
											data = data.replaceAll(kode, "");
											hakAks.setHakAkses(data);
											App.getHakAks().save(hakAks);
										}
									}
								}

							}

						}

					}

				} catch (Exception e) {
					App.printErr(e);
				}

				super.setValueAt(aValue, column);

			}
		}

	}



	@Override
	public String toString() {
		return nama;
	}

	public boolean isAktif() {
		return aktif;
	}

	public void setAktif(boolean aktif) {
		this.aktif = aktif;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

}
