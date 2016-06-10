<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改密码</title>
<jsp:include page="../competition/common/common.jsp" />
<jsp:include page="admin/common/common.jsp" />
</head>
<body>
	<jsp:include page="../competition/common/top.jsp" />

	<div class="container">
		<div class="row" style="margin-top: 30px;">
			<div class="col-md-4 col-xs-12 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">更改密码</div>
					</div>
					<div class="panel-body">
						<form id="update-password-form">
							<div class="form-group">
								<label for="form-update-old-passwsord">旧密码：</label> <input class="form-control"
									type="password" name="oldPwd" id="form-update-old-password" />
							</div>
							<div class="form-group">
								<label for="form-update-new-password">新密码：</label><input class="form-control"
									type="password" name="newPwd" id="form-update-new-password" />
							</div>
							<div class="form-group">
								<label for="form-update-confirm-password">确认密码：</label><input class="form-control"
									type="password" id="form-update-confirm-password" />
							</div>
						</form>
						<div class="form-group" style="text-align: center;">
							<button class="btn btn-primary" id="update-btn">更改密码</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function checkForm() {
			var oldPwd = $("#form-update-old-password").val();
			var newPwd = $("#form-update-new-password").val();
			var confirmPwd = $("#form-update-confirm-password").val();

			if (isStringEmpty(oldPwd)) {
				showErrorToast("旧密码不能为空");
				return false;
			}

			if (isStringEmpty(newPwd)) {
				showErrorToast("新密码不能为空");
				return false;
			}

			if (isStringEmpty(confirmPwd)) {
				showErrorToast("确认密码不能为空");
				return false;
			}

			if (newPwd != confirmPwd) {
				showErrorToast("新密码和确认密码不相等");
				return false;
			}

			return true;
		}

		$(function() {
			$("#update-btn").bind("click", function() {
				$("#update-btn").attr("disabled", "disabled");
				$.post("doPasswordUpdate", $("#update-password-form").serialize(), function(data, textStatus) {
					if (textStatus == 'success') {
						if (data != null) {
							if (data.code == 2) {
								showErrorToast("您当前没有更改密码的权限");
							} else if (data.code == 26) {
								showSuccessToast("成功更改密码");
							} else if (data.code == 27) {
								showErrorToast("旧密码有误，请稍后再试");
							} else if (data.code == 20) {
								showErrorToast("当前用户编号无效，请稍后再试");
							}
						} else {
							showErrorToast("您当前没有更改密码的权限");
						}
					} else {
						showErrorToast("与服务器的通讯失败，请稍后再试");
					}

					$("#update-btn").removeAttr("disabled");
				});
			});
		});
	</script>
</body>
</html>