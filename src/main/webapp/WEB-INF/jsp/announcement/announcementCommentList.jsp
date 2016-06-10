<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../competition/common/common.jsp" />
<jsp:include page="../user/admin/common/common.jsp" />
</head>
<body>
	<%--
		该页面可以复用，通过 function 参数区分不同的功能，function 参数以及对应的值如下所示：
		1 -- 普通的评论查询功能
		2 -- 查询某个公告的评论列表 
	--%>

	<jsp:include page="../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9" style="padding: 9px;">
			<div>
				<h4>
					<s:property value="#request.titleWord" />
				</h4>
			</div>
			<s:if test="#request.comments != null && #request.comments.size > 0">
				<!-- 查询结果显示 -->
				<div class="row">
					<div class="col-md-10">
						<div class="list-group">
							<s:iterator value="#request.comments" var="comment">
								<div class="list-group-item">
									<span class="comment-hidden-info-id" style="display: none;"> <s:property
											value="#comment.commentId" />
									</span>
									<p class="comment-info-p">
										<span class="comment-content-label">发布者：</span> <span><s:property
												value="#comment.commentPublisher.username" /></span>
									</p>
									<p class="comment-info-p">
										<span class="comment-content-label">公告：</span> <span><s:property
												value="#comment.announcement.announcementTitle" /></span>
									</p>
									<p class="comment-info-p">
										<span class="comment-content-label">状态：</span>
										<s:if test="#comment.commentStatus == 1">
											<span class="comment-status-normal comment-status"
												commentid="<s:property value="#comment.commentId"/>">正常</span>
										</s:if>
										<s:if test="#comment.commentStatus == 2">
											<span class="comment-status-hidden comment-status"
												commentid="<s:property value="#comment.commentId"/>">被隐藏</span>
										</s:if>
										<s:if test="#comment.commentStatus == 3">
											<span class="comment-status-deleted comment-status"
												commentid="<s:property value="#comment.commentId"/>">已删除</span>
										</s:if>
									</p>
									<p>
										<s:property value="#comment.commentContent" />
									</p>
									<p style="text-align: right;">
										<%-- 根据评论不同的状态显示不同的操作 --%>
										<s:if test="#comment.commentStatus == 1">
											<%-- 这里是正常的评论 --%>
											<button class="btn btn-default btn-comment-opr btn-hide-comment">隐藏该评论</button>
										</s:if>
										<s:if test="#comment.commentStatus == 2">
											<%-- 被隐藏的评论 --%>
											<button class="btn btn-default btn-comment-opr btn-recover-comment">恢复该评论</button>
										</s:if>
										<s:if test="#comment.commentStatus == 3">
											<%-- 已删除的评论 --%>
											<button class="btn btn-default btn-comment-opr btn-recover-comment">恢复该评论</button>
										</s:if>
									</p>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<!-- 分页显示 -->
				<div class="row">
					<div class="col-md-10 pagination-row">
						<ul class="pagination">
							<s:if test="#request.minPage != #request.maxPage && #request.page != 1">
								<li><a id="nav-back-one" href="javascript:void(0);">«</a></li>
							</s:if>
							<s:iterator value="#request.allPages" var="pageInt">
								<s:if test="#pageInt == #request.page">
									<li class="active"><a class="pagincation-page" href="javascript:void(0);"><s:property
												value="#pageInt" /></a></li>
								</s:if>
								<s:else>
									<li><a class="pagincation-page" href="javascript:void(0);"><s:property
												value="#pageInt" /></a></li>
								</s:else>
							</s:iterator>
							<s:if test="#request.maxPage != #request.minPage && #request.page != #request.maxPage">
								<li><a id="nav-next-one" href="javascript:void(0);">»</a></li>
							</s:if>
						</ul>
					</div>
				</div>
			</s:if>
			<s:else>
				<s:if test="#request.function == 1">
					<div class="row">
						<div class="alert alert-danger">
							<p>没有找到满足条件的评论</p>
						</div>
					</div>
				</s:if>
				<s:if test="#request.function == 2">
					<div class="row">
						<div class="alert alert-warning">
							<p>该公告还没有评论</p>
						</div>
					</div>
				</s:if>
			</s:else>
		</div>
	</div>

	<!-- 隐藏表单，储存查询条件 -->
	<s:if test="#request.function == 1">
		<%-- 如果是从普通的评论查询跳转过来的 --%>
		<form id="form-request" action="doCommentQuery" method="post" style="display: none;">
			<!-- 使用公告标题作为查询条件 -->
			<input type="checkbox" name="query.type" id="query-form-criteria-announcement-title" value="5" />
			<!-- 使用发布时间作为查询条件 -->
			<input type="checkbox" name="query.type" id="query-form-criteria-time-range" value="3" />
			<!-- 使用评论状态作为查询条件 -->
			<input type="checkbox" name="query.type" id="query-form-criteria-comment-type" value="4" />
			<!-- 公告标题 -->
			<input class="form-control" type="text" name="query.announcementTitle"
				id="query-form-input-title" />
			<!-- 发布时间 -->
			<input class="form-control" type="text" readonly="readonly" name="query.rawTime"
				id="query-form-input-time" />
			<!-- 正常的评论 -->
			<input type="checkbox" name="query.commentType" value="1" checked="checked"
				id="query-form-type-normal" />
			<!-- 被隐藏的评论 -->
			<input type="checkbox" name="query.commentType" value="2" id="query-form-type-hidden" />
			<!-- 用户删除的评论 -->
			<input type="checkbox" name="query.commentType" value="3" id="query-form-type-deleted" />
			<!-- 页码 -->
			<input type="text" name="query.page" id="form-request-page" value="1" />
		</form>
	</s:if>
	<s:if test="#request.function == 2">
		<%-- 如果是从查询一篇公告的评论那里跳转过来的 --%>
		<form id="form-request" action="showAnnouncementCommentList" method="get" style="display: none;">
			<input type="text" name="announcementId" value="<s:property value="#request.announcementId"/>" />
			<input type="text" id="form-request-page" name="page" value="<s:property value="#request.page"/>" />
		</form>
	</s:if>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>

	<%-- 如果是从查询跳转过来的，就把查询条件的表单回填 --%>
	<s:if test="#request.function == 1">
		<script type="text/javascript">
			<s:if test="#request.showback.announcementTitleIncluded">
			$("#query-form-criteria-announcement-title").attr("checked", "checked");
			$("#query-form-input-title").val("<s:property value="#request.showback.annonucementTitle"/>");
			</s:if>
			<s:if test="#request.showback.commentTimeRangeIncluded">
			$("#query-form-criteria-time-range").attr("checked", "checked");
			$("#query-form-input-time").val("<s:property value="#request.showback.rawTime"/>");
			</s:if>
			<s:if test="#request.showback.commentTypeIncluded">
			$("#query-form-criteria-comment-type").attr("checked", "checked");
			<s:if test="#request.showback.typePublishedIncluded">
			$("#query-form-type-normal").attr("checked", "checked");
			</s:if>
			<s:if test="#request.showback.typeHiddenIncluded">
			$("#query-form-type-hidden").attr("checked", "checked");
			</s:if>
			<s:if test="#request.showback.typeDeletedIncluded">
			$("#query-form-type-deleted").attr("checked", "checked");
			</s:if>
			</s:if>
		</script>
	</s:if>

	<script type="text/javascript">
		function gotoPage(page) {
			$("#form-request-page").val(page);
			$("#form-request").submit();
		}

		function doRecover() {
			var button = $(this);
			var id = trim(button.parent().parent().find(".comment-hidden-info-id").html());

			$("button.btn-comment-opr").attr("disabled", "disabled");
			$.post("commentRecover", {
				"commentId" : id
			}, function(data, textStatus) {
				if (textStatus == 'success') {
					if (data == null) {
						showErrorToast("您没有恢复评论的权限");
					} else if (data.code == 17) {
						// 恢复成功
						showSuccessToast("成功恢复评论");

						button.removeClass("btn-recover-comment").addClass("btn-hide-comment").html("隐藏该评论").unbind("click").bind("click", doHide);
						$("span.comment-status[commentid='" + id + "']").html("正常").removeClass("comment-status-deleted").removeClass("comment-status-hidden")
								.addClass("comment-status-normal");
					} else if (data.code == 2) {
						showErrorToast("您没有恢复评论的权限");
					} else if (data.code == 18) {
						showErrorToast("指定的评论编号无效，请刷新页面再进行操作");
					}
				} else {
					showErrorToast("与服务器通信失败，请稍后再试");
				}

				$("button.btn-comment-opr").removeAttr("disabled");
			});
		}

		function doHide() {
			var button = $(this);
			var id = trim(button.parent().parent().find(".comment-hidden-info-id").html());

			$("button.btn-comment-opr").attr("disabled", "disabled");
			$.post("commentHide", {
				"commentId" : id
			}, function(data, textStatus) {
				if (textStatus == 'success') {
					if (data == null) {
						showErrorToast("您没有隐藏评论操作的权限");
					} else if (data.code == 16) {
						// 隐藏成功
						showSuccessToast("成功隐藏评论");

						button.removeClass("btn-hide-comment").addClass("btn-recover-comment").html("恢复该评论").unbind("click").bind("click", doRecover);
						$("span.comment-status[commentid='" + id + "']").html("被隐藏").removeClass("comment-status-normal").addClass("comment-status-hidden");
					} else if (data.code == 2) {
						// 没有权限
						showErrorToast("您没有隐藏评论操作的权限");
					} else if (data.code == 18) {
						// 评论编号无效
						showErrorToast("进行操作的评论");
					}
				} else {
					showErrorToast("与服务器通信失败，请稍后再试");
				}

				$("button.btn-comment-opr").removeAttr("disabled");
			});
		}

		$(function() {
			$("a.pagincation-page").bind("click", function() {
				var aLink = $(this);
				var id = aLink.html();

				gotoPage(id);
			});

			$("#nav-back-one").bind("click", function() {
				gotoPage(<s:property value="#request.page - 1"/>);
			});

			$("#nav-next-one").bind("click", function() {
				gotoPage(<s:property value="#request.page + 1"/>);
			});

			$("button.btn-hide-comment").bind("click", doHide);
			$("button.btn-recover-comment").bind("click", doRecover);
		});
	</script>
</body>
</html>