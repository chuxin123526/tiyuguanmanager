<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>

</head>

<body>
	<jsp:include page="../../common/top.jsp"></jsp:include>
	<div class="row">
		<div class="col-md-3">
			<jsp:include page="../common/leftNavigation.jsp"></jsp:include>
		</div>

		<div class="col-md-9">


			<form action = "competitionNewsManagerAction_add" method = "post">
				<div class="form-group">
					<label for="title">title</label> <input
						type="text" class="form-control" id="title" name = "competitionNews.title"
						placeholder="title">
				</div>
				
				<div class="form-group">
					<label for="title">content</label> 
					<textarea id = "content" rows="10" class = "form-control" name = "competitionNews.content">
	
					</textarea>
				</div>
				
				
				<input type = "submit" value = "commit" class = "btn btn-primary form-control">
			</form>

		</div>

	</div>
	<jsp:include page="../../common/pagefoot.jsp"></jsp:include>
</body>
<script type="text/javascript">

$(document).ready(function(){
	CKEDITOR.replace( 'content' );
}) ; 

</script>
</html>