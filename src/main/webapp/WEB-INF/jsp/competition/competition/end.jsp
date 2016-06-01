<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../common/common.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="navigation.jsp"></jsp:include>
	<div class="container">
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
						<tr>
							<td>name</td>
							<td>competitionType</td>
							<td>description</td>
							<td>applyUserName</td>
							<td>mainTeam</td>
							<td>guestTeam</td>
							<td>beginTime</td>
							<td>endTime</td>
							<td>status</td>
							<td>mainScore</td>
							<td>guestScore</td>

						</tr>
						<tr>
							<td>name</td>
							<td>competitionType</td>
							<td>description</td>
							<td>applyUserName</td>
							<td>mainTeam</td>
							<td>guestTeam</td>
							<td>beginTime</td>
							<td>endTime</td>
							<td>status</td>
							<td>mainScore</td>
							<td>guestScore</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../common/pagefoot.jsp"></jsp:include>
</body>
</html>