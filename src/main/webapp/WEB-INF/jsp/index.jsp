<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<title>尼玛的主页</title>
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
							<button class="btn btn-primary">
								<span>进入</span><span class="glyphicon glyphicon-triangle-right"></span>
							</button>
							<div class="clear"></div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>赛事</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 这里是说明文字 </span>
							</p>
							<button class="btn btn-primary">
								<span>进入</span><span class="glyphicon glyphicon-triangle-right"></span>
							</button>
							<div class="clear"></div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>场地</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 这里是说明文字 </span>
							</p>
							<button class="btn btn-primary">
								<span>进入</span><span class="glyphicon glyphicon-triangle-right"></span>
							</button>
							<div class="clear"></div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>公告管理</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 这里是说明文字 </span>
							</p>
							<button class="btn btn-primary" id="btn-announcement-module">
								<span>进入</span><span class="glyphicon glyphicon-triangle-right"></span>
							</button>
							<div class="clear"></div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 function-box">
						<div class="function-box-body well">
							<h2>用户管理</h2>
							<p>
								<img src="image/icon/instrument.png" /> <span> 这里是说明文字 </span>
							</p>
							<button class="btn btn-primary" id="btn-user-module">
								<span>进入</span><span class="glyphicon glyphicon-triangle-right"></span>
							</button>
							<div class="clear"></div>
						</div>
					</div>
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
									<a class="list-group-item"><s:property value="#announcement.announcementTitle" /></a>
								</s:iterator>
								<p style="text-align: right; margin-top: 10px;">
									<span>查看更多...</span>
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
	<script type="text/javascript">
		$(function() {
			$("#btn-user-module").bind("click", function() {
				location.href = "${pageContext.request.contextPath}/user/adminIndex";
			});

			$("#btn-announcement-module").bind("click", function() {
				location.href = "${pageContext.request.contextPath}/announcement/index";
			});
			// /announcement/index
		});
	</script>
</body>
</html>