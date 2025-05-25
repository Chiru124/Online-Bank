package com.bank.servlet;

import java.io.IOException;

import com.bank.dao.CustomerDAO;
import com.bank.dao.CustomerDAOImp1;
import com.bank.dao.TransactionDAO;
import com.bank.dao.TransactionDAOImpl;
import com.bank.dto.Customer;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/delete")
public class DeleteAcc extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acc=req.getParameter("delete");
		long acc_no=Long.parseLong(acc);
		CustomerDAO cdao=new CustomerDAOImp1(); 
		 TransactionDAO tdao=new TransactionDAOImpl();
		 HttpSession session=req.getSession(false);
		 Customer c=(Customer)session.getAttribute("customer");
		Customer c1=cdao.getCustomer(acc_no); 
		String name=c1.getName();
		//getCustomer(long accno) already overridden in transferAmount module inside CustomerDAOImpl 
		    if(c.getAccno()!=c1.getAccno()) 
		    { 
		     double amount=c1.getBal(); 
		     c.setBal(c.getBal()+c1.getBal()); 
		     boolean res1=cdao.updateCustomer(c); 
		//updateCustomer() already overridden in deposit module inside CustomerDAOImpl 
		     if(res1) 
		     { 
		      Transaction t1=new Transaction(); 
		      t1.setTransactionId(TransactionID.generateTransactionId()); 
		      t1.setUser(c.getAccno());      
		      t1.setRec_acc(c1.getAccno());      
		      t1.setAmount(amount);     
		      t1.setTransaction("CREDITED");     
		      t1.setBalance(c.getBal());     
		      boolean res=tdao.insertTransaction(t1); 
		 
		//insertTransaction() already overridden in deposit module inside TransactionDAOImpl 
		      if(res1&&res) 
		      {  
		        cdao.deleteCustomer(c1); 
		        session.setAttribute("success",name+" Account deleted Successfully");
		        RequestDispatcher rd=req.getRequestDispatcher("CustomersProfile.jsp");
		        rd.forward(req, resp);
		      } 
		      else 
		      { 
		       session.setAttribute("Failure","Failed to delete");
		        RequestDispatcher rd=req.getRequestDispatcher("CustomersProfile.jsp");
		        rd.forward(req, resp);
		      } 
		 
		     } 
		     else 
		     {  
		      session.setAttribute("Failure","Failed to delete; amount transfer failed from the customer account");
		        RequestDispatcher rd=req.getRequestDispatcher("CustomersProfile.jsp");
		        rd.forward(req, resp);
		     }     } 
		    else 
		    { 
		     session.setAttribute("Failure","Cannot delete the admin data");
		     RequestDispatcher rd=req.getRequestDispatcher("CustomersProfile.jsp");
		     rd.forward(req, resp);
		     
		    }
	}

}
