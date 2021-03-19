package com.individual.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.individual.entity.imgDTO;

public class test1 {
	
	public void test1(){}

	public List<imgDTO> small(int rd_num) {
		List<imgDTO> list = new ArrayList<imgDTO>() ;
		
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
				System.out.println(src); // 작은 이미지

				String[] src1 = src.split("/");
				System.out.println("src1::" + src1[5]);
				System.out.println("src1::" + src1[4]);
				String imgname1 = src1[5].substring(0, src1[5].indexOf("."));
				
				io.setImg_name(imgname1);
				io.setImg_path("img/small/" + imgname1 + ".jpg");
				list.add(io);
				
				// 작은 이미지 저장
				URL imgUrl = new URL(src);
				BufferedImage jpg = ImageIO.read(imgUrl);
				File file = new File(
						"D:\\workspace\\individual_project\\WebContent\\img\\small\\" + imgname1 + ".jpg");
				ImageIO.write(jpg, "jpg", file);

				System.out.println(src1[5]);
				String lastsrc = src1[5].substring(0, src1[5].indexOf(".") + 1);

				String src2 = "https://w.wallhaven.cc/full/" + src1[4] + "/wallhaven-" + lastsrc; // 큰 이미지
				System.out.println(src2);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
			return list;
	}

}
