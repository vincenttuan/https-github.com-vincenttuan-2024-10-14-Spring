package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Room;
import com.example.demo.service.RoomService;

/**
 * Rest API 設計
 * Method URI
 * ----------------------------------------------------------------
 * GET    /room/{roomId}                        查詢指定會議室(單筆)
 * GET    /rooms                                查詢所有會議室(多筆)
 * POST   /room           /room/add             新增會議室
 * PUT    /room/{roomId}  /room/update/{roomId} 完整修改會議室
 * PATCH  /room/{roomId}  /room/update/{roomId} 指定修改會議室(Homework)
 * DELETE /room/{roomId}  /room/delete/{roomId} 刪除會議室
 * 
 * */

@RestController
@RequestMapping("/booking/rest")
public class BookingRestController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/room/{roomId}")
	public String getRoom(@PathVariable("roomId") Integer roomId) {
		Room room = roomService.getRoomById(roomId);
		return room.toString();
	}
	
}
