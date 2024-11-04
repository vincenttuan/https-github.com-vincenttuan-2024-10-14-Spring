package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoomDao;
import com.example.demo.exception.RoomAlreadyExistsException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;

@Service
public class RoomService {
	
	@Autowired
	//@Qualifier("roomDaoImpl")
	private RoomDao roomDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<RoomDto> getAllRooms() {
		return roomDao.findAllRooms()  // List<Room>
					  .stream()
					  .map(this::convertToDto)  // Room 轉 RoomDto
					  .collect(Collectors.toList()); // 收集起來
					
	}
	
	public Room getRoomById(Integer roomId) {
		return roomDao.getRoomById(roomId)
					  .orElseThrow(() -> new RoomNotFoundException("找不到會議室: roomId " + roomId));
	}
	
	public void addRoom(Room room) {
		addRoom(room.getRoomId(), room.getRoomName(), room.getRoomSize());
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
	
	public void updateRoom(Integer roomId, Room room) {
		updateRoom(roomId, room.getRoomName(), room.getRoomSize());
	}
	
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		// 該 Room 是否已經存在
		Optional<Room> existingRoom = roomDao.getRoomById(roomId);
		if(existingRoom.isEmpty()) {
			throw new RoomAlreadyExistsException("修改失敗: " + roomId + " 會議室不存在");
		}
		// 修改 room
		Room room = new Room(roomId, roomName, roomSize);
		roomDao.updateRoom(roomId, room);
	}
	
	public void deleteRoom(Integer roomId) {
		// 該 Room 是否已經存在
		Optional<Room> existingRoom = roomDao.getRoomById(roomId);
		if(existingRoom.isEmpty()) {
			throw new RoomAlreadyExistsException("刪除失敗: " + roomId + " 會議室不存在");
		}
		// 刪除 room
		roomDao.deleteRoom(roomId);
	}
	
	// Entity 轉 Dto
	private RoomDto convertToDto(Room room) {
		return modelMapper.map(room, RoomDto.class);
	}
	
	// Dto 轉 Entity
	private Room convertToEntity(RoomDto roomDto) {
		return modelMapper.map(roomDto, Room.class);
	}
	
}
