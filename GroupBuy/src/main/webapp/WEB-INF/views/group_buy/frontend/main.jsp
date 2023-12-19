<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網首頁</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		<div style="padding: 15px">
			<form method="post" action="./result" class="pure-form">
				<fieldset>
					<legend>團購網首頁</legend>
					商品: <select id="productId" name="productId">
							<c:forEach items="${ products }" var="product" >
								<option value="${ product.productId }">
									${ product.productName }
									( ${ product.price } 元 / ${ product.unit } )
								</option>
							</c:forEach>
						 </select><p />
					數量: <input type="number" id="quantity" name="quantity" value="5" /><p />	 
					<button type="submit" class="pure-button pure-button-primary">新增</button>	 
				</fieldset>
			</form>
		</div>
	</body>
</html>