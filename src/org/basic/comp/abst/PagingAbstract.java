package org.basic.comp.abst;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.basic.comp.adapter.PagingInterface;
import org.basic.comp.adapter.ParentPagingInterface;
import org.basic.comp.adapter.TableModelInterface;

import com.global.App;


public abstract class PagingAbstract implements PagingInterface {

	protected ParentPagingInterface tableModel;

	protected JPanel panelPaging;
	protected JButton reloadButton;
	protected JButton firstButton;
	protected JButton backButton;
	protected JTextField curentTextFild;
	protected JLabel pageLabel;
	protected JButton nextButton;
	protected JButton endButton;
	protected JLabel jumlahRowLabel;

	protected long jumlahData=(long) 0;
	protected int curentHalaman=1;
	protected int jumlahHalaman=1;
	protected int jumlahPerHalaman=50;

	@Override
	public void loadFirst() {
		resetJumlahHalaman();
//		curentHalaman=jumlahHalaman;
//		reset();
		last();
		reset();
	}

	public void resetJumlahHalaman() {
		tableModel.loadJumlahData();
		jumlahHalaman = (int) (jumlahData / jumlahPerHalaman);
		if (jumlahData % jumlahPerHalaman != 0) {
			jumlahHalaman++;
		}
		
		reset();
	}

	public void reset() {
		curentTextFild.setText(curentHalaman + "");
		pageLabel.setText("  from " + (jumlahHalaman) + " page  ");
		jumlahRowLabel.setText(" Total " + jumlahData + " row");
	}
	

	public void setFistPaging() {
		reset();
		backButton.setEnabled(false);
		firstButton.setEnabled(false);
		if (jumlahHalaman <= 1) {
			nextButton.setEnabled(false);
			endButton.setEnabled(false);
			curentTextFild.setEditable(false);
		} else {
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			curentTextFild.setEditable(true);
		}

	}

	public void next() {
		curentHalaman++;
		if (curentHalaman == jumlahHalaman) {
			nextButton.setEnabled(false);
			endButton.setEnabled(false);
		}
		backButton.setEnabled(true);
		firstButton.setEnabled(true);
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload();
	}

	public void prev() {
		curentHalaman--;
		if (curentHalaman == 1) {
			backButton.setEnabled(false);
			firstButton.setEnabled(false);
		}
		nextButton.setEnabled(true);
		endButton.setEnabled(true);
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload();
	}

	public void first() {
		curentHalaman = 1;
		backButton.setEnabled(false);
		firstButton.setEnabled(false);
		if (jumlahHalaman > 1) {
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
		}
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload();
	}

	public void last() {
		curentHalaman = jumlahHalaman ;
		backButton.setEnabled(true);
		firstButton.setEnabled(true);
		nextButton.setEnabled(false);
		endButton.setEnabled(false);
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload();
	}

	public void go() {
		int tmp = curentHalaman;
		try {
			curentHalaman = Integer.parseInt(curentTextFild.getText());
		} catch (Exception e) {
		}
		if (curentHalaman == 1 || curentHalaman > (jumlahHalaman)) {
			curentHalaman = tmp;
		} else {
			//curentHalaman--;
		}
		if (curentHalaman == 1) {
			first();
		} else if (curentHalaman == jumlahHalaman ) {
			last();
		} else {
			backButton.setEnabled(true);
			firstButton.setEnabled(true);
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			tableModel.reload();
		}

	}
	
	public void initComponent() {

		panelPaging = new JPanel();
		reloadButton = new JButton();
		firstButton = new JButton();
		backButton = new JButton();
		curentTextFild = new JTextField(5);
		pageLabel = new JLabel();
		nextButton = new JButton();
		endButton = new JButton();
		jumlahRowLabel = new JLabel();


		reloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.reload();
			}
		});
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		firstButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		curentTextFild.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				go();
			}
		});
	}

	public Long getJumlahData() {
		return jumlahData;
	}

	public void setJumlahData(long jumlahData) {
		this.jumlahData = jumlahData;
	}

	public int getJumlahHalaman() {
		return jumlahHalaman;
	}

	public void setJumlahHalaman(int jumlahHalaman) {
		this.jumlahHalaman = jumlahHalaman;
	}

	public int getJumlahPerHalaman() {
		return jumlahPerHalaman;
	}

	public void setJumlahPerHalaman(int jumlahPerHalaman) {
		this.jumlahPerHalaman = jumlahPerHalaman;
	}

	public int getCurentHalaman() {
		if (curentHalaman<=0) {
			curentHalaman=1;
		}
		return curentHalaman;
	}

	public void setCurentHalaman(int curentHalaman) {
		this.curentHalaman = curentHalaman;
	}

	public JPanel getPanelPaging() {
		return panelPaging;
	}
	
	

}
