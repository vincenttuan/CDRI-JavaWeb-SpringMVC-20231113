<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-後台報表</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/GroupBuy/css/group_buy.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		
		<div style="padding: 15px">
			<table>
				<tr>
					<td valign="top">
						<form class="pure-form">
							<fieldset>
								<legend>團購網-後台報表</legend>
								<table class="pure-table">
								    <thead>
								        <tr>
								        	<th>序號</th>
								            <th>UserID</th>
								            <th>帳號</th>
								            <th>金額</th>
								            <th>明細</th>
								        </tr>
								    </thead>
								    <tbody>
								    	<c:forEach items="${ reports }" var="report" varStatus="status">
									        <tr class="${ (status.count % 2) == 0 ? 'pure-table-odd' : 'pure-table' }">
									        	<td>${ status.count + 1 }</td>
									            <td>${ report.userId }</td>
									            <td>${ report.username }</td>
									            <td>$${ report.total }</td>
									            <td>
									            	<a href="javascript:void(0);"
									            		onClick="location.href='./report?userId=${ report.userId }';" 
									            		class="button-secondary pure-button">明細</button>
									            </td>
									        </tr>
								        </c:forEach>
								    </tbody>
								</table>
							</fieldset>
						</form>
					</td>
					<td valign="top" style="padding-left: 15px">
						<form class="pure-form">
							<fieldset>
								<legend>團購網- John 結帳明細</legend>
								<table class="pure-table pure-table-bordered">
									<thead>
										<tr><th>購物車序號</th><th>購物車日期</th><th>購物項目</th></tr>
									</thead>
									<tbody>
										<c:forEach items="${ carts }" var="cart">
											<tr>
												<td>${ cart.cartId }</td>
												<td>
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss E" value="${ cart.checkoutTime }" />
												</td>
												<td>
													<table>
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
														</tbody>
													</table>
												</td>
												
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