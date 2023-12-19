<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-購物車</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/GroupBuy/css/group_buy.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>團購網-結帳成功</legend>
					<table class="pure-table pure-table-bordered">
						<thead>
							<tr><th>序號</th><th>品名</th><th>價格</th><th>單位</th><th>數量</th><th>小計</th></tr>
						</thead>
						<tbody>
							<c:forEach items="${ cart.cartItems }" var="item">
								<tr>
									<td>${ item.itemId }</td>
									<td>${ item.product.productName }</td>
									<td>${ item.product.price }</td>
									<td>${ item.product.unit }</td>
									<td>${ item.quantity }</td>
									<td>${ item.product.price * item.quantity }</td>
								</tr>	
							</c:forEach>
							<tr>
								<td colspan="5" align="right">總計</td>
								<td>${ total }</td>
							</tr>
						</tbody>
					</table>
					<p />
				</fieldset>
			</form>
		</div>
		
		
	</body>
</html>