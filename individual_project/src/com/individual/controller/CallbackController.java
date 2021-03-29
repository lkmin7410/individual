package com.individual.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/callback")
public class CallbackController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("콜백 여까지옴");
		String clientId = "ApU4ZbwqZbBR5YTjciim";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "iQw4thrb5f";// 애플리케이션 클라이언트 시크릿값";
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8080/individual_Project/logininfo", "UTF-8");
//ex)) http://3.36.176.60:3306/Myproject2/logininfo
		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";     //회원정보 받기 위해서는 access_token값을 받을 필요가 있음
		String refresh_token = "";
		System.out.println("apiURL=" + apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();    //
				Object obj = parsing.parse(res.toString());   //
				JSONObject jsonObj = (JSONObject) obj;   //  제이슨 오브젝트에서 access_token 꺼내기 위한 작업(어떻게 했었는지 잘 기억이 안남;)

				access_token = (String) jsonObj.get("access_token");  // access_token값을 받는다 
				refresh_token = (String) jsonObj.get("refresh_token"); // refresh 이거는 토큰 갱신할때 필수라고 함

				System.out.println("acc_to: " + access_token);
				
				Logininfo li = new Logininfo();   // 회원정보를 받기위해서 Logininfo 라고 컨트롤러를 하나 생성해서 
				li.doGet(req, resp, access_token); //토큰값을 두겟으로 보냄 두겟 생성하면 req, resp 자동으로 받아있는데 거기에 뒤에 access토큰을 추가로 보냄
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
