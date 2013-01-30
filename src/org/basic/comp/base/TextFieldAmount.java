package org.basic.comp.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.global.App;
import com.google.common.base.CharMatcher;

public class TextFieldAmount extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3501974976360534771L;

	public TextFieldAmount() {
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
		
		Font f=UIManager.getFont("TextField.font");
		setFont(f);
		setHorizontalAlignment(SwingConstants.RIGHT);
		
		addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
            }
           
            @Override
            public void keyReleased(KeyEvent e) {
            	Character tmp0=e.getKeyChar();
            	String tmp=getText();
            	
            		if (tmp0!=null && tmp0.toString().matches("[0-9,\\.]")) {
                		String tmp2=CharMatcher.is(',').removeFrom(tmp);
                		if (!(tmp0!=null && tmp0.toString().equalsIgnoreCase(".") && tmp2.matches("[0-9]*\\."))) {
                			if (tmp2.matches("[0-9]*\\.{1}0")) {
                    			try {
        							Double tmp3=Double.parseDouble(tmp2);
        							setText(App.paymentFormat1.format(tmp3));
        						} catch (Exception e2) {
        							// TODO: handle exception
        						}
                    		}else
                    			if (tmp2.matches("[0-9]*\\.{1}[0-9]{2,3}")) {
                        			try {
            							Double tmp3=Double.parseDouble(tmp2);
            							setText(App.paymentFormat2.format(tmp3));
            						} catch (Exception e2) {
            							// TODO: handle exception
            						}
                        		}else
                    		if (tmp2.matches("[0-9]*\\.?[0-9]*")) {
                    			try {
        							Double tmp3=Double.parseDouble(tmp2);
        							setText(App.paymentFormat.format(tmp3));
        						} catch (Exception e2) {
        							// TODO: handle exception
        						}
                    		}else{
                    			setText(tmp.substring(0, tmp.length()-1));
                    		}
                		}
                		
    					
    				}else{
    					setText(CharMatcher.is(tmp0).removeFrom(tmp));
    				}
				
                
            }
           
            @Override
            public void keyPressed(KeyEvent e) {
            }
       });
	}


	public static BigDecimal getValue(TextFieldAmount filed){
		if (!filed.getText().trim().equalsIgnoreCase("")) {
			//hilangkan koma
			String tmp0=CharMatcher.is(',').removeFrom(filed.getText());
			// hanya boloeh angka dan satu titik
			if (tmp0.matches("[0-9]*\\.?[0-9]*")) {
				BigDecimal tmp=new BigDecimal(tmp0);
				return tmp;
			}
		}
			return new BigDecimal(0);
		
	}

}
