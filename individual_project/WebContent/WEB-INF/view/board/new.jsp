<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<style>
body {
/* background: url(//static.wallhaven.cc/images/layout/blue-gradients.020-wh.jpg) top center/cover no-repeat fixed,#171717 url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left repeat; */
	background: #171717
		url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left
		repeat;
}

* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: orange;
}

ul, li {
	list-style: none;
}

.inner {
	width: 1650px;
	max-width: 1650px;
	height: 1120px;
	margin: 0 auto;
}

header {
	height: 110px;
}

header .top .logo {
	float: left;
	padding-left: 10px;
}

header .top .menu li {
	float: left;
	padding: 10px;
}

header .search {
	display: inline-block;
	padding: 10px;
}

header .search input {
	width: 400px;
	height: 25px;
	font-size: 15px;
}

header .right_menu {
	float: right;
	padding: 10px;
}

section .choice {
	padding-left: 10px;
	height: 55px;
	color: white;
}

section .img_list ul {
	text-align: center;
}

section .img_list ul li {
	display: inline-block;
	text-align: center;
	margin-bottom: 10px;
	    padding: 0 4px;
}
section .img_list ul li:hover {
opacity: 0.5;
}


section .img_list ul li img {
	border-radius: 5px;
	box-sizing: border-box;
	box-shadow: 0 0 8px rgb(0 0 0/ 80%);
}

section .inner{
text-align: center;
}
section .button {
	display: inline-block;
}

section .button ul li{
	float: left;
	
}

section .button ul li a{
color: white;
margin: 15px;
font-size: 20px;
}
</style>

</head>

<body>

	<header>
		<div class="top">
			<div class="logo">
				<a href="Main"><h1>W Share</h1></a>
			</div>
			<ul class="menu">
				<li><a href="New">NEW</a></li>
				<li><a href="Top">TOP LIST</a></li>
				<li><a href="Random">RANDOM</a></li>
				<li><a href="Upload">UPLOAD</a></li>
				<li><a href="Forum">FORUM</a></li>
			</ul>

			<form action="Search">
		<div class="search">
			<input type="search" name="search">
		</div>
		<input type="submit" value="검색">
		
		</form>

			<div class="right_menu">
					<c:if test="${not empty sessionScope.session_id}">
					<a href="Main">Log out</a>
				</c:if>
				<c:if test="${empty sessionScope.session_id}">
					<a href="logIn">Log in</a>
					<a href="signUp">Sign Up</a>
				</c:if>
			</div>
		</div>
	</header>


	<section>
		<div class="inner">
			<div class="choice">
				<h1>New List</h1>
				<p>최근 배경</p>
			</div>

			<div class="img_list">
				<ul>
					<c:forEach var="i" items="${img}">
						<li><a href="Detail?img_name=${i.img_name}"> <img
								src="${i.img_path}"></a></li>
					</c:forEach>
				</ul>
			</div>

			<!-- 페이징 설정을 위한 Startnum, Lastnum, Page 변수 -->
			<c:set var="page" value="${(empty param.page)?1:param.page}"></c:set>
			<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
			<c:set var="lastNum"
				value="${fn:substringBefore(Math.ceil(count/19), '.')}"></c:set>

		
				<div class="button">
					<!-- 이전 버튼 -->
					<ul class="pagination">
						<li><c:if test="${startNum > 1}">
								<li><a class="page-link" href="?page=${startNum-1}">이전</a></li>
							</c:if> <c:if test="${startNum <= 1}">
								<li><a class="page-link" href="#"
									onclick="alert('첫번째 페이지 입니다.');">이전</a></li>
							</c:if></li>
						<li>
							<!-- 페이징 --> <c:forEach var="i" begin="0" end="4">
								<c:if test="${(startNum+i) <= lastNum}">
									<li><a class="page-link" href="New?page=${startNum+i}">${startNum+i}</a></li>
								</c:if>
							</c:forEach>
						</li>
						<li>
							<!-- Next 버튼 --> <c:if test="${startNum+5 <= lastNum}">
								<li><a class="page-link" href="?page=${startNum+5}">다음</a></li>
							</c:if> <c:if test="${startNum+5 > lastNum}">
								<li><a class="page-link" href="#"
									onclick="alert('마지막 페이지 입니다.');">다음</a></li>
							</c:if>
						</li>
				</div>
			



			<%-- <ul>
					<c:forEach var="i" items="${img}">
						<li><img src="${i.img_path}"></li>
					</c:forEach>
				</ul> --%>
		</div>
	</section>

</body>

</html>