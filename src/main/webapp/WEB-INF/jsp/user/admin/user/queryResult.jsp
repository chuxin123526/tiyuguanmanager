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

		<s:if test="#request.result.totalCount!=0">
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
								<td><span> <s:property value="#user.userId" />
								</span></td>
								<td><span> <s:property value="#user.username" />
								</span></td>
								<td>
									<button class="btn btn-default">修改</button>
								</td>
								<td><s:if test="#user.status==0">
										<button class="btn btn-default">禁用</button>
									</s:if> <s:else>
										<button class="btn btn-default">启用</button>
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
									<li class="active"><a class="pagincation-page" href="javascript:void(0);"><s:property value="#page" /></a></li>
								</s:if>
								<s:else>
									<li><a class="pagincation-page" href="javascript:void(0);"><s:property value="#page" /></a></li>
								</s:else>
							</s:iterator>
							<s:if test="#request.maxPage != #request.minPage && #request.queryShowback.page != #request.maxPage">
								<li><a id="nav-next-one" href="javascript:void(0);">»</a></li>
							</s:if>
						</ul>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
			<!-- 如果没有查询结果 -->
			<div class="col-md-9" style="padding-top: 10px">
				<div class="row">
					<div class="alert alert-danger">
						<p>没有找到相关的数据！</p>
					</div>
				</div>
			</div>
		</s:else>
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
			<input type="hidden" name="query.page" id="query-form-page"
				value="<s:property value="#request.query.page"/>" />
		</form>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
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