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
			<!-- 顶部标题部分 -->
			<div class="row">
				<div class="col-md-12">
					<h3>
						<s:property value="#request.tipWord" />
					</h3>
				</div>
			</div>
			<!-- 用于显示角色基本信息 -->
			<%-- 如果当前是变更角色信息，则会显示角色的编号，该编号在添加的过程由后台生成，不可编辑 --%>
			<form id="role-form" onsubmit="return false;">
				<s:if test="#request.isUpdateRole">
					<div class="row form-group">
						<div class="col-md-2" style="text-align: right;">
							<label class="form-label" for="role-form-id">角色编号：</label>
						</div>
						<div class="col-md-10">
							<input class="form-control" id="role-form-id" type="text" name="form.id"
								value="#request.role.id" readonly="readonly" />
						</div>
					</div>
				</s:if>
				<%-- 无论是创建角色还是变更角色，角色名称都是可以编辑的 --%>
				<div class="row form-group">
					<div class="form-label col-md-2">
						<label class="form-label">角色名称：</label>
					</div>
					<div class="col-md-10">
						<input class="form-control" id="role-form-name" type="text" name="form.name"
							value="<s:property value="#request.role.name"/>" />
					</div>
				</div>
				<!-- 权限区域 -->
				<div class="row form-group">
					<div class="form-label col-md-2">
						<label class="form-label">角色权限：</label>
					</div>
					<div class="col-md-10">
						<div class="row">
							<s:iterator value="#request.allPermissions" var="permission">
								<div class="col-md-3">
									<span> <input name="form.permissions" type="checkbox"
										id='role-permission-<s:property value="#permission.type"/>'
										value="<s:property value="#permission.type"/>" /> &nbsp;<s:property
											value="#permission.name" />
									</span>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<%-- 根据不同的操作显示不同的按钮 --%>
				<div class="form-group" style="text-align: right;">
					<s:if test="#request.isUpdateRole">
						<button class="btn btn-primary" id="form-btn-save">保存</button>
					</s:if>
					<s:else>
						<button class="btn btn-primary" id="form-btn-create">创建</button>
					</s:else>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
	<%-- 如果是变更角色信息，在这回显现有的权限信息，绑定按钮 --%>
	<s:if test="#request.isUpdateRole">
		<script type="text/javascript">
			<s:iterator value="#request.role.permissions" var="permission">
			$('#role-permission-<s:property value="#permission.type"/>').attr("checked", "checked");
			</s:iterator>
		</script>
	</s:if>
	<s:else>
		<script type="text/javascript">
			$("#form-btn-create").bind("click", function() {
				$("#form-btn-create").attr("disabled", "disabled");
				
				var data = $("#role-form").serialize();
				$.post("insertRole", data, function(data, textStatus) {
					if (textStatus == 'success') {
						
					} else {
						
					}
				});
			});
		</script>
	</s:else>
</body>
</html>