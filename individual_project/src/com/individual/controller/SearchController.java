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

@WebServlet("/Search")
public class SearchController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = req.getSession();
		Service sv = new Service();
		String page1 = req.getParameter("page");
		int page = 1;
		if (page1 != null) {
			page = Integer.parseInt(page1);
		}

		String sc = req.getParameter("search");
		System.out.println(sc);

		List<imgDTO> list_sc = sv.search(sc, page);

		int result = sv.contentCount_search(sc);
		System.out.println(result);

		req.setAttribute("count", result);
		req.setAttribute("search", list_sc);
		
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Service sv = new Service();
		String page1 = req.getParameter("page");
		int page = 1;
		if (page1 != null) {
			page = Integer.parseInt(page1);
		}

		String tag = req.getParameter("tag");
		System.out.println(tag);
		
		String sc = req.getParameter("search");
		System.out.println(sc);

		List<imgDTO> list_sc = sv.search(sc, page);

		int result = sv.contentCount_search(sc);
		System.out.println(result);

		req.setAttribute("count", result);
		req.setAttribute("search", list_sc);
		req.getRequestDispatcher("/WEB-INF/view/board/search.jsp").forward(req, resp);
	}
}
