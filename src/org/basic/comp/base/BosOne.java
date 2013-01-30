
package org.basic.comp.base;

import java.util.prefs.Preferences;


import com.basic.dao.BosDao;
import com.basic.db.Bos;
import com.bmb.util.Db;
import com.global.App;

public class BosOne {
	private String loc = "";

	public void seta() {
		BosDao dao=App.getBosDao();
		Bos bos2=dao.getById(2);
		
		if (bos2 != null) {
			int jmlStatus=bos2.getJml();
			String namaKode=bos2.getName();
			if (jmlStatus != 0) {
				Preferences userPref = Preferences.userRoot();
				String x = userPref.get("ortptnk", "x");
				
				if (namaKode != null || !namaKode.equalsIgnoreCase("")) {
					if (!x.equalsIgnoreCase(namaKode)) {
						bos2.setJml(0);
						bos2.setName("");
						dao.update(bos2);
					}
				}
			} else {
				Bos bos1=dao.getById(1);
				if (bos1 != null) {
					int jumlahLogin=bos1.getJml();
					setLoc(" [Trial Version after " + jumlahLogin + " Login]");
				}else{
					dao.create1st();
					setLoc(" [Trial Version after 20 Login]");
				}
			}

			if (namaKode == null || namaKode.equalsIgnoreCase("")) {
				bos2.setName(dao.creatRendom());
				bos2.setJml(0);
				dao.update(bos2);
			}
		} else {
			dao.create1st();
			setLoc(" [Trial Version after 20 Login]");

		}
		
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
}
