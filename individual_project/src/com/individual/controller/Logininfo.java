package com.individual.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.individual.entity.UserBeans;
import com.individual.service.Service;


@WebServlet("/user/logininfo")
public class Logininfo extends HttpServlet{
	                                                                                                 //여기서 날려준 access값을 String으로 받아줌
	protected void doGet(HttpServletRequest req, HttpServletResponse resp, String access_token) throws ServletException, IOException {
		UserBeans ub = new UserBeans();
		Service sv = new Service();
		
		System.out.println("에씨씨: "+access_token);               //함 찍어보고 
        String token = access_token; // 네이버 로그인 접근 토큰(access토큰값 받아온거 넣기);
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders); //  이게 회원 정보를 한줄로 받아오는놈
        
        JSONParser jp = new JSONParser();
        
        JSONObject jo;
        
        try {
        	jo = (JSONObject) jp.parse(responseBody);
        	JSONObject jo2 = (JSONObject) jo.get("response");
        	String id = "naver_"+jo2.get("id").toString();
        	String name = jo2.get("name").toString();
        	String email = jo2.get("email").toString();
        	String nickname = jo2.get("nickname").toString();
        	System.out.println("id: "+id);
        	System.out.println("name: "+name);
        	System.out.println("email: "+email);
        	System.out.println("nickname: "+nickname);
        	
        	ub.setId(id);
        	ub.setNaver_name(name);
        	ub.setNaver_email(email);
        	ub.setNaver_nickname(nickname);
        	
        	int result = sv.idCheck(id);
        	
        	if(result == 0) {
        		sv.naver_login(ub);
        	}
        	HttpSession session=req.getSession(); 
        	session.setAttribute("nick", nickname);
        	
		} catch (Exception e) {
			e.printStackTrace();
		}

        resp.sendRedirect("Main");
	}

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}