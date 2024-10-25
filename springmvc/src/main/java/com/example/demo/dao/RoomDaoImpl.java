package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Room;

@Repository
public class RoomDaoImpl implements RoomDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Room> findAllRooms() {
		String sql = "select roomId, roomName, roomSize from room";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
	}

	@Override
	public Optional<Room> getRoomById(Integer roomId) {
		String sql = "select roomId, roomName, roomSize from room where roomId=?";
		Room room = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), roomId);
		return room == null ? Optional.empty() : Optional.of(room);
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoom(Integer roomId, Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoom(Integer roomId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
