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
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="bbs">
掲示板
</div>

<div class="header">

<div class="header-text">
		<c:if test="${ loginUser.getPostId() == 1 }">
		<a href="manageUser">ユーザー管理画面</a>
		</c:if>
		<a href="newMessages">新規投稿画面</a>
		<a href="logout"><button>ログアウト</button></a>
</div>

</div>

<div class="main-contents">

<div class="contents-title">
ホーム
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


		<div class="completeMessages">
			<c:if test="${ not empty completeMessages }">
			<ul>
						<c:forEach items="${completeMessages}" var="completeMessage">
							<li><c:out value="${completeMessage}" />
						</c:forEach>
					</ul>

				<c:remove var="completeMessages" scope="session"/>
			</c:if>
		</div>

		<form action="home" method="get">
			<div class="search">
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
				<input id="submit" type="submit" value="検索する">

			</div>
		</form>

			<a href="home"><button>全件表示</button></a>




		<div class="messages">
		<div class="article-list">
		   記事一覧
		</div>



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


					<div class="article">

					<h5><span>投稿記事</span></h5>

						<div class="loginId-name">
							<span class="loginId"><c:out value="${message.loginId}" /></span>
							<span class="name"><c:out value="${message.name}" /></span></div>
						<div class="category">カテゴリー：<c:out value="${message.category}" /></div>
						<div class="title">件名：<c:out value="${message.title}" /></div>
						<div class="text">本文：<pre><c:out value="${message.text}" /></pre></div>
						<div class="date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" />


						<form action="home" method="post">
						<c:if test="${loginUser.postId == 2 }">
						<input type="hidden" name="messageId" value="${message.messageId}">
								<input id="submit" type="submit" name="submit" value="削除する"
								 		onclick="return confirm('本当にこの記事を削除しますか？')" />
						</c:if>

						<c:if test="${loginUser.postId == 1 }">
							<c:if test="${loginUser.id  == message.userId}">
								<input type="hidden" name="messageId" value="${message.messageId}">
										<input id="submit" type="submit" name="submit" value="削除する"
										 		onclick="return confirm('本当にこの記事を削除しますか？')" />
							</c:if>
						</c:if>

						<c:if test="${loginUser.postId == 3 }">
							<c:if test="${loginUser.branchId == message.branchId }">
								<input type="hidden" name="messageId" value="${message.messageId}">
										<input id="submit" type="submit" name="submit" value="削除する"
										 		onclick="return confirm('本当にこの記事を削除しますか？')" />
							</c:if>
						</c:if>

						<c:if test="${loginUser.postId == 4 }">
							<c:if test="${loginUser.id  == message.userId}">
								<input type="hidden" name="messageId" value="${message.messageId}">
										<input id="submit" type="submit" name="submit" value="削除する"
										 		onclick="return confirm('本当にこの記事を削除しますか？')" />
							</c:if>
						</c:if>

						</form></div>

						<br />
						</div>

						<div class="comment-title">
						コメント
						</div>



						<br />
							<div class="comments">
								<c:forEach items="${comments}" var="comment">
									<div class="comment">

									<c:if test="${message.messageId==comment.messageId}">
										<div class="one-comment">
											<span class="loginId"><c:out value="${comment.loginId}" /></span>
											<span class="name"><c:out value="${comment.name}" /></span>
										<div class="comment-text"><pre><c:out value="${comment.text}" /></pre></div>
										<div class="date"><fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>

										</div>


										<form action="home" method="post">
											<c:if test="${loginUser.postId == 2 }">
											<input type="hidden" name="id" value="${comment.id}">
													<input id="submit" type="submit" name="submit" value="削除する"
													 		onclick="return confirm('本当にこのコメントを削除しますか？')" />
											</c:if>

											<c:if test="${loginUser.postId == 1 }">
												<c:if test="${loginUser.id  == comment.userId}">
													<input type="hidden" name="id" value="${comment.id}">

															<input id="submit" type="submit" name="submit" value="削除する"
															 		onclick="return confirm('本当にこのコメントを削除しますか？')" />
												</c:if>
											</c:if>

											<c:if test="${loginUser.postId == 3 }">
												<c:if test="${loginUser.branchId == comment.branchId }">
													<input type="hidden" name="id" value="${comment.id}">

															<input id="submit" type="submit" name="submit" value="削除する"
															 		onclick="return confirm('本当にこのコメントを削除しますか？')" />
												</c:if>
											</c:if>

											<c:if test="${loginUser.postId == 4 }">
												<c:if test="${loginUser.id  == comment.userId}">
													<input type="hidden" name="id" value="${comment.id}">
															<input id="submit" type="submit" name="submit" value="削除する"
															 		onclick="return confirm('本当にこのコメントを削除しますか？')" />
												</c:if>
											</c:if>
										</form>

									</c:if>
									</div>

								</c:forEach>
							</div>


						<form action="comment" method="post"><br />
						<input type="hidden" name="messageId" value="${message.messageId}">
							<div class="new-comment">
								コメント :

									<textarea name="comment" cols="50" rows="5" class="comment-box"></textarea>
									<br />
									<input id="submit" type="submit" value="コメント">
							</div>
							<br />

						</form>



				</c:forEach>
			</div>




</body>
</html>