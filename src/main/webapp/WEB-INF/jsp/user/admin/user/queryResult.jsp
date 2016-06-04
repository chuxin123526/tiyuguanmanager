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
				<h3>查询结果</h3>
			</div>
			<div class="row form-group">
				<!-- 数据显示 -->
				<table class="table table-hover table-with-button">
					<thead>
						<tr>
							<th style="width: 3.5em">#</th>
							<th>用户名</th>
							<th style="width: 3.5em"></th>
							<th style="width: 3.5em"></th>
						</tr>
					</thead>
					<s:iterator value="#request.userList" var="user">
						<tr>
							<td>
								<span>
									<s:property value="#user.userId"/>
								</span>
							</td>
							<td>
								<span>
									<s:property value="#user.username"/>
								</span>
							</td>
							<td>
								<button class="btn btn-primary">修改</button>
							</td>
							<td>
								<s:if test="#user.status==0">
									<button class="btn btn-default">禁用</button>
								</s:if>
								<s:else>
									<button class="btn btn-default">启用</button>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</div>

	<!-- 用于保存查询条件的隐藏表单 -->
	<div id="query-criteria" style="display: none">
		<form id="query-criteria-form" action="userQuery.action" method="post">
			<!--  -->
			<input type="checkbox" id="query-form-criteria-username" name="query.criteria" value="0" />
			<!--  -->
			<input type="checkbox" id="query-form-criteria-role" name="query.criteria" value="1" />
			<!--  -->
			<input type="checkbox" id="query-form-criteria-account-type" name="query.criteria" value="2" />
			<!--  -->
			<input type="hidden" id="query-form-username" name="query.username" value="" />
			<!--  -->
			<input type="hidden" id="query-form-role" name="query.roleId" value="" />
			<!--  -->
			<input type="checkbox" name="query.accountType" id="query-form-type-student" value="0">
			<!--  -->
			<input type="checkbox" name="query.accountType" id="query-form-type-teacher" value="2" />
			<!--  -->
			<input type="checkbox" name="query.accountType" id="query-form-type-employee" value="1" />
			<!--  -->
			<input type="radio" name="query.forbidden" id="query-form-forbidden-yes" value="1"
				checked="checked" />
			<!--  -->
			<input type="radio" name="query.forbidden" id="query-form-forbidden-no" value="0" />
			<!--  -->
		</form>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>

</body>
</html>