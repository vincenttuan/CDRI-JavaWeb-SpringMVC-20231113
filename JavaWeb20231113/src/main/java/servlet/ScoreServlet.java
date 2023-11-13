package servlet;

import java.io.IOException;

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
		
	}
	
}
