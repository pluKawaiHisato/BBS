<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3>新規投稿</h3>
<div class="main-contents">
<div class="header">
	<a href="home">ホーム</a>
</div>

		<div class="errorMessages">
			<c:if test="${ not empty errorMessages }">
					<ul>
						<c:forEach items="${errorMessages}" var="message">
							<li><c:out value="${message}" />
						</c:forEach>
					</ul>

				<c:remove var="errorMessages" scope="session"/>
			</c:if>
		</div>




<form action="newMessages" method="post"><br />

<div class="form-area">
カテゴリー :(10文字以内で入力してください)<br />
			<input type="text" class="catecory" name="category" value="${editMessage.category}">
	</div>
	<br />
	<br />

	<div class="form-area">
		件名 :(50文字以内で入力してください)<br />
			<input type="text" size="50" class="title" name="title" value="${editMessage.title}">
	</div>

 	<br />
	<br />

<div class="form-area">
	本文:（1000文字以内で入力してください）<br />
			<textarea name="text" cols="70" rows="15" class="newMessage-box" >${editMessage.text}</textarea>
			<br />
			<br />
			<input type="submit" value="新規投稿">
			<br />


	</div>

</form>
</div>

</body>
</html>