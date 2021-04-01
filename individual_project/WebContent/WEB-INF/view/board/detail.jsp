<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<style>
body {
	text-align: center;
}

body, html {
	height: 100%;
	width: 100%;
	min-width: 800px;
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

div {
	display: block;
}

/* header st */
header {
	position: fixed;
	z-index: 300;
	width: 100%;
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
	padding-right: 170px;
}

header .search .search_bar {
	width: 400px;
	height: 25px;
	font-size: 15px;
}

header .right_menu {
	float: right;
	padding: 10px;
}

/* header end */

/* aside st */
.sidebar {
	z-index: 150;
	bottom: 0;
	top: 50px;
	transition: .15s top;
	width: 17%;
	height: 915px;
	max-height: 1300px;
	overflow: visible;
	float: left;
}

.sidebar {
	background-color: #1b1b1b;
	border-color: #292929;
	box-shadow: 0 0 7px rgb(0 0 0/ 50%);
	color: white;
	padding: 1px;
	position: fixed;
}

aside .side_bar_back {
	position: relative;
	height: 100%;
	overflow: hidden;
}

aside .side_bar_contents {
	height: 100%;
	overflow-x: hidden;
	overflow-y: scroll;
	padding: 1em 2em;
	text-shadow: 0 1px 1px #000;
}

aside .side_bar_contents h3 {
	font-size: 2em;
	font-weight: 400px;
}

aside .side_bar_contents .side_bar_section .tag li {
	position: relative;
	display: inline-block;
	margin: 0 4px 4px 0;
	text-align: center;
	background-color: #293033;
	box-shadow: 1px 1px 3px rgb(0 0 0/ 70%);
	text-shadow: 1px 1px 1px rgb(0 0 0/ 30%);
	border-radius: 8px 0;
}
aside .side_bar_contents .side_bar_section .tag li:hover {
	transform: scale( 1.15 );
}

aside .side_bar_contents .side_bar_section .tag li a {
	color: #94db94;
}

aside .side_bar_contents .side_bar_section dl dt {
	width: 6em;
}

aside .side_bar_contents .side_bar_section h2 {
	margin: 30px 0;
	
}

.side_bar_section dl dt {
	z-index: 100;
	float: left;
	width: 10em;
	text-align: right;
	color: #85aaaf;
}

dl dd, dl dt {
	position: relative;
	display: block;
}

aside .side_bar_contents .comment {
	margin-top: 30px;
}

aside .side_bar_contents .comment_zone dl {
	border: 1px solid white;
	margin-bottom: 5px;
}

aside .side_bar_contents .comment_zone dt {
	text-align: left;
	color: #85aaaf;
}

aside .side_bar_contents .comment_zone dd {
	text-align: left;
}

.input {
	margin: 30px 0;
}
/* aside end */

/* section main st */
main {
	display: block;
}

#main {
	position: relative;
	min-height: 100%;
	min-width: 800px;
	border-color: #000;
	border-top-width: 50px;
	transition: .15s border-top-width;
	background: #171717
		url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left
		repeat;
}

#main {
	z-index: 100;
	margin-left: 24.5em;
}

section {
	display: block;
}

.show {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	overflow: hidden;
	z-index: 200;
}

.show>.img_box {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	overflow: scroll;
	background: #171717
		url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left
		repeat;
}

.show #wallpaper {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	display: block;
	margin: auto;
	max-width: 95%;
	max-height: 95%;
	user-select: none;
	-ms-user-select: none;
	-moz-user-select: none;
	-webkit-user-select: none;
	cursor: zoom-in;
	cursor: -webkit-zoom-in;
}

img {
	display: inline-block;
	box-sizing: content-box;
}

/* section main end */
</style>

</head>

<body>
	<header>
		<div class="top">
			<div class="logo">
				<a href="Main">
					<h1>W Share</h1>
				</a>
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
					<input type="search" name="search" class="search_bar" value="${param.search}" > <input
						type="submit" value="🍳" class="input_bar">
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

	<aside class="sidebar">
		<div class="side_bar_back">
			<div class="side_bar_contents">
				<h3>${info.width}X${info.height}</h3>
				<div class="side_bar_section">
					<h2>Tags</h2>
					<ul class="tag">

						<c:forEach var="i" items="${tag}">
							<li><a href="Search?search=${i.tag}">${i.tag}</a></li>
						</c:forEach>

					</ul>
				</div>
				<div class="side_bar_section">
					<h2>Properties</h2>
					<dl>
						<dt>Uploader</dt>
						<dd>${info.uploader}</dd>

						<dt>Category</dt>
						<dd>General</dd>

						<dt>Purity</dt>
						<dd>SFW</dd>

						<dt>Size</dt>
						<dd>360.6 KiB</dd>

						<dt>Views</dt>
						<dd>${info.hit}</dd>

					</dl>
				</div>


				<div class="comment">
					<h2>Comment</h2>

					<div class="comment_zone">
						<c:forEach var="co" items="${c_list}">

							<dl>
								<dt>ID : ${co.userid}</dt>
								<dd>COMMENT : ${co.comment}</dd>
							</dl>

						</c:forEach>
					</div>
					<form action="Detail" method="post">
						<div class="input">
							
							<input type="hidden" name="userid" value="${session_id}">
							<c:if test="${not empty sessionScope.nick}">
							
							</c:if>
							<input type="hidden" name="img_name" value="${full.img_name}">
							<c:if test="${session_id != null}">
								<input type="text" name="comment">
								<input type="submit" value="입력">
							</c:if>
							<c:if test="${session_id == null}">
								<input type="text" value="로그인을 해주세요.">
							</c:if>

						</div>
					</form>
				</div>

			</div>
		</div>
	</aside>

	<main id="main">
		<section class="show">
			<div class="img_box"
				style="margin-right: -17px; margin-bottom: -17px; margin-top: 30px;">
				<img id="wallpaper" src="${full.img_path}" alt="">
			</div>
		</section>
	</main>



</body>

</html>