package com.basic.dao;

import java.util.Random;

import javax.swing.JOptionPane;

import org.bmb.dao.entity.DaoIdListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.db.Bos;
import com.global.App;

@Repository
@Transactional
public class BosDao extends DaoIdListener<Bos> {
	public BosDao() {
		super(Bos.class);
	}
	
	
	@Override
	@Transactional(readOnly = false)
	public void save(Bos domain) {
		sessionFactory.getCurrentSession().save(domain);
	}


	public void create1st(){ 
		Bos bos=new Bos(1, "jmllog", 20000);
		save(bos);
		bos=new Bos(2, creatRendom(), 0);
		save(bos);
		long tmp=App.getHakAks().count();
		if (tmp==0) {
			App.getHakAks().create1st();
			App.getPegawaiDao().create1st();	
		}
		
}
	
	public String creatRendom(){
		Random r = new Random();

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZOIUYTR";
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			tmp.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return tmp.toString();
	}
	public void minus(){
		Bos o=getById((long)2);
		if (o!=null) {
//			String namaKode=getName(o);
			int jmlStatus=o.getJml();
			if (jmlStatus==0) {
				//belum reg
				Bos o1=getById( (long)1);
				if (o1!=null) {
//					String namaKode1=getName(o1);
					int jmlStatus1=o1.getJml();
					if (jmlStatus1<0) {
						JOptionPane.showMessageDialog(null, "Masa Trial Sudah Habis");
						System.exit(0);
					}
					jmlStatus1--;
					o1.setJml(jmlStatus1);
					update(o1);
				}else{
					create1st();
					System.exit(0);
				}
			}else{
				//System.exit(1);
			}
		}else{
			create1st();
			System.exit(0);
		}
	}
}
