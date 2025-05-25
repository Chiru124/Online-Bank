package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.connector.ConnectorFactory;
import com.bank.dto.Customer;

public class CustomerDAOImp1 implements CustomerDAO{
	 private Connection con; 
	 
	public CustomerDAOImp1() {
		this.con=ConnectorFactory.requestConnection();
	}

	@Override
	public boolean insertCustomer(Customer c) {
		PreparedStatement ps=null; 
		String query="INSERT INTO CUSTOMER(NAME,PHONE,MAIL,PIN)VALUES(?,?,?,?)"; 
		int res=0; 
		try { 
			con.setAutoCommit(false); 
			ps=con.prepareStatement(query); 
			ps.setString(1,c.getName());   
			ps.setLong(2, c.getPhone());    
			ps.setString(3, c.getMail());   
			ps.setInt(4, c.getPin());    
			res=ps.executeUpdate();   
			} 
		     catch (SQLException e) {    
		    	 e.printStackTrace(); 
		 } 
		  if(res>0) 
		  { 
		   try { 
		    con.commit(); 
		   } 
		   catch (SQLException e) {    
			   e.printStackTrace(); 
		   } 
		   return true; 
		  } 
		  else  
		  {  
		   try { 
		    con.rollback(); 
		} 
		   catch (SQLException e) {         
			e.printStackTrace(); 
		   } 
		   return false; 
		  } 

	}

	@Override
	public Customer getCustomer(String mail, int pin) {
		PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM CUSTOMER WHERE MAIL=? AND PIN=?"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   ps.setString(1,mail);   
		   ps.setInt(2, pin);  
		   rs=ps.executeQuery(); 
		   if(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setAccno(rs.getInt(1)); 
		    c.setName(rs.getString(2)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(4)); 
		    c.setBal(rs.getDouble(5)); 
		    c.setPin(rs.getInt(6)); 
		   } 
		  } catch (SQLException e) {      
			  e.printStackTrace(); 
		  } 
		  return c; 

	
	}

	@Override
	public Customer getCustomer(long phone, String mail) {
		  PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM CUSTOMER WHERE PHONE=? AND MAIL=?"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   ps.setLong(1,phone);   
		   ps.setString(2, mail); 
		rs=ps.executeQuery(); 
		if(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setAccno(rs.getInt(1)); 
		    c.setName(rs.getString(2)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(4)); 
		    c.setBal(rs.getDouble(5)); 
		    c.setPin(rs.getInt(6)); 
		   } 
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		  return c; 
	}

	@Override
	public Customer getCustomer(long accno) {
		PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM CUSTOMER WHERE acc_no=?"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   ps.setLong(1,accno);    
		rs=ps.executeQuery(); 
		if(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setAccno(rs.getInt(1)); 
		    c.setName(rs.getString(2)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(4)); 
		    c.setBal(rs.getDouble(5)); 
		    c.setPin(rs.getInt(6)); 
		   } 
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		  return c; 
	}

	@Override
	public List getCustomer() {
		PreparedStatement ps=null; 
		ArrayList<Customer> customers=new ArrayList<Customer>(); 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM CUSTOMER"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   rs=ps.executeQuery(); 
		   while(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setAccno(rs.getInt(1)); 
		    c.setName(rs.getString(2)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(4)); 
		    c.setBal(rs.getDouble(5)); 
		    c.setPin(rs.getInt(6)); 
		    customers.add(c); 
		   } 
		  } catch (SQLException e) {    
			  e.printStackTrace(); 
		} 
		return customers; 

	}

	@Override
	public boolean updateCustomer(Customer c) {
		 PreparedStatement ps=null; 
		  int res1=0; 
		  String query="UPDATE CUSTOMER SET NAME=?,PHONE=?,MAIL=?,BALANCE=?,PIN=? WHERE ACC_NO=?"; 
		  try { 
		   con.setAutoCommit(false);  
		   ps=con.prepareStatement(query);  
		   ps.setString(1, c.getName()); 
		   ps.setLong(2, c.getPhone()); 
		   ps.setString(3, c.getMail()); 
		   ps.setDouble(4, c.getBal()); 
		   ps.setInt(5, c.getPin());  
		   ps.setLong(6, c.getAccno()); 
		   res1=ps.executeUpdate();    
		  }
		  catch (SQLException e) {    
			  e.printStackTrace(); 
		  }
		  if(res1>0) 
		  { 
		     try { 
		         con.commit(); 
		      } 
		     catch (SQLException e) {  
			   e.printStackTrace(); 
		   } 
		   return true; 
		  } 
		  else 
		  { 
		   try { 
		    con.rollback(); 
		      }
		   catch (SQLException e) {   
			   e.printStackTrace(); 
		   } 
		   return false; 
		  } 

	}

	@Override
	public boolean deleteCustomer(Customer c) {
		PreparedStatement ps=null; 
		  int res=0; 
		  String query="DELETE FROM CUSTOMER WHERE ACC_NO=?"; 
		  try { 
		   con.setAutoCommit(false);    
		   ps=con.prepareStatement(query);  
		   ps.setLong(1, c.getAccno()); 
		   res=ps.executeUpdate(); 
		    
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		if(res>0) 
		{ 
		   try { 
		    con.commit(); 
		   } catch (SQLException e) { 
			   e.printStackTrace(); 
		   } 
		   return true; 
		  } 
		  else 
		  { 
		   try { 
		    con.rollback(); 
		   } catch (SQLException e) { 
			   e.printStackTrace(); 
		   } 
		   return false; 
		  } 
	}

	@Override
	public Customer getBankBalance() {
		PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT SUM(BALANCE) FROM CUSTOMER";
		  try { 
			   ps=con.prepareStatement(query);      
			   rs=ps.executeQuery(); 
			   if(rs.next()) 
			   { 
			    c=new Customer();  
			    c.setBal(rs.getDouble(1)); 
			   } 
			  } catch (SQLException e) {     
				  e.printStackTrace(); 
			  } 
		
		return c;
	}
	public List getNewCustomers() {
		PreparedStatement ps=null; 
		ArrayList<Customer> customers=new ArrayList<Customer>(); 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM CUSTOMER ORDER BY ACC_NO DESC"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   rs=ps.executeQuery(); 
		   while(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setAccno(rs.getInt(1)); 
		    c.setName(rs.getString(2)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(4)); 
		    c.setBal(rs.getDouble(5)); 
		    c.setPin(rs.getInt(6)); 
		    customers.add(c); 
		   } 
		  } catch (SQLException e) {    
			  e.printStackTrace(); 
		} 
		return customers; 

	}

	@Override
	public List getAccountRequest() {
		PreparedStatement ps=null; 
		ArrayList<Customer> customers=new ArrayList<Customer>(); 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM ACCOUNT_REQUEST ORDER BY REQDATE DESC"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   rs=ps.executeQuery(); 
		   while(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setName(rs.getString(1)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(2)); 
		    c.setPin(rs.getInt(4)); 
		    customers.add(c); 
		   } 
		  } catch (SQLException e) {    
			  e.printStackTrace(); 
		}
		return customers;
	}

	@Override
	public boolean insertRequests(Customer c) {
		PreparedStatement ps=null; 
		String query="INSERT INTO ACCOUNT_REQUEST(NAME,EMAIL,PHONE,PASSWORD)VALUES(?,?,?,?)"; 
		int res=0; 
		try { 
			con.setAutoCommit(false); 
			ps=con.prepareStatement(query); 
			ps.setString(1,c.getName());   
			ps.setLong(3, c.getPhone());    
			ps.setString(2, c.getMail());   
			ps.setInt(4, c.getPin());    
			res=ps.executeUpdate();   
			} 
		     catch (SQLException e) {    
		    	 e.printStackTrace(); 
		 } 
		  if(res>0) 
		  { 
		   try { 
		    con.commit(); 
		   } 
		   catch (SQLException e) {    
			   e.printStackTrace(); 
		   } 
		   return true; 
		  } 
		  else  
		  {  
		   try { 
		    con.rollback(); 
		} 
		   catch (SQLException e) {         
			e.printStackTrace(); 
		   } 
		   return false; 
		  } 
	}

	@Override
	public boolean deleteRequest(Customer c) {
		PreparedStatement ps=null; 
		  int res=0; 
		  String query="DELETE FROM ACCOUNT_REQUEST WHERE PHONE=?"; 
		  try { 
		   con.setAutoCommit(false);    
		   ps=con.prepareStatement(query);  
		   ps.setLong(1, c.getPhone()); 
		   res=ps.executeUpdate(); 
		    
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		if(res>0) 
		{ 
		   try { 
		    con.commit(); 
		   } catch (SQLException e) { 
			   e.printStackTrace(); 
		   } 
		   return true; 
		  } 
		  else 
		  { 
		   try { 
		    con.rollback(); 
		   } catch (SQLException e) { 
			   e.printStackTrace(); 
		   } 
		   return false; 
		  } 
	}

	@Override
	public boolean insertDeleteRequests(Customer c) {
		PreparedStatement ps=null; 
		String query="INSERT INTO DELETED_REQUESTS(NAME,EMAIL,PHONE,PASSWORD)VALUES(?,?,?,?)"; 
		int res=0; 
		try { 
			con.setAutoCommit(false); 
			ps=con.prepareStatement(query); 
			ps.setString(1,c.getName());   
			ps.setLong(3, c.getPhone());    
			ps.setString(2, c.getMail());   
			ps.setInt(4, c.getPin());    
			res=ps.executeUpdate();   
			} 
		     catch (SQLException e) {    
		    	 e.printStackTrace(); 
		 } 
		  if(res>0) 
		  { 
		   try { 
		    con.commit(); 
		   } 
		   catch (SQLException e) {    
			   e.printStackTrace(); 
		   } 
		   return true; 
		  } 
		  else  
		  {  
		   try { 
		    con.rollback(); 
		} 
		   catch (SQLException e) {         
			e.printStackTrace(); 
		   } 
		   return false; 
		  } 
	}

	@Override
	public Customer getAccountRequest(long phone) {
		PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM ACCOUNT_REQUEST WHERE PHONE=?"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   ps.setLong(1,phone);    
		rs=ps.executeQuery(); 
		if(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setName(rs.getString(1)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(2)); 
		    c.setPin(rs.getInt(4)); 
		   } 
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		  return c;
	}

	@Override
	public List getDeleteAccounts() {
		PreparedStatement ps=null; 
		ArrayList<Customer> customers=new ArrayList<Customer>(); 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM DELETE_ACCOUNTS"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   rs=ps.executeQuery(); 
		   while(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setAccno(rs.getInt(1)); 
		    c.setName(rs.getString(2)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(4)); 
		    c.setBal(rs.getDouble(5)); 
		    customers.add(c); 
		   } 
		  } catch (SQLException e) {    
			  e.printStackTrace(); 
		} 
		return customers; 
	}

	@Override
	public Customer getAccountRequest(String mail, int pin) {
		PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM ACCOUNT_REQUEST WHERE EMAIL=? AND PASSWORD=?"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   ps.setString(1,mail); 
		   ps.setInt(2,pin);
		rs=ps.executeQuery(); 
		if(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setName(rs.getString(1)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(2)); 
		    c.setPin(rs.getInt(4)); 
		   } 
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		  return c;
	}

	@Override
	public Customer getdeleteRequest(String mail, int pin) {
		PreparedStatement ps=null; 
		  ResultSet rs=null; 
		  Customer c=null; 
		  String query="SELECT * FROM DELETED_REQUESTS WHERE EMAIL=? AND PASSWORD=?"; 
		  try { 
		   ps=con.prepareStatement(query);   
		   ps.setString(1,mail); 
		   ps.setInt(2,pin);
		rs=ps.executeQuery(); 
		if(rs.next()) 
		   { 
		    c=new Customer(); 
		    c.setName(rs.getString(1)); 
		    c.setPhone(rs.getLong(3)); 
		    c.setMail(rs.getString(2)); 
		    c.setPin(rs.getInt(4)); 
		   } 
		  } catch (SQLException e) {     
			  e.printStackTrace(); 
		  } 
		  return c;
	}

	

}
