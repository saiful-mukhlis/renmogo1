package com.basic.print.interfaces;

import java.awt.Window;
import java.util.Collection;

import javax.swing.JFrame;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public interface PrintInterface {

	/**
	 * Contoh :
	 * 
	 * <pre>
	 * reportChild.setColspan(5, 1, &quot;Jumlah&quot;);
	 * </pre>
	 * 
	 * 5 = kolom awal <br/>
	 * 1 = jumlah kolom yang di span <br/>
	 * Jumlah = title header
	 */
	public void buildColSpan();

	/**
	 * Contoh :
	 * 
	 * <pre>
	 * reportChild.addColumn(buildColumn(&quot;no&quot;, &quot;No&quot;, 8));
	 * </pre>
	 * 
	 * no = nama properti filed <br/>
	 * No = title headernya <br/>
	 * 8 = lebarnya
	 */
	public void buildColumn();

	public AbstractColumn buildColumn(String property, String title, int width);

	public AbstractColumn buildColumn(String property, String title, int width,
			Style style, Style styleHeader, Collection styleCond);

	/**
	 * Bila db tidak dibutuhkan null kan saja
	 * 
	 * <pre>
	 * reportChild = new DynamicReportBuilder();
	 * // reportChild.setTitle(title);
	 * // reportChild.setSubtitle(subTitle);
	 * // reportChild.setHeaderHeight(tinggiHeader);
	 * // reportChild.setUseFullPageWidth(satuHalamanPenuh);
	 * reportChild.setColumnsPerPage(1);
	 * reportChild.setAllowDetailSplit(true);
	 * reportChild.setDefaultStyles(titleStyle, titleStyle, headerStyle, isiStyle);
	 * reportChild.setPageSizeAndOrientation(Page.Page_A4_Landscape());
	 * 
	 * reportChild.setAllowDetailSplit(true);
	 * // reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
	 * // reportChild.setPageSizeAndOrientation(Page.Page_Legal_Portrait());
	 * </pre>
	 * 
	 * @param db
	 */
	public void init();

	/**
	 * 
	 * <pre>
	 * 
	 * initStyles();
	 * init(db);
	 * initReportChild();
	 * buildColumn();
	 * buildColSpan();
	 * // yang di atas sudah sebagai default
	 * pm = new ArrayList&lt;&gt;();
	 * List&lt;ODocument&gt; model = App.getUsrDao().getAll(db);
	 * int index = 0;
	 * for (ODocument oDocument : model) {
	 * 	int no = index + 1;
	 * 	UsrPM u = new UsrPM();
	 * 	ODocument tmp2 = oDocument.field(Usr.GRP);
	 * 	tmp2.field(Grp.NAME);
	 * 	u.setO2(tmp2);
	 * 
	 * 	ODocument tmp3 = oDocument.field(Usr.JENIS_PEKERJAAN);
	 * 	if (tmp3 != null) {
	 * 		tmp3.field(JenisPekerjaan.NAMA);
	 * 		u.setO3(tmp3);
	 * 	} else {
	 * 		u.setO3(null);
	 * 	}
	 * 	u.setO(oDocument);
	 * 	u.setNo(no + &quot;&quot;);
	 * 	u.setO(oDocument);
	 * 	pm.add(u);
	 * 
	 * 	index++;
	 * }
	 * 
	 * </pre>
	 * 
	 * @param db
	 */
	public void buildReport();

	/**
	 * <pre>
	 * Object[] possibilities = App.getBulan();
	 * NamaBulan s = (NamaBulan) JOptionPane.showInputDialog(getFrame(panel),
	 * 		&quot;Bulan : \n&quot;, &quot;Pilih Bulan&quot;, JOptionPane.PLAIN_MESSAGE, null,
	 * 		possibilities, possibilities[0]);
	 * if (s != null) {
	 * 	boolean salah = true;
	 * 	while (salah) {
	 * 		String inputValue = JOptionPane.showInputDialog(getFrame(panel),
	 * 				&quot;Tahun :&quot;, &quot;Inputkan Tahun&quot;, JOptionPane.PLAIN_MESSAGE);
	 * 		if (inputValue != null) {
	 * 			if (inputValue.length() == 4) {
	 * 				try {
	 * 					int th = Integer.parseInt(inputValue);
	 * 					salah = false;
	 * 					DialogLoading dl = new DialogLoading(db, getWindow(panel),
	 * 							new LoadingAdapter() {
	 * 
	 * 								&#064;Override
	 * 								public void runTask() {
	 * 									List&lt;DynamicReport&gt; childReport = new ArrayList&lt;DynamicReport&gt;();
	 * 									List&lt;List&gt; datas = new ArrayList&lt;List&gt;();
	 * 									buildReport(db, lajura, s, th);
	 * 									childReport.add(reportChild.build());
	 * 									datas.add(data);
	 * 
	 * 									String t1 = &quot;&quot;;
	 * 									String t2 = &quot;&quot;;
	 * 									String t3 = &quot;&quot;;
	 * 									ODocument f = App.getFormatDao().getOne(db,
	 * 											FormatDao.code, &quot;kop&quot;);
	 * 									if (f != null) {
	 * 										t1 = f.field(FormatDao.kop1);
	 * 										t2 = f.field(FormatDao.kop2);
	 * 										t3 = f.field(FormatDao.kop3);
	 * 										tinggiHeader = 50;
	 * 										satuHalamanPenuh = true;
	 * 									}
	 * 
	 * 									AbstractJadwalReport report = new AbstractJadwalReport(
	 * 											t1, t2, t3, &quot;&quot;);
	 * 									// report.setLandscape(false);
	 * 									report.setDatas(datas);
	 * 									report.setChildReport(childReport);
	 * 									runReport(db, report);
	 * 
	 * 								}
	 * 
	 * 							});
	 * 					dl.setVisible(true);
	 * 				} catch (Exception e) {
	 * 					// TODO: handle exception
	 * 					App.printErr(e);
	 * 				}
	 * 			}
	 * 		} else {
	 * 			salah = false;
	 * 		}
	 * 
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @param db
	 */
	public void run();

	public void runPrint();

	public void runPdf();

	public void runWord();

	public void runExcel();

	/**
	 * pembentukan tabel di dalam report
	 */
	public void initReportChild();

	/**
	 * deklarasi semua style
	 */
	public void initStyles();

	public Window getWindow(Object o);

	public JFrame getFrame(Object o);

}
