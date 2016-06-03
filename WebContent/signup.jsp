<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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

	<label for="branchId">支店</label>
	<input name="branchId" value="${editUser.branchId}" id="branchId"/> <br />

	<label for="postId">部署・役職</label>
	<input name="postId" value="${editUser.branchId}" id="postId"/> <br />

	<input type="submit" value="登録" /> <br />
	<a href="./">戻る</a>
</form>
</div>


</body>
</html>