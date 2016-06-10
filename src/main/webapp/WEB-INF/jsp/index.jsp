<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<title>体育馆管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common-word.css">
</head>
<body>
	<jsp:include page="competition/common/common.jsp" />
	<jsp:include page="competition/common/top.jsp" />

	<div class="container">
		<div class="row home-welcome-line">
			<div class="col-md-12 home-welcome">
				<span>${sessionScope.user.username}，欢迎您！</span>
			</div>
		</div>
		<div class="row">
			<!-- 功能区 -->
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>器材</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 体育馆器材的查询，租借预约等 </span>
							</p>
							<p class="btn-p">
								<a class="btn btn-primary" href="#"> <span>进入</span><span
									class="glyphicon glyphicon-triangle-right"></span>
								</a>
							</p>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>赛事</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 赛事的查询，预约，申请等 </span>
							</p>
							<p class="btn-p">
								<a class="btn btn-primary" href="#"> <span>进入</span><span
									class="glyphicon glyphicon-triangle-right"></span>
								</a>
							</p>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>场地</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 场地的查询，预约，租借等 </span>
							</p>
							<p class="btn-p">
								<a class="btn btn-primary" href="#"> <span>进入</span><span
									class="glyphicon glyphicon-triangle-right"></span>
								</a>
							</p>
						</div>
					</div>
					<s:if test="#session.announcementAdminAccess">
						<div class="col-md-6 col-sm-6 col-xs-12 function-box">
							<div class="function-box-body well">
								<h2>公告管理</h2>
								<p>
									<img src="image/icon/instrument.png" /> <span> 这里是说明文字 </span>
								</p>
								<p class="btn-p">
									<a class="btn btn-primary" id="btn-announcement-module"
										href="${pageContext.request.contextPath}/announcement/adminIndex"> <span>进入</span><span
										class="glyphicon glyphicon-triangle-right"></span>
									</a>
								</p>
							</div>
						</div>
					</s:if>
					<s:if test="#session.userAdminAccess">
						<div class="col-md-6 col-sm-6 col-xs-12 function-box">
							<div class="function-box-body well">
								<h2>用户管理</h2>
								<p>
									<img src="image/icon/instrument.png" /> <span> 角色和用户的添加、删除和修改等 </span>
								</p>
								<p class="btn-p">
									<a class="btn btn-primary" id="btn-user-module"
										href="${pageContext.request.contextPath}/user/adminIndex"> <span>进入</span><span
										class="glyphicon glyphicon-triangle-right"></span>
									</a>
								</p>
							</div>
						</div>
					</s:if>
				</div>
			</div>
			<!-- 公告区 -->
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">最新公告</h3>
					</div>
					<div class="panel-body">
						<div class="list-group">
							<s:if test="#request.announcementList.size != 0">
								<s:iterator value="#request.announcementList" var="announcement">
									<a class="list-group-item" class="announcement-link" target="_blank"
										href="announcement/announcementPage?id=<s:property value="#announcement.announcementId"/>"><s:property
											value="#announcement.announcementTitle" /></a>
								</s:iterator>
								<p style="text-align: right; margin-top: 10px;">
									<span><a target="_blank"
										href="${pageContext.request.contextPath}/announcement/allAnnouncement">更多公告</a></span>
								</p>
							</s:if>
							<s:else>
								<span>暂时没有公告</span>
							</s:else>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>