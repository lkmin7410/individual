<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

 .search {
	display: inline-block;
	padding: 10px;
}

 .search input {
	width: 400px;
	height: 25px;
	font-size: 15px;
}

header .right_menu {
	float: right;
	padding: 10px;
}

#section .top_logo {
	text-align: center;
	margin-bottom: 15px;
}

#section .top_logo h1 {
	color: orange;
	font-size: 40px;
}

#section .inner {
	width: 1400px;
	max-width: 1400px;
	margin: 0 auto;
	height: 666px;
	background-color: white;
}

#section .inner .board .content ul {
	
}

#section .inner .board .content ul li {
	float: left;
	width: calc(100%/ 5);
	text-align: center;
}

#section .inner .top_nav {
	width: 100%;
	height: 50px;
	background-color: #212121;
	border-radius: 2px;
	box-shadow: inset 0 0 0.75em rgb(255 255 255/ 2%), 0 2px 0 #1c1c1c, 0
		3px 4px -3px #000, 0 1px 2px rgb(0 0 0/ 20%);
	display: inline-block;
	color: white;
}

#section .inner .board .top_nav ul li {
	font-size: 30px;
	padding: 10px 0;
}

#section .inner {
	width: 100%;
	background-color: #151515;
	background: repeating-linear-gradient(45deg, #161616, #161616 2em, #141414 2em,
		#141414 4em);
}

#section .inner .board .text_zone ul li {
	padding: 5px 0;
	border-bottom: 1px solid silver;
	height: 50px;
	line-height: 50px;
}

section .button {
	display: inline-block;
}

section .button ul li {
	float: left;
}

section .button ul li a {
	color: white;
	margin: 15px;
	font-size: 20px;
}

section .footer{
text-align: center;
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
				<li><a href="">FORUM</a></li>
			</ul>

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


	<section id="section">
		<div class="top_logo">
			<h1>BOARD</h1>
		</div>
		<div class="inner">

			<div class="board">
				<div class="content top_nav">
					<ul>
						<li>??? ??????</li>
						<li>??? ??????</li>
						<li>?????????</li>
						<li>?????????</li>
						<li>?????????</li>
					</ul>
				</div>
				<hr>
				<div class="content text_zone">
					<ul>
						<c:forEach var="l" items="${f_list}">
							<li><a href="">${l.seq}</a></li>
							<li><a href="ForumDetail?seq=${l.seq}">${l.title}</a></li>
							<li><a href="">${l.regdate}</a></li>
							<li><a href="">${l.writer}</a></li>
							<li><a href="">${l.hit}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="write">
			<c:if test="${empty sessionScope.session_id}">
				<a href="logIn" onclick="alert('????????? ?????? ??????????????????.')">?????????</a>
				</c:if>
			<c:if test="${not empty sessionScope.session_id}">
				<a href="Write">?????????</a>
				</c:if>
			</div>

		</div>

		<!-- ????????? ????????? ?????? Startnum, Lastnum, Page ?????? -->
		<c:set var="page" value="${(empty param.page)?1:param.page}"></c:set>
		<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
		<c:set var="lastNum"
			value="${fn:substringBefore(Math.ceil(count/10), '.')}"></c:set>

	<div class="footer">
		<div class="button">
			<!-- ?????? ?????? -->
			<ul class="pagination">
				<li><c:if test="${startNum > 1}">
						<li><a class="page-link" href="?page=${startNum-1}">??????</a></li>
					</c:if> <c:if test="${startNum <= 1}">
						<li><a class="page-link" href="#"
							onclick="alert('????????? ????????? ?????????.');">??????</a></li>
					</c:if></li>
				<li>
					<!-- ????????? --> <c:forEach var="i" begin="0" end="4">
						<c:if test="${(startNum+i) <= lastNum}">
							<li><a class="page-link" href="Forum?page=${startNum+i}">${startNum+i}</a></li>
						</c:if>
					</c:forEach>
				</li>
				<li>
					<!-- Next ?????? --> <c:if test="${startNum+5 <= lastNum}">
						<li><a class="page-link" href="?page=${startNum+5}">??????</a></li>
					</c:if> <c:if test="${startNum+5 > lastNum}">
						<li><a class="page-link" href="#"
							onclick="alert('????????? ????????? ?????????.');">??????</a></li>
					</c:if>
				</li>
		</div>
			<form action="Forum">
				<div class="search">
					<input type="search" name="search">
				</div>
				<input type="submit" value="??????">
			</form>
</div>

	</section>
	

</body>
</html>