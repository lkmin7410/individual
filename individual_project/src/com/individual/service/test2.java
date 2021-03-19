package com.individual.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.individual.entity.imgDTO;

public class test2 {

	public void test2(){}
	
	public List<imgDTO> full(int rd_num) {
		List<imgDTO> list = new ArrayList<imgDTO>() ;
		// TODO Auto-generated method stub
		try {
			// 1. 수집 대상 URL
			String URL = "https://wallhaven.cc/search?q=id%3A711&sorting=random&ref=fp&seed=wtYF2i&page="+rd_num;
			
			// 2. Connection 생성
			Connection conn = Jsoup.connect(URL);

			// 3. HTML 파싱.
			Document html = conn.get(); // conn.post();
			Elements el = html.select(".lazyload");
			
			for (Element elem : el) {
				imgDTO io = new imgDTO();
				
				String src = elem.attr("data-src");
				System.out.println(src); // 큰 이미지

				String[] src1 = src.split("/");
				System.out.println("src1::" + src1[5]);
				System.out.println("src1::" + src1[4]);
				String imgname2 = src1[5].substring(0, src1[5].indexOf("."));  //img name
				System.out.println(src1[5]);

				String lastsrc = src1[5].substring(0, src1[5].indexOf(".") + 1);
				String src2 = "https://w.wallhaven.cc/full/" + src1[4] + "/wallhaven-" + lastsrc; // 큰 이미지
				System.out.println(src2);
				
				String src3="";
				String fullfilesrc = "";
				// 큰 이미지 저장
				try {
					src3 = src2+"jpg";
					URL imgUrl2 = new URL(src3);
					BufferedImage jpg2 =ImageIO.read(imgUrl2);
					File file2 = new File("D:\\workspace\\individual_project\\WebContent\\img\\full\\"+(imgname2)+".jpg");
					ImageIO.write(jpg2, "jpg", file2);
					fullfilesrc = "img/full/"+(imgname2)+".jpg";
					
				} catch(Exception e) {
					src3 = src2+"png";
					URL imgUrl2 = new URL(src3);
					BufferedImage png =ImageIO.read(imgUrl2);
					File file2 = new File("D:\\workspace\\individual_project\\WebContent\\img\\full\\"+(imgname2)+".png");
					ImageIO.write(png, "png", file2);
					fullfilesrc = "img/full/"+(imgname2)+".png";
				}
				
				io.setImg_name(imgname2);
				io.setImg_path(fullfilesrc);

				
				String URL2 = "https://wallhaven.cc/w/"+imgname2;
				Connection conn2 = Jsoup.connect(URL2);
				Document html2 = conn2.get(); // conn.post();
				
				Elements size = html2.select("#wallpaper");
				String width = size.attr("data-wallpaper-width");
				String height = size.attr("data-wallpaper-height");
				
				System.out.println("size :: "+size);
				System.out.println("width :: "+width);
				System.out.println("height :: "+height);
				
				io.setWidth(width);
				io.setHeight(height);
				
				//태그
				Elements tag = html2.select("title");
				System.out.println(tag.text());
				//db에는 전체를 담고, 출력할때 스플릿 하자 
				
				String a = tag.toString();
				String tags = a.substring(a.indexOf(" "),a.lastIndexOf("|"));
				
//				String str []= tags.split(",|\\|"); // "|" 을 스플릿 할때는 \\ 를 붙여준다.
//				for(int i = 1; i<str.length-1;i++) {
//					System.out.println("태그 :: "+str[i]);
//				}
				io.setTags(tags);
				
				//업로더 
				Elements info = html2.select(".showcase-uploader img");
				System.out.println(info);
				
				String info1 = info.attr("alt");
				System.out.println("업로더 :: "+info1);
				io.setUploader(info1);
				
				list.add(io);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
