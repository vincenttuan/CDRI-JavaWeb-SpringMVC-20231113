package mvc.dao;

import java.util.List;

import mvc.bean.BookingRoom;

// API
public interface BookingDao {
	// 預訂會議室：
	int addBookRoom(BookingRoom bookingRoom);
	
	// 取消預訂
	int cancelBooking(Integer bookingId);
	
	// 查看所有預訂
	List<BookingRoom> findAllBookingRooms();
	
	// 修改預約人
	int updateBookingUsername(Integer bookingId, String newUsername);
	
}
