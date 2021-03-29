package com.individual.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.ContentDTO;
import com.individual.entity.Forum_commentDTO;
import com.individual.service.Service;

@WebServlet("/ForumDetail")
public class Forum_Detail_Controller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Service sv = new Service();

		String userid = req.getParameter("userid");
		String name = req.getParameter("img_name");
		String comment = req.getParameter("comment");
		String seq = req.getParameter("seq");

		Forum_commentDTO fd = new Forum_commentDTO(seq, userid, name, comment);

		sv.forum_comment_write(fd);
		sv.forum_comment_list(seq);
		
		resp.sendRedirect("ForumDetail?seq=" + seq);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		Service sv = new Service();

		sv.forum_click_count(seq);
		ContentDTO list = sv.Forum_detail_info(seq);
		System.out.println("조회수" + list.getHit());
		
		List<Forum_commentDTO> f_list = sv.forum_comment_list(seq);
		
		req.setAttribute("f_list", f_list);
		req.setAttribute("list", list);

		req.getRequestDispatcher("/WEB-INF/view/board/forum_detail.jsp").forward(req, resp);
	}
}
