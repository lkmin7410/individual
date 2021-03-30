package com.individual.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.service.Service;

@WebServlet("/Remove_Forum")
public class Remove_ForumController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq1 = req.getParameter("seq");
		int seq = Integer.parseInt(seq1);
		
		Service sv = new Service();
		
		sv.remove_content_write(seq);
		
		
		resp.sendRedirect("Forum");
	}
}
