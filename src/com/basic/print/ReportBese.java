package com.basic.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.basic.lang.LApp;
import com.basic.print.model.TtdModel;
import com.global.App;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class ReportBese {

	protected List<String> header = new ArrayList();
	protected List<String> footer = new ArrayList();

	/**
	 * <strong>Default</strong> <br/>
	 * 
	 * <pre>
	 * Style styleNul = new StyleBuilder(true)
	 * 		.setHorizontalAlign(HorizontalAlign.CENTER)
	 * 		.setVerticalAlign(VerticalAlign.MIDDLE)
	 * .setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();
	 * 
	 * <pre>
	 */
	protected Style styleHeader;
	
	/**
	 * <strong>Default</strong> <br/>
	 * 
	 * <pre>
	 * Style styleNul = new StyleBuilder(true)
	 * 		.setHorizontalAlign(HorizontalAlign.CENTER)
	 * 		.setVerticalAlign(VerticalAlign.MIDDLE)
	 * .setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();
	 * 
	 * <pre>
	 */
	protected Style styleFooter;

	protected Map params = new HashMap();
	protected JasperPrint jp;
	protected JasperReport jr;
	protected DynamicReport dr;
	protected DynamicReportBuilder frb;

	protected boolean isLandscape = true;

	protected FastReportBuilder reportBasic;

	protected List<DynamicReport> childReport = new ArrayList<DynamicReport>();
	protected List<List> childData = new ArrayList<List>();
	
	private String titleFrame;

	public DynamicReport buildReport() throws Exception {

		reportBasic = buildReportBasic();

		int i = 0;
		for (DynamicReport r : childReport) {
			addConcatenatedReport(reportBasic, r, "data" + i);
			params.put("data" + i, childData.get(i));
			i++;
		}

		DynamicReport dynamicReport2 = buildTtd();

		if (dynamicReport2 != null) {
			addConcatenatedReport(reportBasic, dynamicReport2, "statistics3");
			params.put("statistics3", dataTtd());
		}

		DynamicReport dr = reportBasic.build();

		return dr;
	}

	public JFrame runReport(){
		
		try {
			testReport();
		} catch (Exception e1) {
			App.printErr(e1);
		}
		//JasperViewer.viewReport(report.getJp(), false);	
		JasperViewer jasperViewer = new JasperViewer(getJp(),
				false);
//		try {
//			JasperPrintManager.printPage(getJp(), 0, false);
//		} catch (JRException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//JFrame x=getFrame(jasperViewer);
		jasperViewer.setIconImage(App.getImage(LApp.iconApp16).getImage());
		if (titleFrame==null) {
			jasperViewer.setTitle(App.getT("Print Preview"));
		}else{
			jasperViewer.setTitle(titleFrame);
		}
		jasperViewer.setVisible(true);
		return jasperViewer;
	}
	
	public boolean runPrint(){
		try {
			testReport();
		} catch (Exception e1) {
			App.printErr(e1);
		}
		try {
			JasperPrintManager.printPage(getJp(), 0, false);
		} catch (JRException e) {
			App.printErr(e);
			return false;
		}
		return true;
	}
	
	public boolean runPdf(JPanel panel, String namaFile){
		try {
			testReport();
		} catch (Exception e1) {
			App.printErr(e1);
		}
		JFileChooser fc=new JFileChooser();
		fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
		fc.setSelectedFile(new File(namaFile+".pdf"));
		int returnVal = fc .showSaveDialog(panel);
		if (returnVal==JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			JRPdfExporter exporter=new JRPdfExporter();
	        FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		        try {
					exporter.exportReport();
					try {
						fos.close();
					} catch (IOException e) {
						App.printErr(e);
						return false;
					}
				} catch (JRException e) {
					App.printErr(e);
					return false;
				}

			} catch (FileNotFoundException e) {
				App.printErr(e);
				return false;
			}

	        

		}
		
		return true;
	}
	
	public boolean runExcel( JPanel panel, String namaFile){
		try {
			testReport();
		} catch (Exception e1) {
			App.printErr(e1);
		}
		JFileChooser fc=new JFileChooser();
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Excel Documents", "xls"));
		fc.setSelectedFile(new File(namaFile+".xls"));
		int returnVal = fc .showSaveDialog(panel);
		if (returnVal==JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			JRXlsExporter exporter=new JRXlsExporter();
	        FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		        
		        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		        try {
					exporter.exportReport();
					try {
						fos.close();
					} catch (IOException e) {
						App.printErr(e);
						return false;
					}
				} catch (JRException e) {
					App.printErr(e);
					return false;
				}

			} catch (FileNotFoundException e) {
				App.printErr(e);
				return false;
			}

	        

		}
		
		return true;
	}
	
	public boolean runWord(JPanel panel, String namaFile){
		try {
			testReport();
		} catch (Exception e1) {
			App.printErr(e1);
		}
		JFileChooser fc=new JFileChooser();
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Word Documents", "rtf"));
		fc.setSelectedFile(new File(namaFile+".rtf"));
		int returnVal = fc .showSaveDialog(panel);
		if (returnVal==JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			JRRtfExporter exporter=new JRRtfExporter();
	        FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		        
		        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		        try {
					exporter.exportReport();
					try {
						fos.close();
					} catch (IOException e) {
						App.printErr(e);
						return false;
					}
				} catch (JRException e) {
					App.printErr(e);
					return false;
				}

			} catch (FileNotFoundException e) {
				App.printErr(e);
				return false;
			}

	        

		}
		
		return true;
	}
	
	public void testReport() throws Exception {
		dr = buildReport();
		JRDataSource ds = getDataSource();
		jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		if (ds != null) {
			jp = JasperFillManager.fillReport(jr, params, ds);
		} else {
			jp = JasperFillManager.fillReport(jr, params);
		}
	}

	protected JRDataSource getDataSource() {
		Collection dummyCollection = getDummyCollection();
		// dummyCollection = SortUtils.sortCollection(dummyCollection,
		// dr.getColumns());

		JRDataSource ds = new JRBeanCollectionDataSource(dummyCollection);

		return ds;
	}

	public static List getDummyCollection() {

		List<String> datas = new ArrayList<String>();
		int i = 0;

		datas.add("-");
		return datas;
	}

	public JasperPrint getJp() {
		return jp;
	}

	public JasperReport getJr() {
		return jr;
	}

	public void setChildReport(List<DynamicReport> childReport) {
		this.childReport = childReport;
	}

	public void setDatas(List<List> datas) {
		this.childData = datas;
	}

	public void setLandscape(boolean isLandscape) {
		this.isLandscape = isLandscape;
	}

	public FastReportBuilder buildReportBasic() {
		if (header.size() > 0) {
			FastReportBuilder buildReportBasic = new FastReportBuilder();
			if (isLandscape) {
				buildReportBasic.setPageSizeAndOrientation(Page
						.Page_A4_Landscape());
			} else {
				buildReportBasic.setPageSizeAndOrientation(Page
						.Page_A4_Portrait());
			}
			buildReportBasic.setColumnsPerPage(new Integer(1));

			StringBuilder head = new StringBuilder();

			for (String s : header) {
				if (s != null) {
					head.append(s + "\\n");
				}
			}

			if (styleHeader == null) {
				Style styleNul = new StyleBuilder(true)
						.setHorizontalAlign(HorizontalAlign.CENTER)
						.setVerticalAlign(VerticalAlign.MIDDLE)
						.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();

				buildReportBasic.setDefaultStyles(styleNul, styleNul, styleNul,
						styleNul);
			} else {
				buildReportBasic.setDefaultStyles(styleHeader, styleHeader,
						styleHeader, styleHeader);
			}
			buildReportBasic.setTitle(head.toString());
			buildReportBasic.setUseFullPageWidth(true);
			return buildReportBasic;
		}else{

			FastReportBuilder buildReportBasic = new FastReportBuilder();
			if (isLandscape) {
				buildReportBasic.setPageSizeAndOrientation(Page
						.Page_A4_Landscape());
			} else {
				buildReportBasic.setPageSizeAndOrientation(Page
						.Page_A4_Portrait());
			}
			buildReportBasic.setColumnsPerPage(new Integer(1));


			if (styleHeader == null) {
				Style styleNul = new StyleBuilder(true)
						.setHorizontalAlign(HorizontalAlign.CENTER)
						.setVerticalAlign(VerticalAlign.MIDDLE)
						.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();

				buildReportBasic.setDefaultStyles(styleNul, styleNul, styleNul,
						styleNul);
			} else {
				buildReportBasic.setDefaultStyles(styleHeader, styleHeader,
						styleHeader, styleHeader);
			}
			buildReportBasic.setTitle(App.getT("Print Preview"));
			buildReportBasic.setUseFullPageWidth(true);
			return buildReportBasic;
		
		}

	}

	public DynamicReport buildTtd() {
		if (footer.size() == 8) {
			DynamicReportBuilder drb2 = new DynamicReportBuilder();

			if (isLandscape) {
				drb2.setPageSizeAndOrientation(Page.Page_A4_Landscape());
			} else {
				drb2.setPageSizeAndOrientation(Page.Page_A4_Portrait());
			}

			drb2.setUseFullPageWidth(true);
			drb2.setColumnsPerPage(1);
			drb2.setAllowDetailSplit(false);
			
			Style styleNul;
			
			if (styleFooter==null) {
				styleNul = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();
			}else{
				styleNul=styleFooter;
			}

			 

			drb2.addColumn(ColumnBuilder.getNew()
					.setColumnProperty("kosong", String.class.getName())
					.setTitle("").setWidth(new Integer(300))
					.setFixedWidth(false).setStyle(styleNul).build());

			drb2.addColumn(buildColumn("kosong", "", 100, styleNul));
			drb2.addColumn(buildColumn("ket", "", 400, styleNul));
			drb2.addColumn(buildColumn("kosong", "", 900, styleNul));
			drb2.addColumn(buildColumn("ket2", "", 500, styleNul));
			drb2.addColumn(buildColumn("kosong", "", 300, styleNul));

			return drb2.build();
		}
		return null;
	}

	public AbstractColumn buildColumn(String property, String title, int width,
			Style style, Style styleHeader) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(style);
		c.setHeaderStyle(styleHeader);
		return c.build();
	}

	public AbstractColumn buildColumn(String property, String title, int width,
			Style style) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(style);
		return c.build();
	}

	public Collection dataTtd() {
		if (footer.size() == 8) {
			List<TtdModel> ttd = new ArrayList<TtdModel>();
			TtdModel t = new TtdModel(footer.get(0), footer.get(4));
			ttd.add(t);
			t = new TtdModel(footer.get(1), footer.get(5));
			ttd.add(t);
			t = new TtdModel("", "");
			ttd.add(t);
			t = new TtdModel("", "");
			ttd.add(t);
			t = new TtdModel("", "");
			ttd.add(t);
			t = new TtdModel("", "");
			ttd.add(t);

			ttd.add(t);
			t = new TtdModel(footer.get(2), footer.get(6));
			ttd.add(t);
			t = new TtdModel(footer.get(3), footer.get(7));
			ttd.add(t);
			return ttd;
		}
		return null;

	}

	public void addConcatenatedReport(FastReportBuilder buildReportBasic,
			DynamicReport dynamicReport, String dataPath) {
		buildReportBasic.addConcatenatedReport(dynamicReport,
				new ClassicLayoutManager(), dataPath,
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
				DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
	}

	public Style getStyleHeader() {
		return styleHeader;
	}

	public void setStyleHeader(Style styleHeader) {
		this.styleHeader = styleHeader;
	}

	public Style getStyleFooter() {
		return styleFooter;
	}

	public void setStyleFooter(Style styleFooter) {
		this.styleFooter = styleFooter;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	public DynamicReport getDr() {
		return dr;
	}

	public void setDr(DynamicReport dr) {
		this.dr = dr;
	}

	public FastReportBuilder getReportBasic() {
		return reportBasic;
	}

	public void setReportBasic(FastReportBuilder reportBasic) {
		this.reportBasic = reportBasic;
	}

	public List<String> getHeader() {
		return header;
	}

	public List<String> getFooter() {
		return footer;
	}

	public boolean isLandscape() {
		return isLandscape;
	}

	public List<DynamicReport> getChildReport() {
		return childReport;
	}

	public List<List> getChildData() {
		return childData;
	}

	public void setJp(JasperPrint jp) {
		this.jp = jp;
	}

	public void setJr(JasperReport jr) {
		this.jr = jr;
	}

	public String getTitleFrame() {
		return titleFrame;
	}

	public void setTitleFrame(String titleFrame) {
		this.titleFrame = titleFrame;
	}

	
	
}
