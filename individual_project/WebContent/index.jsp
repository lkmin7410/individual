<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<style>
* {
	margin: 0;
	padding: 0;
}

ul, li {
	list-style: none;
}

a {
	text-decoration: none;
}

.inner {
	width: 1200px;
	max-width: 1200px;
	margin: 0 auto;
}

body {
	/* background: url(//static.wallhaven.cc/images/layout/blue-gradients.020-wh.jpg) top center/cover no-repeat fixed,#171717 url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left repeat; */
	background: #171717
		url(//static.wallhaven.cc/images/layout/bg-dark-grain.png) top left
		repeat;

}

hr {
	border: none;
	clear: both;
}

#top {


}

#top .logo {
	text-align: center;
}

#top .logo h1 {
	font-size: 50px;
	color: orange;
	margin: 50px 0;
}

#top .inner .menu {
	height: 100px;
}

#top .inner .menu ul {
	margin-top: 30px;
}

#top .inner .menu ul li {
	float: left;
	width: calc(100%/ 5);
	text-align: center;
}

#top .inner .menu ul li a {
	padding: 10px 50px;
	box-sizing: border-box;
	background-color: threeddarkshadow;
	color: blanchedalmond;
	border-radius: 5px;
}

#top .search {
	text-align: center;
	height: 100px;
}

#top .search .search_bar {
	width: 600px;
	height: 40px;
	font-size: 25px;
}
#top .search .input_bar{
	
	height: 40px;
	font-size: 25px;
}

#top .inner .tag {
	top: 70px;
}

#top .inner .tag li {
	float: left;
	width: calc(100%/ 4);
	text-align: center;
	margin: 10px 0;
}

#top .inner .tag li:hover a {
	color: white;
}

#top .inner .tag li a {
	font-size: 25px;
	color: orange;
}

#top .inner .login {
	display: block;
	text-align: center;
}

#top .inner .login a {
	color: silver;
	margin: 150px;
	font-size: 25px;
}

#middle .inner .img {
	margin-top: 50px;
}

#middle .inner .img li{
display: inline-block;
text-align: center;
}

#middle .inner .img li:hover {
opacity: 0.5;
}

#middle .inner .img img {
	width: 100%;
	border-radius: 5px;
	box-shadow: 0 0 8px rgb(0 0 0/ 80%);
}

#middle .inner .img ul:nth-child(1) li, #middle .inner .img ul:nth-child(3) li,
	#middle .inner .img ul:nth-child(5) li {
	width: calc(100%/ 5);
	float: left;
	padding: 10px;
	box-sizing: border-box;
}

#middle .inner .img ul:nth-child(2), #middle .inner .img ul:nth-child(4)
	{
	text-align: center;
}

#middle .inner .img ul:nth-child(2) li, #middle .inner .img ul:nth-child(4) li
	{
	width: calc(100%/ 5);
	display: inline-block;
	padding: 10px;
	box-sizing: border-box;
}
</style>

</head>
<body>
	<header id="top">
		<div class="logo">
			<h1>Wall Papers</h1>
		</div>


		<div class="inner">
			<div class="menu">
				<ul>
					<li><a href="New">NEW</a></li>
				<li><a href="Top">TOP LIST</a></li>
				<li><a href="Random">RANDOM</a></li>
				<li><a href="Upload">UPLOAD</a></li>
				<li><a href="Forum">FORUM</a></li>
				</ul>
			</div>
		</div>
		<form action="Search">
		<div class="search">
			<input type="search" name="search" class="search_bar">
			<input type="submit" value="🍳" class="input_bar"> 
		</div>
		
		
		</form>
		<div class="inner">

			<div class="login">
				<%-- ${session_id} --%>
				<c:if test="${not empty sessionScope.session_id}">
					<a href="Logout">Log out</a>
				</c:if>
				<c:if test="${empty sessionScope.session_id}">
					<a href="logIn">Log in</a>
					<a href="signUp">Sign Up</a>
				</c:if>
			</div>
			<hr>
		</div>
	</header>
	<hr>
	<section id="middle">
		<div class="inner">
			<div class="img">
				<ul>
					<c:forEach var="i" items="${img}" begin="0" end="24" >
						<li> <a href="Detail?img_name=${i.img_name}"><img src="${i.img_path}"></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>

	</section>


</body>
</html>