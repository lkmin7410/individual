package com.individual.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.service.Service;


@WebServlet("/user/idCheck")
public class idCheckController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");	
		int check = Service.getInstance().idCheck(id);	
		req.setAttribute("check", check);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/WEB-INF/view/user/idCheck.jsp").forward(req, resp);
	}

}
