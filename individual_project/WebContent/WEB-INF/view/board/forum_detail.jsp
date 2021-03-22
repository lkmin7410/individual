<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

img { 
width: 100%;
}

.content{
width: 700px;
}

</style>


<c:set var="root" value="${pageContext.request.contextPath}"/>
</head>
<body>
	<div>
		
			<ul>
				<li>제목
					<div>
						<p>${list.title}</p>
					</div>
				</li>

				<li>내용
					<div class="content">
						<p>${list.content}</p>
						<img src="${root}/upload/${list.img}">
					</div>
				</li>
			</ul>
			<input type="hidden" name="session_id"
				value="${sessionScope.session_id}"> <input type="submit"
				value="글 쓰기">
		
	</div>
</body>
</html>