package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 用來處理成績的網路服務
// .../servlet/score?score=100&score=45&score=80
@WebServlet(value = "/servlet/score")
public class ScoreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 接收客戶端來的請求參數
		String[] scores = req.getParameterValues("score");
		
		// 請印出所有分數
		PrintWriter out = resp.getWriter();
		out.print("所有成績: " + Arrays.toString(scores) + "<p>");
		// Java 8 Stream
		Arrays.stream(scores).forEach(score -> out.print(score + "<br>"));
		Arrays.stream(scores).forEach(out::print);
		// 成績筆數 = ? 平均 = ? 總分 = ? 最高分 = ? 最低分 = ? 
	}
	
}
