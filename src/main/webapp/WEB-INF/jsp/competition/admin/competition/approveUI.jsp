<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../../common/top.jsp"></jsp:include>

	<div class="col-md-3 ">
		<jsp:include page="../common/leftNavigation.jsp"></jsp:include>
	</div>

	<div class="container">
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-8">
				<form action="competitionManagerAction_approve" method="post">
					<input type = "hidden" name = "competition.id" value = "${competition.id}">
					<div class="form-group">
						<label for="name">name</label> <input type="text" readonly = "readonly"
							name="competition.name" class="form-control" id="name" value = "${competition.name}"
							placeholder="name" />
					</div>

					<div class="form-group">
						<label for="applyUserName">applyUserName</label> <input readonly = "readonly"
							type="text" name="competition.applyUserName" class="form-control" value = "${competition.applyUserName}"
							id="applyUserName" placeholder="applyUserName" />
					</div>

					<div class="form-group">
						<label for="description">description</label> <input type="text" readonly = "readonly"
							name="competition.description" class="form-control" value = "${competition.description}"
							id="description" placeholder="description" />
					</div>

					<div class="form-group">
						<label for="mainTeam">mainTeam</label> <input type="text" readonly = "readonly"
							name="competition.mainTeam" class="form-control" id="mainTeam" value = "${competition.mainTeam}"
							placeholder="mainTeam" />
					</div>

					<div class="form-group">
						<label for="guestTeam">guestTeam</label> <input type="text" readonly = "readonly"
							name="competition.guestTeam" class="form-control" id="guestTeam" value = "${competition.guestTeam}"
							placeholder="guestTeam" />
					</div>

					<div class="form-group">
						<label for="beginTime">beginTime</label> <input type="text" readonly = "readonly"
							name="beginTime" class="form-control" id="beginTime" value = "${competition.beginTime}"
							placeholder="beginTime" />
					</div>

					<div class="form-group">
						<label for="endTime">endTime</label> <input type="text" readonly = "readonly"
							name="endTime" class="form-control" id="endTime" value = "${competition.endTime}"
							placeholder="endTime" />
					</div>


					<div class="radio">
						<label> <input type="radio" name="isPass"
							 value="yes" checked /> 通过
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="isPass"
							 value="no" /> 不通过
						</label>
					</div>
					
					<div class="form-group">
						<label for="comment">意见:</label>
						<textarea id = "comment" class="form-control" name = "comment" rows="3"></textarea>
					</div>
					

					<div class="form-group">
						<input class="btn btn-primary form-control" type="submit"
							value="commit">
					</div>




				</form>

			</div>
		</div>
	</div>



	<jsp:include page="../../common/pagefoot.jsp"></jsp:include>
</body>
</html>