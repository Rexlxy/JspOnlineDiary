package com.rex.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	public Connection getConnection() throws Exception{
		Class.forName(PropertiesUtil.getValue("jdbcName"));
		Connection con = DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"),
				PropertiesUtil.getValue("dbUserName"), PropertiesUtil.getValue("dbPassword"));
		return con;
	}
	
	
	public void closeConnection(Connection con) throws Exception{
		if(con!=null)	con.close();
	}
	
	//main method to check the connection 
	public static void main(String[] args){
		DbUtil dbUtil = new DbUtil();
		try {
			Connection con = dbUtil.getConnection();
			if(con != null){
				System.out.println("Database: Successfully connected");
			} else {
				System.out.println("Database: Failed to connect");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Database: Failed to connect");
			e.printStackTrace();
		}
	}

}
