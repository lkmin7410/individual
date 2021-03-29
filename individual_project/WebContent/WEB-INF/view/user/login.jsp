<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>회원가입page</title>
<link rel="stylesheet" href="${root}/css/user/style.css?ver=1">
<script type="text/javascript" src="${root}/js/user/signUp.js"></script>
</head>
<body>
<a href="${apiURL}">네이버 로그인</a>
<img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG">
	<form id="joinform" name="joinform" action="logIn" method="post"
		onsubmit="return createFrom(this)">

		<h4 style="text-align: center;">로그인(*필수입력사항입니다.)</h4>
		<div class="menu" style="border-bottom-width: 0px;">


			<div id="id">아이디</div>
			<span>* <input type="text" class="checkInfo" name="id"
				size="12" />
			</span>
		</div>

		<div class="menu " style="border-bottom-width: 0px;">
			<div id="id">비밀번호</div>
			<span>* <input type="password" class="checkInfo" name="pw"
				size="12" />
			</span>
		</div>

		<div class="menu" style="text-align: center;">
			<span> <input type="submit" value="로그인" /> <input
				type="reset" value="취소" />
			</span>
		</div>
		
		<div class="sign">
			<a href="signUp">회원가입</a>
		</div>
	</form>
</body>
</html>