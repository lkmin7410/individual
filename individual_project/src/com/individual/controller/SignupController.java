package com.individual.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.UserBeans;
import com.individual.service.Service;

@WebServlet("/signUp")
public class SignupController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id =  req.getParameter("id");
		String pw =  req.getParameter("pw");
		String name =  req.getParameter("name");
		String email =  req.getParameter("email");
		
		System.out.println(req.getParameter("id"));
		System.out.println("pw ::" + pw);
		System.out.println("name ::" + name);
		System.out.println("email ::" + email);
		
		UserBeans ub = new UserBeans(id,pw,name,email);
		Service sv = new Service();
		
		sv.signup(ub);
		
		resp.sendRedirect("Main");
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp").forward(req, resp);
	}
}
