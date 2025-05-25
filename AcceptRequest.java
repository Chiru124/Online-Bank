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
@WebServlet("/accept")
public class AcceptRequest extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phoneNo=req.getParameter("accept");
		long phone=Long.parseLong(phoneNo);
		CustomerDAO cdao=new CustomerDAOImp1();
		Customer c=cdao.getAccountRequest(phone);
		if(c!=null) {
		cdao.insertCustomer(c);
		cdao.deleteRequest(c);
		RequestDispatcher rd=req.getRequestDispatcher("AccountRequests.jsp");
		rd.forward(req, resp);
		}
	}

}
