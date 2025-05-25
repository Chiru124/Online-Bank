package com.bank.servlet;

import java.io.IOException;

import com.bank.dto.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logs=req.getParameter("logout");
		HttpSession session=req.getSession(false);
		Customer c=(Customer)session.getAttribute("customer");
		if(logs.equals("Logout") && session!=null)
		{
			session.invalidate();
			req.setAttribute("succes", "Loggged out Succefully");
			RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
			rd.forward(req, resp);
			
		}
		}
	

}
