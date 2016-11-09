package com.cjon.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.util.DBTemplate;
	
	public class BankDAO {
	 
	 private DBTemplate template;
	 
	 public BankDAO(){
	  
	 }
	 
	
	 public DBTemplate getTemplate() {
	  return template;
	 }
	
	 public void setTemplate(DBTemplate template) {
		this.template = template;
	 }
	 
	 
	
	 public BankDTO update(BankDTO dto) {
	  
		  //Database처리
		  //일반 JDBC처리 코드 필요 
		  
		  Connection con=template.getCon();
		  
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  
		  try{
			   /*
			   //1. JDBC Driver Loading
			   Class.forName("com.mysql.jdbc.Driver");
			   
			   //2. Database 접속
			   String url="jdbc:mysql://localhost:3306/library"; //JDBC URL
			   String id="jQuery";
			   String pw="jQuery";
			   con=DriverManager.getConnection(url,id,pw);
			   */
			   
			   //con.setAutoCommit(false);
			   
			   //3. 사용할 SQL을 작성하고 PreparedStatement 생성
			   String sql="update bank set balance= balance+? where userid=?";
			   pstmt=con.prepareStatement(sql);
			   pstmt.setInt(1, dto.getBalance());
			   pstmt.setString(2, dto.getUserid());
			   
			   //4. 실행
			   int count=pstmt.executeUpdate();
			   
			   //결과처리
			   if(count==1){
				    //정상적으로 처리되면
				    String sql1="select userid, balance from bank where userid=?";
				    PreparedStatement pstmt1=con.prepareStatement(sql1);
				    //IN Parameter처리 
				    pstmt1.setString(1, dto.getUserid());
				    rs=pstmt1.executeQuery();
				    
				    if(rs.next()){
				    	dto.setBalance(rs.getInt("balance"));
				    }
				    //con.commit();
				    //template.commit();
				    dto.setResult(true); //정상처리되었다는 징표를 DTO에 저장 
				    
				    try{
					     rs.close();
					     pstmt1.close();
				    }catch(Exception e){
				    	e.printStackTrace();
				    }
			   }else{
				    //con.rollback();
				    //template.rollback();
				    dto.setResult(false);
			   }
			   
		  }catch(Exception e){
			  e.printStackTrace();
		  }finally{
			   try{
				   pstmt.close();
			   }catch(Exception e2){
			    
			   }
		  }
		
		  return dto;
		 }
		
	 
	 public BankDTO updateWithdraw(BankDTO dto) {
		  	  
		 //Database처리
		 //일반 JDBC처리 코드 필요 
		 Connection con=template.getCon();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		  
		  try{
		   
			   //3. 사용할 SQL을 작성하고 PreparedStatement 생성
			   String sql="update bank set balance= balance-? where userid=?";
			   pstmt=con.prepareStatement(sql);
			   pstmt.setInt(1, dto.getBalance());
			   pstmt.setString(2, dto.getUserid());
			   
			   //4. 실행
			   int count=pstmt.executeUpdate();
			   
			   //결과처리
			   if(count==1){
				    //정상적으로 처리되면
				    String sql1="select userid, balance from bank where userid=?";
				    PreparedStatement pstmt1=con.prepareStatement(sql1);
				    //IN Parameter처리 
				    pstmt1.setString(1, dto.getUserid());
				    rs=pstmt1.executeQuery();
				    
				    if(rs.next()){
				    	dto.setBalance(rs.getInt("balance"));
				    }
				    if(dto.getBalance()<0){
					     System.out.println("예금 금액이 적어 출력할 수 없어요.");
					     //con.rollback();
					     dto.setResult(false);
				    }else{
					     //con.commit();
					     //System.out.println("commit");
					     dto.setResult(true);
				    }
			    
				    try{
					     rs.close();
					     pstmt1.close();
				    }catch(Exception e){
				    	e.printStackTrace();
				    }
			   }   
		   
		  	}catch(Exception e){
		  		e.printStackTrace();
		  	}finally{
				 try{
					 pstmt.close();
				 }catch(Exception e2){
		    
				 }
		  	}
		 
		  return dto;
		 
	 	}
	
	}
