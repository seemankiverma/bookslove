<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file ="header.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME PAGE</title>
</head>
<body>
<div container>
<br><br>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
    <li data-target="#myCarousel" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="book">
    <div class="item active">
      <img src="<c:url value="/resources/images/t1larg.vampire.kbcnn.jpg" ></c:url>" width="1500" height="100">
    </div>

    <div class="item">
       <img src='<c:url value="/resources/images/Novel-comp-2012-winner-books.jpg" ></c:url>'>
    </div>

    <div class="item">
      <img src='<c:url value="/resources/images/Mythlogy-book-banner.jpg"></c:url>'>
    </div>

    <div class="item">
      <img src='<c:url value="/resources/images/most-popular-books-aug-week2.jpg"></c:url>'>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<br>

</div>


</body>
<%@include file ="footer.jsp"%>
</html>