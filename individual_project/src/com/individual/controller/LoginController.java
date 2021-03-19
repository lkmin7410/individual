package com.individual.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.individual.entity.UserBeans;
import com.individual.service.Service;

@WebServlet("/logIn")
public class LoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		UserBeans ub = new UserBeans(id, pw);
		Service sv = new Service();

		int result = sv.login(ub);
		System.out.println(result);
		if (result == 1) {
//				PrintWriter out = resp.getWriter();
			session.setAttribute("session_id", id);
			resp.sendRedirect("Main");
		} else {
			resp.sendRedirect("logIn");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
	}
}
