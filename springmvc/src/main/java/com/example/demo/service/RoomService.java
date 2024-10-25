package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Room;
import com.example.demo.dao.RoomDao;
import com.example.demo.exception.RoomAlreadyExistsException;
import com.example.demo.exception.RoomNotFoundException;

@Service
public class RoomService {
	
	@Autowired
	//@Qualifier("roomDaoImpl")
	private RoomDao roomDao;
	
	public List<Room> getAllRooms() {
		return roomDao.findAllRooms();
	}
	
	public Room getRoomById(Integer roomId) {
		return roomDao.getRoomById(roomId)
					  .orElseThrow(() -> new RoomNotFoundException("找不到會議室: roomId " + roomId));
	}
	
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
		// 該 Room 是否已經存在
		Optional<Room> existingRoom = roomDao.getRoomById(roomId);
		if(existingRoom.isPresent()) {
			throw new RoomAlreadyExistsException("新增失敗: " + roomId + " 會議室已存在");
		}
		// 新增 room
		Room room = new Room(roomId, roomName, roomSize);
		roomDao.addRoom(room);
	}
	
}
