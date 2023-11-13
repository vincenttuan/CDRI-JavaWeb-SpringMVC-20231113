package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

// 可以用來顯示今天日期與天氣
// 設計網址: http://localhost:8080/JavaWeb20231113/servlet/TodayServlet 
@WebServlet(value = "/servlet/TodayServlet")
public class TodayServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 設定支援 utf-8 編碼
		res.setCharacterEncoding("utf-8"); // 給 java 使用
		res.setContentType("text/html;charset=utf-8"); // 給瀏覽器使用 
		
		Date today = new Date();
		// 格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss E");
		String todayString = sdf.format(today); // 2023-11-13 下午 12:10:50 星期一
		// 將結果回應給前端
		res.getWriter().print("今天: " + todayString);
	}
	
}
