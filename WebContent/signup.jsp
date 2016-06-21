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
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="bbs">
掲示板
</div>


<div class="header">

<div class="header-text">
		<a href="manageUser"> < ユーザー管理画面</a>
</div>

</div>

<div class="main-contents">

<div class="contents-title">
新規ユーザー登録
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

<div class="form-area">
<form action="signup" method="post"> <br />
	<label for="account">名前:(10文字以内で入力してください)</label><br />
	<input name="name" value="${editUser.name}" id="name"/><br />
	<br /><br />

	<label for="loginId">ログインID:(6文字以上20文字以内で入力してください)</label><br />
	<input name="loginId" value="${editUser.loginId}" id="loginId"/><br />
	<br /><br />

	<label for="password">パスワード:(6文字以上225文字以内で入力してください)</label><br />
	<input name="password" type="password" size="50" id="password"/> <br />
	<br /><br />

	<label for="checkPassword">確認用パスワード:(パスワードをもう一度入力してください)</label><br />
	<input name="checkPassword" type="password" size="50" id="checkPassword"/> <br />
	<br /><br />

	<label for="branchId">所属</label><br />
	<select name="branchId" id =branchId>
	<option value="0">(選択してください)</option>

		<c:forEach items="${branches}" var="branch">
			<option value="${branch.id}" <c:if test="${ editUser.branchId == branch.id }">selected='selected'</c:if> >
			<c:out value="${branch.getName()}" />
			</option>
		</c:forEach>

	</select>
	<br />
	<br />

	<label for="postId">部署・役職</label><br />

	<select name="postId" id=postId>
	<option value="0">(選択してください)</option>
		<c:forEach items="${posts}" var="post">
			<option value="${post.id}" <c:if test="${ editUser.postId == post.id }">selected='selected'</c:if>>
			<c:out value="${post.getName()}" /></option>
		</c:forEach>
	</select>
	<br />

	<br />

	<c:remove var="editUser" scope="session"/>
	<input id="submit" type="submit" value="登録" /> <br />
	<br />

</form>
</div>

</div>


</body>
</html>