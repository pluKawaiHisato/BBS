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

		<c:if test="${ loginUser.getPostId() == 3 }">
		<a href="manageUser">ユーザー管理画面</a>
		</c:if>
		<a href="newMessages">新規投稿画面</a>
		<a href="logout">ログアウト</a>

			<c:if test="${ not empty errorMessages }">

					<ul>
						<c:forEach items="${errorMessages}" var="message">
							<li><c:out value="${message}" />
						</c:forEach>
					</ul>

				<c:remove var="ErrorMessages" scope="session"/>
			</c:if>

		<form action="home" method="get">
			<div class="Search">
			<br />
			カテゴリー検索

				<select name="searchCategory" id="category">

					<option value="">(選択しない)</option>
					<c:forEach items="${categoryList}" var="categoryList">

						<option value="${categoryList.getCategory()}"
							<c:if test="${selectedCategory == categoryList.getCategory()}">selected='selected'</c:if> >
						<c:out value="${categoryList.getCategory()}" />
						</option>

					</c:forEach>
				</select>


			日時検索

				<c:if test="${empty startDate }">
					<input type="date" name="startDate" value="${oldestDate}">
				</c:if>
				<c:if test="${not empty startDate }">
					<input type="date" name="startDate" value="${startDate}">
				</c:if>
				～
				<c:if test="${ empty endDate }">
					<input type="date" name="endDate" value="${newestDate}">
				</c:if>
				<c:if test="${ not empty endDate }">
					<input type="date" name="endDate" value="${endDate}">
				</c:if>
				<input type="submit" value="検索する">

			</div>
		</form>





		<div class="messages">
		＝＝＝＝＝＝＝＝＝＝＝＝＝   記事一覧  ＝＝＝＝＝＝＝＝＝＝＝＝＝



					<c:if test="${ not empty articleErrorMessages }">
						<div class="articleErrorMessages">
							<ul>
								<c:forEach items="${articleErrorMessages}" var="aeMessage">
									<li><c:out value="${aeMessage}" />
								</c:forEach>
							</ul>
						</div>
						<c:remove var="articleErrorMessages" scope="session"/>
					</c:if>

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
						<div class="text">本文：<pre><c:out value="${message.text}" /></pre></div>
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
										<div class="text"><pre><c:out value="${comment.text}" /></pre></div>
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


	</div>



</div>



</body>
</html>