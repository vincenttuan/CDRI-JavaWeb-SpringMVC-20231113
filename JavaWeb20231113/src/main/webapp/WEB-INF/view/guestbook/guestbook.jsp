<%@page import="guestbook.model.Guestbook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<Guestbook> guestbooks = (List<Guestbook>) request.getAttribute("guestbooks");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Guest Book</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		<table>
			<tr>
				<!-- 留言輸入 -->
				<td valign="top">
					<form method="post" action="" class="pure-form">
						<fieldset>
							<legend>訪客留言輸入</legend>
							匿稱: <input type="text" id="nickname" name="nickname" placeholder="請輸入暱稱" required /><p />
							年齡: <input type="number" id="age" name="age" placeholder="請輸入年齡" required /><p />
							性別: <input type="radio" id="sex" name="sex" value="M" checked />男 
							     <input type="radio" id="sex" name="sex" value="F" />女<p />
							<button type="reset">清除</button>&nbsp;<button type="submit">送出</button>    
						</fieldset>
					</form>
				</td>
				<!-- 統計圖表 -->
				<td valign="top">
					<div id="chart1" />
					<div id="chart2" />
				</td>
			</tr>
			<tr>
				<!-- 留言列表 -->
				<td colspan="2" valign="top">
					
					<table class="pure-table pure-table-bordered">
						<thead>
							<tr>
								<th>id</th><th>暱稱</th><th>年齡</th><th>性別</th><th>時間</th>
							</tr>
						</thead>
						<tbody>
							<% for(Guestbook gb : guestbooks) { %>
								<tr>
									<td><%=gb.getId() %></td>
									<td><%=gb.getNickname() %></td>
									<td><%=gb.getAge() %></td>
									<td><%=gb.getSex() %></td>
									<td><%=gb.getDate() %></td>
								</tr>
							<% } %>
						</tbody>
					</table>
					
				</td>
			</tr>
		</table>
		
	</body>
</html>