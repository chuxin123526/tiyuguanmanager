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
			<!-- 顶部标题部分 -->
			<div class="row">
				<div class="col-md-12">
					<h4>
						<s:property value="#request.tipWord" />
					</h4>
				</div>
			</div>
			<s:if test="#request.roleList.size > 0">
				<div class="row">
					<!-- 中间数据表格部分 -->
					<table class="table table-hover table-with-button">
						<thread>
						<tr>
							<th style="width: 3.5em">#</th>
							<th>名称</th>
							<th style="width: 3.5em"></th>
							<th style="width: 3.5em"></th>
						</tr>
						</thread>
						<s:iterator value="roleList" var="role">
							<tr>
								<td><span><s:property value="#role.roleId" /></span></td>
								<td><span><s:property value="#role.name" /></span></td>
								<td><s:if test="#session.user.role.permissions.{?#this.type==3}.size>0">
										<button class="btn btn-default update-btn"
											onclick="gotoUpdatePage(<s:property value="#role.roleId" />)">修改</button>
									</s:if></td>
								<td><s:if test="#session.user.role.permissions.{?#this.type==2}.size>0">
										<button class="btn btn-default delete-btn"
											onclick="deleteRole(<s:property value="#role.roleId" />, this);">删除</button></td>
								</s:if>
							</tr>
						</s:iterator>
					</table>
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
			</s:if>
			<s:else>
				<div class="row">
					<div class="alert alert-danger">
						<p>没有找到相关的数据！</p>
					</div>
				</div>
			</s:else>
		</div>
	</div>

	<jsp:include page="../../../competition/common/pagefoot.jsp"></jsp:include>
	<%-- 直接访问角色列表 --%>
	<s:if test="#request.from == 0">
		<script type="text/javascript">
			$("#nav-role-list").addClass("active");
		</script>
	</s:if>
	<%-- 如果是从添加角色处跳转，则显示相应的提示信息 --%>
	<s:if test="#request.from==1">
		<script type="text/javascript">
			showSuccessToast("角色添加成功");
			$("#nav-role-list").addClass("active");
		</script>
	</s:if>
	<%-- 如果是成功删除角色，则显示相应的提示信息 --%>
	<s:if test="#request.from==2">
		<script type="text/javascript">
			showSuccessToast("角色删除成功");
			$("#nav-role-list").addClass("active");
		</script>
	</s:if>
	<%-- 如果是成功变更角色信息，则显示相应的提示信息 --%>
	<s:if test="#request.from==3">
		<script type="text/javascript">
			showSuccessToast("角色信息变更成功");
			$("#nav-role-list").addClass("active");
		</script>
	</s:if>
	<s:if test="#request.from == 4">
		<script type="text/javascript">
			$("#nav-search-role").addClass("active");
		</script>
	</s:if>
	<script type="text/javascript">
		var count = <s:property value="#request.roleList.size" />;
		var page = <s:property value="#request.page" />;
	</script>

	<%-- 分页导航根据列表还是查询结果进行区分 --%>
	<s:if test="#request.from!=4">
		<script type="text/javascript">
		function gotoPage(page) {
			location.href = "${pageContext.request.contextPath}/user/roleList?page=" + page;
		}
		
		function gotoPageWithDeleteInfo(page) {
			location.href = "${pageContext.request.contextPath}/user/roleList?from=2&page=" + page;
		}
		
		function gotoUpdatePage(roleId) {
			location.href = "${pageContext.request.contextPath}/user/updateRolePage?form.id=" + roleId + "&page=" + page;
		}
		</script>
	</s:if>
	<s:else>
		<form id="query-criteria-form" action="roleQuery" method="post">
			<input type="hidden" name="form.name" value='<s:property value="#request.form.name"/>' /> <input
				id="form-page" type="hidden" name="page" value='<s:property value="#request.page" />' /> <input
				type="hidden" id="form-from" name="from" disabled="disabled" value="2" />
		</form>

		<script type="text/javascript">
			function gotoPage(page) {
				$("#form-page").val(page);
				$("#form-from").attr("disabled", "disabled");
				$("#query-criteria-form").submit();
			}
			
			function gotoPageWithDeleteInfo(page) {
				$("#form-page").val(page);
				$("#form-from").removeAttr("disabled");
				$("#query-criteria-form").submit();
			}
			
			function gotoUpdatePage(roleId) {
				location.href = "${pageContext.request.contextPath}/user/updateRolePage?form.id=" + roleId + "&page=1";
			}
		</script>
	</s:else>

	<script type="text/javascript">	
		function deleteRole(roleId, btn) {	
				$("button.delete-btn").attr("disabled", "disabled");
				
				$.post("deleteRole", {"form.id": roleId}, function(data, textStatus) {
					if (textStatus == 'success') {
						if (data == null) {
							showErrorToast("您没有删除角色的权限");
							$("button.delete-btn").removeAttr("disabled");
							
							return;
						}
						
						switch (data.code) {
						case 14:
							showErrorToast("角色 \""+data.name+"\" 下仍然具有用户，请先变更这些用户的角色再删除");
							break;
						case 15:
							break;
						case 16:
							showErrorToast("角色 \""+data.name+"\" 为系统保留角色，不能被删除");
							break;
						case 17:
							if (count == 1) {
								gotoPageWithDeleteInfo(page-1);
							} else {
								gotoPageWithDeleteInfo(page);
							}
							break;
						}
					} else {
						showErrorToast("与服务器通讯失败，请稍后再试");
					}
					
					$("button.delete-btn").removeAttr("disabled");
				});
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