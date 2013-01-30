package org.basic.comp.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.border.Border;


import com.basic.icon.IconBase;
import com.global.App;

public class TextFieldSearch extends JTextField{

    private Icon icon;
    private Insets dummyInsets;

    public TextFieldSearch(){
        super();
        setFirst();
        this.icon = IconBase.SEARCH;

        Border border = App.border;
        TextField dummy = new TextField();
        this.dummyInsets = border.getBorderInsets(dummy);
        
        //setMargin(new Insets(2, 50, 2, 2));
    }

    public void setFirst(){
    	setColumns(20);
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
//				setMargin(new Insets(2, 20, 2, 2));
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

        int textX = 2;

        if(this.icon!=null){
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            int x = dummyInsets.left + 5;//this is our icon's x
            textX = x+iconWidth+2; //this is the x where text should start
            int y = (this.getHeight() - iconHeight)/2;
            icon.paintIcon(this, g, x, y);
        }

        //setMargin(new Insets(2, textX, 2, 2));

    }

}