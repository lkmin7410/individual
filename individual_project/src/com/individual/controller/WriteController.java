package com.individual.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.ContentDTO;
import com.individual.service.Service;

@WebServlet("/Write")
public class WriteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Service sv = new Service();
		
		String writer = req.getParameter("session_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		ContentDTO cd = new ContentDTO(title,content,writer);
		
		sv.content_write(cd);
		
		resp.sendRedirect("Forum");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/board/write.jsp").forward(req, resp);
	}
}
