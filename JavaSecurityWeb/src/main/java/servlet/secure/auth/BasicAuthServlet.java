package servlet.secure.auth;

/*
 * BasicAuthServlet 是一個實現了 HTTP Basic Authentication 的 Servlet。
 * 由於用戶名和密碼在 HTTP 請求中以 Base64 編碼的形式發送（而非加密形式），
 * 注意：在實務應用上必須使用 HTTPS 進行通信，以確保傳輸過程中的安全性。
 * 
 * 預設的用戶名和密碼，用於HTTP Basic和Digest認證
 * username = user
 * password = 1234
 * */
public class BasicAuthServlet {
	
}
