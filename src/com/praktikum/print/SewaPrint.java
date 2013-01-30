package com.praktikum.print;

import javax.swing.JPanel;

import com.basic.print.ReportBese;
import com.basic.print.defaults.PrintDefault;
import java.util.ArrayList;
import java.util.List;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import org.basic.comp.listener.LoadingAdapter;

import com.basic.comp.impl.DialogLoading;
import com.basic.lang.LUsr;
import com.global.App;
import com.praktikum.print.model.SewaPM;

public class SewaPrint extends PrintDefault {
	protected List<SewaPM> pm=new ArrayList<SewaPM>();
	protected String title="";
	public SewaPrint(JPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void init() {
		reportChild = new DynamicReportBuilder();
		reportChild.setTitle(title);
		// reportChild.setSubtitle(subTitle);
		// reportChild.setHeaderHeight(tinggiHeader);
		 reportChild.setUseFullPageWidth(true);
		reportChild.setColumnsPerPage(1);
		reportChild.setAllowDetailSplit(true);
		reportChild.setDefaultStyles(titleStyle, titleStyle, headerStyle,
				isiStyle);
		reportChild.setPageSizeAndOrientation(Page.Page_A4_Landscape());

		reportChild.setAllowDetailSplit(true);
		// reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		// reportChild.setPageSizeAndOrientation
	}
	@Override
	public void buildColumn() {
		reportChild.addColumn(buildColumn("no",App.getT("No") , 30));
		reportChild.addColumn(buildColumn("tgl",App.getT("Tanggal") , 55));
		reportChild.addColumn(buildColumn("pegawai",App.getT("Pegawai") , 55));
		reportChild.addColumn(buildColumn("pelanggan",App.getT("Pelanggan") , 55));
		reportChild.addColumn(buildColumn("mobil",App.getT("Mobil") , 100));
		reportChild.addColumn(buildColumn("start",App.getT("Start") , 100));
		reportChild.addColumn(buildColumn("end",App.getT("End") , 100));
		reportChild.addColumn(buildColumn("jml",App.getT("Jumlah") , 30));
		reportChild.addColumn(buildColumn("harga",App.getT("Harga") , 30));
		reportChild.addColumn(buildColumn("total",App.getT("Total") , 40));
	}

	@Override
	public void run() {
		buildReport();
		DialogLoading dl=new DialogLoading(getWindow(panel),new LoadingAdapter() {

			@Override
			public void runTask() {
				List<DynamicReport> childReport=new ArrayList<DynamicReport>();
				List<List> datas=new ArrayList<List>();
				buildReport();
				childReport.add(reportChild.build());
				datas.add(pm);

				ReportBese report=new ReportBese();
				report.setDatas(datas);
				report.setChildReport(childReport);
				report.runReport();
				
			}
		
		});
		dl.setVisible(true);
	}
	
	public void runPrint() {
		buildReport();
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport();
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runPrint();
	}
	
	public void runPdf() {
		buildReport();
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport();
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runPdf(panel, title);
	}
	
	public void runWord() {
		buildReport();
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport();
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runWord( panel, title);
	}
	
	public void runExcel() {
		buildReport();
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport();
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runExcel( panel, title);
	}


	public List<SewaPM> getPm() {
		return pm;
	}


	public void setPm(List<SewaPM> pm) {
		this.pm = pm;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
