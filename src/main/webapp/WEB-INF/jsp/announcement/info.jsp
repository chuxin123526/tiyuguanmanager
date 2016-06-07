<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../competition/common/common.jsp" />
</head>
<body>
	<jsp:include page="../competition/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="col-md-3">
			<jsp:include page="common/navigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9">
			<div class="row" style="padding: 10px">
				<div class="col-md-12">
					<s:if test="#request.function == 1">
						<div class="alert alert-success">
							<p>成功发布公告</p>
						</div>
					</s:if>
					<s:if test="#request.function == 2">
						<div class="alert alert-success">
							<p>成功保存草稿</p>
						</div>
					</s:if>
					<s:if test="#request.function == 3">
						<div class="alert alert-success">
							<p>成功修改公告</p>
						</div>
					</s:if>
					<s:if test="#request.function == 4">
						<div class="alert alert-success">
							<p>成功保存草稿</p>
						</div>
					</s:if>
					<s:if test="#request.function == 5">
						<div class="alert alert-danger">
							<p>
								无法找到编号为<span><s:property value="#request.announcement.announcementId" /></span>的公告或草稿，请确认后再重试！
							</p>
						</div>
					</s:if>
					<s:if test="#request.function == 6">
						<div class="alert alert-success">
							<p>成功发布公告</p>
						</div>
					</s:if>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../competition/common/pagefoot.jsp"></jsp:include>
</body>
</html>