<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<jsp:include page="../competition/common/common.jsp" />
<jsp:include page="admin/common/common.jsp" />
</head>
<body>
	<jsp:include page="../competition/common/top.jsp" />

	<s:if test="#request.user != null">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2" style="margin-top: 30px;">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">用户信息</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-2" style="text-align: right;">
									<label>用户编号：</label>
								</div>
								<div class="col-md-10">
									<p>
										<s:property value="#request.user.userId" />
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2" style="text-align: right;">
									<label>用户名：</label>
								</div>
								<div class="col-md-10">
									<p>
										<s:property value="#request.user.username" />
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2" style="text-align: right;">
									<label>姓名：</label>
								</div>
								<div class="col-md-10">
									<p>
										<s:property value="#request.user.realname" />
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2" style="text-align: right;">
									<label>所属角色：</label>
								</div>
								<div class="col-md-10">
									<p>
										<s:property value="#request.user.role.name" />
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2" style="text-align: right;">
									<label>当前状态：</label>
								</div>
								<div class="col-md-10">
									<s:if test="#request.user.status == 0">
										<p>正常状态</p>
									</s:if>
									<s:else>
										<p style="color: red;">禁用状态</p>
									</s:else>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2" style="text-align: right;">
									<label>账号类型：</label>
								</div>
								<div class="col-md-10">
									<s:if test="#request.user.type == 0">
										<p>学生账号</p>
									</s:if>
									<s:elseif test="#reuqest.user.type == 1">
										<p>工作人员账号</p>
									</s:elseif>
									<s:elseif test="#request.user.type == 2">
										<p>教师账号</p>
									</s:elseif>
								</div>
							</div>
							<s:if test="#request.user.type == 0 || #request.user.type == 2">
								<div class="row">
									<div class="col-md-2" style="text-align: right;">
										<label>证件类型：</label>
									</div>
									<div class="col-md-10">
										<s:if test="#request.user.identifierType == 1">
											<p>身份证</p>
										</s:if>
										<s:elseif test="#request.user.identifierType == 2">
											<p>护照</p>
										</s:elseif>
										<s:elseif test="#request.user.identifierType == 3">
											<p>军人证</p>
										</s:elseif>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2" style="text-align: right;">
										<label>证件号码：</label>
									</div>
									<div class="col-md-10">
										<p>
											<s:property value="#request.user.identifierNumber" />
										</p>
									</div>
								</div>
								<s:if test="#request.user.type == 0">
									<div class="row">
										<div class="col-md-2" style="text-align: right;">
											<label>学号：</label>
										</div>
										<div class="col-md-10">
											<p>
												<s:property value="#request.user.studentNumber" />
											</p>
										</div>
									</div>
								</s:if>
							</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:if>
</body>
</html>