package com.individual.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.individual.entity.imgDTO;
import com.individual.service.Service;
import com.individual.service.test1;
import com.individual.service.test2;


@WebServlet("/Main")
public class MainController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Random rd = new Random();
		int rd_num = rd.nextInt(1300);
		
		Service sv = new Service();	
		test1 t1 = new test1();
		test2 t2 = new test2();
		
//		List<imgDTO> insert_list = t1.small(rd_num); //작은 이미지를 크롤링 후 이미지 폴더에 사진 저장, db에 경로 저장
//		sv.insertSmallimg(insert_list);
//
//		List<imgDTO> f_insert_list = t2.full(rd_num); 		//큰 이미지를 크롤링 후 이미지 폴더에 사진 저장, db에 경로 저장
//		sv.insertFullimg(f_insert_list); 
//		sv.insertDetail(f_insert_list); 


		//이미지 출력
		List<imgDTO> list = sv.getImgPath();
		
		req.setAttribute("img", list);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
