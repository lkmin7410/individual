package com.individual.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.BoardDTO;
import com.individual.entity.imgDTO;
import com.individual.service.Service;

@WebServlet("/Detail")
public class DetailController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String img_name = req.getParameter("img_name");
		String comment = req.getParameter("comment");
		
		System.out.println(comment);
		
		String userid = req.getParameter("userid");
		BoardDTO bd = new BoardDTO();
		bd.setImg_name(img_name);
		bd.setComment(comment);
		bd.setUserid(userid);
		Service sv = new Service();
		sv.comment_write(bd);
		
		resp.sendRedirect("Detail?img_name="+img_name);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String img_name = req.getParameter("img_name");
		Service sv = new Service();
		sv.click_count(img_name);
		List<BoardDTO>c_list = sv.comment_list(img_name);
		imgDTO img = sv.getImgPath_full(img_name);
		imgDTO info = sv.detail_info(img_name);
		List<imgDTO> tag = sv.detail_info_tag(img_name);
		

//		for(imgDTO a : tag) {
//			System.out.println("return 받고 ForEach - tag :: "+a.getTag());
//		}

		req.setAttribute("full", img);
		req.setAttribute("info", info);
		req.setAttribute("tag", tag);
		req.setAttribute("c_list", c_list);
		req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);
	}
}
