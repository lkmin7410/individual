package com.individual.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.imgDTO;
import com.individual.service.Service;

@WebServlet("/Top")
public class Top_ListController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Service sv = new Service();

		String page1 = req.getParameter("page");
		int page = 1;

		if (page1 != null) {
			page = Integer.parseInt(page1);
		}

		System.out.println("####################" + page);
		int result = sv.contentCount();
		List<imgDTO> list = sv.getImgPath(page,result);
		
		System.out.println("count :: "+ result);
//		for(imgDTO a:list) {
//			System.out.println("탑 리스트에서 출력하는 값 :: "+a.getImg_path());
//		}
		

		req.setAttribute("count", result);
		req.setAttribute("img", list);
		req.getRequestDispatcher("/WEB-INF/view/board/top.jsp").forward(req, resp);
	}
}
