package guestbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
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
				// TODO: handle exception
			}
		}
	}

	@Override
	public void create(Guestbook guestbook) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Guestbook> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
