package com.bank.main;

import java.util.Scanner;

import com.bank.dto.Customer;
import com.bank.dao.CustomerDAO;
import com.bank.dao.CustomerDAOImp1;

public class Login {

	public static void login() {
		Scanner sc=new Scanner(System.in);  
		  CustomerDAO cdao=new CustomerDAOImp1();  
		  System.out.println("Enter your Account number"); 
		  String accno=sc.nextLine();  
		  System.out.println("Enter your PIN"); 
		  int pin=sc.nextInt(); 
		  Customer c=cdao.getCustomer(accno, pin); 
		  if(c!=null) 
		  { 
			  if(c.getAccno()==1100110011) {
				  System.out.println("welcome Admin :"+c.getName());
				  Admin.admin(c);
			  }
			  else {
		   System.out.println("Login successful!"); 
		   App.options(c); 
			  }
		  } 
		  else 
		  { 
		   System.out.println("Failed to login!"); 
		  }   
		 } 

		
	}

