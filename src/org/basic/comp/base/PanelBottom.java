package org.basic.comp.base;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelBottom {
	private JPanel panel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;

	private JSeparator batas1;
	private JSeparator batas2;
	private JSeparator batas3;
	private JSeparator batas4;
	private JSeparator batas5;
	private JSeparator batas6;
	private JSeparator batas7;
	
	public void build(){
		initComponent();
		setFirst();
	}
	
	public void setFirst(){
		label1.setText("Ctrl+L : Login/Logout");
		label2.setText("Ctrl+W : Close");
		label3.setText("Ctrl+X : Exit");
		label4.setText("Ctrl+H : Open Hak Akses");
		label5.setText("Ctrl+G : Open Pegawai");
		label6.setText("Ctrl+R : Open Pelanggan");
		label7.setText("Ctrl+T : Open Transaksi");
	}

	public void initComponent(){
		panel=new JPanel(new BorderLayout());
		
		JPanel p=new JPanel();
		
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		label4=new JLabel();
		label5=new JLabel();
		label6=new JLabel();
		label7=new JLabel();

		batas1=new JSeparator();
		batas2=new JSeparator();
		batas3=new JSeparator();
		batas4=new JSeparator();
		batas5=new JSeparator();
		batas6=new JSeparator();
		batas7=new JSeparator();
		
		
		FormLayout fl_panel_1 = new FormLayout(new ColumnSpec[] {
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,
                FormFactory. RELATED_GAP_COLSPEC,
                FormFactory. PREF_COLSPEC,},
           new RowSpec[] {
                FormFactory. NARROW_LINE_GAP_ROWSPEC,
                FormFactory. PREF_ROWSPEC,
                FormFactory. NARROW_LINE_GAP_ROWSPEC,});

		
		p.setLayout(fl_panel_1);
        
        p.add(label1, "1, 2");
        batas1.setOrientation(SwingConstants. VERTICAL);
        p.add(batas1, "3, 1, 1, 3");
        
        p.add(label2, "5, 2");
        batas2.setOrientation(SwingConstants. VERTICAL);
        p.add(batas2, "7, 1, 1, 3");
        
        p.add(label3, "9, 2");
        batas3.setOrientation(SwingConstants. VERTICAL);
        p.add(batas3, "11, 1, 1, 3");
        
        p.add(label4, "13, 2");
        batas4.setOrientation(SwingConstants. VERTICAL);
        p.add(batas4, "15, 1, 1, 3");
        
        p.add(label5, "17, 2");
        batas5.setOrientation(SwingConstants. VERTICAL);
        p.add(batas5, "19, 1, 1, 3");
        
        p.add(label6, "21, 2");
        batas6.setOrientation(SwingConstants. VERTICAL);
        p.add(batas6, "23, 1, 1, 3");
        
        p.add(label6, "25, 2");
        batas6.setOrientation(SwingConstants. VERTICAL);
        p.add(batas6, "27, 1, 1, 3");
        
//        panel.add(new JSeparator(), BorderLayout.NORTH);
        panel.add(p, BorderLayout.CENTER);
       

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	public JLabel getLabel4() {
		return label4;
	}

	public void setLabel4(JLabel label4) {
		this.label4 = label4;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public void setLabel5(JLabel label5) {
		this.label5 = label5;
	}

	public JLabel getLabel6() {
		return label6;
	}

	public void setLabel6(JLabel label6) {
		this.label6 = label6;
	}

	public JSeparator getBatas1() {
		return batas1;
	}

	public void setBatas1(JSeparator batas1) {
		this.batas1 = batas1;
	}

	public JSeparator getBatas2() {
		return batas2;
	}

	public void setBatas2(JSeparator batas2) {
		this.batas2 = batas2;
	}

	public JSeparator getBatas3() {
		return batas3;
	}

	public void setBatas3(JSeparator batas3) {
		this.batas3 = batas3;
	}

	public JSeparator getBatas4() {
		return batas4;
	}

	public void setBatas4(JSeparator batas4) {
		this.batas4 = batas4;
	}

	public JSeparator getBatas5() {
		return batas5;
	}

	public void setBatas5(JSeparator batas5) {
		this.batas5 = batas5;
	}

	public JSeparator getBatas6() {
		return batas6;
	}

	public void setBatas6(JSeparator batas6) {
		this.batas6 = batas6;
	}
	
	
}
