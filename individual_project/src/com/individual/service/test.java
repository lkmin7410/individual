package com.individual.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.individual.entity.imgDTO;

public class test {
	
//	Elements el = html.select(".feat-row img");
//	String src = elem.attr("src");
//	메인화면 사진 크롤링 
//	===================
//	Elements el = html.select(".lazyload");
//	String src = elem.attr("data-src");
//	탑 리스트 사진 크롤링
//	===================

	public void test() {}
	
	
	public static void main(String[] args) {
		
		Random rd = new Random();
		
		try {
			// 1. 수집 대상 URL
			String URL = "https://wallhaven.cc/w/m93dgm";//+rd.nextInt(126);

			// 2. Connection 생성
			Connection conn = Jsoup.connect(URL);

			// 3. HTML 파싱.
			Document html = conn.get(); // conn.post();
			//이미지 사이즈
			Elements size = html.select("#wallpaper");
			String width = size.attr("data-wallpaper-width");
			String height = size.attr("data-wallpaper-height");
			
			System.out.println("size :: "+size);
			System.out.println("width :: "+width);
			System.out.println("height :: "+height);
			
			
			//태그
			Elements tag = html.select("title");
			System.out.println(tag.text());
			//db에는 전체를 담고, 출력할때 스플릿 하자 
			
			String a = tag.toString();
			String tags = a.substring(a.indexOf(" "),a.lastIndexOf("|"));
			
//			String str []= tags.split(",|\\|"); // "|" 을 스플릿 할때는 \\ 를 붙여준다.
//			for(int i = 1; i<str.length-1;i++) {
//				System.out.println("태그 :: "+str[i]);
//			}
			
			//업로더 
			Elements info = html.select(".showcase-uploader img");
			System.out.println(info);
			
			String info1 = info.attr("alt");
			System.out.println("업로더 :: "+info1);
			
			
//			System.out.println(db.getImg_name());
//			System.out.println(db.getWidth());
//			System.out.println(db.getHeight());
//			System.out.println(db.getTags());
//			System.out.println(db.getUploader());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
