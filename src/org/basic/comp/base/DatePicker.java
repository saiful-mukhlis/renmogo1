package org.basic.comp.base;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;

import org.jdesktop.swingx.JXDatePicker;

import com.global.App;

public class DatePicker extends JXDatePicker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7914592521691664079L;

	public DatePicker() {
		super();
		setFirst();
	}

	public DatePicker(Date selection, Locale locale) {
		super(selection, locale);
		setFirst();
	}

	public DatePicker(Date selected) {
		super(selected);
		setFirst();
	}

	public DatePicker(Locale locale) {
		super(locale);
		setFirst();
	}

	public void setFirst(){
		//setEditor(new FormattedTextFieldSearch());
		setFormats(App.dateFormat);
		setBorder(App.border);
		setBackground(Color.WHITE);
		getEditor().setBorder(BorderFactory.createEmptyBorder());
		getEditor().setBackground(Color.WHITE);
		getEditor().setCaretColor(App.selected);
		getEditor().addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				setBorder(App.border);
			}
			@Override
			public void focusGained(FocusEvent e) {
				setBorder(App.borderSelected);
			}
		});
		getEditor().addMouseListener(new MouseAdapter() {

			
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
	
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//
//        if(this.icon!=null){
//            int iconHeight = icon.getIconHeight();
//            int x = 16;//this is our icon's x
//            int y = (this.getHeight() - iconHeight)/2;
//            icon.paintIcon(this, g, x, y);
//        }
//
//        //setMargin(new Insets(2, textX, 2, 2));
//
//    }
//    
//    private Icon icon=IconBase.SEARCH;
}
