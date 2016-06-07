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
	<jsp:include page="../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9" style="padding: 9px;">
			<!-- 顶部标题 -->
			<div class="row">
				<div class="col-md-12">
					<h4>
						<s:property value="#request.tipWord" />
					</h4>
				</div>
			</div>
			<!-- 显示结果 -->
			<s:if test="#request.msgWord != null">
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-success">
							<p>
								<s:property value="#request.msgWord" />
							</p>
						</div>
					</div>
				</div>
			</s:if>
			<s:if test="#request.announcementList.size != 0">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-hover table-with-button">
							<tr>
								<th style="width: 3.5em">#</th>
								<th>公告标题</th>
								<th style="width: 3.5em; text-align: center;"></th>
								<th style="width: 3.5em; text-align: center;"></th>
								<th style="width: 3.5em; text-align: center;"></th>
							</tr>
							<s:iterator value="#request.announcementList" var="announcement">
								<tr>
									<td><span class="announcement-table-id"><s:property
												value="#announcement.announcementId" /></span></td>
									<td><span><s:property value="#announcement.announcementTitle" /></span></td>
									<s:if test="#announcement.announcementStatus == 3">
										<td class="td-with-button"></td>
										<td class="td-with-button"><button class="btn btn-default list-form-update">修改</button></td>
										<td class="td-with-button"><button class="btn btn-default list-form-delete">删除</button></td>
									</s:if>
									<s:if test="#announcement.announcementStatus == 2">
										<td class="td-with-button"><button class="btn btn-default list-form-update">修改</button></td>
										<td class="td-with-button"><button class="btn btn-default list-form-publish">发布</button></td>
										<td class="td-with-button"><button class="btn btn-default list-form-delete">删除</button></td>
									</s:if>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
				<!-- 分页导航部分 -->
				<div class="row">
					<div class="col-md-12 pagination-row">
						<ul class="pagination">
							<s:if test="#request.maxPage != #request.minPage && #request.showback.page != 1">
								<li><a id="nav-back-one" href="javascript:void(0);">«</a></li>
							</s:if>
							<s:iterator value="#request.allPages" var="page">
								<s:if test="#page == #request.showback.page">
									<li class="active"><a class="pagincation-page" href="javascript:void(0);"><s:property
												value="#page" /></a></li>
								</s:if>
								<s:else>
									<li><a class="pagincation-page" href="javascript:void(0);"><s:property
												value="#page" /></a></li>
								</s:else>
							</s:iterator>
							<s:if
								test="#request.maxPage != #request.minPage && #request.showback.page != #request.maxPage">
								<li><a id="nav-next-one" href="javascript:void(0);">»</a></li>
							</s:if>
						</ul>
					</div>
				</div>
			</s:if>
			<s:else>
				<s:if test="#request.msgWord == null">
					<div class="row">
						<div class="col-md-12">
							<div class="alert alert-danger">
								<p>没有找到符合条件的记录</p>
							</div>
						</div>
					</div>
				</s:if>
			</s:else>
		</div>
	</div>

	<!-- 隐藏表单，保存查询条件 -->
	<div style="display: none;">
		<s:if test="#request.function == 1">
			<form action="publishedAnnouncementList" method="post" id="query-form">
				<input type="text" name="page" id="form-page" /> <input type="hidden" name="msgWord"
					id="form-msg-word" />
			</form>
		</s:if>
		<s:if test="#request.function == 2">
			<form action="draftAnnouncementList" method="post" id="query-form">
				<input type="text" name="page" id="form-page" /> <input type="hidden" name="msgWord"
					id="form-msg-word" />
			</form>
		</s:if>
		<s:if test="#request.function == 3">
			<form action="doQuery" method="post" id="query-form">
				<%-- 使用标题作为查询条件 --%>
				<input type="checkbox" name="query.criteria" id="form-criteria-title" value="1" />
				<%-- 使用内容作为查询条件 --%>
				<input type="checkbox" name="query.criteria" id="form-criteria-content" value="2" />
				<%-- 根据发布时间作为查询条件 --%>
				<input type="checkbox" name="query.criteria" id="form-criteria-time" value="4" />
				<%-- 根据草稿类型作为查询条件 --%>
				<input type="checkbox" name="query.criteria" id="form-criteria-type" value="5" />
				<%-- 标题条件 --%>
				<input type="text" name="query.title" id="form-title" />
				<%-- 内容条件 --%>
				<input type="text" name="query.content" id="form-content" />
				<%-- 发布时间 --%>
				<input type="text" readonly="readonly" name="query.rawTime" id="form-begin-time" />
				<%-- 公告类型：已发布 --%>
				<input type="checkbox" name="query.type" id="form-type-published" value="3" />
				<%-- 公告类型：草稿 --%>
				<input type="checkbox" name="query.type" id="form-type-draft" value="2" />
				<%-- 公告类型：已删除 --%>
				<input type="checkbox" name="query.type" id="form-type-deleted" value="4" /F>
				<%-- 页码 --%>
				<input type="text" name="page" id="form-page" /> <input style="display: none;"
					checked="checked" type="checkbox" name="query.criteria" value="5" /> <input
					style="display: none;" checked="checked" type="checkbox" name="query.type" value="3" />
				<%-- 用于在下个页面显示消息的，不会传递 --%>
				<input type="hidden" name="msgWord" id="form-msg-word" />
			</form>

			<script type="text/javascript">
				<s:if test="#request.showback.titleIncluded">
				$("#form-criteria-title").attr("checked", "checked");
				</s:if>
				<s:if test="#request.showback.contentIncluded">
				$("#form-criteria-content").attr("checked", "checked");
				</s:if>
				<s:if test="#request.showback.publishTimeIncluded">
				$("#form-criteria-time").attr("checked", "checked");
				$("#form-begin-time").val("<s:property value="#request.showback.rawTime"/>");
				</s:if>
				<s:if test="#request.showback.typeIncluded">
				<s:if test="#request.showback.typePublishedIncluded">
				$("#form-type-published").attr("checked", "checked");
				</s:if>
				<s:if test="#request.showback.typeDraftIncluded">
				$("#form-type-draft").attr("checked", "checked");
				</s:if>
				<s:if test="#request.showback.typeDeletedIncluded">
				$("#form-type-deleted").attr("checked", "checked");
				</s:if>
				</s:if>

				$("#form-title").val("<s:property value="#request.showback.title"/>");
				$("#form-content").val("<s:property value="#request.showback.content"/>");
				$("#form-page").val("<s:property value="#request.showback.page"/>");
			</script>
		</s:if>
	</div>

	<script type="text/javascript">
		function gotoPage(page) {
			$("#form-page").val(page);
			$("#query-form").submit();
		}

		$("a.pagincation-page").bind("click", function() {
			var adiv = $(this);
			gotoPage(adiv.html());
		});

		$("button.list-form-update").bind("click", function() {
			var button = $(this);
			var id = trim(button.parent().parent().find("span.announcement-table-id").html());

			location.href = "${pageContext.request.contextPath}/announcement/updateAnnouncement?announcement.announcementId=" + id;
		});

		$("button.list-form-publish").bind("click", function() {
			var button = $(this);
			var id = trim(button.parent().parent().find("span.announcement-table-id").html());

			$("table-with-button button").attr("disabled", "disabled");
			$.post("pushDraft", {
				"announcement.announcementId" : id
			}, function(data, textStatus) {
				if (textStatus == 'success') {
					// form-msg-word
					if (data.code == 10) {
						$("#form-msg-word").val("成功将指定草稿发布为正式公告");
						$("#query-form").submit();
					} else if (data.code == 9) {
						// 公告编号无效
						$("table-with-button button").removeAttr("disabled");
					} else if (data.code == 7) {
						// 指定的公告对象不是一个草稿对象
						$("table-with-button button").removeAttr("disabled");
					}
				} else {
					showErrorToast("与服务器通讯失败，请稍后再试");
					$("table-with-button button").removeAttr("disabled");
				}
			});
		});

		$("button.list-form-delete").bind("click", function() {
			var button = $(this);
			var id = trim(button.parent().parent().find("span.announcement-table-id").html());

			// 这里需要用户点击两次，确认
			if (button.hasClass("btn-default")) {
				button.removeClass("btn-default").addClass("btn-danger").html("点击删除");
			} else if (button.hasClass("btn-danger")) {

				// 确认删除以后执行的
				$("table-with-button button").attr("disabled", "disabled");
				$.post("doDeleteAnnouncement", {
					"announcement.announcementId" : id
				}, function(data, textStatus) {
					if (textStatus == 'success') {
						if (data.code == 11) {
							// 成功删除
							$("#form-msg-word").val("成功删除指定的公告");
							$("#query-form").submit();
						} else if (data.code == 4) {
							// 找不到指定公告编号对应的公告记录
							$("table-with-button button").removeAttr("disabled");
						}
					} else {
						showErrorToast("与服务器通讯失败，请稍后再试！");
						$("table-with-button button").removeAttr("disabled");
					}
				});
			}
		});
	</script>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
</body>
</html>