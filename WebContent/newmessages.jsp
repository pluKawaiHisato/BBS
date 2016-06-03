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
<div class="main-contents">
<form action="newMessages" method="post"><br />

カテゴリー :
	<select name="category">
		<option value="">(選択してください)</option>
		<option value="予定">予定</option>
		<option value="人事">人事</option>
		<option value="イベント">イベント</option>
	</select>

	<br />
	<br />

	<div class="form-area">
		件名 :
			<textarea name="title" cols="50" rows="1" class="newMessage-box"></textarea>
	</div>

	<br />
	<br />

<div class="form-area">
	新規投稿 :
			<textarea name="text" cols="70" rows="15" class="newMessage-box"></textarea>
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