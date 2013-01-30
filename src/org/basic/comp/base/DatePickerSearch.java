package org.basic.comp.base;

import java.awt.Color;
import java.util.Date;
import java.util.Locale;


import org.jdesktop.swingx.JXDatePicker;

import com.global.App;

public class DatePickerSearch extends JXDatePicker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7914592521691664079L;

	public DatePickerSearch() {
		super();
		setFirst();
	}

	public DatePickerSearch(Date selection, Locale locale) {
		super(selection, locale);
		setFirst();
	}

	public DatePickerSearch(Date selected) {
		super(selected);
		setFirst();
	}

	public DatePickerSearch(Locale locale) {
		super(locale);
		setFirst();
	}

	public void setFirst(){
		setEditor(new FormattedTextFieldSearch());
		setFormats(App.dateFormat);
		//setBorder(App.border);
		setBackground(Color.WHITE);
		
		
		
//		getEditor().setBorder(BorderFactory.createEmptyBorder());
//		getEditor().setBackground(Color.WHITE);
//		getEditor().setCaretColor(App.aqua);
//		getEditor().addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				setBorder(App.border);
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//				setBorder(App.borderSelected);
//			}
//		});
		
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
