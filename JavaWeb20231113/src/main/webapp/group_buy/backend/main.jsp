<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網後台首頁</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu_backend.jspf" %>
		
		<!-- 商品訂購 -->
		<div style="padding: 15px">
			<table>
				<tr>
					<!-- 商品新增 -->
					<td valign="top">
						<form method="post" action="./result.jsp" class="pure-form">
							<fieldset>
								<legend>商品新增</legend>
								品名: <input type="text" id="productName" name="productName" value="🍺 啤酒" placeholder="請輸入品名" required><p />
								價格: <input type="number" id="price" name="price" value="400" placeholder="請輸入價格" required /><p />
								單位: <select id="unit" name="unit">
										<option value="1">包</option>
										<option value="2">捆</option>
										<option value="3">打</option>
										<option value="4" selected>箱</option>
										<option value="5">組</option>
									</select>
								&nbsp;&nbsp;&nbsp;&nbsp;	
								上架: <input type="checkbox" id="launch" name="launch" checked />	
								<p />
								<button type="submit" class="pure-button pure-button-primary">新增</button>	
							</fieldset>
						</form>
					</td>
					<!-- 商品列表 -->
					<td valign="top" style="padding-left: 15px">
						<form class="pure-form">
							<fieldset>
								<legend>商品列表</legend>
								<table class="pure-table pure-table-bordered">
									<thead>
										<tr>
											<th>序號</th><th>品名</th><th>價格</th><th>單位</th><th>上架</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td><td>🍖 雞腳凍</td><td>50</td><td>包</td><td><input type="checkbox" checked /></td>
										</tr>
										<tr>
											<td>2</td><td>🥤 可樂</td><td>100</td><td>打</td><td><input type="checkbox" checked /></td>
										</tr>
										<tr>
											<td>3</td><td>🍔 漢堡</td><td>250</td><td>箱</td><td><input type="checkbox" checked /></td>
										</tr>
										<tr>
											<td>4</td><td>🍭 棒棒糖</td><td>80</td><td>梱</td><td><input type="checkbox" /></td>
										</tr>
									</tbody>
								</table>	
							</fieldset>
						</form>
					</td>
				</tr>
			</table>
		
			
		</div>
		
	</body>
</html>