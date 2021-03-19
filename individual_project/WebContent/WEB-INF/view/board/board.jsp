<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
	background:
		url(//static.wallhaven.cc/images/layout/blue-gradients.020-wh.jpg) top
		center/cover no-repeat fixed, #171717
		url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left
		repeat;
		
		width: 100%;
		height: 100%;
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

#forums{
width: 90%;
margin: 0 auto;
}
#forums .forums_category>ul>li{
height: 250px;
background-color: purple;
margin: 10px 0;
}
#forums .forums_category>ul>li>h2{
background-color: fuchsia;
}


</style>


</head>
<body>
	<header>
		<div class="top">
			<div class="logo">
				<a href="Main"><h1>Wall Papers</h1></a>
			</div>
			<ul class="menu">
				<li><a href="New">NEW</a></li>
				<li><a href="Top">TOP LIST</a></li>
				<li><a href="Random">RANDOM</a></li>
				<li><a href="Upload">UPLOAD</a></li>
				<li><a href="">FORUM</a></li>
			</ul>

			<form action="Search">
				<div class="search">
					<input type="search" name="search">
				</div>
				<input type="submit" value="검색">

			</form>

			<div class="right_menu">
				<a href="">회원가입</a> <a href="">로그인</a>
			</div>
		</div>
	</header>

	<section id="forums">
		<div class="forums_main">
			<div class="forums_category">
				<ul>
					<li>
					<h2>Wall papers</h2>
						<ul>
							<li>반</li>
							<li>갑</li>
							<li>습</li>
						</ul>
					</li>
					<li>
					<h2>Off-Topic</h2>
						<ul>
							<li>니</li>
							<li>다</li>
							<li>ㅎ</li>
						</ul>
					</li>
					<li>
						<ul>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</li>
				</ul>
			</div>

		</div>

	</section>


</body>
</html>