package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Room;
import com.example.demo.dao.RoomDao;

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
					  .orElseThrow(() -> new RuntimeException("找不到會議室: roomId " + roomId));
	}
	
}
