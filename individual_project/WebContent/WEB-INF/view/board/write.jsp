<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style >

</style>


<title>Insert title here</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<div>
			<ul>
				<li>제목 <input type="text" name="title" />
				</li>
				<li>
					<div>
						<input type="file" id="image" accept="image/*"
						onchange="setThumbnail(event);" name="file" />
					<div id="image_container"></div>
					
					<script>
						function setThumbnail(event) {
							var reader = new FileReader();
							reader.onload = function(event) {
								var img = document.createElement("img");
								img.setAttribute("src", event.target.result);
								document.querySelector("div#image_container")
										.appendChild(img);
							};
							reader.readAsDataURL(event.target.files[0]);
						}
					</script>
					</div>
				</li>
				<li>내용 <textarea cols="200px" rows="50px" name="content">
				
				
				
				
				</textarea>
				</li>
			</ul>
			<input type="hidden" name="session_id" value="${sessionScope.session_id}" />
			<input type="submit" value="글 쓰기" />
		</div>
	</form>
</body>
</html>