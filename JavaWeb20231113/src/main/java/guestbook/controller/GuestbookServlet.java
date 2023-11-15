package guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.model.Guestbook;
import guestbook.model.GuestbookDao;
import guestbook.model.GuestbookDaoInMemory;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private GuestbookDao guestbookDao = new GuestbookDaoInMemory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得所有留言紀錄
		List<Guestbook> guestbooks = guestbookDao.readAll();
		
		// 重導到 /WEB-INF/view/guestbook/guestbook.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/guestbook/guestbook.jsp");
		req.setAttribute("guestbooks", guestbooks); // 傳遞參數給 jsp
		rd.forward(req, resp);
	}
	
	// 給表單新增使用
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("Hello Post");
	}
	
}
