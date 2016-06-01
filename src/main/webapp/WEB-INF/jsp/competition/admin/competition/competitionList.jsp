<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../../common/top.jsp"></jsp:include>


	<div class="col-md-2">
		<jsp:include page="../common/leftNavigation.jsp"></jsp:include>
	</div>

	<div class="col-md-10">

		<div class="row">
			<div style="margin-top: 10px;">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>name</th>
							<th>competitionType</th>
							<th>description</th>
							<th>applyUserName</th>
							<th>mainTeam</th>
							<th>guestTeam</th>
							<th>beginTime</th>
							<th>endTime</th>
							<th>status</th>
							<th>mainScore</th>
							<th>guestScore</th>
							<th>operation</th>

						</tr>
					</thead>

					<tbody>
						<s:iterator value="competitionList" var="competition">
							<tr>
								<td>${competition.name}</td>
								<s:if test="competition.competitionType != null">
									<td>${competition.competitionType.name}</td>
								</s:if>	
								<s:else>
									<td> </td>
								</s:else>
								<td>${competition.description}</td>
								<td>${competition.applyUserName}</td>
								<td>${competition.mainTeam}</td>
								<td>${competition.guestTeam}</td>
								<td>${competition.beginTime}</td>
								<td>${competition.endTime}</td>
								<td>${competition.status}</td>
								<td>${competition.mainScore}</td>
								<td>${competition.guestScore}</td>
								<td>
									<s:if test="#competition.status == \"未审核\"">
										<a href = "competitionManagerAction_approveUI?competition.id=${competition.id}">approve</a>&nbsp;&nbsp;
									</s:if>
									
								</td>
							</tr>
						</s:iterator>

					</tbody>


				</table>
			</div>
		</div>
	</div>

	<jsp:include page="../../common/pagefoot.jsp"></jsp:include>
</body>
</html>