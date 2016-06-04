<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发生了错误</title>
<jsp:include page="../../competition/common/common.jsp" />
<style>
div.face {
	text-align: center;
}

div.face span {
	font-size: 100pt;
}

h2 {
	font-family: "微软雅黑", "黑体", "Arial"
}

div.description p {
	margin-top: 5px;
	margin-left: 20px;
}
</style>
</head>
<body>
	<jsp:include page="../../competition/common/top.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-4 face">
				<span> :-( </span>
			</div>
			<div class="col-md-8"></div>
		</div>
		<div class="row">
			<div class="col-md-11 col-md-offset-1 description">
				<h2>发生了错误</h2>
				<p>
					<s:property value="#request.errMsg" />
				</p>
				<p>
					点击<span><a href="${pageContext.request.contextPath}/user/adminIndex">这里</a></span>返回到主页
				</p>
			</div>
		</div>
	</div>
</body>
</html>