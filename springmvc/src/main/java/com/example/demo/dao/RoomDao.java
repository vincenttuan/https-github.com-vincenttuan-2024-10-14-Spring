package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Room;

public interface RoomDao {
	List<Room> findAllRooms();
	Optional<Room> getRoomById(Integer roomId);
	void addRoom(Room room);
	void updateRoom(Integer roomId, Room room);
	void deleteRoom(Integer roomId);
}
