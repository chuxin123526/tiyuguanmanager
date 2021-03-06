<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<s:if test="#request.function == 3">
	<title>用户查询</title>
</s:if>
<s:else>
	<title>用户认证</title>
</s:else>

<jsp:include page="../../../competition/common/common.jsp" />
<jsp:include page="../common/common.jsp" />
</head>
<body>
	<jsp:include page="../../../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="../common/navigation.jsp"></jsp:include>
		</div>

		<s:if test="#request.result.totalCount!=0">
			<div class="col-md-9" style="margin-top: 10px;">
				<div class="row">
					<h4>
						<s:property value="#request.tipWord" />
					</h4>
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
								<td><span class="table-data-user-id"> <s:property value="#user.userId" />
								</span></td>
								<td><span> <s:property value="#user.username" />
								</span></td>
								<td><s:if test="#request.function != 3">
										<button class="btn btn-default table-btn-update">修改</button>
									</s:if></td>
								<td><s:if test="#request.function != 3">
										<s:if test="#user.status==0">
											<s:if test="#session.user.role.permissions.{?#this.type==12}.size>0">
												<button class="btn btn-default table-btn-status table-btn-forbid">禁用</button>
											</s:if>
										</s:if>
										<s:else>
											<s:if test="#session.user.role.permissions.{?#this.type==13}.size>0">
												<button class="btn btn-default table-btn-status table-btn-enable">启用</button>
											</s:if>
										</s:else>
									</s:if> <s:else>
										<s:if test="#session.user.role.permissions.{?#this.type==15}.size>0">
											<button class="btn btn-default table-btn-verify-info">进入认证</button>
										</s:if>
									</s:else></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<!-- 分页导航部分 -->
				<div class="row">
					<div class="col-md-12 pagination-row">
						<ul class="pagination">
							<s:if test="#request.maxPage != #request.minPage && #request.queryShowback.page != 1">
								<li><a id="nav-back-one" href="javascript:void(0);">«</a></li>
							</s:if>
							<s:iterator value="#request.allPages" var="page">
								<s:if test="#page == #request.queryShowback.page">
									<li class="active"><a class="pagincation-page" href="javascript:void(0);"><s:property
												value="#page" /></a></li>
								</s:if>
								<s:else>
									<li><a class="pagincation-page" href="javascript:void(0);"><s:property
												value="#page" /></a></li>
								</s:else>
							</s:iterator>
							<s:if
								test="#request.maxPage != #request.minPage && #request.queryShowback.page != #request.maxPage">
								<li><a id="nav-next-one" href="javascript:void(0);">»</a></li>
							</s:if>
						</ul>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
			<!-- 如果没有查询结果 -->
			<div class="col-md-9" style="margin-top: 10px;">
				<div class="row">
					<s:if test="#request.function == 3">
						<h4>
							<s:property value="#request.tipWord" />
						</h4>
					</s:if>
					<s:else>
						<h4>查询结果</h4>
					</s:else>
				</div>
				<div class="row">
					<div class="alert alert-danger">
						<%-- 如果是从用户信息认证跳转过来的 --%>
						<s:if test="#request.function == 3">
							<p>系统中没有等待认证的用户</p>
						</s:if>
						<s:else>
							<p>没有找到满足条件的用户数据</p>
						</s:else>
					</div>
				</div>
			</div>
		</s:else>
	</div>

	<!-- 用于保存查询条件的隐藏表单 -->
	<div id="query-criteria" style="display: none">
		<s:if test="#request.function != 3">
			<form id="query-criteria-form" action="userQuery.action" method="post">
		</s:if>
		<s:else>
			<form id="query-criteria-form" action="verifyUserPage.action" method="post">
		</s:else>
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
		<input type="hidden" name="query.page" id="query-form-page"
			value="<s:property value="#request.query.page"/>" />
		</form>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>

	<%-- 用户认证  --%>
	<s:if test="#request.function == 3">
		<script type="text/javascript">
			$("#nav-verify-user").addClass("active");
		</script>
	</s:if>
	<s:else>
		<script type="text/javascript">
			$("#nav-search-user").addClass("active");
		</script>
	</s:else>

	<script type="text/javascript">
	function gotoPage(page) {	
		$("#query-form-page").val(page);
		$("#query-criteria-form").submit();
	}
	
	$("a.pagincation-page").bind("click", function(){
		gotoPage($(this).html());
	});
	
	$("#nav-back-one").bind("click", function(){
		gotoPage(<s:property value="#request.queryShowback.page - 1"/>);
	});
	
	$("#nav-next-one").bind("click", function(){
		gotoPage(<s:property value="#request.queryShowback.page + 1"/>);
	});
	
	$("button.table-btn-update").bind("click", function(){
		var button = $(this);
		var id = trim(button.parent().parent().find("span.table-data-user-id").html());
		
		location.href = "${pageContext.request.contextPath}/user/updateUserPage?userId=" + id;
	});
	
	$("button.table-btn-verify-info").bind("click", function(){
		var button = $(this);
		var id = trim(button.parent().parent().find("span.table-data-user-id").html());
		
		location.href = "${pageContext.request.contextPath}/user/verifyUserDetailedPage?userId=" + id;
	});
	
	$("button.table-btn-status").bind("click", function(){
		var button = $(this);
		var id = trim(button.parent().parent().find("span.table-data-user-id").html());
		
		button.attr("disabled", "disabled");
		if (button.hasClass("table-btn-enable")) {
			// 如果是启用按钮
			$.post("enableUser", {"userId": id}, function(data, textStatus){
				if (textStatus == 'success') {
					if (data == null) {
						showErrorToast("您没有启用账户的权限");
					} else if (data.code == 21) {
						// 成功启用账户
						button.removeClass("table-btn-enable");
						button.addClass("table-btn-forbid");
						button.html("禁用");
						
						showSuccessToast("成功启用账户");
					} else if (data.code == 20) {
						showErrorToast("用户编号有误，请刷新页面后再重试！");
					} else if (data.code == 2) {
						showErrorToast("您没有启用账户的权限！");
					} else {
						showErrorToast("系统异常，请稍后再试！");
					}
				} else {
					showErrorToast("与服务器通讯失败，请稍后再试");
				}
				
				button.removeAttr("disabled");
			});	
		} else if (button.hasClass("table-btn-forbid")) {
			// 如果是禁用按钮
			$.post("forbidUser", {"userId": id}, function(data, textStatus){
				if (textStatus == 'success') {
					if (data == null) {
						showErrorToast("您没有禁用账户的权限！");
					} else if (data.code == 22) {
						// 成功禁用账户
						button.addClass("table-btn-enable");
						button.removeClass("table-btn-forbid");
						button.html("启用");
						
						showSuccessToast("成功禁用账户");
					} else if (data.code == 20) {
						showErrorToast("用户编号有误，请刷新页面后再重试！");
					} else if (data.code == 2) {
						showErrorToast("您没有禁用账户的权限！");
					} else {
						showErrorToast("系统异常，请稍后再试！");
					}
				} else {
					showErrorToast("与服务器通讯失败，请稍后再试");
				}
				
				button.removeAttr("disabled");
			});
		} else {
			button.removeAttr("disabled");
		}
	});
	
	<s:if test="#request.queryShowback.nameIncluded">
		$("#query-form-criteria-username").attr("checked", "checked");
	</s:if>
	<s:if test="#request.queryShowback.roleIncluded">
		$("#query-form-criteria-role").attr("checked", "checked");
	</s:if>
	<s:if test="#request.queryShowback.accountTypeIncluded">
		$("#query-form-criteria-account-type").attr("checked", "checked");
	</s:if>
	<s:if test="#request.queryShowback.username != null">
		$("#query-form-username").val("<s:property value="#request.queryShowback.username"/>");
	</s:if>
	$("#query-form-role").val("<s:property value="#request.queryShowback.roleId" />");
	<s:if test="#request.queryShowback.typeStudentIncluded">
		$("#query-form-type-student").attr("checked", "checked");
	</s:if>
	<s:if test="#request.queryShowback.typeTeacherIncluded">
		$("#query-form-type-teacher").attr("checked", "checked");
	</s:if>
	<s:if test="#request.queryShowback.typeEmployeeInclueded">
		$("#query-form-type-employee").attr("checked", "checked");
	</s:if>
	<s:if test="#request.queryShowback.forbiddenIncluded">
		$("#query-form-forbidden-no").attr("checked", "checked");
	</s:if>
	<s:else>
		$("#query-form-forbidden-yes").attr("checked", "checked");
	</s:else>
	</script>
</body>
</html>