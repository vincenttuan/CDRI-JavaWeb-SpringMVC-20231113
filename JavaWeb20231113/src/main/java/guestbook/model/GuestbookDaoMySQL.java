package guestbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDaoMySQL implements GuestbookDao {
	private Connection conn;
	
	public GuestbookDaoMySQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/web?serverTimezone=Asia/Taipei";
			String username = "web";
			String password = "12345678";
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException | ClassNotFoundException e) {
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
		String sql = "insert into guestbook(nickname, age, sex, message) values(?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 配置 sql ? 資料
			pstmt.setString(1, guestbook.getNickname());
			pstmt.setInt(2, guestbook.getAge());
			pstmt.setString(3, guestbook.getSex());
			pstmt.setString(4, guestbook.getMessage());
			// 提交送出
			int rowcount = pstmt.executeUpdate();
			System.out.println("rowcount(異動筆數) = " + rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Guestbook> readAll() {
		String sql = "select id, nickname, age, sex, message, date from guestbook order by id";
		List<Guestbook> guestbooks = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql) ) {
			
			// 將 rs 的資料逐筆注入到 guestbook 物件中
			while (rs.next()) {
				Guestbook guestbook = new Guestbook();
				guestbook.setId(rs.getInt("id"));
				guestbook.setNickname(rs.getString("nickname"));
				guestbook.setAge(rs.getInt("age"));
				guestbook.setSex(rs.getString("sex"));
				guestbook.setMessage(rs.getString("message"));
				//guestbook.setDate(rs.getDate("date"));
				guestbook.setDate(new Date(rs.getTimestamp("date").getTime()));
				// 加入到 guestbooks 集合中
				guestbooks.add(guestbook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return guestbooks;
	}

}
