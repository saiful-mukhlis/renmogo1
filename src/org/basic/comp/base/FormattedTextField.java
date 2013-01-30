package org.basic.comp.base;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;

import javax.swing.JFormattedTextField;

import com.global.App;

public class FormattedTextField extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8589786662563811554L;

	public FormattedTextField() {
		super();
		setFirst();
	}

	public FormattedTextField(AbstractFormatter formatter) {
		super(formatter);
		setFirst();
	}

	public FormattedTextField(AbstractFormatterFactory factory,
			Object currentValue) {
		super(factory, currentValue);
		setFirst();
	}

	public FormattedTextField(AbstractFormatterFactory factory) {
		super(factory);
		setFirst();
	}

	public FormattedTextField(Format format) {
		super(format);
		setFirst();
	}

	public FormattedTextField(Object value) {
		super(value);
		setFirst();
	}

	public void setFirst(){
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
		addMouseListener(new MouseAdapter() {

			
			@Override
			public void mouseExited(MouseEvent mouseevent) {
				// TODO Auto-generated method stub
				if (!isFocusOwner()) {
					setBorder(App.border);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent mouseevent) {
				// TODO Auto-generated method stub
				setBorder(App.borderSelected);
			}
		
		});
	}
}
