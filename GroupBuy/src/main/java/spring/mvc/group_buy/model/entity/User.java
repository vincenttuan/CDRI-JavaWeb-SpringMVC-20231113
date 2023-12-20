package spring.mvc.group_buy.model.entity;

/**
2. 使用者
level: 1(一般會員-進行團購), 2(後臺維運人員-進行團購+上架商品)
+--------+----------+----------+-------+
| userId | username | password | level |
+--------+----------+----------+-------+
|  101   | user123  | pass123  |   1   |
|  102   | user456  | pass456  |   2   |
|  103   | user789  | pass789  |   1   |
+--------+----------+----------+-------+
*/
public class User {
	private Integer userId; // 使用者代號
	private String username; // 使用者名稱
	private String password; // 使用者密碼
	private Integer level; // 使用者權限
	
	public User() {
		
	}
	
	public User(Integer userId, String username, String password, Integer level) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.level = level;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", level=" + level
				+ "]";
	}
	
	
	
}
