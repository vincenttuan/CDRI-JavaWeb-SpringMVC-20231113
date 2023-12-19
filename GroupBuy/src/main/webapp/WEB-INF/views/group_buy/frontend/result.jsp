<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-新增結果</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>團購網-新增結果</legend>
					商品: ${ product.productName } ( ${ product.price } 元 / ${ product.unit } )<p />
					數量: ${ quantity } ${ product.unit }<p />	 
				</fieldset>
			</form>
		</div>
		
	</body>
</html>