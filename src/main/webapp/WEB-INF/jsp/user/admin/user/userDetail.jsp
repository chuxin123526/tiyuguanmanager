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

		<div class="col-md-9">
			<s:if test="#request.user != null">
				<div class="row">
					<h3>
						<s:property value="#request.tipWord" />
					</h3>
				</div>
				<div class="row form-group">
					<form id="create-form" onsubmit="return false;">
						<!-- 用户编号 -->
						<div class="row form-group">
							<div class="col-md-2 form-label">
								<label class="form-label" for="create-form-username">用户编号：</label>
							</div>
							<div class="col-md-3">
								<input class="form-control" id="create-form-user-id" name="create.userId" type="text"
									readonly="readonly" />
							</div>
						</div>
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
						<s:if test="#request.function != 2">
							<div class="row form-group">
								<div class="col-md-2 form-label">
									<input type="checkbox" id="create-form-change-password" /> <label class="form-label"
										for="create-form-password">变更用户密码：</label>
								</div>
								<div class="col-md-3">
									<input class="form-control" disabled="disabled" id="create-form-password"
										name="create.password" type="password" />
								</div>
								<div class="col-md-3 error-msg">
									<span class="error-msg" id="error-password"></span>
								</div>
							</div>
						</s:if>
						<!-- 性别 -->
						<div class="row form-group">
							<div class="col-md-2 form-label">
								<label class="form-label">性别：</label>
							</div>
							<%-- 如果是用户信息认证页面，需要显示字符串，而不是单选框 --%>
							<s:if test="#request.function != 2">
								<div class="col-md-2 form-radio">
									<span> <input type="radio" name="create.gender" id="create-form-gender-male"
										value="1" checked="checked" />&nbsp;男
									</span>
								</div>
								<div class="col-md-2 form-radio">
									<span> <input type="radio" name="create.gender" id="create-form-gender-female"
										value="0" />&nbsp;女
									</span>
								</div>
							</s:if>
							<s:else>
								<div class="col-md-3">
									<input class="form-control" type="text" readonly="readonly" id="create-form-gender-string" />
								</div>
							</s:else>
						</div>
						<!-- 角色 -->
						<div class="row form-group">
							<div class="col-md-2 form-label">
								<label class="form-label">用户所属角色：</label>
							</div>
							<div class="col-md-3">
								<s:if test="#request.function != 2">
									<select class="form-control" id="create-form-role-select" name="create.roleId">
										<s:iterator value="#request.roleList" var="role">
											<option value='<s:property value="#role.roleId"/>'>
												<s:property value="#role.name" />
											</option>
										</s:iterator>
									</select>
								</s:if>
								<s:else>
									<input type="text" class="form-control" id="create-form-role-username" readonly="readonly" />
								</s:else>
							</div>
						</div>
						<!-- 选择账号类型 -->
						<div class="row form-group">
							<div class="col-md-2 form-label">
								<label class="form-label">账号类型：</label>
							</div>
							<%-- 如果是用户信息认证页面，需要显示字符串，而不是单选框 --%>
							<s:if test="#request.function != 2">
								<div class="col-md-2 form-radio">
									<span> <input id="create-form-type-student" type="radio" class="account-type"
										name="create.accountType" value="0" checked="checked" />&nbsp;学生账号
									</span>
								</div>
								<div class="col-md-2 form-radio">
									<span> <input id="create-form-type-teacher" type="radio" class="account-type"
										name="create.accountType" value="2" />&nbsp;老师账号
									</span>
								</div>
								<div class="col-md-2 form-radio">
									<span> <input id="create-form-type-employee" type="radio" class="account-type"
										name="create.accountType" value="1" />&nbsp;工作人员账号
									</span>
								</div>
							</s:if>
							<s:else>
								<div class="col-md-3">
									<input type="text" class="form-control" id="create-form-account-type-string"
										readonly="readonly" />
								</div>
							</s:else>
						</div>
						<!-- 证件类型 -->
						<div class="row form-group" id="form-group-id-type">
							<div class="col-md-2 form-label">
								<label class="form-label">证件类型：</label>
							</div>
							<%-- 如果是用户信息认证页面，需要显示字符串，而不是单选框 --%>
							<s:if test="#request.function != 2">
								<div class="col-md-2 form-radio">
									<span> <input type="radio" class="create-form-id-type" name="create.identifierType"
										id="create-form-id-citizen" value="1" checked="checked" />&nbsp;身份证
									</span>
								</div>
								<div class="col-md-2 form-radio">
									<span> <input type="radio" class="create-form-id-type" name="create.identifierType"
										id="create-form-id-passpoer" value="2" />&nbsp;护照
									</span>
								</div>
								<div class="col-md-2 form-radio">
									<span> <input type="radio" class="create-form-id-type" name="create.identifierType"
										id="create-form-id-army" value="3" />&nbsp;军人证
									</span>
								</div>
							</s:if>
							<s:else>
								<div class="col-md-3">
									<input type="text" class="form-control" id="create-form-id-type-string" readonly="readonly" />
								</div>
							</s:else>
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
						<!-- 底部按钮 -->
						<div class="row form-group" style="text-align: right;">
							<s:if test="#request.function == 1">
								<div id="verify-button-flow">
									<s:if test="#request.user.role.name != '注册用户'">
										<button id="btn-form-verify-cancel" class="btn btn-danger">撤销用户认证</button>
									</s:if>
									<button id="btn-form-update" class="btn btn-primary">保存变更</button>
								</div>
							</s:if>
							<s:if test="#request.function == 2">
								<div id="verify-button-flow">
									<button id="btn-form-back" class="btn btn-default">返回</button>
									<button id="btn-form-pass-verify" class="btn btn-primary">认证通过</button>
								</div>
							</s:if>
							<div class="alert alert-success" id="verify-success-box" style="display: none;"></div>
						</div>
					</form>
				</div>
			</s:if>
			<s:else>
				<div class="row">
					<div class="alert alert-danger">
						<p>
							没有找到编号为
							<s:property value="#request.userId" />
							的用户，请确认后再重试！
						</p>
					</div>
				</div>
			</s:else>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript">
		var idNumberWeight = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var checkBit = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');

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
			if ($("input#create-form-change-password").is(":checked")) {
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

		$("input#create-form-change-password").bind("click", function() {
			var box = $("input#create-form-change-password");
			if (box.is(":checked")) {
				$("input#create-form-password").removeAttr("disabled");
			} else {
				$("input#create-form-password").attr("disabled", "disabled");
			}
		});

		function refreshForm() {
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
		}

		$('input[type="radio"].account-type').bind("click", refreshForm);
	</script>

	<%-- 用户信息回显 --%>
	<s:if test="#request.user != null">
		<script type="text/javascript">
			$("#create-form-user-id").val("<s:property value="#request.user.userId"/>");
			$("#create-form-username").val("<s:property value="#request.user.username"/>");
			<s:if test="#request.user.gender == 0">
			$("#create-form-gender-female").attr("checked", "checked");
			</s:if>
			<s:else>
			$("#create-form-gender-male").attr("checked", "checked");
			</s:else>
			
			<%-- 如果是变更用户信息，就提供一个可以选择的下拉选框，否则就仅仅显示名称就好 --%>
			<s:if test="#request.function != 2">
			var roleId = trim("<s:property value="#request.user.role.roleId"/>");
			$("#create-form-role-select option[value='" + roleId + "']").attr("selected", "selected");
			</s:if>
			<s:else>
			var roleName = trim("<s:property value="#request.user.role.name"/>");
			$("#create-form-role-username").val(roleName);
			</s:else>

			var accountType = trim("<s:property value="#request.user.type"/>");
			$("input.account-type[value='" + accountType + "']").attr("checked", "checked");
			refreshForm();
			
			$("#create-form-realname").val(trim("<s:property value="#request.user.realname"/>"));
			
			var phone = trim("<s:property value="#request.user.contracts.{?#this.type==1}[0].content"/>");
			$("#create-form-mobile-number").val(phone);

			// 如果是学生和老师账号，回显信息
			<s:if test="#request.user.type == 0 || #request.user.type == 2">
			var idType = trim("<s:property value="#request.user.identifierType"/>");
			$("input.create-form-id-type[value='" + idType + "']").attr("checked", "checked");
			
			$("input#create-form-id-number").val(trim("<s:property value="#request.user.identifierNumber"/>"));
			<s:if test="#request.user.type == 0">
				$("#create-form-student-number").val(trim("<s:property value="#request.user.studentNumber"/>"));
			</s:if>
			</s:if>
		</script>
	</s:if>

	<%-- 如果是用户信息认证，则全部的文本框均为只读状态，以及将单选框选项转换为字符串 --%>
	<s:if test="#request.function == 2">
		<script type="text/javascript">
			$("form#create-form input[type='text']").attr("readonly", "readonly");
			$("form#create-form input[type='text']").parent().removeClass("col-md-3").addClass("col-md-5");
			
			var genderValue = <s:property value="#request.user.gender "/>;
			if (genderValue == 0) {
				$("#create-form-gender-string").val("女");
			} else  {
				$("#create-form-gender-string").val("男");
			}
			
			var accountType = <s:property value="#request.user.type" />;
			if (accountType == 0) {
				$("#create-form-account-type-string").val("学生账号");
			} else if (accountType == 1) {
				$("#create-form-account-type-string").val("工作人员账号");
			} else if (accountType == 2) {
				$("#create-form-account-type-string").val("老师账号");
			}
			
			var idType = <s:property value="#request.user.identifierType"/>;
			if (idType == 1) {
				$("#create-form-id-type-string").val("身份证");
			} else if (idType == 2) {
				$("#create-form-id-type-string").val("护照");
			} else if (idType == 3) {
				$("#create-form-id-type-string").val("军人证");
			}
			
			$("button#btn-form-back").bind("click", function(){
				history.go(-1);
			});
			
			$("button#btn-form-pass-verify").bind("click", function(){
				var id = trim($("#create-form-user-id").val());
				
				$("div#verify-button-flow button").attr("disabled", "disabled");
				
				$.post("verifyUser", {"userId": id}, function(data, textStatus){
					if (textStatus == 'success') {
						if (data.code == 2) {
							showErrorToast("您没有进行用户认证的权限！");
						} else if (data.code == 24) {
							showSuccessToast("该用户已经成功通过信息认证！");
							
							var currentCount = parseInt(trim($("span#user-count").html()));
							currentCount = currentCount - 1;
							
							if (currentCount > 0) {
								$("span#user-count").html(currentCount);
							} else {
								$("span#user-count").html("");
							}
							
							$("div#verify-button-flow").css("display", "none");
							$("div#verify-success-box").removeClass().addClass("alert alert-success").html("该用户成功通过认证");
							$("div#verify-success-box").css("display", "block");
						} else if (data.code == 20) {
							showErrorToast("指定的用户编号无效，请重新在列表中选择要进行认证的用户重新操作");
						}
					} else {
						showErrorToast("与服务器通信异常，请稍后再试");
					}
					
					$("div#verify-button-flow button").removeAttr("disabled");
				});
			});
		</script>
	</s:if>

	<%-- 用户信息变更部分代码 --%>
	<s:if test="#request.function == 1">
		<script type="text/javascript">
			$("#btn-form-verify-cancel").bind("click", function() {
				var id = trim($("#create-form-user-id").val());
				
				$("div#verify-button-flow button").attr("disabled", "disabled");
				
				$.post("deverifyUser", {"userId": id}, function(data, textStatus){
					if (textStatus == 'success') {
						if (data.code == 2) {
							showErrorToast("您没有进行用户认证的权限！");
						} else if (data.code == 25) {
							showSuccessToast("该用户已经成功撤销信息认证！");
							$("div#verify-button-flow").css("display", "none");
							$("div#verify-success-box").removeClass().addClass("alert alert-success").html("该用户信息撤销认证成功");
							$("div#verify-success-box").css("display", "block");
						} else if (data.code == 20) {
							showErrorToast("指定的用户编号无效，请重新在列表中选择要进行认证信息撤销的用户重新操作");
						}
					} else {
						showErrorToast("与服务器通信异常，请稍后再试");
					}
					
					$("div#verify-button-flow button").removeAttr("disabled");
				});
			});
		
			$("#btn-form-update").bind("click", function(){
				var data = $("#create-form").serialize();
				
				$("div#verify-button-flow button").attr("disabled", "disabled");
				$.post("updateUser", data, function(data, textStatus) {
					if (textStatus == 'success') {
						if (data.code == 23) {
							// 成功更改信息
							showSuccessToast("该用户信息已经成功更改");
							$("div#verify-button-flow").css("display", "none");
							$("div#verify-success-box").removeClass().addClass("alert alert-success").html("该用户成功信息已经成功更改");
							$("div#verify-success-box").css("display", "block");
						} else if (data.code == 8) {
							// 表单有误
						} else if (data.code == 20) {
							// 表单用户编号有误
							showErrorToast("用户编号有误，请重新在列表中打开更改页面");
						} else if (data.code == 9) {
							// 新的用户名已经被使用
							$("#create-form-username").parent().addClass("has-error");
							$("#error-username").html("该用户名已经存在");
							$("#error-username").focus();
							
							showErrorToast("该用户名已经存在");
						} else if (data.code == 15) {
							// 指定的角色名称无效
							showErrorToast("角色信息有误，请重新在列表中打开更改页面");
						} else if (data.code == 2) {
							// 没有相应的权限
							showErrorToast("您没有变更用户信息的权限");
						} else {
							// 未知异常
							showErrorToast("遇到未知异常，请稍后再试");
						}
					} else {
						showErrorToast("与服务器通信异常，请稍后再试");
					}
					
					$("div#verify-button-flow button").removeAttr("disabled");
				});
			});
		</script>
	</s:if>
</body>
</html>