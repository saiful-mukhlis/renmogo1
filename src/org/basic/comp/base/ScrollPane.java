package org.basic.comp.base;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;

import com.global.App;

public class ScrollPane extends JScrollPane {

	public ScrollPane() {
		super();
		setFirst();
	}
	
	public void setFirst(){
		setBackground(Color.WHITE);
		setBorder(App.border);
	}

	public ScrollPane(Component view) {
		super(view);
		setFirst();
		if (view instanceof TextArea) {
			((TextArea) view).setPane(this);
		}
	}

}
