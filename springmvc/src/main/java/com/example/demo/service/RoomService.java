package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoomDao;

@Service
public class RoomService {
	
	@Autowired
	//@Qualifier("roomDaoImpl")
	private RoomDao roomDao;
	
}
