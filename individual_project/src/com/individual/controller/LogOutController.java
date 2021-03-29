package com.individual.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.individual.entity.imgDTO;
import com.individual.service.Service;
import com.individual.service.test1;


@WebServlet("/Logout")
public class LogOutController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("session_id") != null || session.getAttribute("nick") != null) {
		session.removeAttribute("session_id");
		session.removeAttribute("nick");
		}else {
		session.removeAttribute("state");
		}
		resp.sendRedirect("/individual_Project/Main");
	}
}
