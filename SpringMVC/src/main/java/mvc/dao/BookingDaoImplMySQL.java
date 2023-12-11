package mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mvc.bean.BookingRoom;
import mvc.bean.Room;

@Repository("bookingDaoMySQL") // 自行定義名稱
// 預設的 name = "bookingDaoImplMySQL"
public class BookingDaoImplMySQL implements BookingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public int addBookRoom(BookingRoom bookingRoom) {
		String sql = "insert into BookingRoom(roomId, username, bookingDate) values(?, ?, ?)";
		int rowcount = jdbcTemplate.update(sql, bookingRoom.getRoomId(), bookingRoom.getUsername(), bookingRoom.getBookingDate());
		return rowcount;
	}

	@Override
	public int cancelBooking(Integer bookingId) {
		String sql = "delete from BookingRoom where bookingId = ?";
		int rowcount = jdbcTemplate.update(sql, bookingId);
		return rowcount;
	}

	@Override
	public List<BookingRoom> findAllBookingRooms() {
		String sql = "select "
				+ "b.bookingId, b.roomId, b.username, b.bookingDate, b.createDate, "
				+ "r.roomName, r.roomSize "
				+ "from BookingRoom b "
				+ "left join Room r on b.roomId = r.roomId";
		System.out.println(sql);
		// 定義對應邏輯/規則
		RowMapper<BookingRoom> mapper = new RowMapper<BookingRoom>() {
			@Override
			public BookingRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
				// 取出資料列的每一個欄位資訊
				Integer bookingId = rs.getInt("bookingId");
				Integer roomId = rs.getInt("roomId");
				String username = rs.getString("username");
				String bookingDate = rs.getString("bookingDate");
				Timestamp createDate = rs.getTimestamp("createDate");
				String roomName = rs.getString("roomName");
				Integer roomSize = rs.getInt("roomSize");
				// 將上述欄位資訊注入到 bookingRoom 物件中
				BookingRoom bookingRoom = new BookingRoom();
				bookingRoom.setBookingId(bookingId);
				bookingRoom.setRoomId(roomId);
				bookingRoom.setUsername(username);
				bookingRoom.setBookingDate(bookingDate);
				bookingRoom.setCreateDate(createDate);
				// 注入 Room 物件 ??
				Room room = new Room(roomId, roomName, roomSize);
				bookingRoom.setRoom(room);
				
				return bookingRoom;
			}
		};
		
		List<BookingRoom> bookingRooms = jdbcTemplate.query(sql, mapper);
		return bookingRooms;
		
		//return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookingRoom.class));
	}

	@Override
	public int updateBookingUsername(Integer bookingId, String newUsername) {
		String sql = "update BookingRoom set username = ? where bookingId = ?";
		int rowcount = jdbcTemplate.update(sql, newUsername, bookingId);
		return rowcount;
	}
	
}
