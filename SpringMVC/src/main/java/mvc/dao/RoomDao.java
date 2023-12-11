package mvc.dao;

import java.util.List;

import mvc.bean.Room;

public interface RoomDao {
	
	List<Room> findAllRooms();
	Room getRoom(Integer roomId);
	int addRoom(Room room);
}
