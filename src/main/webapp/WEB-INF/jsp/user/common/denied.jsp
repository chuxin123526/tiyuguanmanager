<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限不足！</title>
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
				<h2>您没有访问本页面的权限！</h2>
				<p>这里应该有一大段的说明文字 blah blah....</p>
			</div>
		</div>
	</div>
</body>
</html>