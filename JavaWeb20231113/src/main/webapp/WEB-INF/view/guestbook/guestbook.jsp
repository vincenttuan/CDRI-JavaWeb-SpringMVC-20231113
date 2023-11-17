<%@page import="java.util.Set"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="guestbook.model.Guestbook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
	interface Pattern {
		String ROW_PATTERN = "['%s', %s]";
		String TABLE_ROW_PATTERN = "[%d, '%s', %d, '%s', '%s', '%s'],";
	}	

	// 自訂方法, 將日期格式設定成 yyyy-MM-dd HH:mm:ss E
	private String getDateFormatString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		return sdf.format(date);
	}
%>

<%
	List<Guestbook> guestbooks = (List<Guestbook>) request.getAttribute("guestbooks");
	long manAmount = guestbooks.stream().filter(gt -> gt.getSex().equals("M")).count();
	long femaleAmount = guestbooks.stream().filter(gt -> gt.getSex().equals("F")).count();
	Map<Integer, Long> ageMap = guestbooks.stream()
										  .collect(Collectors.groupingBy(Guestbook::getAge, Collectors.counting()));
	out.print(ageMap);
	
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Guest Book</title>
		
		<%@ include file="header.jspf" %>
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	      google.charts.load('current', {'packages':['corechart', 'table']});
	      google.charts.setOnLoadCallback(drawCharts);
	      
	      function drawCharts() {
	    	  drawChart();  // pie chart
	    	  drawChart2(); // column chart
	    	  drawTable(); // table chart
	      }
	      
	      function drawChart() {
	
	        var data = google.visualization.arrayToDataTable([
	          ['Sex', 'Amount'],
	          ['Man',    <%=manAmount %>],
	          ['Female', <%=femaleAmount %>],
	        ]);
	
	        var options = {
	          title: 'Guestbook by sex',
	          is3D: true
	        };
	
	        var chart = new google.visualization.PieChart(document.getElementById('chart1'));
			
	        chart.draw(data, options);
	      }
	      
	      function drawChart2() {
	    		
	        var data = google.visualization.arrayToDataTable([
	          ['Age', 'Amount'],
	          <%
	          	// java 7
	          	/*
	          	String rowPattern = "['%s', %s],";
	          	Set<Integer> keys = ageMap.keySet();
	          	for(Integer key : keys) {
	          		//out.print("['" + key + "', " + ageMap.get(key) + "],");
	          		out.print(String.format(rowPattern, key, ageMap.get(key)));
	          	}
	          	*/
	          	// java 8
	          	// 組合 result = ['18', 1],['19', 2],['20', 1],['21', 1],
	          	
	          	String result = ageMap.keySet().stream()
	          						  .map(key -> String.format(Pattern.ROW_PATTERN, key, ageMap.get(key)))
	          						  .collect(Collectors.joining(","));
	          	
	          	out.print(result);
	          	
	          	
	          	
	          	//rowPattern = "[%d, '%s', %d, '%s', '%s', '%s'],";
	          %>
	        ]);
			
	        var options = {
	          title: 'Guestbook by age',
	          is3D: true
	        };
	
	        var chart = new google.visualization.ColumnChart(document.getElementById('chart2'));
			
	        chart.draw(data, options);
	      }
	      
	      function drawTable() {
	          var data = new google.visualization.DataTable();
	          data.addColumn('number', '序號');
	          data.addColumn('string', '暱稱');
	          data.addColumn('number', '年齡');
	          data.addColumn('string', '性別');
	          data.addColumn('string', '時間');
	          data.addColumn('string', '訊息');
	          data.addRows([
	        	  <%
	        	  	for(Guestbook gb : guestbooks) {
	        	  		out.println(String.format(Pattern.TABLE_ROW_PATTERN, gb.getId(), gb.getNickname(), gb.getAge(),
	        	  				gb.getSex(), getDateFormatString(gb.getDate()), gb.getMessage()));
	        	  	}
	        	  %>
	          ]);

	          var table = new google.visualization.Table(document.getElementById('chart3'));

	          table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
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
					<div id="chart1" style="width: 500px; height: 300px;"></div>
				</td>
				<td valign="top">
					<div id="chart2" style="width: 500px; height: 300px;"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3" valign="top">
					<!-- 留言筆數 -->
					留言筆數: <%=guestbooks.size() %> 筆
				</td>
			</tr>
			<tr>
				<!-- 留言列表 -->
				<td colspan="3" valign="top">
					
					<table class="pure-table pure-table-bordered" style="width: 95%">
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
					<p />
					<div id="chart3"></div>
					
				</td>
			</tr>
		</table>
		
	</body>
</html>