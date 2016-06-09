<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板ホーム画面</title>
</head>
<body>
<div class="main-contents">

<div class="header">

	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
	</c:if>
	<c:if test="${not empty loginUser }">
		<a href="newMessages">新規投稿画面</a>
		<a href="manageUser">ユーザー管理画面</a>
		<a href="logout">ログアウト</a>

	<form action="home" method="get"><br />
		<div class="Search">
			<br />
			カテゴリー検索

			<select name="searchCategory" id="category">

				<option value="">(選択しない)</option>
				<c:forEach items="${categoryList}" var="category">

					<option value="${category.getCategory()}"
						<c:if test="${selectCategory == category.getCategory()}">selected='selected'</c:if> >
					<c:out value="${category.getCategory()}" />
					</option>

				</c:forEach>
			</select>


			日時検索

			<input type="date" name="startDate" value="oldestDate">
			～
			<input type="date" name="endDate" value="newestDate">
			<input type="submit" value="検索する">
		</div>
	</form>


		<div class="messages">


			<c:forEach items="${messages}" var="message">
				<div class="message">
				<br />
				== 記事内容 ==
				<br />
					<div class="loginId-name">
						<span class="loginId"><c:out value="${message.loginId}" /></span>
						<span class="name"><c:out value="${message.name}" /></span>
					<div class="category">カテゴリー：<c:out value="${message.category}" /></div>
					<div class="title">件名：<c:out value="${message.title}" /></div>
					<div class="text">本文：<c:out value="${message.text}" /></div>
					<div class="date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>

					<br />
					-- コメント --


					<br />
						<div class="comments">
							<c:forEach items="${comments}" var="comment">
								<div class="comment">

								<c:if test="${message.messageId==comment.messageId}">
									<div class="loginId-name">
										<span class="loginId"><c:out value="${comment.loginId}" /></span>
										<span class="name"><c:out value="${comment.name}" /></span>
									<div class="text"><c:out value="${comment.text}" /></div>
									<div class="date"><fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
									</div>
									<br />

									</c:if>
								</div>
							</c:forEach>
						</div>


					<form action="comment" method="post"><br />
					<input type="hidden" name="messageId" value="${message.messageId}">


						<div class="form-area">
							コメント :
								<textarea name="comment" cols="50" rows="5" class="comment-box"></textarea>
								<br />
								<input type="submit" value="コメント">
						</div>
						<br />

					</form>

				</div>
			</div>
		</c:forEach>
	</div>


</c:if>
</div>

</div>

</body>
</html>