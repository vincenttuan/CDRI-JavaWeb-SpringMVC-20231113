package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;

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
		
		// 成績筆數 = ? 平均 = ? 總分 = ? 最高分 = ? 最低分 = ? 
		Map<String, Number> map = getScoreInfo(scores);
		resp.getWriter().print(map);
		
	}
	
	private Map<String, Number> getScoreInfo(String[] scores) {
		if (scores == null) {
			return null;
		}
		IntSummaryStatistics stat = Arrays.stream(scores) // 字串陣列流
				//.mapToInt(str -> Integer.parseInt(str)) // int 陣列串流
				.mapToInt(Integer::parseInt) // int 陣列串流
				.summaryStatistics();
		
		Map<String , Number> map = new HashMap<>();
		map.put("count", stat.getCount());
		map.put("average", stat.getAverage());
		map.put("sum", stat.getSum());
		map.put("max", stat.getMax());
		map.put("min", stat.getMin());
		return map;
	}
	
	
}
