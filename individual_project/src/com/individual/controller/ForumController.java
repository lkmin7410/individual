package com.individual.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.ContentDTO;
import com.individual.service.Service;

@WebServlet("/Forum")
public class ForumController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Service sv = new Service();

		String search1 = req.getParameter("search");
		String search = "";
//		String seq1= req.getParameter("seq");
//		int seq = Integer.parseInt(seq1);
		
		if (search1 != null) {
			search = search1;
		}
		
		int count = sv.forumCount(search);
		
//		System.out.println(search);
//		System.out.println(count);
		
		String page1 = req.getParameter("page");
		int page = 1;

		if (page1 != null) {
			page = Integer.parseInt(page1);
		}

		List<ContentDTO> list = new ArrayList<ContentDTO>();

		list = sv.getForum_list(page, count, search);
		
//		sv.remove_content_write(seq);
//		for (ContentDTO a : list) {
//			System.out.println(a.getTitle());
//		}
		req.setAttribute("f_list", list);
		req.setAttribute("count", count);

		req.getRequestDispatcher("/WEB-INF/view/board/forum.jsp").forward(req, resp);
	}
}
