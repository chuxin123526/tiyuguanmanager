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

		<div class="col-md-9" style="padding: 9px">
			<div>
				<h4>查询公告评论</h4>
			</div>
			<form id="query-form" action="doCommentQuery" method="post">
				<div class="row form-group">
					<div class="col-md-2" style="text-align: right;">
						<label>使用的条件：</label>
					</div>
					<div class="col-md-8">
						<!-- 根据公告标题查询  -->
						<span> <input type="checkbox" name="query.type"
							id="query-form-criteria-announcement-title" value="5" checked="checked" />&nbsp;公告标题
						</span> &nbsp;
						<!-- 根据发布时间 -->
						<span> <input type="checkbox" name="query.type" id="query-form-criteria-time-range"
							value="3" />&nbsp;发布时间
						</span> &nbsp;
						<!-- 根据评论类型 -->
						<span> <input type="checkbox" name="query.type" id="query-form-criteria-comment-type"
							value="4" />&nbsp;评论状态
						</span>
					</div>
				</div>
				<!-- 公告标题 -->
				<div class="row form-group" id="query-form-div-title">
					<div class="col-md-2 form-label">
						<label class="form-label">关键字：</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" type="text" name="query.announcementTitle"
							id="query-form-input-title" />
					</div>
				</div>
				<!-- 发布时间 -->
				<div class="row form-group" id="query-form-div-time">
					<div class="col-md-2 form-label">
						<label class="form-label">发布时间：</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" type="text" readonly="readonly" name="query.rawTime"
							id="query-form-input-time" value="<s:property value="#request.rawTime"/>" />
					</div>
				</div>
				<!-- 评论类型 -->
				<div class="row form-group" id="query-form-div-comment-type">
					<div class="col-md-2" style="text-align: right;">
						<label>评论状态：</label>
					</div>
					<div class="col-md-8">
						<!-- 正常的评论 -->
						<span> <input type="checkbox" name="query.commentType" value="1" checked="checked"
							id="query-form-type-normal" />&nbsp;正常评论
						</span> &nbsp;
						<!-- 被隐藏的评论 -->
						<span> <input type="checkbox" name="query.commentType" value="2"
							id="query-form-type-hidden" />&nbsp;被隐藏的评论
						</span> &nbsp;
						<!-- 由用户删除的评论 -->
						<span> <input type="checkbox" name="query.commentType" value="3"
							id="query-form-type-deleted" />&nbsp;用户删除的评论
						</span>
					</div>
				</div>
			</form>
			<!-- 表单按钮 -->
			<div class="row form-group">
				<div class="col-md-10" style="text-align: right;">
					<button class="btn btn-default" id="btn-clear-form">清除条件</button>
					<button class="btn btn-primary" id="btn-query">查询</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/moment.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datepicker/daterangepicker.js"></script>

	<script type="text/javascript">
		$("#query-form-input-time").daterangepicker(null, function(start, end, label) {

		});

		var titleCheckBox = $("#query-form-criteria-announcement-title");
		var titleFormDiv = $("#query-form-div-title");

		var timeRangeCheckBox = $("#query-form-criteria-time-range");
		var timeRangeFormDiv = $("#query-form-div-time");

		var statusCheckBox = $("#query-form-criteria-comment-type");
		var statusFormDiv = $("#query-form-div-comment-type");

		var typeNormalCheckBox = $("#query-form-type-normal");
		var typeHiddenCheckBox = $("#query-form-type-hidden");
		var typeDeletedCheckBox = $("#query-form-type-deleted");

		function refreshForm() {
			if (titleCheckBox.is(":checked")) {
				titleFormDiv.css("display", "block");
			} else {
				titleFormDiv.css("display", "none");
			}

			if (timeRangeCheckBox.is(":checked")) {
				timeRangeFormDiv.css("display", "block");
			} else {
				timeRangeFormDiv.css("display", "none");
			}

			if (statusCheckBox.is(":checked")) {
				statusFormDiv.css("display", "block");
			} else {
				statusFormDiv.css("display", "none");
			}
		}

		function clearForm() {
			titleCheckBox.attr("checked", "checked");
			timeRangeCheckBox.removeAttr("checked");
			statusCheckBox.removeAttr("checked");

			refreshForm();

			typeNormalCheckBox.attr("checked", "checked");
			typeHiddenCheckBox.removeAttr("checked");
			typeDeletedCheckBox.removeAttr("checked");
		}

		$("input[name='query.type']").bind("click", refreshForm);
		refreshForm();

		$("button#btn-clear-form").bind("click", clearForm);
		$("button#btn-query").bind("click", function() {
			$("#query-form").submit();
		});
	</script>
</body>
</html>