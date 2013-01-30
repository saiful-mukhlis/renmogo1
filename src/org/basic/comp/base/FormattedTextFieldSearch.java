package org.basic.comp.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.basic.icon.IconBase;
import com.global.App;

public class FormattedTextFieldSearch extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8589786662563811554L;

	public FormattedTextFieldSearch() {
		super();
		setFirst();
	}

	public FormattedTextFieldSearch(AbstractFormatter formatter) {
		super(formatter);
		setFirst();
	}

	public FormattedTextFieldSearch(AbstractFormatterFactory factory,
			Object currentValue) {
		super(factory, currentValue);
		setFirst();
	}

	public FormattedTextFieldSearch(AbstractFormatterFactory factory) {
		super(factory);
		setFirst();
	}

	public FormattedTextFieldSearch(Format format) {
		super(format);
		setFirst();
	}

	public FormattedTextFieldSearch(Object value) {
		super(value);
		setFirst();
	}

//	public void setFirst(){
//		setBorder(App.border);
//		setBackground(Color.WHITE);
//		setCaretColor(App.aqua);
//		addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				setBorder(App.border);
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//				setBorder(App.borderSelected);
//			}
//		});
//	}
	
	
	 public void setFirst(){
		 this.icon = IconBase.SEARCH;

//	        Border border = UIManager.getBorder("JFormattedTextField.border");
//	        JFormattedTextField dummy = new JFormattedTextField();
//	        this.dummyInsets = border.getBorderInsets(dummy);
			setBackground(Color.WHITE);
			
			
			setBorder(BorderFactory.createCompoundBorder(App.border,
	                BorderFactory.createEmptyBorder(0, 27, 0, 0)));
			setBackground(Color.WHITE);
			setCaretColor(App.selected);
			addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					setBorder(BorderFactory.createCompoundBorder(App.border,
			                BorderFactory.createEmptyBorder(0, 27, 0, 0)));
				}
				@Override
				public void focusGained(FocusEvent e) {
					setBorder(BorderFactory.createCompoundBorder(App.borderSelected,
			                BorderFactory.createEmptyBorder(0, 27, 0, 0)));
//					setMargin(new Insets(2, 20, 2, 2));
				}
			});
			addMouseListener(new MouseAdapter() {

				
				@Override
				public void mouseExited(MouseEvent mouseevent) {
					// TODO Auto-generated method stub
					if (!isFocusOwner()) {
						setBorder(BorderFactory.createCompoundBorder(App.border,
				                BorderFactory.createEmptyBorder(0, 27, 0, 0)));
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent mouseevent) {
					// TODO Auto-generated method stub
					setBorder(BorderFactory.createCompoundBorder(App.borderSelected,
			                BorderFactory.createEmptyBorder(0, 27, 0, 0)));
				}
			
			});
		}
	    
	    
	    public void setIcon(Icon icon){
	        this.icon = icon;
	    }

	    public Icon getIcon(){
	        return this.icon;
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);


	       // int textX = 2;

	        if(this.icon!=null){
	            int iconWidth = icon.getIconWidth();
	            int iconHeight = icon.getIconHeight();
	            int x = (iconWidth+2)/2;//this is our icon's x
	            //textX = x+iconWidth+2; //this is the x where text should start
	            int y = (this.getHeight() - iconHeight)/2;
	            icon.paintIcon(this, g, x, y);
	        }

	        //setMargin(new Insets(2, textX, 2, 2));

	    }
	    
	    private Icon icon;
//	    private Insets dummyInsets;
}
