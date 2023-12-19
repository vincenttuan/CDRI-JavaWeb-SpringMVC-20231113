<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-後臺商品維護</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/GroupBuy/css/group_buy.css">
		<script type="text/javascript">
			function changeLaunch(productId, isLaunch) {
				location.href = './main/update_product_launch?productId=' + productId + '&isLaunch=' + isLaunch;
			}
		</script>
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		
		<div style="padding: 15px">
			<table>
				<tr>
					<td valign="top">
						<form method="post" action="./result" class="pure-form">
							<fieldset>
								<legend>團購網-商品新增</legend>
								品名: <input type="text" id="productName" name="productName" value="水果茶" /><p />
								價格: <input type="number" id="productPrice" name="productPrice" value="40" /><p />
								單位: <select id="productUnit" name="productUnit">
										<option value="捆">捆</option>
										<option value="包" selected>包</option>
										<option value="打">打</option>
										<option value="箱">箱</option>
										<option value="組">組</option>
									 </select>
								&nbsp;&nbsp;&nbsp;&nbsp;	 
								上架: <input type="checkbox" id="isLaunch" name="isLaunch" value="true" checked> 	 
								<p />
								<button type="submit" class="pure-button pure-button-primary">新增</button>
							</fieldset>
						</form>
					</td>
					<td valign="top" style="padding-left: 15px">
						<form class="pure-form">
							<fieldset>
								<legend>團購網-後臺商品維護</legend>
								<table class="pure-table pure-table-bordered">
									<thead>
										<tr><th>序號</th><th>ID</th><th>品名</th><th>價格</th><th>單位</th><th>上架</th></tr>
									</thead>
									<tbody>
										<c:forEach items="${ products }" var="product" varStatus="status">
										<tr>
											<td>${ status.count+1 }</td>
											<td>${ product.productId }</td>
											<td>${ product.productName }</td>
											<td>${ product.price }</td>
											<td>${ product.unit }</td>
											<td><input 
													onClick="changeLaunch(${ product.productId }, this.checked)"
													type="checkbox" ${ (product.isLaunch) ? 'checked' : '' } 
													value="true"> 上架</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								<p />
							</fieldset>
						</form>
					</td>
				</tr>
			</table>
		</div>
		
		
		
		
	</body>
</html>