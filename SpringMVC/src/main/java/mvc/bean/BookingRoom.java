package mvc.bean;

import java.sql.Timestamp;

import com.google.gson.Gson;

// Entity
public class BookingRoom {
	// 一般欄位
	private Integer bookingId;
	private Integer roomId;
	private String username;
	private String bookingDate;
	private Timestamp createDate;
	
	// 關聯欄位(多對一 ManyToOne)
	private Room room;
	
	public BookingRoom() {
		
	}

	public BookingRoom(Integer bookingId, Integer roomId, String username, String bookingDate, Timestamp createDate,
			Room room) {
		this.bookingId = bookingId;
		this.roomId = roomId;
		this.username = username;
		this.bookingDate = bookingDate;
		this.createDate = createDate;
		this.room = room;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
	
	
}
