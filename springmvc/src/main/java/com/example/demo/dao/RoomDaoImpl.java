package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Room;

@Repository
@PropertySource("classpath:sql.properties")
public class RoomDaoImpl implements RoomDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${room.sql.findAllRooms}")
	private String findAllRoomsSql;
	
	@Value("${room.sql.getRoomById}")
	private String getRoomByIdSql;
	
	@Value("${room.sql.addRoom}")
	private String addRoomSql;
	
	@Value("${room.sql.updateRoom}")
	private String updateRoomSql;
	
	@Value("${room.sql.deleteRoom}")
	private String deleteRoomSql;
	
	@Override
	public List<Room> findAllRooms() {
		return jdbcTemplate.query(findAllRoomsSql, new BeanPropertyRowMapper<>(Room.class));
	}

	@Override
	public Optional<Room> getRoomById(Integer roomId) {
		Room room = jdbcTemplate.queryForObject(getRoomByIdSql, new BeanPropertyRowMapper<>(Room.class), roomId);
		return room == null ? Optional.empty() : Optional.of(room);
	}

	@Override
	public void addRoom(Room room) {
		jdbcTemplate.update(addRoomSql, room.getRoomId(), room.getRoomName(), room.getRoomId());
	}

	@Override
	public void updateRoom(Integer roomId, Room room) {
		jdbcTemplate.update(updateRoomSql, room.getRoomName(), room.getRoomSize(), room.getRoomId());
	}

	@Override
	public void deleteRoom(Integer roomId) {
		jdbcTemplate.update(deleteRoomSql, roomId);
	}
	
}
