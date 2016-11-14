<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
				$http.put('http://localhost:8080/bookslove/cart/removecartitem/'+cartItemId)
			.success(function(){
				$scope.refreshCart();
			})
		    }

		    $scope.clearCart=function(){
				$http.put('http://localhost:8080/bookslove/cart/removeAllItems/'+$scope.cartId).success(function(){
				$scope.refreshCart();
			})
		    }

		    $scope.calculateGrandTotal=function(){
			var grandTotal=0.0
			for(var i=0;i<$scope.cart.cartItem.length;i++)
				grandTotal=grandTotal+$scope.cart.cartItem[i].totalPrice;
			return grandTotal;
		}
		    
		});
</script>
</head>
<body>
<div class="container-wrapper">
<div class="container">
<section>
<div class="jumbotron">
<div class="container">
<h3>Cart</h3>
<p>All the selected books in your shopping cart</p>
</div>
</div>
</section>
<div ng-app="myapp" ng-controller="myController">

<div ng-init="getCart(${cartId})">
<br>
List of books purchased
<div>
<a class="btn btn-danger pull-left" ng-click="clearCart()">
<span class="glyphicon glyphicon-remove-sign"></span> Clear Cart</a>
<a href ="<c:url value="/order/${cartId }" />" class="btn btn-success pull-right">
<span class="glyphicon glyphicon-shopping-cart"></span>Check out</a>
</div>
<table class="table table-hover">
<thead>
	
<tr>
<th>TITLE</th>
<th>QUANTITY</th>
<th>Price</th>
<th>TOTAL PRICE</th>
</tr>
</thead>
<tr ng-repeat="cartitem in cart.cartItem">
<td>
{{cartitem.book.title}}
</td>
<td>
{{cartitem.quantity}}
</td>
<td>
{{cartitem.book.price}}
</td>
<td>
{{cartitem.totalPrice}}
</td>
<td>
<a href="#" class="label label-danger" ng-click="removeFromCart(cartitem.cartItemId)">
<span class="glyphicon glyphicon-remove"></span>remove</a>
</td>
</tr>

</table>
Grand Total Price {{calculateGrandTotal()}}
</div>
</div>
<c:url value="/getAllBooks" var="url"></c:url>
<a href="${url }" class="btn btn-default">Continue shopping</a>
</div>
</div>

</body>
</html>
