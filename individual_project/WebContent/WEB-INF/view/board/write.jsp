<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<form action="Write" method="post">
	<ul>
		<li>제목
		<input type="text" name="title">
		</li>
		
		<li>내용
		<textarea cols="200px" rows="50px" name="content"></textarea>
		</li>
	</ul>
		<input type="hidden" name="session_id" value="${sessionScope.session_id}">
		<input type="submit" value="글 쓰기">
	</form>	
	</div>
</body>
</html>