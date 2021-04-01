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

<c:set var="root" value="${pageContext.request.contextPath}" />

<style>
body {
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
	width: 90%;
	margin: 0 auto;
	text-align: center;
	padding-bottom: 20px;
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

#form {
	display: inline-block;
}

header .search {
	padding: 10px;
}

header .search .s {
	width: 400px;
	height: 25px;
	font-size: 15px;
}

header .right_menu {
	float: right;
	padding: 10px;
}

section .inner .text {
	text-align: center;
	color: orange;
}

section .inner .text h1 {
	font-size: 40pt;
}

.hr {
	text-align: center;
}

hr {
	width: 90%;
	display: inline-block;
}

section .inner {
	background-color: #1a1a1a;
	box-shadow: 1px 1px 5px rgb(0 0 0/ 33%);
	border-radius: 3px;
}

section .inner .upload_zone {
	text-align: center;
}

section .inner .upload_zone ul {
	width: 85%;
	display: inline-block;
	text-align: center;
	display: inline-block;
	background-color: #151515;
	background: repeating-linear-gradient(45deg, #161616, #161616 2em, #141414 2em,
		#141414 4em);
}

section .inner .upload_zone ul li {
	padding: 35px;
	width: 300px;
	display: inline-block;
}

section .inner .upload_zone ul li img {
	min-height: 100px;
	max-height: 200px;
	min-width: 100px;
	height: 100%;
}

.button, .file {
	display: inline-block;
	background-color: #212121;
	border-radius: 2px;
	box-shadow: inset 0 0 0.75em rgb(255 255 255/ 2%), 0 2px 0 #1c1c1c, 0
		3px 4px -3px #000, 0 1px 2px rgb(0 0 0/ 20%);
	width: 85%;
	height: 50px;
	border-radius: 2px;
	box-shadow: inset 0 0 0.75em rgb(255 255 255/ 2%), 0 2px 0 #1c1c1c, 0
		3px 4px -3px #000, 0 1px 2px rgb(0 0 0/ 20%);
	border-radius: 2px;
}

.file h2 {
	color: white;
}

.content p {
	color: white;
}

.content img {
	width: 50%;
}

.comment_zone {
	color: white;
	
}
.comment_zone ul{

}

.comment_zone li {
	width: calc(100% / 3);
	float: left;
	padding: 20px;
	box-sizing: border-box;
}

.text_bar {
margin-top:50px;
width: 700px;
height: 75px;

}
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

			<form action="Search" id="form">
				<div class="search">
					<input type="search" name="search" class="s"> <input
						type="submit" value="검색">
				</div>
			</form>

			<div class="right_menu">
				<c:if test="${not empty sessionScope.session_id}">
					<a href="Logout">Log out</a>
				</c:if>
				<c:if test="${empty sessionScope.session_id}">
					<a href="logIn">Log in</a>
					<a href="signUp">Sign Up</a>
				</c:if>
			</div>
		</div>
	</header>

	<section id="upload">
		<div class="inner">
			<div class="text">
				<h1>W Share</h1>
				<c:if test="${sessionScope.session_id == list.writer}">
				<a href="Edit_Write?seq=${seq}">글 수정</a>
				<a href="Remove_Forum?seq=${seq}">글 삭제</a>
				</c:if>
			</div>
			<div class="hr">
				<hr>
			</div>
			<div class="file">
				<h2>${list.title}</h2>
			</div>

			<div class="upload_zone">
				<div class="content">
					<c:if test="${list.img != null }">
						<img src="${root}/upload/${list.img}">
					</c:if>
				</div>
			</div>

			<div class="button">
				<div class="content">
					<p>${list.content}</p>
				</div>
			</div>

			<div class="button">
				<div class="content">
					<hr>
					<form action="ForumDetail" method="post">
						<div class="input">

							<input type="hidden" name="userid" value="${session_id}">
							<input type="hidden" name="seq" value="${param.seq}">
							<%-- <c:if test="${not empty sessionScope.nick}">
							
							</c:if> --%>
							<input type="hidden" name="img_name" value="${list.img}">
							<c:if test="${session_id != null}">
								<input type="text" name="comment" class="text_bar">
								<input type="submit" value="입력">
							</c:if>
							<c:if test="${session_id == null}">
								<input type="text" value="로그인을 해주세요.">
							</c:if>

							<div class="comment_zone">
							<ul>
								<c:forEach var="i" items="${f_list}">
									<li class="id">${i.userid}</li>
									<li class="comment">${i.comment}</li>
									<li class="regdate">${i.regdate}</li>
									<hr>
								</c:forEach>
								</ul>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</section>

</body>

</html>