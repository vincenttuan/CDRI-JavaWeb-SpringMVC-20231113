package mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mvc.bean.Room;

@Repository
public class RoomDaoImplMySQL implements RoomDao {

	@Override
	public List<Room> findAllRooms() {
		return null;
	}

	@Override
	public Room getRoom(Integer roomId) {
		return null;
	}

}
