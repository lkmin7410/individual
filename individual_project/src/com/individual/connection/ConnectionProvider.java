package com.individual.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

//connection 관리
public class ConnectionProvider {
    public static Connection getConnection() throws ClassNotFoundException {
	Connection conn = null;
		
	try { 
		 String url = "jdbc:mysql://3.34.54.186:3306/project";
		 String id = "lkmin7410";
		 String pw = "!qlalf45306380";
		 String driver = "com.mysql.jdbc.Driver";
		 Class.forName(driver);
		 conn = DriverManager.getConnection(url, id, pw);

	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return conn;
    }
}