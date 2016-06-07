<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../competition/common/common.jsp" />
<jsp:include page="../user/admin/common/common.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/daterangepicker-bs3.css" />
</head>
<body>
	<jsp:include page="../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9" style="padding: 9px;">
			<div>
				<h4>查询公告</h4>
			</div>
			<form id="query-form" action="doQuery" method="post">
				<div class="row form-group">
					<div class="col-md-2 form-label">
						<label>使用的条件：</label>
					</div>
					<div class="col-md-8">
						<span> <input type="checkbox" name="query.criteria" id="form-criteria-title"
							checked="checked" value="1" />&nbsp;标题
						</span> &nbsp; <span> <input type="checkbox" name="query.criteria" id="form-criteria-content"
							value="2" />&nbsp;内容
						</span> &nbsp; <span> <input type="checkbox" name="query.criteria" id="form-criteria-time"
							value="4" />&nbsp;发布时间
						</span> &nbsp; <span> <input type="checkbox" name="query.criteria" id="form-criteria-type"
							value="5" />&nbsp;类型
						</span>
					</div>
				</div>
				<!-- 标题 -->
				<div class="row form-group" id="form-div-title">
					<div class="col-md-2 form-label">
						<label class="form-label">标题：</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" type="text" name="query.title" id="form-title" />
					</div>
				</div>
				<!-- 内容 -->
				<div class="row form-group" id="form-div-content" style="display: none;">
					<div class="col-md-2 form-label">
						<label class="form-label">关键字：</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" type="text" name="query.content" id="form-content" />
					</div>
				</div>
				<!-- 发布时间 -->
				<div class="row form-group" id="form-div-time" style="display: none;">
					<div class="col-md-2 form-label">
						<label class="form-label">发布时间：</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" type="text" readonly="readonly" name="query.rawTime"
							id="form-begin-time" />
					</div>
				</div>
				<!-- 公告类型 -->
				<div class="row form-group" id="form-div-type" style="display: none;">
					<div class="col-md-2" style="text-align: right;">
						<label>公告类型：</label>
					</div>
					<div class="col-md-8">
						<span><input type="checkbox" id="form-type-published" checked="checked"
							name="query.type" value="3" />&nbsp;已发布公告</span> &nbsp; <span><input type="checkbox"
							id="form-type-draft" name="query.type" value="2" />&nbsp;草稿</span> &nbsp; <span><input
							type="checkbox" id="form-type-deleted" name="query.type" value="4" />&nbsp;已删除公告</span>
					</div>
				</div>
			</form>
			<!-- 下面按钮框 -->
			<div class="row form-group" id="form-div-button">
				<div class="col-md-10" style="text-align: right;">
					<button class="btn btn-default" id="btn-clear-criteria">清除条件</button>
					<button class="btn btn-primary" id="btn-do-query">查询</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/moment.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datepicker/daterangepicker.js"></script>
	<script type="text/javascript">
		$('#form-begin-time').daterangepicker(null, function(start, end, label) {

		});
	</script>
	<script type="text/javascript">
		var raw = "<s:property value="#request.tipWord"/> - <s:property value="#request.tipWord"/>";

		$("#form-begin-time").val(raw);

		var titleCheckBox = $("#form-criteria-title");
		var contentCheckBox = $("#form-criteria-content");
		var timeCheckBox = $("#form-criteria-time");
		var typeCheckBox = $("#form-criteria-type");

		var titleDiv = $("#form-div-title");
		var contentDiv = $("#form-div-content");
		var timeDiv = $("#form-div-time");
		var typeDiv = $("#form-div-type");

		function changeForm() {
			if (titleCheckBox.is(":checked")) {
				titleDiv.css("display", "block");
			} else {
				titleDiv.css("display", "none");
			}

			if (contentCheckBox.is(":checked")) {
				contentDiv.css("display", "block");
			} else {
				contentDiv.css("display", "none");
			}

			if (timeCheckBox.is(":checked")) {
				timeDiv.css("display", "block");
			} else {
				timeDiv.css("display", "none");
			}

			if (typeCheckBox.is(":checked")) {
				typeDiv.css("display", "block");
			} else {
				typeDiv.css("display", "none");
			}
		}

		$("input[name='query.criteria']").bind("click", changeForm);
		$("#btn-clear-criteria").bind("click", function() {
			titleCheckBox.attr("checked", "checked");
			contentCheckBox.removeAttr("checked");
			timeCheckBox.removeAttr("checked");
			typeCheckBox.removeAttr("checked");
			
			changeForm();

			$("#query-form input[type='text']").val("");
			$("#form-type-draft").removeAttr("checked");
			$("#form-type-deleted").removeAttr("checked");
		});

		$("#btn-do-query").bind("click", function() {
			$("#query-form").submit();
		});
	</script>
</body>
</html>