<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
</head>
<body>
	<div style="padding-top: 10px;">
		<h4 class="nav-title">角色管理</h4>
		<div class="list-group">
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/user/roleList.action"
				id="nav-role-list">角色列表</a>
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/user/insertRolePage.action"
				id="nav-new-role">创建角色</a>
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/user/roleQueryPage.action"
				id="nav-search-role">角色查找</a>
			<!--  -->
		</div>
		<h4 class="nav-title">用户管理</h4>
		<div class="list-group">
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/user/newUserPage.action"
				id="nav-new-user">创建新用户</a>
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/user/userQueryPage.action"
				id="nav-search-user">查找用户</a>
			<!--  -->
			<a class="list-group-item" href="${pageContext.request.contextPath}/user/verifyUserPage.action"
				id="nav-verify-user">用户信息认证<span id="user-count" class="badge"></span></a>
			<!--  -->
		</div>
	</div>

	<script type="text/javascript">
		$.post("${pageContext.request.contextPath}/user/getUserCountToBeVerify", {}, function(data, textStatus) {
			if (textStatus == 'success') {
				if (data.count > 0) {
					$("span#user-count").html(data.count);
				} else {
					$("span#user-count").html("");
				}
			}
		});
	</script>
</body>
</html>