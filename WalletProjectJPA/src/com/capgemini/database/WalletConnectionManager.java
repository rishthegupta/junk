package com.capgemini.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WalletConnectionManager {
	
	public static Connection getConnection()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver not found");
			e.printStackTrace();
		}
		
		Connection con=null;
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Capgemini123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection not established ");
			e.printStackTrace();
		}
		return con;
	}

}
