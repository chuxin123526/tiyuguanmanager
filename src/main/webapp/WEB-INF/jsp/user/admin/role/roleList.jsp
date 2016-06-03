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
			<!-- 中间数据表格部分 -->
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
						<thread>
						<tr>
							<th style="3.5em">#</th>
							<th style="10em">名称</th>
							<th style="3.5em"></th>
							<th style="3.5em"></th>
						</tr>
						</thread>
						<s:iterator value="roleList" var="role">
							<tr>
								<td><s:property value="#role.id" /></td>
								<td><s:property value="#role.name" /></td>
								<td></td>
								<td></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
			<!-- 分页导航部分 -->
			<div class="row">
				<div class="col-md-12 pagination-row">
					<ul class="pagination">
						<s:if test="#request.maxPage != #request.minPage && #request.page != 1">
							<li><a id="nav-back-one" href="javascript:void(0);">«</a></li>
						</s:if>
						<s:iterator value="#request.allPages" var="page">
							<s:if test="#page == #request.page">
								<li class="active"><a href="javascript:void(0);"
									onclick='gotoPage(<s:property value="#page"/>);'><s:property value="#page" /></a></li>
							</s:if>
							<s:else>
								<li><a href="javascript:void(0);" onclick='gotoPage(<s:property value="#page"/>);'><s:property
											value="#page" /></a></li>
							</s:else>
						</s:iterator>
						<s:if test="#request.maxPage != #request.minPage && #request.page != #request.maxPage">
							<li><a id="nav-next-one" href="javascript:void(0);">»</a></li>
						</s:if>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
	<script type="text/javascript">
		function gotoPage(page) {
			location.href = "${pageContext.request.contextPath}/user/roleList?page=" + page;
		}
	
		$(function() {
			$("#nav-back-one").bind("click", function() {
				gotoPage(<s:property value="#request.page"/> - 1);
			});
			
			$("#nav-next-one").bind("click", function(){
				gotoPage(<s:property value="#request.page"/> + 1);
			});
		});
	</script>
</body>
</html>