//package org.basic.comp.adapter;
//
//import javax.swing.Icon;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
//import org.basic.dao.adapter.DaoInterface;
//
//import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
//import com.orientechnologies.orient.core.record.impl.ODocument;
//
//
//public interface DialogFormInterface extends DetailWidgetInterface{
//	public void buildPanel();
//	public void buildButton(ODatabaseDocumentTx db);
//	public void setLayoutButton();
//	public void setLayoutTitle();
//	public void actionSave();
//	public void actionReset();
//	public void save(ODatabaseDocumentTx db);
//	public void afterOk();
//	public boolean beforeSave(ODatabaseDocumentTx db);
//	public void afterSave(ODocument model);
//	public void afterSave2(ODocument model);
//	public void afterSave3(ODocument model);
//	public void afterSave4(ODocument model);
//	public void afterSave5(ODocument model);
//	public boolean validate(ODatabaseDocumentTx db);
//	public Icon getIcon();
//	public void setIcon(Icon icon);
//	public JLabel getLabelTitle();
//	public void setLabelTitle(JLabel label);
//	public JScrollPane getPane();
//	public DaoInterface getDao();
//	public void setDao(DaoInterface dao);
//	public JPanel getPanelForm();
//	public void setPanelForm(JPanel panelForm);
//	public void setFocusEnter();
//}
