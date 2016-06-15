<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<h3>新規投稿</h3>
<div class="main-contents">
<form action="newMessages" method="post"><br />

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>


<div class="form-area">
カテゴリー :
			<textarea name="category" cols="12" rows="1" class="newMessage-box">${editMessage.category}</textarea>
	</div>
	<br />
	<br />

	<div class="form-area">
		件名 :
			<textarea name="title" cols="50" rows="1" class="newMessage-box">${editMessage.title}</textarea>
	</div>

	<br />
	<br />

<div class="form-area">
	新規投稿 :
			<textarea name="text" cols="70" rows="15" class="newMessage-box" >${editMessage.text}</textarea>
			<br />
			<input type="submit" value="新規投稿">
			<br />

		<br />
		<a href="home">戻る</a>
	</div>

</form>
</div>

</body>
</html>