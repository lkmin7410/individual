package com.individual.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.ContentDTO;
import com.individual.service.Service;

@WebServlet("/ForumDetail")
public class Forum_Detail_Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		Service sv = new Service();
		
		sv.forum_click_count(seq);
		ContentDTO list = sv.Forum_detail_info(seq);
		
		System.out.println(list.getHit());
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/view/board/forum_detail.jsp").forward(req, resp);
	}
}
