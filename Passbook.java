package com.bank.main;

import java.util.Iterator;
import java.util.List;

import com.bank.dto.Customer;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionID;
import com.bank.dao.TransactionDAOImpl;
import com.bank.dao.TransactionDAO;

public class Passbook {
	 public static void getPassbook(Customer c) 
	 { 
	  TransactionDAO tdao=new TransactionDAOImpl();  
	  List<Transaction> passbook=tdao.getTransaction(c.getAccno());  
	  Iterator<Transaction> it=passbook.iterator(); 
	  while(it.hasNext())  
	  { 
	   Transaction t=it.next();   
	   System.out.println("<-------------------------------------->"); 
	   System.out.println("Transaction ID:"+t.getTransactionId()); 
	//+"||"+"Account_Number:"+t.getUser()+"||"+"Transaction Date"); 
	   System.out.println("Account_Number:"+t.getRec_acc()); 
	   System.out.println("Transaction Type:"+t.getTransaction()); 
	   System.out.println("Transaction Date:"+t.getDate());   
	   if(t.getTransaction().equals("CREDITED")) 
	   { 
	    System.out.println("+"+t.getAmount()); 
	    System.out.println("Current Balance:"+t.getBalance()); 
	    System.out.println("<-------------------------------------->"); 
	   } 
	   else 
	   { 
	    System.out.println("-"+t.getAmount()); 
	    System.out.println("Current Balance:"+t.getBalance());     
	    System.out.println("<-------------------------------------->"); 
	   } 
	  } 
	 } 


}
