<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
label.form-label,div.form-radio,div.error-msg {
	line-height: 34px;
	vertical-align: middle;
}

span.error-msg {
	color: #ff0000;
}

@media ( min-width : 992px) {
	div.form-label {
		text-align: right;
	}
}
</style>
<jsp:include page="../competition/common/common.jsp"></jsp:include>
<jsp:include page="common/jslib.jsp"></jsp:include>
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index">体育馆管理系统</a>
			</div>
		</div>
	</nav>
	<!-- 页面内容的容器 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- 说明 -->
				<div class="row">
					<div class="col-md-10 col-md-offset-1 panel panel-default">
						<div class="panel-body">这里是注册须知</div>
					</div>
				</div>
				<!-- 注册表单 -->
				<form action="register" id="register-form" onsubmit="return false;">
					<!-- 用户名 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-username">用户名：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" id="register-form-username" name="register.username" type="text" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-username"></span>
						</div>
					</div>
					<!-- 密码 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-password">密码：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" id="register-form-password" name="register.password"
								type="password" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-password"></span>
						</div>
					</div>
					<!-- 确认密码 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-confirm-password">确认密码：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" id="register-form-confirm-password"
								name="register.confirmpassword" type="password" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-confirm-password"></span>
						</div>
					</div>
					<!-- 性别 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label">性别：</label>
						</div>
						<div class="col-md-1 form-radio">
							<span> <input type="radio" name="register.gender" id="register-form-gender-male"
								value="1" checked="checked" />&nbsp;男
							</span>
						</div>
						<div class="col-md-1 form-radio">
							<span> <input type="radio" name="register.gender" id="register-form-gender-female"
								value="0" />&nbsp;女
							</span>
						</div>
					</div>
					<!-- 证件类型 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label">证件类型：</label>
						</div>
						<div class="col-md-1 form-radio">
							<span> <input type="radio" name="register.identifierType"
								id="register-form-id-citizen" value="1" checked="checked" />&nbsp;身份证
							</span>
						</div>
						<div class="col-md-1 form-radio">
							<span> <input type="radio" name="register.identifierType"
								id="register-form-id-passpoer" value="2" />&nbsp;护照
							</span>
						</div>
						<div class="col-md-1 form-radio">
							<span> <input type="radio" name="register.identifierType" id="register-form-id-army"
								value="3" />&nbsp;军人证
							</span>
						</div>
					</div>
					<!-- 证件号码 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-id-number">证件号码：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="register.identifierNumber"
								id="register-form-id-number" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-id-number"></span>
						</div>
					</div>
					<!-- 真实姓名 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-realname">姓名：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="register.realname" id="register-form-realname" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-stu-realname"></span>
						</div>
					</div>
					<!-- 学号 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-student-number">学号：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="register.studentNumber"
								id="register-form-student-number" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-stu-number"></span>
						</div>
					</div>
					<!-- 手机号码 -->
					<div class="row form-group">
						<div class="col-md-2 col-md-offset-3 form-label">
							<label class="form-label" for="register-form-student-number">手机号：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="register.mobilePhone"
								id="register-form-mobile-number" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-mobile"></span>
						</div>
					</div>
					<!-- 注册按钮 -->
					<div class="row form-group" style="text-align: center;">
						<button class="btn btn-primary" id="form-register-submit-button">注册</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="success-dialog" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">注册成功</h4>
				</div>
				<div class="modal-body">
					恭喜您，<span id="success-box-username"></span>！您已成功注册账号，请点击确定跳转到首页登录！
				</div>
				<div class="modal-footer">
					<button type="button" id="success-box-ok-button" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var idNumberWeight = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var checkBit = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');

		function checkIdNumber(idNumber) {
			var totalWeight = 0;

			// 长度不对
			if (idNumber.length != 18) {
				return false;
			}

			// 计算权值和
			for (var i = 0; i < 17; i++) {
				var c = idNumber.charAt(i);
				if (c >= '0' && c <= '9') {
					totalWeight = totalWeight + (parseInt(c) * idNumberWeight[i]);
				} else {
					return false;
				}
			}

			// 得到校验位
			var checkBitIndex = totalWeight % 11;
			return (checkBit[checkBitIndex] == idNumber.toUpperCase().charAt(17));
		}

		function clearError(element) {
			var div = element.parent().parent();
			if (div.hasClass("has-error")) {
				div.removeClass("has-error");
			}

			div.find("span.error-msg").html("");
		}

		$(function() {
			$("input.form-control").bind("input propertychange", function() {
				clearError($(this));
			});

			jQuery.validator.addMethod("idNumber", function(value, element) {
				if ($('input:radio[name="register.identifierType"]:checked').val() == '1') {
					return checkIdNumber(value);
				} else {
					return true;
				}
			}, "身份证号码有误！");

			$("#register-form").validate({
				rules : {
					"register.username" : {
						required : true,
						minlength : 6,
						maxlength : 16
					},
					"register.password" : {
						required : true,
						minlength : 6,
						maxlength : 16
					},
					"register.confirmpassword" : {
						required : true,
						minlength : 6,
						maxlength : 16,
						equalTo : "#register-form-password"
					},
					"register.identifierNumber" : {
						required : true,
						idNumber : true
					},
					"register.realname" : {
						required : true
					},
					"register.studentNumber" : {
						required : true,
						minlength : 12,
						maxlength : 12
					},
					"register.mobilePhone" : {
						required : true,
						minlength : 11,
						maxlength : 11
					}
				},
				messages : {
					"register.username" : {
						required : "用户名是必填字段",
						minlength : "用户名的长度必须为6-16个字符",
						maxlength : "用户名的长度必须为6-16个字符"
					},
					"register.password" : {
						required : "密码是必填字段",
						minlength : "密码的长度必须为6-16个字符",
						maxlength : "密码的长度必须为6-16个字符"
					},
					"register.confirmpassword" : {
						required : "密码是必填字段",
						minlength : "密码的长度必须为6-16个字符",
						maxlength : "密码的长度必须为6-16个字符",
						equalTo : "两次密码不一致"
					},
					"register.identifierNumber" : {
						required : "身份证号码是必填项",
						idNumber : "输入的身份证号码有误"
					},
					"register.realname" : {
						required : "姓名为必填字段"
					},
					"register.studentNumber" : {
						required : "学号为必填字段",
						minlength : "学号长度有误",
						maxlength : "学号长度有误"
					},
					"register.mobilePhone" : {
						required : "手机号为必填字段",
						minlength : "手机号码长度有误",
						maxlength : "手机号码长度有误"
					}
				},
				errorPlacement : function(error, element) {
					var div = element.parent().parent();
					if (!div.hasClass("has-error")) {
						div.addClass("has-error");
					}

					div.find("span.error-msg").html(error.html());
				},
				submitHandler : function(form) {
					return false;
				}
			});

			$("#form-register-submit-button").bind("click", function() {
				$("#form-register-submit-button").attr("disabled", "disabled");

				var formData = $("#register-form").serialize();
				console.log(formData);

				$.post("register.action", formData, function(data, textStatus) {
					if (textStatus == 'success') {
						var response = data;
						if (response.code == 9) {
							var span = $("span#error-username");
							span.html("该用户名已经被使用！");

							span.parent().parent().addClass("has-error");
						} else if (response.code == 10) {
							$("span#success-box-username").html($("#register-form-username").val());
							$("#success-dialog").modal("show");
						}
					} else {
						showErrorToast("与服务器通讯失败，请稍后再试！");
					}

					$("#form-register-submit-button").removeAttr("disabled");
				});
			});

			$("#success-box-ok-button").bind("click", function() {
				location.href = "${pageContext.request.contextPath}/user/loginPage";
			});
		});
	</script>
</body>
</html>
