<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../../../competition/common/common.jsp" />
<jsp:include page="../common/common.jsp" />
</head>
<body>
	<jsp:include page="../../../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="../common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9">
			<div class="row">
				<div class="col-md-12">
					<h3>角色查询</h3>
				</div>
			</div>
			<form action="roleQuery" id="query-form" method="post">
				<input type="hidden" name="page" value="1" />
				<div class="row form-group">
					<div class="form-label col-md-2">
						<label class="form-label">角色名称：</label>
					</div>
					<div class="col-md-8" id="form-form-name-input-div">
						<input class="form-control" type="text" name="form.name" id="role-form-name" />
					</div>
					<div class="col-md-2 error-msg">
						<span class="error-msg" id="role-form-name-span"></span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-12" style="text-align: right;">
						<button class="btn btn-primary" id="form-role-query-btn">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript">
		$("#form-role-query-btn").bind("click", function() {
			$("#query-form").submit();
		});
	</script>
</body>
</html>