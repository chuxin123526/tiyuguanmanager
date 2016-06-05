<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
</head>
<body>
	<div>
		<h4>角色管理</h4>
		<ol class="list-unstyled">
			<li><a href="${pageContext.request.contextPath}/user/roleList.action">角色列表</a></li>
			<li><a href="${pageContext.request.contextPath}/user/insertRolePage.action">添加角色</a></li>
			<li><a href="${pageContext.request.contextPath}/user/roleQueryPage.action">角色查找</a></li>
		</ol>
		<h4>用户管理</h4>
		<ol class="list-unstyled">
			<li><a href="${pageContext.request.contextPath}/user/newUserPage.action">添加用户</a></li>
			<li><a href="${pageContext.request.contextPath}/user/userQueryPage.action">查找用户</a></li>
			<li><a href="${pageContext.request.contextPath}/user/verifyUserPage.action">用户信息认证</a></li>
		</ol>
	</div>
</body>
</html>