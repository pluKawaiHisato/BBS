<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規ユーザー登録画面</title>
</head>
<body>
<h3>新規ユーザー登録</h3>
<div class="main-contents">

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


<form action="signup" method="post"> <br />
	<label for="account">名前</label>
	<input name="name" value="${editUser.name}" id="name"/><br />

	<label for="loginId">ログインID</label>
	<input name="loginId" value="${editUser.loginId}" id="loginId"/><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="checkPassword">パスワード(確認)</label>
	<input name="checkPassword" type="password" id="checkPassword"/> <br />

	<label for="branchId">所属</label>
	<select name="branchId" id =branchId>
	<option value="0">(選択してください)</option>
		<c:forEach items="${branches}" var="branch">
			<option value="${branch.id}" <c:if test="${ edittUser.branchId == branch.id }">selected='selected'</c:if> >
			<c:out value="${branch.getName()}" />
			</option>
		</c:forEach>
	</select>
	<br />

	<label for="postId">部署・役職</label>

	<select name="postId" id=postId>
	<option value="0">(選択してください)</option>
		<c:forEach items="${posts}" var="post">
			<option value="${post.id}" <c:if test="${ editUser.postId == post.id }">selected='selected'</c:if>>
			<c:out value="${post.getName()}" /></option>
		</c:forEach>
	</select>
	<br />

	<c:remove var="editUser" scope="session"/>
	<input type="submit" value="登録" /> <br />
	<a href="manageUser">戻る</a>
</form>
</div>


</body>
</html>