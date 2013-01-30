package org.basic.comp.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.jgoodies.animation.*;
import com.jgoodies.animation.animations.BasicTextAnimation;
import com.jgoodies.animation.animations.BasicTextAnimations;
import com.jgoodies.animation.components.BasicTextLabel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Builds a page that consists of two <code>BasicTextLabel</code>s
 * that render an intro animation.
 *
 * @author Karsten Lentzsch
 * @version $Revision: 1.6 $
 * 
 * @see Animation
 * @see BasicTextLabel
 */
public final class BasicTextLabelIntro {

    // UI Components
    private BasicTextLabel label1;
    private BasicTextLabel label2;
    
    /**
     * Refers to the Action that starts the intro animation.
     */
    private Action animateAction;


    // Self Starter ***********************************************************
    
//    public static void main(String[] args) {
//        /*try {
//            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
//        } catch (Exception e) {
//            // Likely PlasticXP is not in the class path; ignore.
//        }*/
//		String nativeLF = UIManager.getSystemLookAndFeelClassName();
//
//	    // Install the look and feel
//	    try {
//			UIManager.setLookAndFeel(nativeLF);
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        JFrame frame = new JFrame();
//        frame.setTitle("Animation Tutorial :: BasicTextLabel Intro");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        JComponent panel = new BasicTextLabelIntro().buildPanel();
//        frame.getContentPane().add(panel);
//        frame.pack();
//        frame.setVisible(true);
//        
//        
//    }
    
    
    // Instance Creation ******************************************************
    
    public BasicTextLabelIntro() {
        //duration = 4000;
        initComponents();
    }
    
    
    // Building *************************************************************

    /**
     * Creates and configures the UI components.
     */
    private void initComponents() {
        Font font = new Font("Arial", Font.BOLD, 56);
        label1 = new BasicTextLabel(" ");
        label1.setFont(font);
        //label1.setBounds(0, 0, 350, 100);
        label1.setOpaque(false);

        label2 = new BasicTextLabel(" ");
        label2.setFont(font);
        //label2.setBounds(0, 0, 350, 100);
        label2.setOpaque(false);
        
        animateAction = new AnimateAction();
    }
    
    
    /**
     * Builds and returns a panel with the preview in the top 
     * and the tool panel in the bottom.
     * 
     * @return the built panel
     */
    public JComponent buildPanel() {
        FormLayout layout = new FormLayout(
                "fill:pref:grow",
                "fill:pref:grow, p, p");
        
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();
        builder.add(buildPreviewPanel(), cc.xy(1, 1));
        builder.addSeparator("",         cc.xy(1, 2));
       // builder.add(buildToolsPanel(),   cc.xy(1, 3));
        
        Animation animation = createAnimation();
        int fps = 30;
        animation.addAnimationListener(new StartStopHandler());
        new Animator(animation, fps).start();
        
        return builder.getPanel();
    }
    
    
    /**
     * Builds this intro panel with two overlayed labels in the center.
     * 
     * @return the panel that contains the two overlayed labels
     */
    public JPanel buildPreviewPanel() {
        FormLayout layout = new FormLayout(
                "fill:200dlu:grow",
                "fill:100dlu:grow");
        JPanel panel = new JPanel(layout);
        CellConstraints cc = new CellConstraints();
        panel.setBackground(Color.WHITE);
        panel.add(label1, cc.xy(1, 1));
        panel.add(label2, cc.xy(1, 1));
        return panel;
    }
    

    public JComponent buildToolsPanel() {
        FormLayout layout = new FormLayout(
                "right:pref:grow",
                "pref");
        
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JButton(animateAction), cc.xy(1, 1));
        return builder.getPanel();
    }
        
    
    // Animation Creation ***************************************************

    /**
     * Creates and returns a composed animation for the intro.
     * 
     * @return the composed animation
     */
    private Animation createAnimation() {
        Animation welcome =
            BasicTextAnimation.defaultFade(
                label1,
                2500,
                "Welcome To",
                Color.DARK_GRAY);

        Animation theJGoodiesAnimation =
            BasicTextAnimation.defaultFade(
                label1,
                4000,
                "Application Rental Mobil Gowes",
                Color.DARK_GRAY);

        Animation description =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                3000,
                -100,
                "is open source|" +
                "user friendly|fast.......|developed in Java language",
                Color.DARK_GRAY);

        Animation features =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                4000,
                500,
                "by Mhs. ITATS",
                Color.DARK_GRAY);

        Animation featureList =
            BasicTextAnimations.defaultFade(
                label1,
                label2,
                2000,
                0,
                "What you can?|maintenance pegawai|maintenance hak akses|and Others.|Why this?|flexible|powerful|" +
                "quickly",
                Color.DARK_GRAY);

        Animation all =
            Animations.sequential(
                new Animation[] {
                    Animations.pause(1000),
                    welcome,
                    Animations.pause(1000),
                    theJGoodiesAnimation,
                    Animations.pause(1000),
                    description,
                    Animations.pause(1000),
                    features,
                    Animations.pause(1000),
                    featureList,
                    Animations.pause(1500),
                    });

        return all;
    }
    
    
    // Animation Action *******************************************************
    
    private final class AnimateAction extends AbstractAction {
        
        private AnimateAction() {
            super("Animate");
        }
          
        public void actionPerformed(ActionEvent e) {
            Animation animation = createAnimation();
            int fps = 30;
            animation.addAnimationListener(new StartStopHandler());
            new Animator(animation, fps).start();
        }  
        
    }
    
        
    /**
     * Disables the actions at animation start and enables them
     * when the animation stopped. Also restores the text label's text.
     */
    private final class StartStopHandler extends AnimationAdapter {
        
        public void animationStarted(AnimationEvent e) {
            animateAction.setEnabled(false);
        }
        
        public void animationStopped(AnimationEvent e) {
        	Animation animation = createAnimation();
            int fps = 30;
            animation.addAnimationListener(new StartStopHandler());
            new Animator(animation, fps).start();
        }
    }

}