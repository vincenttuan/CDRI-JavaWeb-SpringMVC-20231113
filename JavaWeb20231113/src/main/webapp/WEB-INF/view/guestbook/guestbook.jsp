<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Guest Book</title>
	</head>
	<body>
		<table>
			<tr>
				<!-- 留言輸入 -->
				<td valign="top">
					<form method="post" action="">
						<fieldset>
							<legend>訪客留言輸入</legend>
							匿稱: <input type="text" id="nickname" name="nickname" placeholder="請輸入暱稱" required /><p />
							年齡: <input type="number" id="age" name="age" placeholder="請輸入年齡" required /><p />
							性別: <input type="radio" id="sex" name="sex" checked />男 
							     <input type="radio" id="sex" name="sex" />女<p />
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
					
				</td>
			</tr>
		</table>
		
	</body>
</html>