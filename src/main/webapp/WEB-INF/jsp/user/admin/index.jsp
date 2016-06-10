<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../../competition/common/common.jsp" />
<jsp:include page="common/common.jsp" />
</head>
<body>
	<jsp:include page="../../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9">
			<jsp:include page="home.jsp" />
		</div>
	</div>

	<jsp:include page="../../competition/common/pagefoot.jsp"></jsp:include>
</body>
</html>