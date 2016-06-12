<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<s:if test="#session.user.role.permissions.{?#this.type == 1}.size>0">
				<a class="list-group-item" href="${pageContext.request.contextPath}/user/insertRolePage.action"
					id="nav-new-role">创建角色</a>
			</s:if>
			<!--  -->
			<s:if test="#session.user.role.permissions.{?#this.type == 4}.size>0">
				<a class="list-group-item" href="${pageContext.request.contextPath}/user/roleQueryPage.action"
					id="nav-search-role">角色查找</a>
			</s:if>
		</div>
		<h4 class="nav-title">用户管理</h4>
		<div class="list-group">
			<s:if test="#session.user.role.permissions.{?#this.type == 10}.size>0">
				<!--  -->
				<a class="list-group-item" href="${pageContext.request.contextPath}/user/newUserPage.action"
					id="nav-new-user">创建新用户</a>
			</s:if>
			<s:if test="#session.user.role.permissions.{?#this.type==11}.size>0">
				<!--  -->
				<a class="list-group-item" href="${pageContext.request.contextPath}/user/userQueryPage.action"
					id="nav-search-user">查找用户</a>
			</s:if>
			<!--  -->
			<s:if test="#session.user.role.permissions.{?#this.type==15}.size>0">
				<a class="list-group-item" href="${pageContext.request.contextPath}/user/verifyUserPage.action"
					id="nav-verify-user">用户信息认证<span id="user-count" class="badge"></span></a>
			</s:if>
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