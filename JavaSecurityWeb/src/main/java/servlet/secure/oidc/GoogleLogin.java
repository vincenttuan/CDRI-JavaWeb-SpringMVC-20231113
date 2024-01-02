package servlet.secure.oidc;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.util.OIDCUtil;

@WebServlet("/secure/oidc/google_login")
public class GoogleLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String responseType = "code"; // 認證碼變數名稱
		
		// 範圍（Scope）: 在您的授權請求中，您指定了範圍（scope）為 "email openid"。
        // 這裡的 "openid" 是使用 OpenID Connect 必須的範圍，它表明您的應用程式將使用 OIDC 來對用戶進行身份驗證。
        // 而 "email" 是要求提供用戶的電子郵件資訊。
        // 測試結果: openid 不加也可以（可能是 google oidc 認證系統預設）
		String scope = "email openid";
		
		// 取得 Google 授權 URL
		String authURL = OIDCUtil.getAuthorizationUrl(responseType, scope);
		
		// 重新導向到 Google 授權頁面
		resp.sendRedirect(authURL);
		
	}
	
}
