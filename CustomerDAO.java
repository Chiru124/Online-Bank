package com.bank.dao;

import java.util.List;

import com.bank.dto.Customer;

public interface CustomerDAO {
	public boolean insertCustomer(Customer c);  
	public Customer getCustomer(String mail,int pin); 
	public Customer getCustomer(long phone,String mail);  
	public Customer getCustomer(long accno);  
	public List getCustomer();  
	public boolean updateCustomer(Customer c);  
	public boolean deleteCustomer(Customer c); 
	public Customer getBankBalance();
	public List getNewCustomers();
	public List getAccountRequest();
	public boolean insertRequests(Customer c);
	public boolean deleteRequest(Customer c);
	public boolean insertDeleteRequests(Customer c);
	public Customer getAccountRequest(long phone);
	public List getDeleteAccounts();
	public Customer getAccountRequest(String mail,int pin);
	public Customer getdeleteRequest(String mail,int pin);
	

}
