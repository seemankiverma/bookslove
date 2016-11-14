<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script type="text/javascript">
var myapp=angular.module("myapp",[])
.controller("myController",function($scope,$http){

	$scope.getBookList = function(){
		   $http.get('http://localhost:8080/bookslove/getBooksList').success(function (data){
		       $scope.books = data;
		   })
		}
		
		$scope.addToCart=function(isbn){
			   $http.put('http://localhost:8080/bookslove/cart/add/'+isbn).success(function(){
				   alert('Added Successfully');
			   })
		   }

		$scope.refreshCart=function(){
			$http.get('http://localhost:8080/bookslove/cart/getCart/'+$scope.cartId).success(function(data){
				$scope.cart=data;
			})
		    }
		
		    $scope.getCart=function(cartId){
			$scope.cartId=cartId;
			$scope.refreshCart(cartId);
		    }

		    $scope.removeFromCart=function(cartItemId){
				$http.put(
		'http://localhost:8080/bookslove/cart/removecartitem/'+cartItemId)
			.success(function(){
				$scope.refreshCart();
			})
		    }

		    $scope.clearCart=function(){
				$http.put(
		'http://localhost:8080/bookslove/cart/removeAllItems/'+$scope.cartId)
			.success(function(){
				$scope.refreshCart();
			})
		    }

		    $scope.calculateGrandTotal=function(){
			var grandTotal=0.0
			for(var i=0;i<$scope.cart.cartItems.length;i++)
				grandTotal=grandTotal+$scope.cart.cartItems[i].totalPrice;
			return grandTotal;
		}
		    
		});
</script>
</head>
<body>
<div ng-app ="myapp">
<img src="<c:url value="/resources/images/${bookObj.isbn }.png" />" width="10%" />
ISBN : ${bookObj.isbn } <br>
TITLE : ${bookObj.title } <br>
Price : ${bookObj.price } <br>
Category : ${bookObj.category.categoryName } <br>
<c:url value="/cart/add/${bookObj.isbn }" var="url"></c:url>
<div ng-controller="myController">
<security:authorize access="hasRole('ROLE_USER')">
<a href="#" ng-click="addToCart(${bookObj.isbn})" class="btn btn-warning btn-large">
<span class="glyphicon glyphicon-shopping-cart"></span></a>
<!--  <a href="${url}" class="btn btn-warning btn-large"><span class="glyphicon glyphicon-shopping-cart"></span></a>
-->
</security:authorize>
<a href='<c:url value="/booksList"></c:url>'>Back</a>
</div>
</div>
</body>
</html>
