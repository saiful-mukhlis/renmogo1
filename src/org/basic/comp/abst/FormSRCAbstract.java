//package org.basic.comp.abst;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.Window;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JSeparator;
//
//import org.basic.comp.adapter.FormInterface;
//import org.basic.comp.adapter.FormSRCAdapter;
//import org.basic.comp.adapter.WidgetInterface;
//
//import com.basic.lang.L;
//import com.global.App;
//import com.jgoodies.forms.builder.ButtonBarBuilder2;
//import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
//
//public abstract class FormSRCAbstract extends FormAbstract implements FormSRCAdapter {
//	protected JPanel panelBody;
//
//	protected JButton saveButton;
//	protected JButton resetButton;
//	protected JButton cancelButton;
//	protected JPanel panelAksi;
//	
//	protected List<WidgetInterface> widgets=new ArrayList<WidgetInterface>();
//	
//	
//	@Override
//	public void init(ODatabaseDocumentTx db) {
//		super.init(db);
//		initComponent(db);
//	}
//	
//	@Override
//	public void build(ODatabaseDocumentTx db) {
//		super.build(db);
//		buildPanelAksi();
//	}
//
//	public void initComponent(ODatabaseDocumentTx db){
//		panelBody=new JPanel();
//		panelAksi=new JPanel();
//		saveButton=new JButton();
//		resetButton=new JButton();
//		cancelButton=new JButton();
//		setTextButton(db);
//	}
//	public void setTextButton(ODatabaseDocumentTx db){
//		saveButton.setText(L.save);
//		resetButton.setText(L.reset);
//		cancelButton.setText(L.cancel);
//	}
//	public void aksiCancel() {
//		dispose(panel);
//	}
//	public void dispose(Object o) {
//		if (o instanceof Window) {
//			((Window) o).dispose();
//		} else {
//			if (o instanceof Component) {
//				dispose(((Component) o).getParent());
//			}
//		}
//	}
//	public void buildPanelAksi() {
//
//		ButtonBarBuilder2 builderButton = new ButtonBarBuilder2();
//		builderButton
//				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		builderButton.addGlue();
//		builderButton.addButton(saveButton, resetButton, cancelButton);
//		panelAksi = new JPanel();
//		panelAksi.setLayout(new BorderLayout());
//		panelAksi.add(new JSeparator(), BorderLayout.NORTH);
//		panelAksi.add(builderButton.getPanel(), BorderLayout.CENTER);
//
//		cancelButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				aksiCancel();
//			}
//		});
//
//		saveButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				aksiSave();
//			}
//		});
//
//		resetButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				actionReset();
//			}
//		});
//	}
//	
//	public void changeOtherWidget(Object model){
//		for (WidgetInterface widgetAdapter : widgets) {
//			//widgetAdapter.modelWidgetAdd(model);
//		}
//	}
//
//}
