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
		#request.function 是用于判断当前页面的作用所使用的变量
		1 -- 创建新公告
		2 -- 修改现有公告
	 --%>

	<jsp:include page="../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9" style="padding: 9px;">
			<div class="row">
				<div class="col-md-12">
					<h4>
						<s:property value="#request.tipWord" />
					</h4>
				</div>
			</div>
			<form id="announcement-form">
				<input type="hidden" name="announcement.userId"
					value="<s:property value="#session.user.userId"/>" />
				<s:if test="#request.function == 2">
					<input type="hidden" name="announcement.announcementId"
						value="<s:property value="#request.announcement.announcementId"/>" />
				</s:if>
				<div class="row form-group">
					<div class="col-md-2 form-label">
						<label class="form-label">公告标题：</label>
					</div>
					<div class="col-md-10">
						<input class="form-control" type="text" id="form-title-input" name="announcement.title" />
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-2 form-label">
						<label class="form-label">公告内容：</label>
					</div>
					<div class="col-md-10">
						<textarea id="announcement-content" rows="2" cols="20" class="ckeditor"
							name="announcement.content"></textarea>
					</div>
				</div>
			</form>
			<div class="row form-group">
				<div class="col-md-12 form-group" style="text-align: right;">
					<!-- 
					<%-- 发布新公告或者是修改草稿 --%>
					<s:if
						test="#request.function == 1 || (#request.function == 2 && #request.announcement.type == 2)">
						<button class="btn btn-default" id="btn-save-draft"
							class="btn-save-draft btn-announcement-flow">
							<s:if test="#request.function == 1">
								<span>保存为草稿</span>
							</s:if>
							<s:if test="#request.function == 2">
								<span>保存</span>
							</s:if>
						</button>
					</s:if>
					<button class="btn btn-primary" id="btn-save-published"
						class="btn-save-published btn-announcement-flow">
						<s:if test="#request.function == 1">
							<span>发布</span>
						</s:if>
						<s:if test="#request.function == 2">
							<s:if test="#request.announcement.type == 2">
								<span>保存并发布</span>
							</s:if>
							<s:else>
								<span>保存</span>
							</s:else>
						</s:if>
					</button>
					 -->
					<%-- 发布新公告 --%>
					<s:if test="#request.function == 1">
						<%-- 如果具有保存草稿的权限，则显示保存草稿的按钮 --%>
						<s:if test="#session.user.role.permissions.{?#this.type==32}.size>0">
							<button class="btn btn-default btn-save-draft btn-announcement-flow" id="btn-save-draft">
								<span>保存草稿</span>
							</button>
						</s:if>

						<%-- 如果具有发布公告的权限，则显示发布的按钮 --%>
						<s:if test="#session.user.role.permissions.{?#this.type==31}.size>0">
							<button class="btn btn-primary btn-save-published btn-announcement-flow"
								id="btn-save-published">
								<span>立即发布</span>
							</button>
						</s:if>

						<%-- 如果两个权限都没有。虽然好像会被拦截器拦截掉，不过还是显示一下好一点。 --%>
						<s:if test="#session.user.role.permissions.{?(#this.type==31 || #this.type==32)}.size == 0">
							<div class="alert alert-danger">
								<p>您没有发布草稿和公告的权限</p>
							</div>
						</s:if>
					</s:if>
					<%-- 如果是修改已经发布的公告 --%>
					<s:if test="#request.function == 2 && #request.announcement.type == 3">
						<%-- 需要同时具备发布公告和修改的权限才可以对正式发布的公告进行修改 --%>
						<s:if
							test="#session.user.role.permissions.{?#this.type == 31}.size>0 && #session.user.role.permissions.{?#this.type==35}.size>0">
							<button class="btn btn-primary btn-save-published btn-announcement-flow"
								id="btn-save-published">
								<span>保存</span>
							</button>
						</s:if>
						<s:else>
							<div class="alert alert-danger">
								<p>您没有变更已经发布公告的权限</p>
							</div>
						</s:else>
					</s:if>
					<%-- 如果修改的是草稿 --%>
					<s:if test="#request.function == 2 && #request.announcement.type == 2">
						<%-- 如果只具备修改公告的权限，就只能保存；如果同时具有发布公告权限的话，就显示保存以及发布两个按钮 --%>
						<s:if test="#session.user.role.permissions.{?#this.type == 35}">
							<button class="btn btn-default btn-save-draft btn-announcement-flow" id="btn-save-draft">
								<span>保存草稿</span>
							</button>
						</s:if>

						<%-- 如果还具备发布公告的权限 --%>
						<s:if
							test="#session.user.role.permissions.{?#this.type == 31}.size>0 && #session.user.role.permissions.{?#this.type==35}.size>0">
							<button class="btn btn-primary btn-save-published btn-announcement-flow"
								id="btn-save-published">
								<span>保存并发布</span>
							</button>
						</s:if>

						<%-- 如果都不具备这样的权限 --%>
						<s:if test="#session.user.role.permissions.{?(#this.type == 35)}.size == 0">
							<div class="alert alert-danger">
								<p>您没有变更已经发布公告的权限</p>
							</div>
						</s:if>
					</s:if>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		var editor = CKEDITOR.replace("announcement.content");

		function checkForm() {
			if (isStringEmpty($("#form-title-input").val())) {
				showErrorToast("公告的标题不能为空");
				return false;
			}

			if (isStringEmpty($("#announcement-content").val())) {
				showErrorToast("公告的内容不能为空");
				return false;
			}

			return true;
		}

		function save() {
			editor.updateElement();
		}

		function setContent(content) {
			editor.setData(content);
		}
	</script>
	<%-- 如果是创建新公告 --%>
	<s:if test="#request.function == 1">
		<script type="text/javascript">
			$("#nav-new-announcement").addClass("active");

			$("button#btn-save-published").bind("click", function() {
				save();
				if (!checkForm()) {
					console.log("form-check-failed");
					return;
				}

				$("button.btn-announcement-flow").attr("disabled", "disabled");
				$.post("publishAnnouncement", $("#announcement-form").serialize(), function(data, textStatus) {
					if (textStatus == 'success') {
						if (data == null) {
							showErrorToast("您没有发布公告的权限！");
						} else if (data.code == 3) {
							location.href = "${pageContext.request.contextPath}/announcement/info?function=1";
						} else if (data.code == 4) {
							showErrorToast("请重新登录再试");
						} else if (data.code == 8) {
							showErrorToast("表单有误，请核对后再提交");
						}
					} else {
						showErrorToast("与服务器通讯失败，请稍后再试！");
					}

					$("button.btn-announcement-flow").removeAttr("disabled");
				});
			});

			$("button#btn-save-draft").bind("click", function() {
				save();
				if (!checkForm()) {
					console.log("form-check-failed");
					return;
				}

				$("button.btn-announcement-flow").attr("disabled", "disabled");
				$.post("publisherDraft", $("#announcement-form").serialize(), function(data, textStatus) {
					if (textStatus == 'success') {
						if (data == null) {
							showErrorToast("您没有发布草稿的权限");
						} else if (data.code == 5) {
							location.href = "${pageContext.request.contextPath}/announcement/info?function=2";
						} else if (data.code == 4) {
							showErrorToast("请重新登录再试");
						} else if (data.code == 8) {
							showErrorToast("表单有误，请核对后再提交");
						} else if (data.code == 2) {
							showErrorToast("您没有发布草稿的权限");
						}
					} else {
						showErrorToast("与服务器通讯失败，请稍后再试！");
					}

					$("button.btn-announcement-flow").removeAttr("disabled");
				});
			});
		</script>
	</s:if>
	<%-- 如果是修改公告或草稿 --%>
	<s:if test="#request.function == 2">
		<pre style="display: none;" id="origin-title">
			<s:property value="#request.announcement.title" escape="false" />
		</pre>
		<pre style="display: none;" id="origin-content">
			<s:property value="#request.announcement.content" escape="false" />
		</pre>
		<script type="text/javascript">
			$("#form-title-input").val(trim($("#origin-title").html()));
			setContent($("#origin-content").html());
		</script>
		<%-- 草稿 --%>
		<s:if test="#request.announcement.type == 2">
			<script type="text/javascript">
				$("button#btn-save-draft").bind("click", function() {
					save();
					if (!checkForm()) {
						return;
					}

					$("button.btn-announcement-flow").attr("disabled", "disabled");
					$.post("doUpdateDraft", $("#announcement-form").serialize(), function(data, textStatus) {
						if (textStatus == 'success') {
							if (data == null) {
								showErrorToast("您没有更改草稿的权限");
							} else if (data.code == 6) {
								// 成功变更草稿内容，跳转到信息页面并显示相应的提示信息
								location.href = "${pageContext.request.contextPath}/announcement/info?function=4";
							} else if (data.code == 9) {
								// 指定的公告编号无效
								showErrorToast("该公告的编号已经失效，请确认后再进行相应的操作");
							} else if (data.code == 4) {
								// 指定的用户编号无效
							} else if (data.code == 1) {
								// 后台表单校验失败
							}
						} else {
							showErrorToast("与服务器通讯失败，请稍后再试！");
						}

						$("button.btn-announcement-flow").removeAttr("disabled");
					});
				});

				$("button#btn-save-published").bind("click", function() {
					save();
					if (!checkForm()) {
						return;
					}

					$("button.btn-announcement-flow").attr("disabled", "disabled");
					$.post("doUpdateAnnouncement", $("#announcement-form").serialize(), function(data, textStatus) {
						if (textStatus == 'success') {
							if (data == null) {
								showErrorToast("您没有发布和修改公告的权限");
							} else if (data.code == 8) {
								//  成功修改并发布公告，转到相应的信息提示界面
								location.href = "${pageContext.request.contextPath}/announcement/info?function=6";
							} else if (data.code == 9) {
								// 公告编号无效
								showErrorToast("该公告编号已经失效，请确认后再进行该操作");
							} else if (data.code == 4) {
								// 指定的用户编号无效
								showErrorToast("当前登录状态已经失效，请重新登录");
							} else if (data.code == 1) {
								// 后台表单校验失败
								showErrorToast("表单填写有误，请确认核实后再次保存");
							}
						} else {
							showErrorToast("与服务器通讯失败，请稍后再试！");
						}

						$("button.btn-announcement-flow").removeAttr("disabled");
					});
				});
			</script>
		</s:if>
		<%-- 已经发布的公告 --%>
		<s:if test="#request.announcement.type == 3">
			<script type="text/javascript">
				$("button#btn-save-published").bind("click", function() {
					save();
					if (!checkForm()) {
						return;
					}

					$("button.btn-announcement-flow").attr("disabled", "disabled");
					$.post("doUpdateAnnouncement", $("#announcement-form").serialize(), function(data, textStatus) {
						if (textStatus == 'success') {
							if (data == null) {
								// 没有相应的权限
								showErrorToast("您没有更改公告的权限");
							} else if (data.code == 8) {
								// 变更公告内容之后跳转到相应的信息页面
								location.href = "${pageContext.request.contextPath}/announcement/info?function=3";
							} else if (data.code == 9) {
								// 指定的公告编号无效
								showErrorToast("当前的公告编号已经失效，请确认后再进行操作");
							} else if (data.code == 4) {
								// 指定的用户编号无效
								showErrorToast("登录状态已经失效，请重新登录再继续该操作");
							} else if (data.code == 1) {
								// 后台表单校验失败
								showErrorToast("(╯‵□′)╯︵┻━┻ 别耍坏，表单后台校验出错了");
							}
						} else {
							showErrorToast("与服务器通讯失败，请稍后再试！");
						}

						$("button.btn-announcement-flow").removeAttr("disabled");
					});
				});
			</script>
		</s:if>
	</s:if>
</body>
</html>