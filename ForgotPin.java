package com.bank.servlet;

import java.io.IOException;

import com.bank.dao.CustomerDAO;
import com.bank.dao.CustomerDAOImp1;
import com.bank.dto.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/forgot")
public class ForgotPin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phoneNo=req.getParameter("phone");
		String mail=req.getParameter("mail");
		String password=req.getParameter("password");
		String confirm_password=req.getParameter("confirm_password");
		long phone=Long.parseLong(phoneNo);
		int pass=Integer.parseInt(password);
		int pass1=Integer.parseInt(confirm_password);
		CustomerDAO cdao=new CustomerDAOImp1();
		Customer c=cdao.getCustomer(phone,mail);
		if(c!=null&&pass==pass1) {
			 c.setPin(pass); 
			 boolean result=cdao.updateCustomer(c);   
			 if(result) 
			   { 
				   req.setAttribute("success","password updated successfully");
					RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
					rd.forward(req, resp); 
			   } 
			   else 
			   { 
				   req.setAttribute("failed","fail to update the password");
					RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
					rd.forward(req, resp); 
			   } 
			   
			  } 
			  else 
			  { 
				   req.setAttribute("failed","fail to update the password");
					RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
					rd.forward(req, resp); 
			  } 
		}
	}

