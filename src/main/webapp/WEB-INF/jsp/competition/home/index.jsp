<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta http-equiv="charset" content="utf-8">
</head>
<body>
<jsp:include page="../common/common.jsp"></jsp:include>
<jsp:include page="../common/top.jsp"></jsp:include>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style = "margin-top : 10px ; ">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

<div id="myCarousel" class="carousel slide col-md-7" style = "padding : 0px ; ">
   <!-- 轮播（Carousel）指标 -->
   <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
   </ol>   
   <!-- 轮播（Carousel）项目 -->
   <div class="carousel-inner ">
      <div class="item active ">
         <img src="${pageContext.request.contextPath }/image/competition/test.jpg" alt="First slide">
      </div>
      <div class="item">
         <img src="${pageContext.request.contextPath }/image/competition/test1.jpg" alt="Second slide">
      </div>
      <div class="item">
         <img src="${pageContext.request.contextPath }/image/competition/test3.jpg" alt="Third slide">
      </div>
   </div>
   <!-- 轮播（Carousel）导航 -->
   <a class="carousel-control left" href="#myCarousel" 
      data-slide="prev">&lsaquo;</a>
   <a class="carousel-control right" href="#myCarousel" 
      data-slide="next">&rsaquo;</a>
</div> 











</body>
</html>