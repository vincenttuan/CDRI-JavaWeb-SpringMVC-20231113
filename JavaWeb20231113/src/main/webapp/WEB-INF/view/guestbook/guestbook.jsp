<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="guestbook.model.Guestbook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
	// 自訂方法, 將日期格式設定成 yyyy-MM-dd HH:mm:ss E
	private String getDateFormatString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		return sdf.format(date);
	}
%>

<%
	List<Guestbook> guestbooks = (List<Guestbook>) request.getAttribute("guestbooks");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Guest Book</title>
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	      google.charts.load('current', {'packages':['corechart']});
	      google.charts.setOnLoadCallback(drawChart);
	
	      function drawChart() {
	
	        var data = google.visualization.arrayToDataTable([
	          ['Task', 'Hours per Day'],
	          ['Work',     11],
	          ['Eat',      2],
	          ['Commute',  2],
	          ['Watch TV', 2],
	          ['Sleep',    7]
	        ]);
	
	        var options = {
	          title: 'My Daily Activities'
	        };
	
	        var chart = new google.visualization.PieChart(document.getElementById('chart1'));
	
	        chart.draw(data, options);
	      }
	    </script>
		
	</head>
	<body style="padding: 15px">
		<table>
			<tr>
				<!-- 留言輸入 -->
				<td valign="top">
					<form method="post" action="./guestbook" class="pure-form">
						<fieldset>
							<legend>訪客留言輸入</legend>
							匿稱: <input type="text" id="nickname" name="nickname" placeholder="請輸入暱稱" required /><p />
							年齡: <input type="number" id="age" name="age" placeholder="請輸入年齡" required /><p />
							性別: <input type="radio" id="sex" name="sex" value="M" checked />男 
							     <input type="radio" id="sex" name="sex" value="F" />女<p />
							訊息:<p />
							<textarea rows="3" cols="30" id="message" name="message" required ></textarea><p />
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
								<th>id</th><th>暱稱</th><th>年齡</th><th>性別</th><th>時間</th><th>訊息</th>
							</tr>
						</thead>
						<tbody>
							<% for(Guestbook gb : guestbooks) { %>
								<tr>
									<td><% out.print(gb.getId()); %></td>
									<td><%=gb.getNickname() %></td>
									<td><%=gb.getAge() %></td>
									<td><%=gb.getSex() %></td>
									<td><%=getDateFormatString(gb.getDate()) %></td>
									<td><%=gb.getMessage() %></td>
								</tr>
							<% } %>
						</tbody>
					</table>
					
				</td>
			</tr>
		</table>
		
	</body>
</html>