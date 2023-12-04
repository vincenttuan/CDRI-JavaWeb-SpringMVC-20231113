package guestbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GuestbookDaoMySQL implements GuestbookDao {
	private Connection conn;
	
	public GuestbookDaoMySQL() {
		String url = "jdbc:mysql://localhost:3306/web?serverTimezone=Asia/Taipei";
		String username = "web";
		String password = "12345678";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void create(Guestbook guestbook) {
		String sql = "insert into guestbook(nickname, age, message) values(?, ?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 配置 sql ? 資料
			pstmt.setString(1, guestbook.getNickname());
			pstmt.setInt(2, guestbook.getAge());
			pstmt.setString(3, guestbook.getMessage());
			// 提交送出
			int rowcount = pstmt.executeUpdate();
			System.out.println("rowcount(異動筆數) = " + rowcount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Guestbook> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
