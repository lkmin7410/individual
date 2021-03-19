package com.individual.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Upload")

//@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
//maxFileSize=1024*1024*50,      	// 50 MB
//maxRequestSize=1024*1024*100)   // 100 MB

public class UploadController extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.getRequestDispatcher("/WEB-INF/view/board/upload.jsp").forward(req, resp);
			
			String file = req.getParameter("file_select");
			System.out.println(file);
			
			
//		   	resp.setContentType("image/jpeg"); // 컨텐츠타입 설정
//		    // -> body에 binary data 들어갈 것임
//
//		    //파일을 출력한다.
//		    ServletOutputStream out = resp.getOutputStream();
//
//		    //파일을 읽어온다.
//		    FileInputStream fis = new FileInputStream("C:\\workspace\\individual_project\\WebContent\\img\\full\\28823x.jpg");
//
//		    // 입출력 속도 향상을 위한 버퍼드 스트림 사용
//		    BufferedInputStream bis = new BufferedInputStream(fis);
//		    BufferedOutputStream bos = new BufferedOutputStream(out);
//
//		    int readBytes = 0; // 읽은 바이트 수
//		    while((readBytes = bis.read()) != -1) {
//		      bos.write(readBytes);
//		    }
//
//		    bis.close();
//		    bos.close();
		  }
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String file = req.getParameter("file_select");
		System.out.println(file);
//		doGet(req, resp);
	}

}
