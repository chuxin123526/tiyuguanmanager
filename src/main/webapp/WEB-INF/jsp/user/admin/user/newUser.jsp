<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../../../competition/common/common.jsp" />
<jsp:include page="../common/common.jsp" />
</head>
<body>
	<jsp:include page="../../../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="../common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9" style="margin-top: 10px;">
			<div class="row">
				<h4>创建新用户</h4>
			</div>
			<div class="row form-group">
				<form id="create-form" onsubmit="return false;">
					<!-- 用户名 -->
					<div class="row form-group">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-username">用户名：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" id="create-form-username" name="create.username" type="text" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-username"></span>
						</div>
					</div>
					<!-- 密码 -->
					<div class="row form-group">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-password">密码：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" id="create-form-password" name="create.password" type="password" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-password"></span>
						</div>
					</div>
					<!-- 确认密码 -->
					<div class="row form-group">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-confirm-password">确认密码：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" id="create-form-confirm-password" name="create.confirmpassword"
								type="password" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-confirm-password"></span>
						</div>
					</div>
					<!-- 性别 -->
					<div class="row form-group">
						<div class="col-md-2 form-label">
							<label class="form-label">性别：</label>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" name="create.gender" id="create-form-gender-male" value="1"
								checked="checked" />&nbsp;男
							</span>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" name="create.gender" id="create-form-gender-female"
								value="0" />&nbsp;女
							</span>
						</div>
					</div>
					<!-- 角色 -->
					<div class="row form-group">
						<div class="col-md-2 form-label">
							<label class="form-label">用户所属角色：</label>
						</div>
						<div class="col-md-3">
							<select class="form-control" name="create.roleId">
								<s:iterator value="#request.roleList" var="role">
									<option value='<s:property value="#role.roleId"/>'>
										<s:property value="#role.name" />
									</option>
								</s:iterator>
							</select>
						</div>
					</div>
					<!-- 选择账号类型 -->
					<div class="row form-group">
						<div class="col-md-2 form-label">
							<label class="form-label">账号类型：</label>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" class="account-type" name="create.accountType" value="0"
								checked="checked" />&nbsp;学生账号
							</span>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" class="account-type" name="create.accountType" value="2" />&nbsp;老师账号
							</span>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" class="account-type" name="create.accountType" value="1" />&nbsp;工作人员账号
							</span>
						</div>
					</div>
					<!-- 证件类型 -->
					<div class="row form-group" id="form-group-id-type">
						<div class="col-md-2 form-label">
							<label class="form-label">证件类型：</label>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" name="create.identifierType" id="create-form-id-citizen"
								value="1" checked="checked" />&nbsp;身份证
							</span>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" name="create.identifierType" id="create-form-id-passpoer"
								value="2" />&nbsp;护照
							</span>
						</div>
						<div class="col-md-2 form-radio">
							<span> <input type="radio" name="create.identifierType" id="create-form-id-army"
								value="3" />&nbsp;军人证
							</span>
						</div>
					</div>
					<!-- 证件号码 -->
					<div class="row form-group" id="form-group-id-number">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-id-number">证件号码：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="create.identifierNumber"
								id="create-form-id-number" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-id-number"></span>
						</div>
					</div>
					<!-- 真实姓名 -->
					<div class="row form-group" id="form-group-realname">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-realname">姓名：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="create.realname" id="create-form-realname" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-stu-realname"></span>
						</div>
					</div>
					<!-- 学号 -->
					<div class="row form-group" id="form-group-stu-number">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-student-number">学号：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="create.studentNumber"
								id="create-form-student-number" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-stu-number"></span>
						</div>
					</div>
					<!-- 手机号码 -->
					<div class="row form-group" id="form-group-mobile">
						<div class="col-md-2 form-label">
							<label class="form-label" for="create-form-student-number">手机号：</label>
						</div>
						<div class="col-md-3">
							<input class="form-control" type="text" name="create.mobilePhone"
								id="create-form-mobile-number" />
						</div>
						<div class="col-md-3 error-msg">
							<span class="error-msg" id="error-mobile"></span>
						</div>
					</div>
					<!-- 注册按钮 -->
					<div class="row form-group" style="text-align: right;">
						<button class="btn btn-primary" id="form-create-submit-button">添加</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript">
		var idNumberWeight = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var checkBit = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');

		$("#nav-new-user").addClass("active");

		// 身份证号码校验
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

		// 表单校验函数
		function checkForm() {
			var pass = true;

			// 用户名校验
			var username = trim($("#create-form-username").val());
			if (isStringEmpty(username)) {
				$("#create-form-username").parent().addClass("has-error");
				$("#error-username").html("用户名为必填项");

				pass = false;
			} else {
				if (username.length > 16 || username.length < 6) {
					$("#create-form-username").parent().addClass("has-error");
					$("#error-username").html("用户名长度必须为 6~16 ");

					pass = false;
				}
			}

			// 密码校验
			var password = $("#create-form-password").val();
			if (isStringEmpty(password)) {
				$("#create-form-password").parent().addClass("has-error");
				$("#error-password").html("密码为必填项");

				pass = false;
			} else {
				if (password.length > 16 || password.length < 6) {
					$("#create-form-password").parent().addClass("has-error");
					$("#error-password").html("确认密码必须为 6~16 ");

					pass = false;
				}
			}

			// 确认密码校验
			var confirmPassword = $("#create-form-confirm-password").val();
			if (isStringEmpty(confirmPassword)) {
				$("#create-form-confirm-password").parent().addClass("has-error");
				$("#error-confirm-password").html("密码为必填项");

				pass = false;
			} else {
				if (confirmPassword.length > 16 || confirmPassword.length < 6) {
					$("#create-form-confirm-password").parent().addClass("has-error");
					$("#error-confirm-password").html("确认密码必须为 6~16 ");

					pass = false;
				} else {
					if (password != confirmPassword) {
						$("#create-form-confirm-password").parent().addClass("has-error");
						$("#error-confirm-password").html("两次输入的密码不一致");

						pass = false;
					}
				}
			}

			var accountType = $('input[name="create.accountType"]:checked').val();
			if (accountType == 0 || accountType == 2) {
				// 学生账号
				// 1. 如果选择身份证的话就验证身份号码是否有效
				var idType = $("input[name='create.identifierType']:checked").val();
				if (idType == 1) {
					var idNumber = trim($("#create-form-id-number").val());
					if (!checkIdNumber(idNumber)) {
						$("#create-form-id-number").parent().addClass("has-error");
						$("#error-id-number").html("身份证号码有误");

						pass = false;
					}
				}

				// 2. 如果是学生账号还需要校验学号
				if (accountType == 0) {
					var stuNumber = trim($("#create-form-student-number").val());
					if (stuNumber.length != 12) {
						$("#create-form-student-number").parent().addClass("has-error");
						$("#error-stu-number").html("学号长度有误");

						pass = false;
					}
				}
			}

			// 校验姓名
			var name = $("#create-form-realname").val();
			if (isStringEmpty(name)) {
				$("#create-form-realname").parent().addClass("has-error");
				$("#error-stu-realname").html("姓名不能为空！");

				pass = false;
			}

			// 校验手机号码
			var phone = trim($("#create-form-mobile-number").val());
			if (isStringEmpty(phone)) {
				$("#create-form-mobile-number").parent().addClass("has-error");
				$("#error-mobile").html("手机号码不能为空");

				pass = false;
			} else {
				if (phone.length != 11) {
					$("#create-form-mobile-number").parent().addClass("has-error");
					$("#error-mobile").html("手机号码长度为空");

					pass = false;
				}
			}

			return pass;
		}

		function clearError(element) {
			var div = element.parent();

			if (div.hasClass("has-error")) {
				div.removeClass("has-error");
			}
			div.parent().find("span.error-msg").html("");
		}

		function clearForm() {
			$("input[type='text']").val("");
			$("input[type='password']").val("");
		}

		$("input.form-control").bind("input propertychange", function() {
			clearError($(this));
		});

		$('input[type="radio"].account-type').bind("click", function() {
			var d = $('input[name="create.accountType"]:checked').val();
			if (d == 0) {
				// 显示证件类型字段
				$("div#form-group-id-type").css("display", "block");

				// 显示证件号码字段
				$("div#form-group-id-number").css("display", "block");

				// 显示学号字段
				$("div#form-group-stu-number").css("display", "block");
			} else if (d == 1) {
				// 隐藏证件类型字段
				$("div#form-group-id-type").css("display", "none");

				// 隐藏证件号码字段
				$("div#form-group-id-number").css("display", "none");

				// 隐藏学号字段
				$("div#form-group-stu-number").css("display", "none");
			} else {
				// 显示证件类型字段
				$("div#form-group-id-type").css("display", "block");

				// 显示证件号码字段
				$("div#form-group-id-number").css("display", "block");

				// 显示学号字段
				$("div#form-group-stu-number").css("display", "none");
			}
		});

		$("#form-create-submit-button").bind("click", function() {
			if (checkForm()) {
				$("#form-create-submit-button").attr("disabled", "disabled");

				$.post("newUser.action", $("#create-form").serialize(), function(data, textStatus) {
					if (textStatus == 'success') {
						if (data == null) {
							showErrorToast("您没有创建用户的权限");
						} else if (data.code == 19) {
							// 添加成功
							showSuccessToast("成功添加用户");
							clearForm();
						} else if (data.code == 8) {
							// 表单有误
						} else if (data.code == 9) {
							// 存在同名用户
							$("#create-form-username").parent().addClass("has-error");
							$("#create-form-username").focus();
							$("#error-username").html("该用户名已经被使用");
						} else if (data.code == 15) {
							// 角色无效
							showErrorToast("当前选择的角色无效，请确认后重试");
						} else if (data.code == 2) {
							showErrorToast("您没有创建用户的权限");
						}
					} else {
						showErrorToast("与服务器通讯出错，请稍后再试");
					}

					$("#form-create-submit-button").removeAttr("disabled");
				});
			}
		});
	</script>
</body>
</html>