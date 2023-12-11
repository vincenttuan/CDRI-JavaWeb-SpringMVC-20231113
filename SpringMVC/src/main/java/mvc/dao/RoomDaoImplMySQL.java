package mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.Room;

@Repository
public class RoomDaoImplMySQL implements RoomDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<Room> findAllRooms() {
		String sql = "select roomId, roomName, roomSize from room order by roomId";
		List<Room> rooms = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
		return rooms;
	}

	@Override
	public Room getRoom(Integer roomId) {
		String sql = "select roomId, roomName, roomSize from room where roomId = :roomId";
		Map<String, Object> params = new HashMap<>();
		params.put("roomId", roomId);
		
		Room room = namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Room.class));
		return room;
	}
	
	@Override
	public int addRoom(Room room) {
		String sql = "insert Room(roomId, roomName, roomSize) values(:roomId, :roomName, :roomSize)";
		Map<String, Object> params = new HashMap<>();
		params.put("roomId", room.getRoomId());
		params.put("roomName", room.getRoomName());
		params.put("roomSize", room.getRoomSize());
		return namedParameterJdbcTemplate.update(sql, params);
	}

}
