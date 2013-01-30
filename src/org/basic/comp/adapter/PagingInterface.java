package org.basic.comp.adapter;

import javax.swing.JPanel;


public interface PagingInterface {
public void loadFirst();
public void setCurentHalaman(int curentHalaman);
public int getCurentHalaman();
public void setJumlahPerHalaman(int jumlahPerHalaman) ;
public int getJumlahPerHalaman();
public void setJumlahHalaman(int jumlahHalaman);
public int getJumlahHalaman();
public void setJumlahData(long jumlahData);
public Long getJumlahData();
public JPanel getPanelPaging();
}
