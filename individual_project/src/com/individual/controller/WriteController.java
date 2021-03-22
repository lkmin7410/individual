package com.individual.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.individual.entity.ContentDTO;
import com.individual.service.Service;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 임시파일의 크기
		maxFileSize = 1024 * 1024 * 50, // 최대 업로드 파일 용량
		maxRequestSize = 1024 * 1024 * 5 * 5 // 전체요청에 대한 파일 용량
)

@WebServlet("/Write")
public class WriteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Service sv = new Service();
		String img = null;
		String writer = req.getParameter("session_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		Collection<Part> parts = req.getParts();
		StringBuilder builder = new StringBuilder();
		String filePath = null;
		String fileName = null;

		for (Part p : parts) {
			if (!p.getName().equals("file"))
				continue;
			if (p.getSize() == 0)
				continue;

			Part filePart = p;
			fileName = p.getSubmittedFileName();

			builder.append(fileName);
			builder.append(",");

			InputStream fis = filePart.getInputStream();
			String realPath = req.getServletContext().getRealPath("upload");
			System.out.println("realpath : " + realPath);

			filePath = realPath + File.separator + fileName;
			System.out.println(filePath);

			File fl = new File(realPath);
			if (!fl.exists())
				fl.mkdirs();

			FileOutputStream fos = new FileOutputStream(filePath);

			byte[] buf = new byte[1024]; // 파일 옮기는구문
			int size = 0; // 파일 옮기는구문 갯수

			while ((size = fis.read(buf)) != -1) // 파일 옮기는구문
				fos.write(buf, 0, size); // 파일 옮기는구문
			fos.close();
			fis.close();
		}

		img = fileName;

		ContentDTO cd = new ContentDTO(title, content, writer, img);

		sv.content_write(cd);

		resp.sendRedirect("Forum");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/board/write.jsp").forward(req, resp);
	}
}
