package com.bmb.db.config;

import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class DataSource extends BasicDataSource {
	public DataSource() {
		super();
		
//		conn system/a
//		create tablespace tablespace_sa datafile 'sa.dat' size 40m;
//		create user sa identified by sa default tablespace tablespace_sa ;
//		grant RESOURCE to sa;
//		grant DBA to sa;
//		grant connect to sa;
//		conn sa/sa
		
//		setDriverClassName("oracle.jdbc.OracleDriver");
//		setUrl("jdbc:oracle:thin:@localhost:1521:xe");//app
//		setUsername("sa");
//		setPassword("sa");
		
//		setUrl("jdbc:mysql://localhost:3306/test");//develop
		
		setDriverClassName("com.mysql.jdbc.Driver");
		setUrl("jdbc:mysql://localhost:3306/rent mobil");//develop
		setUsername("root");
		setPassword("");
		
		
		setInitialSize(10);
		setMaxActive(8);
		setMaxWait(5000);
		setMinIdle(1);
	}

}


