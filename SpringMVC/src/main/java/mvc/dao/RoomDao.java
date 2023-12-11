package mvc.dao;

import java.util.List;
import java.util.Optional;

import mvc.bean.Room;

public interface RoomDao {
	
	List<Room> findAllRooms();
	Optional<Room> getRoom(Integer roomId);
	
}
