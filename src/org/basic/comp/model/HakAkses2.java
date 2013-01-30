//package org.basic.comp.model;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
//import org.jdesktop.swingx.treetable.TreeTableNode;
//import com.basic.db.Grp;
//import com.orientechnologies.orient.core.record.impl.ODocument;
//
//
//public class HakAkses2 extends DefaultMutableTreeTableNode {
//private String nama;
//private String kode;
//private boolean aktif;
//private int key;
//private HakAkses2 parent;
//private List<HakAkses2> childs=new ArrayList<HakAkses2>();
//private ODocument group;
//
//public HakAkses2(String nama, int key) {
//	super();
//	this.nama = nama;
//	this.key = key;
//	this.kode="x"+key+"x";
//	this.aktif=true;
//	this.parent=null;
//	
//}
//
//
//public HakAkses2(String nama, int key, ODocument groupa) {
//	super();
//	group=groupa;
//	this.nama = nama;
//	this.key = key;
//	this.kode="x"+key+"x";
//	if (group!=null) {
//		String data=group.field(Grp.KEY);
//		if (data!=null) {
//			if (data.indexOf(kode)==-1) {
//				this.aktif=false;
//			}else{
//				this.aktif=true;
//			}
//		}
//	}else{
//	}
//	
//	this.parent=null;
//}
//
//
//public void add(HakAkses2 child){
//	child.setParent(this);
//	childs.add(child);
//}
//public String getNama() {
//	return nama;
//}
//public void setNama(String nama) {
//	this.nama = nama;
//}
//public String getKode() {
//	return kode;
//}
//public void setKode(String kode) {
//	this.kode = kode;
//}
//public boolean isAktif() {
//	return aktif;
//}
//public void setAktif(boolean aktif) {
//	this.aktif = aktif;
//}
//public int getKey() {
//	return key;
//}
//public void setKey(int key) {
//	this.key = key;
//}
//public HakAkses2 getParent() {
//	return parent;
//}
//public void setParent(HakAkses2 parent) {
//	this.parent = parent;
//}
//public List<HakAkses2> getChilds() {
//	return childs;
//}
//public void setChilds(List<HakAkses2> childs) {
//	this.childs = childs;
//}
//
//
//public ODocument getGroup() {
//	return group;
//}
//
//
//public void setGroup(ODocument group) {
//	this.group = group;
//	if (group!=null) {
//		String data=group.field(Grp.KEY);
//		if (data!=null) {
//			if (data.indexOf(kode)==-1) {
//				this.aktif=false;
//			}else{
//				this.aktif=true;
//			}
//		}else{
//			this.aktif=false;
//		}
//	}else{
//		this.aktif=false;
//	}
//}
//
//
//@Override
//public String toString() {
//	return nama;
//}
//
//}
