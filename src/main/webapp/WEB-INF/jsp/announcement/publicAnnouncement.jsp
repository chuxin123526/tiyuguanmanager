<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<title>公告列表</title>
<jsp:include page="../competition/common/common.jsp" />
<jsp:include page="../user/admin/common/common.jsp" />
</head>
<body>
	<jsp:include page="../competition/common/top.jsp"></jsp:include>

	<div class="container" style="margin-top: 15px;">
		<%-- 如果当前的系统中没有公告时，显示相应的提示信息 --%>
		<s:if test="#request.announcements != null && #request.announcements.size > 0">
			<!-- 右边的搜索框 -->
			<div class="row">
				<div class="col-md-3 col-md-offset-8 form-group">
					<input type="text" class="form-control" id="announcement-search-box" />
				</div>
				<div class="col-md-1">
					<button class="btn btn-primary" id="search-btn">搜索</button>
				</div>
			</div>
			<hr />
			<!-- 列表正文 -->
			<div class="row" style="margin-bottom: 5px;">
				<div class="col-md-10 col-md-offset-2">
					<h4>
						<s:property value="#request.titleWord" />
					</h4>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-2">
					<div class="list-group">
						<s:iterator value="#request.announcements" var="announcement">
							<div class="list-group-item">
								<div class="public-announcement-list-title">
									<p>
										<a target="_blank"
											href="announcementPage?id=<s:property value="#announcement.announcementId"/>"><s:property
												value="#announcement.announcementTitle" /></a>
									</p>
								</div>
								<div class="public-annoucement-list-time">
									<p>
										<span>发布时间：</span> <span> <s:property
												value="#announcement.announcementPublisherTimeString" />
										</span>
									</p>
								</div>
								<div class="public-announcement-list-content">
									<p>
										<s:property value="#announcement.simpleDescription" />
									</p>
								</div>
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
			<!-- 分页导航 -->
			<div class="row">
				<div class="col-md-10 col-md-offset-2 pagination-row">
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

		</s:else>
	</div>

	<!-- 为了解决一个非常操蛋的问题，出此下策 -->
	<form action="searchAnnouncement" id="goto-search-page" method="post" style="display: none;">
		<input type="hidden" name="keyword" id="search-form-keyword" value="" /> <input type="hidden"
			name="page" id="search-form-page" value="1" />
	</form>

	<!-- 隐藏表单 -->
	<s:if test="#request.function == 1">
		<form id="query-form" style="display: none;" action="allAnnouncement" method="get">
			<input type="hidden" name="page" value="1" id="query-form-page" />
		</form>
	</s:if>
	<s:if test="#request.function == 2">
		<form id="query-form" style="display: none;" action="searchAnnouncement" method="post">
			<input type="hidden" name="page" value="<s:property value="#request.page"/>" /> <input
				type="hidden" name="keyword" value="<s:property value="#request.keyword"/>" />
		</form>
	</s:if>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript">
		function gotoPage(page) {
			$("#query-form-page").val(page);
			$("#query-form").submit();
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

			$("button#search-btn").bind("click", function() {
				$("#search-form-keyword").val($("#announcement-search-box").val());
				$("#goto-search-page").submit();
			});
		});
	</script>
</body>
</html>