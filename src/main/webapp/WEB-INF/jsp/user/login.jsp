<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<title>这个是登录页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
span.login-forget,span.login-register {
	float: right;
	margin-left: 5px;
	margin-right: 5px;
}

div.account-control {
	height: 25px;
	padding-top: 5px;
	padding-bottom: 5px;
	line-height: 25px;
	vertical-align: middle;
}

.clear {
	clear: both;
}

div.dialog {
	height: 500px;
	padding-top: 90px;
	padding-left: 20px;
	padding-right: 20px;
}
</style>

</head>

<body>
	<jsp:include page="../competition/common/common.jsp"></jsp:include>
	<nav class="navbar navbar-default">
		<div class="container container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">HOME</a>
			</div>
		</div>
	</nav>
	<!-- 页面内容的容器 -->
	<div class="container">
		<div class="row">
			<div class="col-md-8"></div>
			<div class="col-md-4">
				<div class="dialog panel panel-default">
					<div class="panel-body">
						<form action="login.action" method="post" onsubmit="return false;">
							<div class="form-group">
								<span id="ajax-return"></span>
							</div>
							<div class="form-group">
								<label for="login-username-box">用户名：</label> <input type="text"
									id="login-username-box" class="form-control" />
							</div>
							<div class="from-group">
								<label for="login-password-box">密码：</label> <input
									type="password" id="login-password-box" class="form-control" />
							</div>
							<div class="form-group account-control">
								<span class="login-register"> <a>注册</a>
								</span>
							</div>
							<div class="form-group">
								<button id="login-login-button" class="btn btn-primary"
									style="width: 100%">登录</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			var button = $("#login-login-button");
			var actionUrl = "${pageContext.request.contextPath}/user/login.action";
			var usernameBox = $("#login-username-box");
			var passwordBox = $("#login-password-box");
			var ajaxReturn = $("span#ajax-return");

			var inputHandleFx = function() {
				var parent = $(this).parent();

				if (parent.hasClass("has-error")) {
					parent.removeClass("has-error");
					ajaxReturn.html("");
				}
			};

			var showUsernameEmptyError = function() {
				usernameBox.parent().addClass("has-error");
				usernameBox.focus();
				ajaxReturn.html("用户名不能为空！");
			};

			var showPasswordEmptyError = function() {
				passwordBox.parent().addClass("has-error");
				passwordBox.focus();
				ajaxReturn.html("密码不能为空！");
			};

			$("#login-username-box").bind("input propertychange", inputHandleFx);

			$("#login-password-box").bind("input propertychange", inputHandleFx);

			$("#login-login-button").bind("click", function() {
				button.attr("disabled", "disabled");
				// 前台校验
				if (isStringEmpty(usernameBox.val())) {
					showUsernameEmptyError();
					button.removeAttr("disabled");
					return;
				}

				if (isStringEmpty(passwordBox.val())) {
					showPasswordEmptyError();
					button.removeAttr("disabled");
					return;
				}

				// 后台校验
				$.post(actionUrl, {
					'username' : usernameBox.val(),
					'password' : passwordBox.val()
				}, function(data, textStatus) {
					if (textStatus == 'success') {
						switch (data.code) {
						case 3:
							// 用户名或者密码错误
							usernameBox.parent().addClass("has-error");
							usernameBox.focus();

							passwordBox.parent().addClass("has-error");

							ajaxReturn.html("用户名或密码有误，请核对后重试");
							break;
						case 4:
							// 用户名为空
							showUsernameEmptyError();
							break;
						case 5:
							// 密码为空
							showPasswordEmptyError();
							break;
						case 6:
							// 登录成功
							ajaxReturn.html("登录成功，请等待跳转...");
							location.href = "${pageContext.request.contextPath}/index";
							break;
						}
					} else {
						ajaxReturn.html("与服务器通信发生错误");
					}

					button.removeAttr("disabled");
				});
			});
		});
	</script>
</body>
</html>
