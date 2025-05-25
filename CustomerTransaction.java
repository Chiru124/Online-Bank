package com.bank.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.bank.dao.CustomerDAO;
import com.bank.dao.CustomerDAOImp1;
import com.bank.dao.TransactionDAO;
import com.bank.dao.TransactionDAOImpl;
import com.bank.dto.Customer;
import com.bank.dto.Transaction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/search")
public class CustomerTransaction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acc=req.getParameter("search");
		long acc_no=Long.parseLong(acc);
		CustomerDAO cdao=new CustomerDAOImp1();
		Customer c=cdao.getCustomer(acc_no);
		 TransactionDAO tdao=new TransactionDAOImpl();
         List<Transaction> transactions=tdao.getTransaction(acc_no); 
 	    Iterator<Transaction> it=transactions.iterator();
	    if(it.hasNext()) {
	    	req.setAttribute("accNumber",acc);
			RequestDispatcher rd=req.getRequestDispatcher("CustomerTransaction.jsp");
			rd.forward(req, resp);
	    }
	    else {
	    	req.setAttribute("fail","Havn't any Transaction History");
			RequestDispatcher rd=req.getRequestDispatcher("CustomerTransaction.jsp");
			rd.forward(req, resp);
	    	
	    }
	}

}
