package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/addArg")
public class AddArgsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/input_args.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String arg1 = req.getParameter("arg1");
		String arg2 = req.getParameter("arg2");
		String arg3 = req.getParameter("arg3");
		
		// 將 arg1 放到 request 變數中
		req.setAttribute("arg1", arg1);
		
		// 將 arg2 放到 session 變數中
		HttpSession session = req.getSession();
		session.setAttribute("arg2", arg2);
		
		// 將 arg3 放到 application 變數中
		ServletContext application = getServletContext();
		application.setAttribute("arg3", arg3);
		
		// 重導到 /view_args.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/view_args.jsp");
		rd.forward(req, resp);
	}
	
}
