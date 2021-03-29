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

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("여기1번");
		String clientId = "ApU4ZbwqZbBR5YTjciim";// 애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:8080/individual_Project/callback", "UTF-8"); 
	   // ex)) "http://localhost:8080/Myproject2/callback" 
		
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;                    
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;          //자신의 클라이언트 아이디랑 콜백주소 State를 합쳐서 api 주소 만들고
		HttpSession session=req.getSession();  // 세션 생성
		String ad = (String) session.getAttribute("nick");
		System.out.println("get id ::"+ad);
		System.out.println("여까지 옴");
		session.setAttribute("session_id", ad); 
		req.setAttribute("apiURL", apiURL);   //api 주소 jsp로 보내기
		
		req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
	}
}
