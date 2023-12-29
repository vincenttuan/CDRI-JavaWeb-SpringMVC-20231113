package servlet.secure.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.util.WebKeyUtil;

/*
 * BasicAuthServlet 是一個實現了 HTTP Basic Authentication 的 Servlet。
 * 由於用戶名和密碼在 HTTP 請求中以 Base64 編碼的形式發送（而非加密形式），
 * 注意：在實務應用上必須使用 HTTPS 進行通信，以確保傳輸過程中的安全性。
 * 
 * 預設的用戶名和密碼，用於HTTP Basic和Digest認證
 * username = user
 * password = 1234
 * */
@WebServlet(value = "/secure/servlet/auth/basic_auth")
public class BasicAuthServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 檢查 client 端的 http header 中的 Authorization 標頭資訊
		String authHeader = req.getHeader("Authorization");
		
		if(authHeader != null && WebKeyUtil.isValidBasicAuth(authHeader)) { // 進行標頭認證檢查
			// 認證成功
			resp.getWriter().println("Welcome to the protected page !");
			return;
		}
		
		// 認證失敗
		final String REALM = "Restricted Area"; // 定義領域
		// 要求客戶端提供認證訊息
		resp.setHeader("WWW-Authenticate", WebKeyUtil.generateBasicChallenge(REALM));
		// 發送 401 未經授權的回應
		resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
}
