<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../common/common.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.css" />
</head>

<script>
	$(document).ready(function() {

		$("#beginTime").datetimepicker({
			format : 'yyyy-mm-dd hh:ii'
		});

		$("#endTime").datetimepicker({
			format : 'yyyy-mm-dd hh:ii'
		});

		$("#test").datetimepicker({
			format : 'yyyy-mm-dd hh:ii'
		});

	});
</script>

<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="navigation.jsp"></jsp:include>
	<div class="container">
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-9">
				<form action="competitionAction_apply" method="post">
				
					<div class="form-group">
						<label for="name">name</label> <input
							type="text" name="competition.name" class="form-control"
							id="name" placeholder="name" />
					</div>

					<div class="form-group">
						<label for="applyUserName">applyUserName</label> <input
							type="text" name="competition.applyUserName" class="form-control"
							id="applyUserName" placeholder="applyUserName" />
					</div>

					<div class="form-group">
						<label for="description">description</label> <input type="text"
							name="competition.description" class="form-control"
							id="description" placeholder="description" />
					</div>

					<div class="form-group">
						<label for="mainTeam">mainTeam</label> <input type="text"
							name="competition.mainTeam" class="form-control" id="mainTeam"
							placeholder="mainTeam" />
					</div>

					<div class="form-group">
						<label for="guestTeam">guestTeam</label> <input type="text"
							name="competition.guestTeam" class="form-control" id="guestTeam"
							placeholder="guestTeam" />
					</div>

					<div class="form-group">
						<label for="beginTime">beginTime</label> <input type="text"
							name="beginTime" class="form-control" id="beginTime"
							placeholder="beginTime" />
					</div>

					<div class="form-group">
						<label for="endTime">endTime</label> <input type="text"
							name="endTime" class="form-control" id="endTime"
							placeholder="endTime" />
					</div>


					<div class="form-group">
						<input class="btn btn-primary form-control" type="submit"
							value="commit">
					</div>

				</form>

			</div>
		</div>
	</div>
	<jsp:include page="../common/pagefoot.jsp"></jsp:include>
</body>
<script
	src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.js"></script>
</html>