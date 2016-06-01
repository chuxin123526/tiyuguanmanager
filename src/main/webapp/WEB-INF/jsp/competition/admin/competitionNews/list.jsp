<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
</head>
<body>
<jsp:include page="../../common/common.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<div class = "container" >
	<div class="row">
	
	<div class = "col-md-3">
		<jsp:include page="../common/leftNavigation.jsp"></jsp:include>
	</div>  
	
	<div class = "col-md-9">
		
		<s:iterator value="competitionNewsList" var="competitionNews">
			<h2>${competitionNews.title}</h2>
	        <p >${competitionNews.content } </p>
	        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
		</s:iterator>
		
        <h2>Heading</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        
         <h2>Heading</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
     
    	  <h2>Heading</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
     
      </div>
      
    </div>
</div>
<jsp:include page="../../common/pagefoot.jsp"></jsp:include>
</body>
</html>