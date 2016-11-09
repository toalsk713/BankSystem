package com.cjon.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	
	public class DBTemplate {
	 
	 private Connection con;
	 
	 public DBTemplate(){
		  try{
			   //1. JDBC Driver Loading
			   Class.forName("com.mysql.jdbc.Driver");
			   
			   //2. Database 접속
			   String url="jdbc:mysql://localhost:3306/library"; //JDBC URL
			   String id="jQuery";
			   String pw="jQuery";
			   con=DriverManager.getConnection(url,id,pw);   
			   con.setAutoCommit(false);
		  }catch(Exception e){
			  System.out.println(e);
		  }
	 }
	 
	 public void commit(){
		  try {
			  con.commit();
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	 }
	 
	 public void rollback(){
		  try {
			  con.rollback();
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	 }
	 
	 //getter/setter
	 public Connection getCon() {
		 return con;
	 }
	 
	 public void setCon(Connection con) {
		 this.con = con;
	 }
	 
	 
	}
