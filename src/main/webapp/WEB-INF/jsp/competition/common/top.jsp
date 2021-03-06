<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
</head>
<body style="padding : 50px ; ">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index">体育馆管理系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li id="nav-bar-home"><a href="${pageContext.request.contextPath}/index">主页<span
							class="sr-only">(current)</span></a></li>
					<li id="nav-bar-instrument"><a href="#">器材</a></li>
					<li id="nav-bar-competition"><a href="#">赛事</a></li>
					<li id="nav-bar-field"><a href="#">场地</a></li>
					<s:if test="#session.userAdminAccess">
						<li id="nav-bar-user"><a href="${pageContext.request.contextPath}/user/adminIndex">用户管理</a></li>
					</s:if>
					<s:if test="#session.announcementAdminAccess">
						<li id="nav-bar-announcement"><a
							href="${pageContext.request.contextPath}/announcement/adminIndex">公告管理</a></li>
					</s:if>
					<li></li>
					<!-- 
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
						 -->
				</ul>
				<!--  
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				-->
				<ul class="nav navbar-nav navbar-right">
					<!-- 
					<li><a href="#">Link</a></li>
					 -->
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.user.username} <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/user/updatePassword">修改密码</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/user/logoutPage">注销</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<script type="text/javascript">
		if (location.href.indexOf("${pageContext.request.contextPath}/index") != -1) {
			$("#nav-bar-home").addClass("active");
		} else if (location.href.indexOf("${pageContext.request.contextPath}/user") != -1) {
			$("#nav-bar-user").addClass("active");
		} else if (location.href.indexOf("${pageContext.request.contextPath}/announcement") != -1) {
			$("#nav-bar-announcement").addClass("active");
		} else if (location.href.indexOf("${pageContext.request.contextPath}/competition") != -1) {
			$("#nav-bar-competition").addClass("active");
		}
	</script>
</body>
</html>