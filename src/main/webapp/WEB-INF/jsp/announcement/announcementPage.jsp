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
		<s:if test="#request.announcement != null && #request.announcement.announcementStatus == 3">
			<!-- 这里是标题和发布时间信息 -->
			<div class="row" style="margin-top: 20px">
				<div class="col-md-12">
					<h4>
						<s:property value="#request.announcement.announcementTitle" />
					</h4>
					<p>
						发布时间：
						<s:property value="#request.time" />
					</p>
				</div>
			</div>
			<hr />
			<!-- 这里是公告正文部分 -->
			<div class="row">
				<div class="col-md-12">
					<s:property value="#request.announcement.announcementContent" escape="false" />
				</div>
			</div>
			<hr />
			<!-- 这里是公告评论框 -->
			<form class="form" id="comment-form" onsubmit="return false;">
				<div class="row">
					<div class="col-md-8">
						<p>发表您的评论：</p>
					</div>
				</div>
				<div class="row">
					<input id="announcement-id-box" type="hidden" name="comment.announcementId"
						value="<s:property value="#request.announcement.announcementId"/>" />
					<div class="col-md-8">
						<textarea style="resize: vertical;" class="form-control" rows="5" cols="20"
							name="comment.content" id="comment-content-box"></textarea>
					</div>
				</div>
			</form>
			<div class="row" style="margin-top: 15px">
				<div class="col-md-8" style="text-align: right;">
					<button class="btn btn-primary" id="comment-publish-btn">发表评论</button>
				</div>
			</div>
			<!-- 这里是公告的评论列表 -->
			<hr />
			<div class="row">
				<div class="col-md-8">
					<span style="display: none;" id="max-comment-id">0</span>
					<div class="alert alert-success" id="comment-status" style="display: block;">
						<p>正在加载评论</p>
					</div>
					<div class="list-group" id="comment-box" style="display: none;">
						<div id="comment-box-content"></div>
						<div class="list-group-item" id="item-load-more-comment-div">
							<button id="comment-more-btn" class="btn btn-primary" style="width: 100%">加载更多评论</button>
						</div>
					</div>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="row">
				<div class="col-md-10 col-md-offset-1" style="margin-top: 30px;">
					<div class="alert alert-danger">
						<p>没有找到指定的公告</p>
					</div>
				</div>
			</div>
		</s:else>
	</div>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript">
		function generateCommentBox(content, time, publisher, id) {
			var mainDiv = $("<div></div>").addClass("list-group-item").attr("announcement-pulisher", publisher).attr("announcement-id", id);
			var contentP = $("<p></p>").addClass("announcement-comment-content").html(content);
			var infoP = $("<p></p>").addClass("announcement-comment-info");

			if (publisher == <s:property value="#session.user.userId"/>) {
				var deleteSpan = $("<span><a href='javascript:void(0);'>删除</a></span>").addClass("comment-delete-link").css("margin-right", "6px");
				infoP.append(deleteSpan);
			}

			infoP.append($("<span></span>").html(time));
			return mainDiv.append(contentP).append(infoP);
		}

		function infoBox(type, content) {
			var infoBox = $("#comment-status");
			infoBox.removeClass().addClass("alert");

			if (type == 1) {
				infoBox.addClass("alert-success");
			} else if (type == 2) {
				infoBox.addClass("alert-warning");
			} else if (type == 3) {
				infoBox.addClass("alert-danger");
			}

			infoBox.find("p").html(content);
		}

		function refreshComment(resetConut, callback) {
			var moreDiv = $("#item-load-more-comment-div");
			var contentDiv = $("#comment-box-content");
			var from = parseInt($("#max-comment-id").html());

			var data = {
				"announcementId" : $("#announcement-id-box").val()
			};

			if (resetConut) {
				data.from = 0;
				data.count = 10;
			} else {
				data.from = 0;
				data.count = from;
			}

			$.post("getAnnoucementComment", data, function(data, textStatus) {
				if (textStatus == 'success') {
					if (data == null) {
						infoBox(3, "当前登录用户没有获得评论信息的权限");
					} else if (data.totalCount == 0) {
						infoBox(2, "暂无评论");

						$("#comment-status").css("display", "block");
						$("#comment-box").css("display", "none");
					} else {
						var comments = data.comments;
						contentDiv.html("");

						for (var i = 0; i < comments.length; i++) {
							contentDiv.append(generateCommentBox(comments[i].content, comments[i].time, comments[i].publisher, comments[i].id));
						}

						$("#comment-status").css("display", "none");
						var newFrom = from + comments.length;
						$("#max-comment-id").html(newFrom);
						$("#comment-box").css("display", "block");

						if (data.hasMore) {

						} else {
							$("#comment-more-btn").html("所有评论加载完毕").attr("disabled", "disabled");
						}

						refreshLinkEvent();
					}

					if (callback) {
						callback(true, !data.hasMore);
					}
				} else {
					showErrorToast("评论更新失败");
					if (callback) {
						callback(false, false);
					}
				}
			});
		}

		$(function() {
			refreshComment(true, null);
		});

		function refreshLinkEvent() {
			$("span.comment-delete-link").find("a").unbind("click");
			$("span.comment-delete-link").find("a").bind("click", function() {
				var thisLink = $(this);
				var id = thisLink.parent().parent().parent().attr("announcement-id");

				$.post("deleteAnnouncementComment", {
					"commentId" : id
				}, function(data, textStatus) {
					if (textStatus == 'success') {
						if (data == null) {
							showErrorToast("您没有删除公告评论的权限");
						} else if (data.code == 14) {
							// 删除成功
							var from = parseInt($("#max-comment-id").html());
							$("#max-comment.id").html(from - 1);

							refreshComment(false, null);

							showSuccessToast("成功删除评论");
						} else if (data.code == 9) {
							// 指定的评论编号无效
							showErrorToast("该评论编号无效，可能该评论已经被删除");
						} else if (data.code == 15) {
							// 删除不是自己发布的评论
							showErrorToast("你只能删除自己发布的评论！");
						} else if (data.code == 2) {
							showErrorToast("您没有删除公告评论的权限");
						}
					} else {
						showErrorToast("与服务器通讯失败，请稍后再试");
					}
				});
			});
		}

		$("#comment-more-btn").bind("click", function() {
			var from = parseInt($("#max-comment-id").html());
			$("#max-comment-id").html(from + 10);

			$("#comment-more-btn").attr("disabled", "disabled").html("正在加载");
			refreshComment(false, function(success, noComment) {
				if (!noComment) {
					$("#comment-more-btn").removeAttr("disabled").html("加载更多评论");
				}
			});
		});

		$("#comment-publish-btn").bind("click", function() {
			var data = $("#comment-form").serialize();

			$("#comment-publish-btn").attr("disabled", "disabled");
			$.post("commentPublish", data, function(data, textStatus) {
				if (textStatus == 'success') {
					if (data == null) {
						showErrorToast("您当前没有发布评论的权限");
					} else if (data.code == 12) {
						// 评论发布成功
						showSuccessToast("评论发布成功");

						$("#max-comment-id").html(parseInt($("#max-comment-id").html()) + 1);
						$("#comment-content-box").val("");

						// 刷新评论列表
						refreshComment(false, null);
					} else if (data.code == 13) {
						// 当前用户无效
						showErrorToast("登录失效，请重新登录");
					} else if (data.code == 2) {
						// 没权限
						showErrorToast("您当前没有发布评论的权限");
					} else if (data.code == 9) {
						// 公告编号无效
						showErrorToast("公告编号无效，可能该公告已经被删除");
					} 
				} else {
					showErrorToast("与服务器通讯失败，请稍后再试");
				}

				$("#comment-publish-btn").removeAttr("disabled");
			});
		});

		function refreshAllComment() {
			$("#comment-box").css("display", "none");
			$("#comment-status").css("display", "block").find("p").html("正在加载评论");
		}
	</script>
</body>
</html>