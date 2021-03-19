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
		
			<ul>
				<li>제목
					<div>
						<p>${list.title}</p>
					</div>
				</li>

				<li>내용
					<div>
						<p>${list.content}</p>
					</div>
				</li>
			</ul>
			<input type="hidden" name="session_id"
				value="${sessionScope.session_id}"> <input type="submit"
				value="글 쓰기">
		
	</div>
</body>
</html>