package com.gms.web.domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import com.gms.web.constant.DB;

import lombok.Data;




@Data
public class DatabaseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String driver,url,username,password;
	private Connection connection;
	public DatabaseBean(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	public Connection getConnection(){
		try {
			Class.forName(driver);
			connection=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	

}
