package org.basic.comp.base;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

import com.global.App;

public class PasswordField extends JPasswordField {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1267926216281332388L;

	public PasswordField() {
		super();
		setBorder(App.border);
		setBackground(Color.WHITE);
		setCaretColor(App.selected);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				setBorder(App.border);
			}
			@Override
			public void focusGained(FocusEvent e) {
				setBorder(App.borderSelected);
			}
		});
	}
}
