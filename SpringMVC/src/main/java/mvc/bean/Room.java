package mvc.bean;

import com.google.gson.Gson;

// Entity
public class Room {
	private Integer roomId;
	private String roomName;
	private Integer roomSize;
	
	public Room() {
		
	}
	
	public Room(Integer roomId, String roomName, Integer roomSize) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomSize = roomSize;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Integer roomSize) {
		this.roomSize = roomSize;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
	
}
