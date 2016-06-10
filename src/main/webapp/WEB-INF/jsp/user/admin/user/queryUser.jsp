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

		<div class="col-md-9" style="margin-top: 10px;">
			<div class="row">
				<h4>查找用户</h4>
			</div>
			<form action="userQuery.action" id="user-query-form" method="post">
				<div class="row form-group">
					<div class="col-md-4" style="text-align: right;">
						<label>要使用的条件：</label>
					</div>
					<div class="col-md-6 form-group">
						<span> <input type="checkbox" id="query-form-criteria-username" name="query.criteria"
							value="0" checked="checked" />&nbsp;用户名
						</span> &nbsp; <span> <input type="checkbox" id="query-form-criteria-role"
							name="query.criteria" value="1" />&nbsp;角色
						</span> &nbsp; <span> <input type="checkbox" id="query-form-criteria-account-type"
							name="query.criteria" value="2" />&nbsp;账号类型
						</span>
					</div>
				</div>
				<div class="row form-group" id="form-div-username">
					<div class="col-md-4 form-label">
						<label class="form-label">用户名：</label>
					</div>
					<div class="col-md-6">
						<input type="text" name="query.username" id="query-form-username" class="form-control" />
					</div>
				</div>
				<div class="row form-group" id="form-div-role" style="display: none;">
					<div class="col-md-4 form-label">
						<label class="form-label">角色：</label>
					</div>
					<div class="col-md-6">
						<select class="form-control" name="query.roleId" id="query-form-role">
							<s:iterator value="#request.roleList" var="role">
								<option value="<s:property value="#role.roleId"/>">
									<s:property value="#role.name" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="row form-group" id="form-div-account-type" style="display: none;">
					<div class="col-md-4" style="text-align: right;">
						<label>账号类型：</label>
					</div>
					<div class="col-md-6">
						<span> <input type="checkbox" name="query.accountType" value="0">&nbsp;学生账号
						</span> &nbsp; <span> <input type="checkbox" name="query.accountType" value="2" />&nbsp;老师账号
						</span> &nbsp; <span> <input type="checkbox" name="query.accountType" value="1" />&nbsp;工作人员账号
						</span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-4" style="text-align: right;">
						<label>是否查询被禁用的账号：</label>
					</div>
					<div class="col-md-6">
						<span><input type="radio" name="query.forbidden" value="1" checked="checked" />&nbsp;是</span>
						<span><input type="radio" name="query.forbidden" value="0" />&nbsp;否</span>
					</div>
				</div>
				<div class="row form-group" style="text-align: right;">
					<button class="btn btn-primary" id="form-query-btn">查询</button>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>

	<script type="text/javascript">
		$("#nav-search-user").addClass("active");

		function changeForm() {
			console.log("debug");

			if ($("input#query-form-criteria-username").is(":checked")) {
				$("div#form-div-username").css("display", "block");
			} else {
				$("div#form-div-username").css("display", "none");
			}

			if ($("input#query-form-criteria-role").is(":checked")) {
				$("div#form-div-role").css("display", "block");
			} else {
				$("div#form-div-role").css("display", "none");
			}

			if ($("input#query-form-criteria-account-type").is(":checked")) {
				$("div#form-div-account-type").css("display", "block");
			} else {
				$("div#form-div-account-type").css("display", "none");
			}
		}

		$(function() {
			$("input[name='query.criteria']").change(changeForm);

			$("button#form-query-btn").bind("click", function() {
				$("button#form-query-btn").attr("disabled", "disabled");

				$("#user-query-form").submit();
			});
		});
	</script>
</body>
</html>