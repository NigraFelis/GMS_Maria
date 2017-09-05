package com.gms.web.factory;

import com.gms.web.constant.DB;
import com.gms.web.constant.Vendor;
import com.gms.web.domain.DatabaseBean;

public class DatabaseFactory {
	public static DatabaseBean createDatabase(
			Vendor vendor,String username,String password){
		String driver="",url="";
		switch (vendor) {
		case ORACLE:
			driver=DB.ORACLE_DRIVER;
			url=DB.ORACLE_URL;
			break;
		case DB2:
			driver=DB.DB2_DRIVER;
			url=DB.DB2_URL;
			break;
		case MSSQL:
			driver=DB.MSSQL_DRIVER;
			url=DB.MSSQL_URL;
			break;
		case MYSQL:
			driver=DB.MYSQL_DRIVER;
			url=DB.MYSQL_URL;
			break;
		case MARIADB:
			driver=DB.MARIADB_DRIVER;
			url=DB.MARIADB_URL;
			break;
		default:
			break;
		}
		return new DatabaseBean(driver, url, username, password);
	}
}
