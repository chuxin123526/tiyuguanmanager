<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
</head>
<body>
	<div>
		<h4>公告管理</h4>
		<div class="list-group">
			<a class="list-group-item"
				href="${pageContext.request.contextPath}/announcement/createAnnouncement">发布新公告</a>
			<!--  -->
			<a class="list-group-item"
				href="${pageContext.request.contextPath}/announcement/publishedAnnouncementList">已发布的公告</a>
			<!--  -->
			<a class="list-group-item"
				href="${pageContext.request.contextPath}/announcement/draftAnnouncementList">草稿管理</a>
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/announcement/queryPage">公告查询</a>
			<!--  -->
			<a class="list-group-item"
				href="${pageContext.request.contextPath}/announcement/announcementTrash">已删除的公告</a>
		</div>
		<h4>评论管理</h4>
		<div class="list-group">
			<a class="list-group-item"
				href="${pageContext.request.contextPath}/announcement/commentQueryPage">评论查询</a>
		</div>
	</div>
</body>
</html>