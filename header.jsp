<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="resources/js/controller.js"></script>
<title>header</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav ">
			<li><a href="<c:url value="/home"/>">Home</a></li>
			<li><a href="<c:url value="/about"/>">About us</a></li>
			<li><a href="<c:url value="/contact"/>">Contact Us</a></li>
			
			<!--  User has logged in  -->
		<c:if test="${pageContext.request.userPrincipal.name!=null}">
		<security:authorize ifAnyGranted="ROLE_ADMIN">
				<li><a href="<c:url value="/admin/book/addBook"></c:url>">Add New Book</a></li>
		</security:authorize>
			<li><a href="<c:url value="/booksListAngular"/>">Browse All Books</a></li>		
			<li><a>Welcome ${pageContext.request.userPrincipal.name }</a></li>
			<security:authorize ifAnyGranted="ROLE_USER">
			<li><a href="<c:url value="/cart/getCartId"/>">Cart</a></li>
			</security:authorize>
			<li><a href="<c:url value="/j_spring_security_logout"></c:url>">logout</a></li>
		</c:if>
		
		<!--  when the user not logged in -->
		<c:if test="${pageContext.request.userPrincipal.name==null}">
		<li><a href="<c:url value="login"></c:url>">login</a></li>
		<li><a href="<c:url value="/customer/registration"></c:url>">Register</a>
		</c:if>
		</ul>
	</div>
	</nav>
</body>
</html>
