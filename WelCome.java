package com.bank.main;

import java.util.Scanner;

public class WelCome {

	public static void main(String[] args) {
		int choice=0; 
		  Scanner sc=new Scanner(System.in); 
		  System.out.println("<=====Welcome to New Bank=====>"); 
          do {
        	   System.out.println("1. Customer Login"); 
        	   System.out.println("2. Create a account");    
        	   System.out.println("3. Exit"); 
        	   choice=sc.nextInt();   
        	   switch(choice) {
        	   case 1:
        		   Login.login();    
        		   break;
        	   case 2:
        		   Signup.signup();  
        		   break; 
        	   case 3:
        		   System.out.println("Exiting the Application. Thank you"); 
        		   break; 
        	   default: 
        		    System.out.println("Invalid input.Please try again!"); 
        		    break;

        	   }
          }while(choice!=3);
          sc.close();

	}

}
