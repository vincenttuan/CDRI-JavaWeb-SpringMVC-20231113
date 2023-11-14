<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	// 接收 ScoreServlet 傳來的資料
	Map<String, Number> scoreInfo = (Map<String, Number>)request.getAttribute("scoreInfo");
	String[] scores = (String[])request.getAttribute("scores");
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>成績統計資訊</title>
		<style type="text/css">
			.red_color {
				color: red
			}
			.blue_color {
				color: blue
			}
		</style>
	</head>
	<body>
		<h1>成績統計資訊</h1>
		所有成績 = <%=Arrays.toString(scores) %> <p />
		<%
			for(String score : scores) {
				if(Integer.parseInt(score) < 60) {
					out.print("<div class='red_color'>" + score + "</div>");
				} else {
					out.print("<div class='blue_color'>" + score + "</div>");
				}
			}
		%>
		成績筆數 = <%=scoreInfo.get("count") %> <p />
		平均 = <%=scoreInfo.get("average") %> <p />
		總分 = <%=scoreInfo.get("sum") %> <p />
		最高分 = <%=scoreInfo.get("max") %> <p />
		最低分 = <%=scoreInfo.get("min") %> <p />
	</body>
</html>