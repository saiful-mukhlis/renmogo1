package com.basic.print.defaults;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.basic.print.adapter.PrintAdapter;

import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class PrintDefault extends PrintAdapter {

	protected DynamicReportBuilder reportChild;
	protected Style isiStyle;
	protected Style headerStyle;
	protected Style titleStyle;
	protected List model;
	protected JPanel panel;



	public void initStyles() {

		headerStyle = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setBorder(Border.THIN)
				.setBorderColor(Color.BLACK)
				.setTransparency(Transparency.OPAQUE)
				.setBackgroundColor(Color.WHITE).build();

		isiStyle = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setBorder(Border.THIN)
				.setBorderColor(Color.BLACK).setStretchWithOverflow(false)
				.setTextColor(Color.BLACK)
				// .setStretchWithOverflow(true)
				// .setBackgroundColor(Color.BLUE)
				.setTransparency(Transparency.OPAQUE)
				// .setTransparent(false)
				.build();

		titleStyle = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setTextColor(Color.BLACK) // VERDANA_BIG_BOLD
				// .setBackgroundColor(Color.BLUE)
				// .setTransparency(Transparency.OPAQUE)
				// .setTransparent(false)
				.build();

	}

	public AbstractColumn buildColumn(String property, String title, int width,
			Style style, Style styleHeader, Collection styleCond) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(style);
		c.addConditionalStyles(styleCond);
		c.setHeaderStyle(styleHeader);
		return c.build();
	}

	public AbstractColumn buildColumn(String property, String title, int width) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(isiStyle);
		c.setHeaderStyle(headerStyle);
		return c.build();
	}

	public Window getWindow(Object o) {
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return getWindow(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	public JFrame getFrame(Object o) {
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return getFrame(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	public PrintDefault(JPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void buildReport() {
		initStyles();
		init();
		initReportChild();
		buildColumn();
		buildColSpan();
	}

}
